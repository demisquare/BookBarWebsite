
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
		<h1><%= username %> - carrello (<%= eleCarrello %>) </h1>
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
	<div id="paypal-button"></div>
</div>

  <script
    src="https://www.paypal.com/sdk/js?client-id=AYWDsSzrI192Fz26mTpZUGsOZu-kCi4AVTakm5K8AebnzBBllJdPn82F6_QIhsamFhFvsBLxfBDnJt5R"> // Required. Replace SB_CLIENT_ID with your sandbox client ID.
  </script>
<script src="https://www.paypalobjects.com/api/checkout.js"></script>



<!-- footer bar -->
<%@include file="component/footer.jsp"%>


<script>


	

let getItemList = () => {
	$.ajax({
		  "async": true,
		  "crossDomain": true,
		  "url": "http://localhost:8080/BookBarWebsite/api/chart",
		  "method": "GET",
		  "headers": {
			    "Content-Type": "application/x-www-form-urlencoded",
			    "Accept": "*/*",
			  },		  "data": {}
		}).done(function (response) {
			let data = JSON.parse(JSON.stringify(response));			
			console.log("Data",data);
			let html = '';
			let k = 0;
			let totale = 0;
			for (let row in data) {
				let dingleRowPrice = parseFloat(data[row].menu.prezzo) * parseInt(data[row].qta);
				console.log(dingleRowPrice, parseFloat(data[row].menu.prezzo), parseInt(data[row].qta))
				let singleRowHTML = '<tr class="userRow"><th scope="row">' + data[row].menu.id + '</th><td>' + data[row].menu.name + '</td><td>' + data[row].qta + '</td><td>' + dingleRowPrice + '</td><td>' + data[row] + '</td>';
				singleRowHTML += '</tr>';
				html += singleRowHTML;
			
				totale += dingleRowPrice;
			}

			let settingPaypal = {
				// Configure environment
				env: 'sandbox',
				client: {
					sandbox: 'demo_sandbox_client_id',
					production: 'demo_production_client_id'
				},
				// Customize button (optional)
				locale: 'it_IT',
				style: {
					size: 'small',
					color: 'gold',
					shape: 'pill',
				},
				payment: function(data, actions) {
					return actions.payment.create({
						transactions: [{
							amount: {
								total: '' + totale,
								currency: 'EUR'
							}
						}]
					});
				},
				// Enable Pay Now checkout flow (optional)
				commit: true,
				// Execute the payment
				onAuthorize: function(data, actions) {
					return actions.payment.execute().then(function() {
						// Show a confirmation message to the buyer
						window.alert('Thank you for your purchase!');
						$(window.location).attr('href', 'http://localhost:8080/BookBarWebsite/ok');
					});
				}
			}

			paypal.Button.render(settingPaypal, '#paypal-button');

			html += '<tr class="userRow"><th scope="row"></th><td></td><td></td><td>Totale</td><td>' + totale + '</td></tr>';
			document.querySelector(".item-table").innerHTML = html;
		});
}

$(document).ready(function() {
	getItemList();
});



</script>
</body>
</html>