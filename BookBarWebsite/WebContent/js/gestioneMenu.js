let getProdList = () => {
	$.ajax({
		  "async": true,
		  "crossDomain": true,
		  "url": "http://localhost:8080/BookBarWebsite/api/prodotti",
		  "method": "GET",
		  "headers": {
		    "Content-Type": "application/json",
		    "Accept": "*/*",
		  },
		  "data": {}
		}).done(function (response) {
			let data = JSON.parse(JSON.stringify(response));
			// {"id":1,"role":"admin","firstName":"Paola","email":"paola@unical.it","password":"paola","lastName":"Ciaone"}
			
			console.log("PROD",data);
			let html = '';
			let k = 0;
			for (let row in data) {
				let singleRowHTML = '<tr class="userRow"><th scope="row">' + k + '</th><td>' + data[row].id + '</td>';
 					singleRowHTML += '<td>' + data[row].nome + '</td><td>' + data[row].descrizione + '</td>';

 				singleRowHTML += '</td><td class="productDeleteBtn" data-id="'+data[row].id+'"><i class="fas fa-trash-alt deleteIcon"></td></tr>';
				html += singleRowHTML;
				k++;
			// Set the value, creating a new option if necessary
			if ($('#mySelect2').find("option[value='" + data[row].id + "']").length) {
			    $('#mySelect2').val(data[row].id).trigger('change');
			} else { 
			    // Create a DOM Option and pre-select by default
			    var newOption = new Option(data[row].nome, data[row].id, true, true);
			    // Append it to the select
			    $('#mySelect2').append(newOption).trigger('change');
			} 
			}

			document.querySelector(".product-table").innerHTML = html;

			let productDeleteBtn = document.querySelectorAll(".productDeleteBtn");
			for (let i = 0; i < productDeleteBtn.length; i++) {
				productDeleteBtn[i].addEventListener("click", function() {
					delProdHandler(productDeleteBtn[i].dataset.id);
				});
			}
		});
}
let menu = null;
let getMenuList = () => {
	$.ajax({
		  "async": true,
		  "crossDomain": true,
		  "url": "http://localhost:8080/BookBarWebsite/api/menu",
		  "method": "GET",
		  "headers": {
		    "Content-Type": "application/json",
		    "Accept": "*/*",
		  },
		  "data": {}
		}).done(function (response) {
			let data = JSON.parse(JSON.stringify(response));
			// {"id":1,"role":"admin","firstName":"Paola","email":"paola@unical.it","password":"paola","lastName":"Ciaone"}
			menu = data;
			console.log("Data",data);
			let html = '';
			let k = 0;
			for (let row in data) {
				let singleRowHTML = '<tr class="userRow"><th scope="row">' + k + '</th><td>' + data[row].id + '</td><td>' + data[row].name + '</td><td>';
				
 				for (let p in data[row].prodotti) {
 					singleRowHTML += '<span class="prodtitle">' + data[row].prodotti[p].nome + '</span> ' + data[row].prodotti[p].descrizione + '<br/>';
 				}

 				singleRowHTML += '</td> <td>' + data[row].prezzo + '</td> <td class="menuPlusBtn" data-toggle="modal" data-target="#addProd2MenuModal" data-id="'+data[row].id+'"><i class="fas fa-plus deleteIcon"></td><td class="menuDeleteBtn" data-id="'+data[row].id+'"><i class="fas fa-trash-alt deleteIcon"></td></tr>';
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
}
	let settings = {
			  "async": true,
			  "crossDomain": true,
			  "url": "http://localhost:8080/BookBarWebsite/gestione_menu",
			  "method": "POST",
			  "headers": {
			    "Content-Type": "application/x-www-form-urlencoded",
			    "Accept": "*/*",
			  },
			  "data": {}
			}
	
	let data = {}
	
	let addProdToMenuHandler = (id) => {
		data.operation="addP";
		data.subject="menu";
		data.id= +id
		settings.data = data;	
		console.log("add prod to menu ", id," ", menu);
		if (menu !== 'null') {
			console.log('id ', data.id)
			let menuFiltrato = _.find(menu, {'id': data.id});
			
			console.log(menuFiltrato.prodotti)
			data.prod = menuFiltrato.prodotti;
		}
		
		let salva = document.querySelector(".addProd2MenuBtn");
		salva.addEventListener('click', addProd2MenuHandler);

	}
	
	let delMenuHandler = (id) => {
		let data = {}
		data.operation="del";
		data.subject="menu";
		data.id=id
		settings.data = data;
		console.log("menuDeleteBtn ", settings.data);
		$.ajax(settings).done(function (response) {
			  console.log(response);
				getMenuList();
			});
	}
	
	
	let delProdHandler = (id) => {
		let data = {}
		data.operation="del";
		data.subject="product";
		data.id=id
		settings.data = data;
		console.log("delete product  ", settings.data);
		$.ajax(settings).done(function (response) {
			  console.log(response);
				getProdList();
			});
	}
	
let addMenuHandler = () => {
	$("#addMenuModal").modal('hide')
	console.log("add menu"); 
	let data = {}
	data.operation="add";
	data.subject="menu";
	data.name=document.querySelector("#menuname").value;
	data.prezzo=document.querySelector("#menuprezzo").value;
	console.log("Prezzo ", data.prezzo)
	settings.data = data;
	console.log("add menu  ", settings.data);
	$.ajax(settings).done(function (response) {
		  console.log(response);
			getMenuList();
		});
	
	}
	
let addProd2MenuHandler = () => {
	
/* 	for (let p in data.prod) {
		console.log(+data.prod[p].id)
		let id = +data.prod[p].id;
		$('#mySelect2').val(id);
		$('#mySelect2').trigger('change');
	} */
	let prodSelezionati = $('#mySelect2').select2('data');
	let newProd = [];
	_.map(prodSelezionati, (el) => {
		console.log("Prodotto selezionato ", +el.id);
		newProd.push(+el.id);
	})
	console.log("Salvo... ", data.id, newProd)
	console.log(data.prod);
	data.newProds = JSON.stringify(newProd);
	data.operation = "update";
	data.subject = "menu";
	settings.data = data;
	
	$.ajax(settings).done(function (response) {
		  console.log(response);
			getMenuList();
		});
	data = {}
}

let addProdHandler = () => {
	$("#addProdModal").modal('hide')
	console.log("add prod"); 
	let data = {}
	data.operation="add";
	data.subject="product";
	data.name=document.querySelector("#prodname").value;
	document.querySelector("#prodname").value = '';
	data.desc=document.querySelector("#proddesc").value;
	document.querySelector("#proddesc").value = '';
	settings.data = data;
	console.log("add prod  ", settings.data);
	$.ajax(settings).done(function (response) {
		  console.log(response);
			getProdList();
		});
	
	}
		let menuAddBtn = document.querySelector(".menuAddBtn");
		menuAddBtn.addEventListener("click", addMenuHandler);
		
		let prodAddBtn = document.querySelector(".prodAddBtn");
		prodAddBtn.addEventListener("click", addProdHandler);

	$(document).ready(function() {
		$('#mySelect2').select2();
		getMenuList();
		getProdList();
  });