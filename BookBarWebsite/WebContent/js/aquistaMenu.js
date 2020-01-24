let menu = null;
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
      let singleRowHTML = `<tr class="userRow"><th scope="row">${k}</th><td>${data[row].id}</td><td>${data[row].name}</td><td>`;

      for (let p in data[row].prodotti) {
        singleRowHTML += `<span class="prodtitle">${data[row].prodotti[p].nome}</span> ${data[row].prodotti[p].descrizione}<br/>`;
      }

      singleRowHTML += `</td><td class="menuPlusBtn" data-toggle="modal" data-target="#addProd2MenuModal" data-id="${data[row].id}">
                          <i class="fas fa-plus deleteIcon"></td>
                          <td class="menuDeleteBtn" data-id="${data[row].id}">
                          <i class="fas fa-trash-alt deleteIcon"></td></tr>`;
      html += singleRowHTML;
      k++;
    }

    document.querySelector(".menu-table").innerHTML = html;

    let menuPlusBtn = document.querySelectorAll(".menuPlusBtn");
    for (let i = 0; i < menuPlusBtn.length; i++) {
      menuPlusBtn[i].addEventListener("click", function() {
        addProdToMenuHandler(menuPlusBtn[i].dataset.id);
      });
    }

    let menuDeleteBtn = document.querySelectorAll(".menuDeleteBtn");
    for (let i = 0; i < menuDeleteBtn.length; i++) {
      menuDeleteBtn[i].addEventListener("click", function() {
        delMenuHandler(menuDeleteBtn[i].dataset.id);
      });
    }
  });
};
