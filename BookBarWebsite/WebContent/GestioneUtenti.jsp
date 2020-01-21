<!-- header bar -->
<%@include file="component/header.jsp"%>

<c:choose>
    <c:when test="${utente.getRole().equals(\"admin\")}">
       
<h1>Utenti</h1>
<button type="button" class="btn btn-success adminAddBtn"
	data-toggle="modal" data-target="#exampleModalCenter">Aggiungi
	un utente</button>

<table class="table table-hover">
	<thead>
		<tr>
			<th scope="col">#</th>
			<th scope="col">ID</th>
			<th scope="col">Email</th>
			<th scope="col">FirstName</th>
			<th scope="col">LastName</th>
			<th scope="col">UserType</th>
			<th scope="col"></th>
		</tr>
	</thead>
	<tbody class="user-table">

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
				<div class="form-group">
					<label for="firstname">First Name</label> <input type="text"
						class="form-control" id="firstname">
				</div>
				<div class="form-group">
					<label for="lastname">Last Name</label> <input type="text"
						class="form-control" id="lastname">
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

    </c:when>
    
        <c:otherwise>

    <% response.sendRedirect("login.jsp"); %>
    </c:otherwise>
</c:choose>

<!-- footer bar -->
<%@ include file="component/footer.jsp"%>

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
				// {"id":1,"role":"admin","firstName":"Paola","email":"paola@unical.it","password":"paola","lastName":"Ciaone"}
				console.log("Data",data);
				let html = '';
				let k = 0;
				for (let row in data) {
					let singleRowHTML = '<tr class="userRow"><th scope="row">' + k + '</th><td>' + data[row].id + '</td><td>' + data[row].email + '</td><td>' + data[row].firstName + '</td><td>' + data[row].lastName + '</td><td>' + data[row].role + '</td><td class="userDeleteBtn" data-id="'+data[row].id+'"><i class="fas fa-trash-alt deleteIcon"></i></td></tr>';
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
		debugger
		data.fn=document.querySelector("#firstname").value;
		data.ln=document.querySelector("#lastname").value;
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
		console.log("ciao")
  });
</script>


</body>
</html>
