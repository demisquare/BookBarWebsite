<!-- header bar -->
<%@include file="component/header.jsp"%>

<h1>Ordini</h1>

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
		</tr>
	</thead>
	<tbody>

	</tbody>
</table>

<script>
	let settings = {
			  "async": true,
			  "crossDomain": true,
			  "url": "http://localhost:8080/BookBarWebsite/gestione_ordini",
			  "method": "POST",
			  "headers": {
			    "Content-Type": "application/x-www-form-urlencoded",
			    "Accept": "*/*",
			  },
			  "data": {}
			}
	
	let inLavorazioneHandler = (id) => {
		let data = {}
		data.operation="lavorazione";
		data.id=id

		settings.data = data;
		
		console.log("Lavorazione ", settings.data);
		$.ajax(settings).done(function (response) {
			  console.log(response);
			});
	}
	
	let consegnaHandler = (id) => {
		let data = {}
		data.operation="consegna";
		data.id=id

		settings.data = data;
		
		console.log("Consegna ", settings.data);
		$.ajax(settings).done(function (response) {
			  console.log(response);
			});
	}

	let deleteHandler = (id) => {
		let data = {}
		data.operation="del";
		data.id=id

		settings.data = data;
		
		console.log("Delete ", settings.data);
		$.ajax(settings).done(function (response) {
			  console.log(response);
			});
	}
	
	let consegnaBtn = document.querySelectorAll(".consegnaBtn");
	for (let i = 0; i < consegnaBtn.length; i++) {
		consegnaBtn[i].addEventListener("click", function() {
			consegnaHandler(consegnaBtn[i].dataset.id);
		});
	}
	
	let deleteBtn = document.querySelectorAll(".deleteBtn");
	for (let i = 0; i < deleteBtn.length; i++) {
		deleteBtn[i].addEventListener("click", function() {
			deleteHandler(deleteBtn[i].dataset.id);
		});
	}
	
	
	let inLavorazioneBtn = document.querySelectorAll(".inLavorazioneBtn");
	for (let i = 0; i < inLavorazioneBtn.length; i++) {
		inLavorazioneBtn[i].addEventListener("click", function() {
			inLavorazioneHandler(inLavorazioneBtn[i].dataset.id);
		});
	}
	
</script>

<!-- footer bar -->
<%@include file="component/footer.jsp"%>