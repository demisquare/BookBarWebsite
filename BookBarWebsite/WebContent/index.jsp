
<!-- header bar -->
<%@include file="component/header.jsp"%>

<div class="container" id="main">

	<div class="row">
		<div class="col-sm-4">
			<h2>Welcome to Book Bar!</h2>
			<p>Lorem ipsum dolor sit amet, consectetur adipisci elit, sed do
				eiusmod tempor incidunt ut labore et dolore magna aliqua. Ut enim ad
				minim veniam, quis nostrum exercitationem ullamco laboriosam, nisi
				ut aliquid ex ea commodi consequatur. Duis aute irure reprehenderit
				in voluptate velit esse cillum dolore eu fugiat nulla pariatur.
				Excepteur sint obcaecat cupiditat non proident, sunt in culpa qui
				officia deserunt mollit anim id est laborum.</p>
				
				<button class="btn btn-lg btn-primary btn-block btn-signin compra1">Compra menu 1</button>
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

let handleCompra1 = () => {
	$.ajax(settings).done(function (response) {
		console.log("Compra menu 1");
});
}
let compra1 = document.querySelector(".compra1");
compra1.addEventListener('click', handleCompra1);


</script>

</body>
</html>