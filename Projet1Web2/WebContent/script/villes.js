$(document).ready(function () {
  loadVilles();

  $('#ajouter').click(function (e) {
    if ($(this).attr('value') == 'Ajouter') {
      e.preventDefault();
      var verif = true;
      var nom = $('#nom').val();

      if (nom == '') {
        $('#nom').css('border', '1px solid red');
        verif = false;
      } else {
        $('#nom').css('border', '1px solid #d4d4d4');
      }

      if (verif) {
        $.ajax({
          url: 'VilleController',
          data: {
            nom: nom,
            op: 'add',
          },
          type: 'POST',
          success: function (data, textStatus, jqXHR) {
            loadVilles();
            $('#nom').val('');
            $('#divannuler').prop('hidden', true);
            swal('Succès!', 'Ajout de la ville avec succès!', 'success');
          },
          error: function (jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
            swal('Echec!', "Echec lors de l'ajout de la ville!", 'warning');
          },
        });
      }
    }
  });

  $('#search').click(function () {
    var nom = $('#villeRech').val();
    if (nom != '') {
      $.ajax({
        url: 'VilleController',
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
    $('#villeRech').val('');
    loadVilles();
  });

  function loadVilles() {
    $.ajax({
      url: 'VilleController',
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
          '</td><td class="text-center"><div><a class="dropdown-item btn-update" data-ville=\'' +
          JSON.stringify(data[i]) +
          '\' href="javascript:void(0)"><i class="dw dw-edit2"></i> Modifier</a><a class="dropdown-item btn-delete" data-id="' +
          data[i].id +
          '" href="javascript:void(0)"><i class="dw dw-delete-3"></i> Supprimer</a></div></td></tr>';
      }
    } else {
      ligne =
        '<td colspan="7" align="center"><p class="fs-2">Pas de villes !<p></td></tr>';
    }
    $('#table').html(ligne);

    $('.btn-delete').click(function () {
      swal({
        title: 'Voulez-vous supprimer cette ville?',
        icon: 'info',
        buttons: true,
        showcancelbutton: true,
      }).then((isConfirm) => {
        if (isConfirm) {
          var id = $(this).data('id');
          $.ajax({
            url: 'VilleController',
            data: { id: id, op: 'delete' },
            type: 'POST',
            success: function (data, textStatus, jqXHR) {
              loadVilles();
              swal(
                'Succès!',
                'Suppression de la ville avec succès!',
                'success'
              );
            },
            error: function (jqXHR, textStatus, errorThrown) {
              console.log(textStatus, errorThrown);
              swal(
                'Echec!',
                'Echec lors de la suppression de la ville!',
                'warning'
              );
            },
          });
        }
      });
    });

    $('.btn-update').click(function () {
      var ville = $(this).data('ville');

      $('#villeId').val(ville.id);
      $('#nom').val(ville.nom);

      $('#ajouter').prop('value', 'Modifier');
      $('#divannuler').prop('hidden', false);

      $('#annuler').click(function () {
        $('#ajouter').prop('value', 'Ajouter');
        $('#villeId').val('');
        $('#nom').val('');
        $('#divannuler').prop('hidden', true);
      });

      $('#ajouter').click(function (e) {
        if ($(this).attr('value') == 'Modifier') {
          e.preventDefault();
          var verif = true;
          var id = $('#villeId').val();
          var nom = $('#nom').val();

          if (nom == '') {
            $('#nom').css('border', '1px solid red');
            verif = false;
          } else {
            $('#nom').css('border', '1px solid #d4d4d4');
          }

          if (verif) {
            $.ajax({
              url: 'VilleController',
              data: {
                id: id,
                nom: nom,
                op: 'update',
              },
              type: 'POST',
              success: function (data, textStatus, jqXHR) {
                loadVilles();
                $('#ajouter').prop('value', 'Ajouter');
                $('#nom').val('');
                $('#divannuler').prop('hidden', true);
                swal(
                  'Succès!',
                  'Modification de la ville avec succès!',
                  'success'
                );
              },
              error: function (jqXHR, textStatus, errorThrown) {
                console.log(textStatus, errorThrown);
                swal(
                  'Echec!',
                  'Echec lors de la modification de la ville!',
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
