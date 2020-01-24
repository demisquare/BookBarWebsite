let settings = {
  async: true,
  crossDomain: true,
  url: "http://localhost:8080/BookBarWebsite/",
  method: "POST",
  headers: {
    "Content-Type": "application/x-www-form-urlencoded",
    Accept: "*/*"
  },
  data: {}
};

let handleCompra = id => {
  let num = parseInt(document.querySelector("#t-e").innerHTML);
  console.log(num);
  document.querySelector("#t-e").innerHTML = num + 1;
  settings.data["id"] = id;
  $.ajax(settings).done(function(response) {
    console.log("Compra menu " + settings.data["id"]);
  });
};

let getMenuList = () => {
  $.ajax({
    async: true,
    crossDomain: true,
    url: "http://localhost:8080/BookBarWebsite/api/menu",
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      Accept: "*/*"
    },
    data: {}
  }).done(function(response) {
    let data = JSON.parse(JSON.stringify(response));
    menu = data;
    console.log("Data", data);
    let html = "";
    let k = 0;
    for (let row in data) {
      let singleRowHTML = `<div class="card" style="width: 18rem;">
                          <img class="card-img-top" src="https://source.unsplash.com/300x20${k}/?coffee,bar" alt="Card image cap">
                          <div class="card-body">
                            <h5 class="card-title">${data[row].name}</h5>
                            <p class="card-text">
                            <ul>`;
      for (let p in data[row].prodotti) {
        singleRowHTML += `<li>${data[row].prodotti[p].nome}</li>`;
      }
      singleRowHTML += `</ul>
                            </p>
                            </div>
                            
                            <div class="card-footer">
                            
                            <a href="#" class="btn btn-primary compra" data-id=${data[row].id}>Book this menu</a>
                            <span class="price-card">${data[row].prezzo}&euro;</span>
                            </div>
                        </div>`;

      /*  				for (let p in data[row].prodotti) {
       singleRowHTML += '<span class="prodtitle">' + data[row].prodotti[p].nome + '</span> ' + data[row].prodotti[p].descrizione + '<br/>';
     } */
      /* 
     singleRowHTML += '</td><td class="menuPlusBtn" data-toggle="modal" data-target="#addProd2MenuModal" data-id="'+data[row].id+'"><i class="fas fa-plus deleteIcon"></td><td class="menuDeleteBtn" data-id="'+data[row].id+'"><i class="fas fa-trash-alt deleteIcon"></td></tr>'; */
      html += singleRowHTML;
      k++;
    }

    document.querySelector(".displayMenu").innerHTML = html;
    let addmenubtn = document.querySelectorAll(".compra");
    for (let i = 0; i < addmenubtn.length; i++) {
      addmenubtn[i].addEventListener("click", function() {
        handleCompra(addmenubtn[i].dataset.id);
      });
    }
  });
};

$(document).ready(function() {
  getMenuList();
  console.log("ciao");
});
