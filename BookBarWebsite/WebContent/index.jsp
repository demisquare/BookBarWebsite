
<!-- header bar -->
<%@include file="component/header.jsp"%>

<div class="container" id="main">

	<div class="row">
		<div class="col-sm-12">
			<h2>Welcome to Book Bar!</h2>
			<p>Lorem ipsum dolor sit amet, consectetur adipisci elit, sed do
				eiusmod tempor incidunt ut labore et dolore magna aliqua. Ut enim ad
				minim veniam, quis nostrum exercitationem ullamco laboriosam, nisi
				ut aliquid ex ea commodi consequatur. Duis aute irure reprehenderit
				in voluptate velit esse cillum dolore eu fugiat nulla pariatur.
				Excepteur sint obcaecat cupiditat non proident, sunt in culpa qui
				officia deserunt mollit anim id est laborum.</p>
		</div>
	</div>
	<div class="row displayMenu">
		<div class="col-sm-4">
		</div>
	</div>

</div>

<!-- footer bar -->
<%@include file="component/footer.jsp"%>


<script>

let settings = {
		  "async": true,
		  "crossDomain": true,
		  "url": "http://localhost:8080/BookBarWebsite/",
		  "method": "POST",
		  "headers": {
		    "Content-Type": "application/x-www-form-urlencoded",
		    "Accept": "*/*",
		  },
		  "data": {}
		}

let handleCompra = (id) => {
	console.log(id)
	settings.data['id'] = id;
	$.ajax(settings).done(function (response) {
		console.log("Compra menu " + settings.data['id']);
});
}

let getMenuList = () => {
	$.ajax({
		  "async": true,
		  "crossDomain": true,
		  "url": "http://localhost:8080/BookBarWebsite/api/menu",
		  "method": "GET",
		  "headers": {
		    "Content-Type": "application/json",
		    "Accept": "*/*",
		  },
		  "data": {}
		}).done(function (response) {
			let data = JSON.parse(JSON.stringify(response));
			menu = data;
			console.log("Data",data);
			let html = '';
			let k = 0;
			for (let row in data) {
				let singleRowHTML = '<div class="col-sm-4">';
				singleRowHTML += '<button class="btn btn-lg btn-primary btn-block btn-signin compra" data-id="'+ data[row].id + '">Compra ' + data[row].name + ' </button>';
				singleRowHTML += '</div>';
				
/*  				for (let p in data[row].prodotti) {
 					singleRowHTML += '<span class="prodtitle">' + data[row].prodotti[p].nome + '</span> ' + data[row].prodotti[p].descrizione + '<br/>';
 				} */
/* 
 				singleRowHTML += '</td><td class="menuPlusBtn" data-toggle="modal" data-target="#addProd2MenuModal" data-id="'+data[row].id+'"><i class="fas fa-plus deleteIcon"></td><td class="menuDeleteBtn" data-id="'+data[row].id+'"><i class="fas fa-trash-alt deleteIcon"></td></tr>'; */
				html += singleRowHTML;
			}

			document.querySelector(".displayMenu").innerHTML = html;
			
			let addmenubtn = document.querySelectorAll(".compra");
			for (let i = 0; i < addmenubtn.length; i++) {
				addmenubtn[i].addEventListener("click", function() {
					handleCompra(addmenubtn[i].dataset.id);
				});
			}
			
		});
}

$(document).ready(function() {
	getMenuList();
	console.log("ciao")
});

</script>

</body>
</html>