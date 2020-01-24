<!-- header bar -->
<%@include file="component/header.jsp"%>

<h1>Ordini</h1>

<table class="table table-hover">
	<thead>
		<tr>
			<th scope="col">#</th>
			<th scope="col">ID</th>
			<th scope="col">Stato</th>
			<th scope="col">Deadline</th>
			<th scope="col">Username</th>
			<th scope="col">Menu</th>
			<th scope="col"></th>
			<th scope="col"></th>
			<th scope="col"></th>
		</tr>
	</thead>
	<tbody class="order-table">

	</tbody>
</table>
<!-- footer bar -->
<%@include file="component/footer.jsp"%>
<script src="js/gestioneOrdini.js"></script>

</body>
</html>