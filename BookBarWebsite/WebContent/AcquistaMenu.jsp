
<!-- header bar -->
<%@include file="component/header.jsp"%>

<div class="container" id="main">


	<h1>Menu disponibili:</h1>
	<button type="button" class="btn btn-success adminAddBtn"
		data-toggle="modal" data-target="#addMenuModal">Procedi
		all'ordine</button>

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



</div>

<!-- footer bar -->
<%@include file="component/footer.jsp"%>
<script src="js/aquistaMenu.js"></script>