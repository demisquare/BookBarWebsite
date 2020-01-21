<!-- header bar -->
<%@include file="component/header.jsp"%>

<h1>Menu</h1>
<button type="button" class="btn btn-success adminAddBtn"
	data-toggle="modal" data-target="#exampleModalCenter">Aggiungi
	un menu</button>

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
	data-toggle="modal" data-target="#exampleModalCenter">Aggiungi
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
			
			console.log("Data",data);
			let html = '';
			let k = 0;
			for (let row in data) {
				let singleRowHTML = '<tr class="userRow"><th scope="row">' + k + '</th><td>' + data[row].id + '</td><td>' + data[row].name + '</td><td>';
				
 				for (let p in data[row].prodotti) {
 					singleRowHTML += '<span class="prodtitle">' + data[row].prodotti[p].nome + '</span> ' + data[row].prodotti[p].descrizione + '<br/>';
 				}

 				singleRowHTML += '</td><td class="menuPlusBtn" data-id="'+data[row].id+'"><i class="fas fa-plus deleteIcon"></td><td class="menuDeleteBtn" data-id="'+data[row].id+'"><i class="fas fa-trash-alt deleteIcon"></td></tr>';
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
		data.id=id
		settings.data = data;		
		console.log("menuPlusBtn ", settings.data);
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
	$(document).ready(function() {
		getMenuList();
		getProdList();
  });
</script>

</body>
</html>