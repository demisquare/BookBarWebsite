let settings = {
  async: true,
  crossDomain: true,
  url: "http://localhost:8080/BookBarWebsite/api/chart",
  method: "POST",
  headers: {
    "Content-Type": "application/x-www-form-urlencoded",
    Accept: "*/*"
  },
  data: {}
};

let delUserHandler = id => {
  let data = {};
  data.operation = "del";
  data.id = id;
  settings.data = data;
  console.log("Vuoi eliminare il menu " + settings.data.id);
  $.ajax(settings).done(function(response) {
    getItemList();
  });
};

let getItemList = () => {
  $.ajax({
    async: true,
    crossDomain: true,
    url: "http://localhost:8080/BookBarWebsite/api/chart",
    method: "GET",
    headers: {
      "Content-Type": "application/x-www-form-urlencoded",
      Accept: "*/*"
    },
    data: {}
  }).done(function(response) {
    let data = JSON.parse(JSON.stringify(response));
    console.log("Data", data);
    let html = "";
    let k = 0;
    let totale = 0;
    let numEle = 0;
    for (let row in data) {
      let qtaRow = parseInt(data[row].qta);
      let dingleRowPrice = parseFloat(data[row].menu.prezzo) * qtaRow;

      console.log(
        dingleRowPrice,
        parseFloat(data[row].menu.prezzo),
        parseInt(data[row].qta)
      );
      let singleRowHTML = `<tr class="userRow">
                              <td scope="row">${data[row].menu.id}</td>
                              <td>${data[row].menu.name}</td>
                              <td>${data[row].qta}</td>
                              <td>${dingleRowPrice}&euro;</td><td class="rowDeleteBtn" data-id="${data[row].menu.id}"><i class="fas fa-trash-alt deleteIcon"></i></td>
                            </tr>`;
      html += singleRowHTML;

      totale += dingleRowPrice;
      numEle += qtaRow;
      console.log(numEle);
    }

    let settingPaypal = {
      // Configure environment
      env: "sandbox",
      client: {
        sandbox: "demo_sandbox_client_id",
        production: "demo_production_client_id"
      },
      // Customize button (optional)
      locale: "it_IT",
      style: {
        layout: "vertical",
        color: "gold",
        shape: "rect",
        label: "paypal"
      },
      payment: function(data, actions) {
        return actions.payment.create({
          transactions: [
            {
              amount: {
                total: "" + totale,
                currency: "EUR"
              }
            }
          ]
        });
      },
      // Enable Pay Now checkout flow (optional)
      commit: true,
      // Execute the payment
      onAuthorize: function(data, actions) {
        return actions.payment.execute().then(function() {
          $(window.location).attr(
            "href",
            "http://localhost:8080/BookBarWebsite/ok"
          );
        });
      }
    };
    document.querySelector("#paypal-button").innerHTML = "";
    paypal.Button.render(settingPaypal, "#paypal-button");
    document.querySelector("#totale-elementi").innerHTML = numEle;
    document.querySelector("#t-e").innerHTML = numEle;
    document.querySelector("#prezzo").innerHTML = totale;
    document.querySelector(".item-table").innerHTML = html;

    let rowDeleteBtn = document.querySelectorAll(".rowDeleteBtn");
    for (let i = 0; i < rowDeleteBtn.length; i++) {
      console.log("Ciaooooooo");
      rowDeleteBtn[i].addEventListener("click", function() {
        console.log("rowDeleteBtn ", rowDeleteBtn[i].dataset.id);
        delUserHandler(rowDeleteBtn[i].dataset.id);
      });
    }
  });
};

$(document).ready(function() {
  getItemList();
});
