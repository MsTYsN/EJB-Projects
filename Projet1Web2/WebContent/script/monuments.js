$(document).ready(function () {
  loadMonuments();
  loadVilles();
  loadCreateurs();

  $('#ajouter').click(function (e) {
    if ($(this).attr('value') == 'Ajouter') {
      e.preventDefault();
      var verif = true;
      var nom = $('#nom').val();
      var adresse = $('#adresse').val();
      var latitude = $('#latitude').val();
      var longitude = $('#longitude').val();
      var ville = $('#ville').val();
      var createur = $('#createur').val();
      var description = $('#description').val();

      if (nom == '') {
        $('#nom').css('border', '1px solid red');
        verif = false;
      } else {
        $('#nom').css('border', '1px solid #d4d4d4');
      }

      if (adresse == '') {
        $('#adresse').css('border', '1px solid red');
        verif = false;
      } else {
        $('#adresse').css('border', '1px solid #d4d4d4');
      }

      if (latitude == '') {
        $('#latitude').css('border', '1px solid red');
        verif = false;
      } else {
        $('#latitude').css('border', '1px solid #d4d4d4');
      }

      if (longitude == '') {
        $('#longitude').css('border', '1px solid red');
        verif = false;
      } else {
        $('#longitude').css('border', '1px solid #d4d4d4');
      }

      if (description == '') {
        $('#description').css('border', '1px solid red');
        verif = false;
      } else {
        $('#description').css('border', '1px solid #d4d4d4');
      }

      if (verif) {
        $.ajax({
          url: 'MonumentController',
          data: {
            nom: nom,
            adresse: adresse,
            latitude: latitude,
            longitude: longitude,
            ville: ville,
            createur: createur,
            description: description,
            op: 'add',
          },
          type: 'POST',
          success: function (data, textStatus, jqXHR) {
            loadMonuments();
            $('#nom').val('');
            $('#adresse').val('');
            $('#latitude').val('');
            $('#longitude').val('');
            $('#description').val('');
            $('#divannuler').prop('hidden', true);
            swal('Succes!', 'Ajout du monument avec succes!', 'success');
          },
          error: function (jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
            swal('Echec!', "Echec lors de l'ajout du monument!", 'warning');
          },
        });
      }
    }
  });

  $('#search').click(function () {
    var nom = $('#monumentRech').val();
    if (nom != '') {
      $.ajax({
        url: 'MonumentController',
        type: 'POST',
        data: { nom: nom, op: 'search' },
        success: function (data, textStatus, jqXHR) {
          remplir(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
          console.log(textStatus, errorThrown);
        },
      });
    }
  });

  $('#annulerRech').click(function () {
    $('#monumentRech').val('');
    loadMonuments();
  });

  function loadMonuments() {
    $.ajax({
      url: 'MonumentController',
      data: { op: 'load' },
      type: 'POST',
      success: function (data, textStatus, jqXHR) {
        remplir(data);
      },
      error: function (jqXHR, textStatus, errorThrown) {
        console.log(textStatus, errorThrown);
      },
    });
  }

  function loadVilles() {
    $.ajax({
      url: 'VilleController',
      data: { op: 'load' },
      type: 'POST',
      success: function (data, textStatus, jqXHR) {
        remplirVille(data);
      },
      error: function (jqXHR, textStatus, errorThrown) {
        console.log(textStatus, errorThrown);
      },
    });
  }

  function loadCreateurs() {
    $.ajax({
      url: 'CreateurController',
      data: { op: 'load' },
      type: 'POST',
      success: function (data, textStatus, jqXHR) {
        remplirCreateur(data);
      },
      error: function (jqXHR, textStatus, errorThrown) {
        console.log(textStatus, errorThrown);
      },
    });
  }

  function remplirVille(data) {
    var ligne =
      '<option selected disabled value="0">Choisir une ville</option>';
    if (data.length > 0) {
      for (var i = 0; i < data.length; i++) {
        ligne +=
          '<option value="' + data[i].id + '">' + data[i].nom + '</option>';
      }
    }
    $('#ville').html(ligne);
  }

  function remplirCreateur(data) {
    var ligne =
      '<option selected disabled value="0">Choisir un createur</option>';
    if (data.length > 0) {
      for (var i = 0; i < data.length; i++) {
        ligne +=
          '<option value="' +
          data[i].id +
          '">' +
          data[i].nom +
          ' ' +
          data[i].prenom +
          '</option>';
      }
    }
    $('#createur').html(ligne);
  }

  function remplir(data) {
    console.log(data);
    var ligne = '';
    if (data.length > 0) {
      for (var i = 0; i < data.length; i++) {
        ligne +=
          '<tr><td class="text-center">' +
          data[i].nom +
          '</td><td class="text-center">' +
          data[i].adresse +
          '</td><td class="text-center">' +
          data[i].latitude +
          '</td><td class="text-center">' +
          data[i].longitude +
          '</td><td class="text-center">' +
          data[i].description +
          '</td><td class="text-center">' +
          data[i].ville.nom +
          '</td><td class="text-center">' +
          data[i].createur.nom +
          ' ' +
          data[i].createur.prenom +
          '</td><td class="text-center"><div><a class="dropdown-item btn-update" data-monument=\'' +
          JSON.stringify(data[i]) +
          '\' href="javascript:void(0)"><i class="dw dw-edit2"></i> Modifier</a><a class="dropdown-item btn-delete" data-id="' +
          data[i].id +
          '" href="javascript:void(0)"><i class="dw dw-delete-3"></i> Supprimer</a></div></td></tr>';
      }
    } else {
      ligne =
        '<td colspan="7" align="center"><p class="fs-2">Pas de monuments !<p></td></tr>';
    }
    $('#table').html(ligne);

    $('.btn-delete').click(function () {
      swal({
        title: 'Voulez-vous supprimer ce monument?',
        icon: 'info',
        buttons: true,
        showcancelbutton: true,
      }).then((isConfirm) => {
        if (isConfirm) {
          var id = $(this).data('id');
          $.ajax({
            url: 'MonumentController',
            data: { id: id, op: 'delete' },
            type: 'POST',
            success: function (data, textStatus, jqXHR) {
              loadMonuments();
              swal(
                'Succes!',
                'Suppression du monument avec succes!',
                'success'
              );
            },
            error: function (jqXHR, textStatus, errorThrown) {
              console.log(textStatus, errorThrown);
              swal(
                'Echec!',
                'Echec lors de la suppression du monument!',
                'warning'
              );
            },
          });
        }
      });
    });

    $('.btn-update').click(function () {
      var monument = $(this).data('monument');

      $('#monumentId').val(monument.id);
      $('#nom').val(monument.nom);
      $('#adresse').val(monument.adresse);
      $('#latitude').val(monument.latitude);
      $('#longitude').val(monument.longitude);
      $('#description').val(monument.description);

      $('#ajouter').prop('value', 'Modifier');
      $('#divannuler').prop('hidden', false);

      $('#annuler').click(function () {
        $('#ajouter').prop('value', 'Ajouter');
        $('#monumentId').val('');
        $('#nom').val('');
        $('#adresse').val('');
        $('#latitude').val('');
        $('#longitude').val('');
        $('#description').val('');
        $('#divannuler').prop('hidden', true);
      });

      $('#ajouter').click(function (e) {
        if ($(this).attr('value') == 'Modifier') {
          e.preventDefault();
          var verif = true;
          var id = $('#monumentId').val();
          var nom = $('#nom').val();
          var adresse = $('#adresse').val();
          var latitude = $('#latitude').val();
          var longitude = $('#longitude').val();
          var ville = $('#ville').val();
          var createur = $('#createur').val();
          var description = $('#description').val();

          if (nom == '') {
            $('#nom').css('border', '1px solid red');
            verif = false;
          } else {
            $('#nom').css('border', '1px solid #d4d4d4');
          }

          if (adresse == '') {
            $('#adresse').css('border', '1px solid red');
            verif = false;
          } else {
            $('#adresse').css('border', '1px solid #d4d4d4');
          }

          if (latitude == '') {
            $('#latitude').css('border', '1px solid red');
            verif = false;
          } else {
            $('#latitude').css('border', '1px solid #d4d4d4');
          }

          if (longitude == '') {
            $('#longitude').css('border', '1px solid red');
            verif = false;
          } else {
            $('#longitude').css('border', '1px solid #d4d4d4');
          }

          if (description == '') {
            $('#description').css('border', '1px solid red');
            verif = false;
          } else {
            $('#description').css('border', '1px solid #d4d4d4');
          }

          if (verif) {
            $.ajax({
              url: 'MonumentController',
              data: {
                id: id,
                nom: nom,
                adresse: adresse,
                latitude: latitude,
                longitude: longitude,
                ville: ville,
                createur: createur,
                description: description,
                op: 'update',
              },
              type: 'POST',
              success: function (data, textStatus, jqXHR) {
                loadMonuments();
                $('#ajouter').prop('value', 'Ajouter');
                $('#nom').val('');
                $('#adresse').val('');
                $('#latitude').val('');
                $('#longitude').val('');
                $('#description').val('');
                $('#divannuler').prop('hidden', true);
                swal(
                  'Succes!',
                  'Modification du monument avec succes!',
                  'success'
                );
              },
              error: function (jqXHR, textStatus, errorThrown) {
                console.log(textStatus, errorThrown);
                swal(
                  'Echec!',
                  'Echec lors de la modification du monument!',
                  'warning'
                );
              },
            });
          }
        }
      });
    });
  }
});
