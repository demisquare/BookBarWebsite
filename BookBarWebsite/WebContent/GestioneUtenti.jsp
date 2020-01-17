<!-- header bar -->
<%@include file="component/header.jsp"%>

<h1>Utenti</h1>
<button type="button" class="btn btn-success adminAddBtn"
	data-toggle="modal" data-target="#exampleModalCenter">Aggiungi
	un utente</button>

<table class="table table-hover">
	<thead>
		<tr>
			<th scope="col">#</th>
			<th scope="col">ID</th>
			<th scope="col">Username</th>
			<th scope="col">Ruolo</th>
			<th scope="col"></th>
		</tr>
	</thead>
	<tbody class="user-table">

		<!-- <c:forEach items="${users}" var="user" varStatus="loop">
			<tr class="userRow">
				<th scope="row"><c:out value="${loop.index}"></c:out></th>
				<td><c:out value="${user.getId()}"></c:out></td>
				<td><c:out value="${user.getUsername()}"></c:out></td>
				<td><c:out value="${user.getRole()}"></c:out></td>
				<td class="userDeleteBtn"
					data-id="<c:out value="${user.getId()}"></c:out>"><i
					class="fas fa-trash-alt"></i></td>
			</tr>
		</c:forEach> -->

	</tbody>
</table>
<div id="jsonResult"></div>
<div class="modal fade" id="exampleModalCenter" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalCenterTitle"
	aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalCenterTitle">Inserisci
					un nuovo utente</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<label for="email">Email/Username</label> <input type="email"
						class="form-control" id="email" aria-describedby="emailHelp">
				</div>
				<div class="form-group">
					<label for="passwd">Password</label> <input type="password"
						class="form-control" id="passwd">
				</div>
				<div class="form-group form-check">
					<input type="checkbox" class="form-check-input" id="admin"
						value="admin"> <label class="form-check-label" for="admin">Admin?</label>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Chiudi</button>
					<button type="submit" class="btn btn-primary salvaUtente">Salva</button>
				</div>
			</div>

		</div>
	</div>
</div>
<!-- footer bar -->
<%@include file="component/footer.jsp"%>

<script>
	let getUserList = () => {
		$.ajax({
			  "async": true,
			  "crossDomain": true,
			  "url": "http://localhost:8080/BookBarWebsite/api/utenti",
			  "method": "GET",
			  "headers": {
			    "Content-Type": "application/json",
			    "Accept": "*/*",
			  },
			  "data": {}
			}).done(function (response) {
				let data = JSON.parse(JSON.stringify(response));

				console.log("Data",data);
				let html = '';
				let k = 0;
				for (let row in data) {
					let singleRowHTML = '<tr class="userRow"><th scope="row">' + k + '</th><td>' + data[row].id + '</td><td>' + data[row].username + '</td><td>' + data[row].role + '</td><td class="userDeleteBtn" data-id="'+data[row].id+'"><i class="fas fa-trash-alt deleteIcon"></i></td></tr>';
					html += singleRowHTML;
					k++;
				}
				document.querySelector(".user-table").innerHTML = html;


				let userAddBtn = document.querySelector(".salvaUtente");
	userAddBtn.addEventListener("click", addUserHandler);
	let userDeleteBtn = document.querySelectorAll(".userDeleteBtn");
	for (let i = 0; i < userDeleteBtn.length; i++) {
		userDeleteBtn[i].addEventListener("click", function() {
			console.log(userDeleteBtn[i].dataset.id);
			delUserHandler(userDeleteBtn[i].dataset.id);
		});
	}
			});
	}

	let settings = {
			  "async": true,
			  "crossDomain": true,
			  "url": "http://localhost:8080/BookBarWebsite/gestione_utenti",
			  "method": "POST",
			  "headers": {
			    "Content-Type": "application/x-www-form-urlencoded",
			    "Accept": "*/*",
			  },
			  "data": {}
			}
	let addUserHandler = () => {
		$("#exampleModalCenter").modal('hide')
		let data = {}
		data.operation="add";
		data.email=document.querySelector("input[type='email']").value;
		data.password=document.querySelector("input[type='password']").value;
		data.isAdmin=document.querySelector("#admin").checked;
		
		settings.data = data;
		
		console.log(settings.data);
		$.ajax(settings).done(function (response) {
				getUserList();
			});
	}
	let delUserHandler = (id) => {
		console.log("Vuoi eliminare l'utente " + id);
		let data = {}
		data.operation="del";
		data.id=id;
		settings.data = data;
		$.ajax(settings).done(function (response) {
				getUserList();
		});
	}
	
	$(document).ready(function() {
		getUserList();
  });
</script>
</body>
</html>
