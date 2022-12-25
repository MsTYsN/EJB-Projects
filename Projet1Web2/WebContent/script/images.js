$(document).ready(function () {
  loadImages();
  loadMonuments();
  $('#uploadimg').on('click', function () {
    $('#photo').trigger('click');
  });
  $('#photo').on('change', function () {
    readIMG(this);
    deleted = false;
    uploaded = true;
  });
  $('#deleteimg').on('click', function () {
    $('#img').prop('src', '');
    $('#photo').val('');
    $(this).prop('hidden', true);
  });

  $('#ajouter').click(function (e) {
    if ($(this).attr('value') == 'Ajouter') {
      e.preventDefault();
      var url = $('#imageUrl').val();
      var monument = $('#monument').val();

      if (url) {
        $.ajax({
          url: 'ImageController',
          data: { op: 'add', url: url, monument: monument },
          method: 'POST',
          success: function (data, textStatus, jqXHR) {
            loadImages();
            swal('Succes!', "Ajout de l'image avec succes!", 'success');
            $('#imageUrl').val('');
            $('#divannuler').prop('hidden', true);
          },
          error: function (jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
            swal('Echec!', "Echec lors de l'ajout de l'image", 'warning');
          },
        });
      }
    }
  });

  $('#search').click(function () {
    var nom = $('#ImageRech').val();
    if (nom != '') {
      $.ajax({
        url: 'ImageController',
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
    $('#ImageRech').val('');
    loadImages();
  });

  function loadImages() {
    $.ajax({
      url: 'ImageController',
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

  function loadMonuments() {
    $.ajax({
      url: 'MonumentController',
      data: { op: 'load' },
      type: 'POST',
      success: function (data, textStatus, jqXHR) {
        remplirMonument(data);
      },
      error: function (jqXHR, textStatus, errorThrown) {
        console.log(textStatus, errorThrown);
      },
    });
  }

  function remplirMonument(data) {
    var ligne =
      '<option selected disabled value="0">Choisir un monument</option>';
    if (data.length > 0) {
      for (var i = 0; i < data.length; i++) {
        ligne +=
          '<option value="' + data[i].id + '">' + data[i].nom + '</option>';
      }
    }
    $('#monument').html(ligne);
  }

  function remplir(data) {
    console.log(data);
    var ligne = '';
    if (data.length > 0) {
      for (var i = 0; i < data.length; i++) {
        ligne +=
          '<tr><td class="text-center"><img style="width:150px;height:100px;" src="' +
          data[i].url +
          '" onerror="this.src=\'images/no-image.png\'"></td><td class="text-center">' +
          data[i].monument.nom +
          '</td><td class="text-center"><div><a class="dropdown-item btn-update" data-image=\'' +
          JSON.stringify(data[i]) +
          '\' href="javascript:void(0)"><i class="dw dw-edit2"></i> Modifier</a><a class="dropdown-item btn-delete" data-id="' +
          data[i].id +
          '" href="javascript:void(0)"><i class="dw dw-delete-3"></i> Supprimer</a></div></td></tr>';
      }
    } else {
      ligne =
        '<td colspan="7" align="center"><p class="fs-2">Pas de images !<p></td></tr>';
    }
    $('#table').html(ligne);

    $('.btn-delete').click(function () {
      swal({
        title: 'Voulez-vous supprimer cet image?',
        icon: 'info',
        buttons: true,
        showcancelbutton: true,
      }).then((isConfirm) => {
        if (isConfirm) {
          var id = $(this).data('id');
          $.ajax({
            url: 'ImageController',
            data: { id: id, op: 'delete' },
            type: 'POST',
            success: function (data, textStatus, jqXHR) {
              loadImages();
              swal('Succes!', 'Suppression de image avec succes!', 'success');
            },
            error: function (jqXHR, textStatus, errorThrown) {
              console.log(textStatus, errorThrown);
              swal(
                'Echec!',
                'Echec lors de la suppression de image!',
                'warning'
              );
            },
          });
        }
      });
    });

    $('.btn-update').click(function () {
      var image = $(this).data('image');

      $('#imageId').val(image.id);
      $('#imageUrl').val(image.url);

      $('#ajouter').prop('value', 'Modifier');
      $('#divannuler').prop('hidden', false);

      $('#annuler').click(function () {
        $('#ajouter').prop('value', 'Ajouter');
        $('#imageId').val('');
        $('#imageUrl').val('');
        $('#divannuler').prop('hidden', true);
      });

      $('#ajouter').click(function (e) {
        if ($(this).attr('value') == 'Modifier') {
          e.preventDefault();
          var id = $('#imageId').val();
          var url = $('#imageUrl').val();
          var monument = $('#monument').val();

          if (url) {
            $.ajax({
              url: 'ImageController',
              data: { op: 'update', id: id, url: url, monument: monument },
              method: 'POST',
              success: function (data, textStatus, jqXHR) {
                loadImages();
                swal(
                  'Succes!',
                  "Modification de l'image avec succes!",
                  'success'
                );
                $('#imageId').val('');
                $('#imageUrl').val('');
                $('#ajouter').prop('value', 'Ajouter');
                $('#divannuler').prop('hidden', true);
              },
              error: function (jqXHR, textStatus, errorThrown) {
                console.log(textStatus, errorThrown);
                swal(
                  'Echec!',
                  "Echec lors de la modification de l'image",
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

function readIMG(input) {
  if (input.files && input.files[0]) {
    var formData = new FormData();
    formData.append('file', input.files[0]);
    formData.append('upload_preset', 'j8s4ikil');
    $.ajax({
      url: 'https://api.cloudinary.com/v1_1/drrizqy1b/image/upload',
      data: formData,
      method: 'POST',
      processData: false,
      contentType: false,
      success: function (data, textStatus, jqXHR) {
        $('#imageUrl').val(data.secure_url);
        $('#divannuler').prop('hidden', true);
      },
      error: function (jqXHR, textStatus, errorThrown) {
        console.log(textStatus, errorThrown);
        swal('Echec!', 'Echec lors de upload', 'warning');
      },
    });
    var reader = new FileReader();
    reader.onload = function (e) {
      $('#img').attr('src', e.target.result);
    };
    reader.readAsDataURL(input.files[0]);
    $('#deleteimg').prop('hidden', false);
  }
}
