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
			<th scope="col">Disponibile</th>
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

let getUserList = () => {
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
 					console.log(data[row].prodotti[p])
 					singleRowHTML += '<span class="prodtitle">' + data[row].prodotti[p].nome + '</span> ' + data[row].prodotti[p].descrizione + '<br/>';
 				}

 				singleRowHTML += '</td></tr>';
				html += singleRowHTML;
				k++;
			}
			
			document.querySelector(".menu-table").innerHTML = html;
			
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
}
	let settings = {
			  "async": true,
			  "crossDomain": true,
			  "url": "http://localhost:8080/BookBarWebsite/gestione_ordini",
			  "method": "POST",
			  "headers": {
			    "Content-Type": "application/x-www-form-urlencoded",
			    "Accept": "*/*",
			  },
			  "data": {}
			}
	
	let inLavorazioneHandler = (id) => {
		let data = {}
		data.operation="lavorazione";
		data.id=id

		settings.data = data;
		
		console.log("Lavorazione ", settings.data);
		$.ajax(settings).done(function (response) {
			  console.log(response);
			  getUserList();
			});
	}
	
	let consegnaHandler = (id) => {
		let data = {}
		data.operation="consegna";
		data.id=id

		settings.data = data;
		
		console.log("Consegna ", settings.data);
		$.ajax(settings).done(function (response) {
			  console.log(response);
			  getUserList();
			});
	}

	let deleteHandler = (id) => {
		let data = {}
		data.operation="del";
		data.id=id

		settings.data = data;
		
		console.log("Delete ", settings.data);
		$.ajax(settings).done(function (response) {
			  console.log(response);
			  getUserList();
			});
	}
	

	$(document).ready(function() {
		getUserList();
		console.log("ciao")
  });
</script>

</body>
</html>