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
				<div class="form-group">
					<label for="menuprezzo">Prezzo</label> <input type="text"
						class="form-control" id="menuprezzo">
				</div>			
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
					<button type="submit" class="btn btn-primary addProd2MenuBtn" data-dismiss="modal">Salva</button>
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
			<th scope="col">Prezzo</th>
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

<script src="js/gestioneMenu.js"></script>
</body>
</html>