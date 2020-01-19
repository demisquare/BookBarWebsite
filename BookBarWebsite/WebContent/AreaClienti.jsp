
<!-- header bar -->
<%@include file="component/header.jsp"%>

<div class="container" id="main">

	<div class="row">
		<div class="col-sm-4">
			<h2>
				<c:if test="${utente != null}">
						Welcome ${utente.getFirstName()};
				</c:if>
			</h2>
			<p>Lorem ipsum dolor sit amet, consectetur adipisci elit, sed do
				eiusmod tempor incidunt ut labore et dolore magna aliqua. Ut enim ad
				minim veniam, quis nostrum exercitationem ullamco laboriosam, nisi
				ut aliquid ex ea commodi consequatur. Duis aute irure reprehenderit
				in voluptate velit esse cillum dolore eu fugiat nulla pariatur.
				Excepteur sint obcaecat cupiditat non proident, sunt in culpa qui
				officia deserunt mollit anim id est laborum.</p>
		</div>
	</div>
</div>

<!-- footer bar -->
<%@include file="component/footer.jsp"%>