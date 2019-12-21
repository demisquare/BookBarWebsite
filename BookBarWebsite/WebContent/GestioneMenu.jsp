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
	<tbody>

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
	<tbody>

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