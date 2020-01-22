
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
	<tbody class="item-table">

	</tbody>
</table>
	</div>
</div>

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
	getItemList();
});

</script>

</body>
</html>