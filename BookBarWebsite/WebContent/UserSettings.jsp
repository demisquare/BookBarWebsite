
<!-- header bar -->
<%@include file="component/header.jsp"%>
<%
String username = "";
int eleCarrello = 0;
if (session.getAttribute("name") != null) {
	username = (String) session.getAttribute("name");
	eleCarrello = (int) session.getAttribute("numEleCarrello");
}
%>
<div class="container" id="main">

		<h1>Ciao <span id="usernameTitle"></span>!</h1>
		
		<div class="row">
			<div class="col-6"><input type="text" name="firstname" id="inputFirstname" class="form-control" placeholder="Nome" autofocus></div>
			<div class="col-6"><input type="text" name="lastname" id="inputLastname" class="form-control" placeholder="Cognome"></div>
		</div>
		<div class="row">
			<div class="col-4"><input type="email" name="email" id="inputEmail" class="form-control" placeholder="Email"></div>
		</div>
				<div class="row">
			<div class="col-4"><input type="password" name="passwordattuale" id="passwordattuale" class="form-control" placeholder="Vecchia Password"></div>
			<div class="col-4"><input type="password" name="passwordnuova" id="passwordnuova" class="form-control" placeholder="Nuova Password"></div>
			<div class="col-4"><input type="password" name="passwordconferma" id="passwordconferma" class="form-control" placeholder="Confirma Password"></div>
		</div>
		<div class="row">
		

		
		</div>
		<div class="row justify-content-center">
		<div class="col-4 logarea">

		
	
		</div>
		
			<div class="col-4 align-content-center">
	<button type="button" class="btn btn-success salvaBtn">Salva</button>
		</div>

</div>
		<div class="row">
		<div class="col-6">
		<h1>Storico ordini </h1>
		</div>
	</div>
	<div class="row">
	<div class="col-12">
		<table class="table table-hover">
	<thead>
		<tr>
			<th scope="col">#</th>
			<th scope="col">ID</th>
			<th scope="col">Stato</th>
			<th scope="col">Deadline</th>
			<th scope="col">Username</th>
			<th scope="col">Menu</th>
		</tr>
	</thead>
	<tbody class="order-table">

	</tbody>
</table>
</div>
	</div>
</div>

<!-- footer bar -->
<%@include file="component/footer.jsp"%>

<script src="js/userSettings.js"></script>
</body>
</html>