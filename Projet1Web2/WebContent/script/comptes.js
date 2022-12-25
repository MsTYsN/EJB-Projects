$(document).ready(function () {
  $.ajax({
    url: 'CompteController',
    data: { op: 'load' },
    method: 'POST',
    success: function (data) {
      remplir(data);
    },
  });

  $('#add').click(function () {
    var solde = $('#solde').val();
    var dateCreation = $('#dateCreation').val();
    $.ajax({
      url: 'CompteController',
      data: { solde: solde, dateCreation: dateCreation },
      method: 'POST',
      success: function (data) {
        remplir(data);
      },
    });
  });

  function remplir(data) {
    var ligne = '';
    data.forEach((e) => {
      ligne +=
        '<tr><td>' +
        e.code +
        '</td><td>' +
        e.solde +
        '</td><td>' +
        e.dateCreation +
        '</td><td>Supprimer</td><td>Modifier</td></tr>';
    });
    $('#content').html(ligne);
  }
});
