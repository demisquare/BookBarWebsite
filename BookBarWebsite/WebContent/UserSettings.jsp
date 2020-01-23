
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

		<h1>Ciao <%= username %>! - impostazioni</h1>
		
		<div class="row">
			<div class="col-6"><input type="text" name="firstname" id="inputFirstname" class="form-control" placeholder="Nome" autofocus></div>
			<div class="col-6"><input type="text" name="lastname" id="inputLastname" class="form-control" placeholder="Cognome"></div>
		</div>
		<div class="row">
			<div class="col-4"><input type="email" name="email" id="inputEmail" class="form-control" placeholder="Email"></div>
		</div>
				<div class="row">
			<div class="col-4"><input type="password" name="passwordattuale" id="passwordattuale" class="form-control" placeholder="Vecchia Password"></div>
			<div class="col-4"><input type="password" name="passwordnuova" id="passwordnuova" class="form-control" placeholder="Nuova Password"></div>
			<div class="col-4"><input type="password" name="passwordconferma" id="passwordconferma" class="form-control" placeholder="Confirma Password"></div>
		</div>
		<div class="row">
		

		
		</div>
		<div class="row justify-content-center">
		<div class="col-4 logarea">

		
	
		</div>
		
			<div class="col-4 align-content-center">
	<button type="button" class="btn btn-success salvaBtn">Salva</button>
		</div>


		<div class="row">
		<h1><%= username %> - ordini </h1>
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

let settings = {
		  "async": true,
		  "crossDomain": true,
		  "url": "http://localhost:8080/BookBarWebsite/user",
		  "headers": {
			    "Content-Type": "application/x-www-form-urlencoded",
			    "Accept": "*/*",
			  }
		};

let inputFN = document.querySelector('#inputFirstname');
let inputLN = document.querySelector('#inputLastname');
let inputEmail = document.querySelector('#inputEmail');
let inputOldPassword = document.querySelector('#passwordattuale');
let inputNewPassword = document.querySelector('#passwordnuova');
let inputConfirmPassword = document.querySelector('#passwordconferma');
let logArea = document.querySelector('.logarea');


let saveChangeHandler = () => {
	logArea.innerHTML = '';
	logArea.classList.remove('redText');
	console.log('Cambiamenti!!!! ');
	if(inputNewPassword.value !== inputConfirmPassword.value) {
		logArea.innerHTML = '<p>Errore! Le password sono diverse!!!</p>';
		logArea.classList.add('redText');

	} else {
		
	console.log(inputFN.value,inputLN.value,inputEmail.value,inputOldPassword.value,inputNewPassword.value,inputConfirmPassword.value);
	settings.method = "POST";
	settings['data'] = {
			operation: "update",
			inputFN: inputFN.value,
			inputLN: inputLN.value,
			inputEmail: inputEmail.value,
			inputOldPassword: inputOldPassword.value,
			inputNewPassword: inputNewPassword.value,
		}

		console.log("settings.data", settings.data);
		$.ajax(settings).done(function (response) {
		        
			if(response.err) {
				logArea.innerHTML = '<p>Errore nel savataggio dei dati</p>';
				logArea.classList.add('redText');
			} else {
				logArea.innerHTML = '<p>Dati Salvati</p>';
				logArea.classList.add('greenText');
			}
			
		});
	}
}

let salvaBtn = document.querySelector('.salvaBtn');
salvaBtn.addEventListener('click', saveChangeHandler);


let getUserInfo = () => {
	settings.data = {};
	settings.method = "POST";
	settings.data['operation'] = "get";
	$.ajax(settings).done(function (response) {
				console.log("User info", response);
				inputFN.value = response.firstName;
				inputLN.value = response.lastName;
				inputEmail.value = response.email;
/* 				inputOldPassword.value = response.password; */
		});
}


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
	getUserInfo();
});

</script>

</body>
</html>