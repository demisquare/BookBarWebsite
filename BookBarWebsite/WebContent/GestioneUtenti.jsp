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

<script src="js/gestioneUtenti.js"></script>

</body>
</html>
