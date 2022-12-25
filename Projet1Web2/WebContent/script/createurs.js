$(document).ready(function () {
  loadCreateurs();

  $('#ajouter').click(function (e) {
    if ($(this).attr('value') == 'Ajouter') {
      e.preventDefault();
      var verif = true;
      var nom = $('#nom').val();
      var prenom = $('#prenom').val();
      var debut = $('#debut').val();
      var fin = $('#fin').val();
      var description = $('#description').val();

      if (nom == '') {
        $('#nom').css('border', '1px solid red');
        verif = false;
      } else {
        $('#nom').css('border', '1px solid #d4d4d4');
      }

      if (prenom == '') {
        $('#prenom').css('border', '1px solid red');
        verif = false;
      } else {
        $('#prenom').css('border', '1px solid #d4d4d4');
      }

      if (debut == '') {
        $('#debut').css('border', '1px solid red');
        verif = false;
      } else {
        $('#debut').css('border', '1px solid #d4d4d4');
      }

      if (fin == '') {
        $('#fin').css('border', '1px solid red');
        verif = false;
      } else {
        $('#fin').css('border', '1px solid #d4d4d4');
      }

      if (description == '') {
        $('#description').css('border', '1px solid red');
        verif = false;
      } else {
        $('#description').css('border', '1px solid #d4d4d4');
      }

      if (verif) {
        $.ajax({
          url: 'CreateurController',
          data: {
            nom: nom,
            prenom: prenom,
            debut: debut,
            fin: fin,
            description: description,
            op: 'add',
          },
          type: 'POST',
          success: function (data, textStatus, jqXHR) {
            loadCreateurs();
            $('#nom').val('');
            $('#prenom').val('');
            $('#description').val('');
            $('#divannuler').prop('hidden', true);
            swal('Succès!', 'Ajout du créateur avec succès!', 'success');
          },
          error: function (jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
            swal('Echec!', "Echec lors de l'ajout du créateur!", 'warning');
          },
        });
      }
    }
  });

  $('#search').click(function () {
    var nom = $('#createurRech').val();
    if (nom != '') {
      $.ajax({
        url: 'CreateurController',
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
    $('#createurRech').val('');
    loadCreateurs();
  });

  function loadCreateurs() {
    $.ajax({
      url: 'CreateurController',
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

  function remplir(data) {
    var ligne = '';
    if (data.length > 0) {
      for (var i = 0; i < data.length; i++) {
        ligne +=
          '<tr><td class="text-center">' +
          data[i].nom +
          '</td><td class="text-center">' +
          data[i].prenom +
          '</td><td class="text-center">' +
          data[i].debut +
          '</td><td class="text-center">' +
          data[i].fin +
          '</td><td class="text-center">' +
          data[i].description +
          '</td><td class="text-center"><div><a class="dropdown-item btn-update" data-createur=\'' +
          JSON.stringify(data[i]) +
          '\' href="javascript:void(0)"><i class="dw dw-edit2"></i> Modifier</a><a class="dropdown-item btn-delete" data-id="' +
          data[i].id +
          '" href="javascript:void(0)"><i class="dw dw-delete-3"></i> Supprimer</a></div></td></tr>';
      }
    } else {
      ligne =
        '<td colspan="7" align="center"><p class="fs-2">Pas de créateurs !<p></td></tr>';
    }
    $('#table').html(ligne);

    $('.btn-delete').click(function () {
      swal({
        title: 'Voulez-vous supprimer ce créateur?',
        icon: 'info',
        buttons: true,
        showcancelbutton: true,
      }).then((isConfirm) => {
        if (isConfirm) {
          var id = $(this).data('id');
          $.ajax({
            url: 'CreateurController',
            data: { id: id, op: 'delete' },
            type: 'POST',
            success: function (data, textStatus, jqXHR) {
              loadCreateurs();
              swal(
                'Succès!',
                'Suppression du créateur avec succès!',
                'success'
              );
            },
            error: function (jqXHR, textStatus, errorThrown) {
              console.log(textStatus, errorThrown);
              swal(
                'Echec!',
                'Echec lors de la suppression du créateur!',
                'warning'
              );
            },
          });
        }
      });
    });

    $('.btn-update').click(function () {
      var createur = $(this).data('createur');

      $('#createurId').val(createur.id);
      $('#nom').val(createur.nom);
      $('#prenom').val(createur.prenom);
      $('#debut').val(createur.debut);
      $('#fin').val(createur.fin);
      $('#description').val(createur.description);

      $('#ajouter').prop('value', 'Modifier');
      $('#divannuler').prop('hidden', false);

      $('#annuler').click(function () {
        $('#ajouter').prop('value', 'Ajouter');
        $('#createurId').val('');
        $('#nom').val('');
        $('#prenom').val('');
        $('#description').val('');
        $('#divannuler').prop('hidden', true);
      });

      $('#ajouter').click(function (e) {
        if ($(this).attr('value') == 'Modifier') {
          e.preventDefault();
          var verif = true;
          var id = $('#createurId').val();
          var nom = $('#nom').val();
          var prenom = $('#prenom').val();
          var debut = $('#debut').val();
          var fin = $('#fin').val();
          var description = $('#description').val();

          if (nom == '') {
            $('#nom').css('border', '1px solid red');
            verif = false;
          } else {
            $('#nom').css('border', '1px solid #d4d4d4');
          }

          if (prenom == '') {
            $('#prenom').css('border', '1px solid red');
            verif = false;
          } else {
            $('#prenom').css('border', '1px solid #d4d4d4');
          }

          if (debut == '') {
            $('#debut').css('border', '1px solid red');
            verif = false;
          } else {
            $('#debut').css('border', '1px solid #d4d4d4');
          }

          if (fin == '') {
            $('#fin').css('border', '1px solid red');
            verif = false;
          } else {
            $('#fin').css('border', '1px solid #d4d4d4');
          }

          if (description == '') {
            $('#description').css('border', '1px solid red');
            verif = false;
          } else {
            $('#description').css('border', '1px solid #d4d4d4');
          }

          if (verif) {
            $.ajax({
              url: 'CreateurController',
              data: {
                id: id,
                nom: nom,
                prenom: prenom,
                debut: debut,
                fin: fin,
                description: description,
                op: 'update',
              },
              type: 'POST',
              success: function (data, textStatus, jqXHR) {
                loadCreateurs();
                $('#ajouter').prop('value', 'Ajouter');
                $('#nom').val('');
                $('#prenom').val('');
                $('#description').val('');
                $('#divannuler').prop('hidden', true);
                swal(
                  'Succès!',
                  'Modification du créateur avec succès!',
                  'success'
                );
              },
              error: function (jqXHR, textStatus, errorThrown) {
                console.log(textStatus, errorThrown);
                swal(
                  'Echec!',
                  'Echec lors de la modification du créateur!',
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
