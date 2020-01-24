let getOrderList = () => {
  $.ajax({
    async: true,
    crossDomain: true,
    url: "http://localhost:8080/BookBarWebsite/api/ordini",
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      Accept: "*/*"
    },
    data: {}
  }).done(function(response) {
    let data = JSON.parse(JSON.stringify(response));
    console.log("Data", data);
    let html = "";
    let k = 0;
    for (let row in data) {
      let email = !!data[row].user ? data[row].user.email : '-' ;
      let singleRowHTML =
        '<tr class="userRow"><th scope="row">' +
        k +
        "</th><td>" +
        data[row].id +
        "</td><td>" +
        data[row].stato +
        "</td><td>" +
        data[row].data +
        "</td><td>" +
        email +
        "</td><td>" +
        data[row].menu.name +
        "</td>";
      singleRowHTML +=
        '<td class="inLavorazioneBtn" data-id="' +
        data[row].id +
        '"><i class="fas fa-tools deleteIcon"></i></td>';
      singleRowHTML +=
        '<td class="consegnaBtn" data-id="' +
        data[row].id +
        '"><i class="fas fas fa-check-square deleteIcon"></i></td>';
      singleRowHTML +=
        '<td class="deleteBtn" data-id="' +
        data[row].id +
        '"><i class="fas fa-trash-alt deleteIcon"></i></td></tr>';
      html += singleRowHTML;
      k++;
    }

    document.querySelector(".order-table").innerHTML = html;

    let consegnaBtn = document.querySelectorAll(".consegnaBtn");
    for (let i = 0; i < consegnaBtn.length; i++) {
      consegnaBtn[i].addEventListener("click", function() {
        consegnaHandler(consegnaBtn[i].dataset.id);
      });
    }

    let deleteBtn = document.querySelectorAll(".deleteBtn");
    for (let i = 0; i < deleteBtn.length; i++) {
      deleteBtn[i].addEventListener("click", function() {
        deleteHandler(deleteBtn[i].dataset.id);
      });
    }

    let inLavorazioneBtn = document.querySelectorAll(".inLavorazioneBtn");
    for (let i = 0; i < inLavorazioneBtn.length; i++) {
      inLavorazioneBtn[i].addEventListener("click", function() {
        inLavorazioneHandler(inLavorazioneBtn[i].dataset.id);
      });
    }
  });
};
let settings = {
  async: true,
  crossDomain: true,
  url: "http://localhost:8080/BookBarWebsite/gestione_ordini",
  method: "POST",
  headers: {
    "Content-Type": "application/x-www-form-urlencoded",
    Accept: "*/*"
  },
  data: {}
};

let inLavorazioneHandler = id => {
  let data = {};
  data.operation = "lavorazione";
  data.id = id;

  settings.data = data;

  console.log("Lavorazione ", settings.data);
  $.ajax(settings).done(function(response) {
    console.log(response);
    getOrderList();
  });
};

let consegnaHandler = id => {
  let data = {};
  data.operation = "consegna";
  data.id = id;

  settings.data = data;

  console.log("Consegna ", settings.data);
  $.ajax(settings).done(function(response) {
    console.log(response);
    getOrderList();
  });
};

let deleteHandler = id => {
  let data = {};
  data.operation = "del";
  data.id = id;

  settings.data = data;

  console.log("Delete ", settings.data);
  $.ajax(settings).done(function(response) {
    console.log(response);
    getOrderList();
  });
};

$(document).ready(function() {
  getOrderList();
  console.log("ciao");
});
