
<!-- header bar -->
<%@include file="component/header.jsp"%>

<div class="container" id="main">
	<div class="row">
		<h1>User settings - ${session.getAttribute("name")} - impostazioni</h1>
		
	</div>
	<div class="row">
		<h1>User settings - ${session.getAttribute("utente")} - carrello</h1>
	</div>
	<div class="row">
		<h1>User settings - ${session.getAttribute("utente")} - ordini passati </h1>
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
	</div>
</div>

<!-- footer bar -->
<%@include file="component/footer.jsp"%>


<script>


let getOrderList = () => {
	$.ajax({
		  "async": true,
		  "crossDomain": true,
		  "url": "http://localhost:8080/BookBarWebsite/api/ordini",
		  "method": "POST",
		  "headers": {
			    "Content-Type": "application/x-www-form-urlencoded",
			    "Accept": "*/*",
			  },		  "data": {}
		}).done(function (response) {
			let data = JSON.parse(JSON.stringify(response));			
			console.log("Data",data);
			let html = '';
			let k = 0;
			for (let row in data) {
				let singleRowHTML = '<tr class="userRow"><th scope="row">' + k + '</th><td>' + data[row].id + '</td><td>' + data[row].stato + '</td><td>' + data[row].data + '</td><td>' + data[row].user.email + '</td><td>' + data[row].menu.name + '</td>';
				singleRowHTML += '</tr>';
				html += singleRowHTML;
				k++;
			}
			
			document.querySelector(".order-table").innerHTML = html;
		});
}

$(document).ready(function() {
	getOrderList();
});

</script>

</body>
</html>