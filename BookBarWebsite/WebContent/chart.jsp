
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

	<div class="row">
		<h1><%= username %> - Totale Elementi: <span id="totale-elementi"> </span> </h1>
	</div>
	<div class="row">
		<table class="table table-hover">
	<thead>
		<tr>
		<th scope="col">Menu Code</th>
			<th scope="col">Name</th>
			<th scope="col">Quantita</th>
			<th scope="col">Prezzo</th>
			<th scope="col">Elimina</th>
		</tr>
	</thead>
	<tbody class="item-table">

	</tbody>
</table>

	</div>
	<div class="row justify-content-end">
    <div class="col-3">
			<div id="paypal-button"></div>
		</div>
		<div class="col-2">
			 
			<p>
				Totale: 
				<span id="prezzo"></span>
				&euro;
			</p>
		</div>
  </div>
</div>

  <script
    src="https://www.paypal.com/sdk/js?client-id=AYWDsSzrI192Fz26mTpZUGsOZu-kCi4AVTakm5K8AebnzBBllJdPn82F6_QIhsamFhFvsBLxfBDnJt5R"> // Required. Replace SB_CLIENT_ID with your sandbox client ID.
  </script>
<script src="https://www.paypalobjects.com/api/checkout.js"></script>



<!-- footer bar -->
<%@include file="component/footer.jsp"%>

<script src="js/chart.js"></script>
</body>
</html>