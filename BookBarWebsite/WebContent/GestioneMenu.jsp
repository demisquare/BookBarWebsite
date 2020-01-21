<!-- header bar -->
<%@include file="component/header.jsp"%>

<h1>Menu</h1>
<button type="button" class="btn btn-success adminAddBtn"
	data-toggle="modal" data-target="#addMenuModal">Aggiungi
	un menu</button>

<div class="modal fade" id="addMenuModal" tabindex="-1"
	role="dialog" aria-labelledby="addMenuModalCenterTitle"
	aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="addMenuModalCenterTitle">Inserisci
					un nuovo menu</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<label for="menuname">Nome</label> <input type="text"
						class="form-control" id="menuname" aria-describedby="menunameHelp">
				</div>
<!-- 				<div class="form-group">
					<label for="menudesc">Descrizione</label> <input type="text"
						class="form-control" id="menudesc">
				</div>
 -->				
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Chiudi</button>
					<button type="submit" class="btn btn-primary menuAddBtn">Salva</button>
				</div>
			</div>

		</div>
	</div>
</div>

<div class="modal fade" id="addProd2MenuModal" tabindex="-1"
	role="dialog" aria-labelledby="addProd2MenuModal"
	aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="addProdModalCenterTitle">Inserisci i prodotti del menu</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<select id="mySelect2" name="states[]" multiple="multiple">
  <option value="1">Caffe</option>
  <option value="2">Latte</option>
</select>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Chiudi</button>
					<button type="submit" class="btn btn-primary addProd2MenuBtn">Salva</button>
				</div>
			</div>

		</div>
	</div>
</div>





<div class="modal fade" id="addProdModal" tabindex="-1"
	role="dialog" aria-labelledby="addProdModal"
	aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="addProdModalCenterTitle">Inserisci
					un nuovo prodotto</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<label for="prodname">Nome</label> <input type="text"
						class="form-control" id="prodname" aria-describedby="prodnamehelp">
				</div>
				<div class="form-group">
					<label for="proddesc">Descrizione</label> <input type="text"
						class="form-control" id="proddesc">
				</div>
				<div class="form-group">
					<label for="prodprezzo">Prezzo</label> <input type="text"
						class="form-control" id="prodprezzo">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Chiudi</button>
					<button type="submit" class="btn btn-primary prodAddBtn">Salva</button>
				</div>
			</div>

		</div>
	</div>
</div>

<table class="table table-hover">
	<thead>
		<tr>
			<th scope="col">#</th>
			<th scope="col">ID</th>
			<th scope="col">Nome</th>
			<th scope="col">Prodotti</th>
			<th scope="col"></th>
			<th scope="col"></th>
		</tr>
	</thead>
	<tbody class="menu-table">

		<c:forEach items="${menus}" var="menu" varStatus="loop">
			<tr class="userRow">
				<th scope="row"><c:out value="${loop.index}"></c:out></th>
				<td><c:out value="${menu.getId()}"></c:out></td>
				<td><c:out value="${menu.getUsername()}"></c:out></td>
				<td><c:out value="${menu.getRole()}"></c:out></td>
				<td class="userDeleteBtn"
					data-id="<c:out value="${user.getId()}"></c:out>"><i
					class="fas fa-trash-alt"></i></td>
			</tr>
		</c:forEach>

	</tbody>
</table>

<h1>Prodotti ${user.getName()}</h1>
<button type="button" class="btn btn-success adminAddBtn"
	data-toggle="modal" data-target="#addProdModal">Aggiungi
	un prodotto</button>

<table class="table table-hover">
	<thead>
		<tr>
			<th scope="col">#</th>
			<th scope="col">ID</th>
			<th scope="col">Nome</th>
			<th scope="col">Descrizione</th>
			<th scope="col">Prezzo</th>
			<th scope="col"></th>
		</tr>
	</thead>
	<tbody class="product-table">

		<c:forEach items="${products}" var="product" varStatus="loop">
			<tr class="userRow">
				<th scope="row"><c:out value="${loop.index}"></c:out></th>
				<td><c:out value="${product.getId()}"></c:out></td>
				<td><c:out value="${product.getUsername()}"></c:out></td>
				<td><c:out value="${product.getRole()}"></c:out></td>
				<td class="userDeleteBtn"
					data-id="<c:out value="${user.getId()}"></c:out>"><i
					class="fas fa-trash-alt"></i></td>
			</tr>
		</c:forEach>

	</tbody>
</table>
<!-- footer bar -->
<%@include file="component/footer.jsp"%>
<script>
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
 					singleRowHTML += '<td>' + data[row].nome + '</td><td>' + data[row].descrizione + '</td><td>' + data[row].prezzo + '</td>';

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

 				singleRowHTML += '</td><td class="menuPlusBtn" data-toggle="modal" data-target="#addProd2MenuModal" data-id="'+data[row].id+'"><i class="fas fa-plus deleteIcon"></td><td class="menuDeleteBtn" data-id="'+data[row].id+'"><i class="fas fa-trash-alt deleteIcon"></td></tr>';
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
	
	let addProdToMenuHandler = (id) => {
		let data = {}
		data.operation="add";
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
		salva.addEventListener('click', addProd2MenuHandler(data));

		console.log(data.prod);
		
		$.ajax(settings).done(function (response) {
			  console.log(response);
				getMenuList();
			});
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
	settings.data = data;
	console.log("add menu  ", settings.data);
	$.ajax(settings).done(function (response) {
		  console.log(response);
			getMenuList();
		});
	
	}
	
let addProd2MenuHandler = (data) => {
	
	for (let p in data.prod) {
		console.log(+data.prod[p].id)
		let id = +data.prod[p].id;
		$('#mySelect2').val(id);
		$('#mySelect2').trigger('change');
	}
	let prodSelezionati = $('#mySelect2').select2('data');
	let newProd = [];
	_.map(prodSelezionati, (el) => {
		console.log("Prodotto selezionato ", +el.id);
		newProd.push(+el.id);
	})
	console.log("ciao ", data.id, newProd)

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
</script>

</body>
</html>