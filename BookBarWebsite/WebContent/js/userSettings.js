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
let usernameTitle = document.querySelector('#usernameTitle');

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

			console.log('Response ',response);
			if(response.err) {
				logArea.innerHTML = '<p>Errore nel savataggio dei dati</p>';
				logArea.classList.add('redText');
			} else {
				logArea.innerHTML = '<p>Dati Salvati</p>';
				logArea.classList.add('greenText');
				inputOldPassword.value = '';
				
				getUserInfo();
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
				usernameTitle.innerHTML = inputFN.value;
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