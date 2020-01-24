
	let getUserList = () => {
		$.ajax({
			  "async": true,
			  "crossDomain": true,
			  "url": "http://localhost:8080/BookBarWebsite/api/utenti",
			  "method": "GET",
			  "headers": {
			    "Content-Type": "application/json",
			    "Accept": "*/*",
			  },
			  "data": {}
			}).done(function (response) {
				let data = JSON.parse(JSON.stringify(response));
				// {"id":1,"role":"admin","firstName":"Paola","email":"paola@unical.it","password":"paola","lastName":"Ciaone"}
				console.log("Data",data);
				let html = '';
				let k = 0;
				for (let row in data) {
					let singleRowHTML = '<tr class="userRow"><th scope="row">' + k + '</th><td>' + data[row].id + '</td><td>' + data[row].email + '</td><td>' + data[row].firstName + '</td><td>' + data[row].lastName + '</td><td>' + data[row].role + '</td><td class="userDeleteBtn" data-id="'+data[row].id+'"><i class="fas fa-trash-alt deleteIcon"></i></td></tr>';
					html += singleRowHTML;
					k++;
				}
				document.querySelector(".user-table").innerHTML = html;


				let userAddBtn = document.querySelector(".salvaUtente");
	userAddBtn.addEventListener("click", addUserHandler);
	let userDeleteBtn = document.querySelectorAll(".userDeleteBtn");
	for (let i = 0; i < userDeleteBtn.length; i++) {
		userDeleteBtn[i].addEventListener("click", function() {
			console.log(userDeleteBtn[i].dataset.id);
			delUserHandler(userDeleteBtn[i].dataset.id);
		});
	}
			});
	}

	let settings = {
			  "async": true,
			  "crossDomain": true,
			  "url": "http://localhost:8080/BookBarWebsite/gestione_utenti",
			  "method": "POST",
			  "headers": {
			    "Content-Type": "application/x-www-form-urlencoded",
			    "Accept": "*/*",
			  },
			  "data": {}
			}
	let addUserHandler = () => {
		$("#exampleModalCenter").modal('hide')
		let data = {}
		data.operation="add";
		data.email=document.querySelector("input[type='email']").value;
		data.password=document.querySelector("input[type='password']").value;
		debugger
		data.fn=document.querySelector("#firstname").value;
		data.ln=document.querySelector("#lastname").value;
		data.isAdmin=document.querySelector("#admin").checked;
		
		settings.data = data;
		
		console.log(settings.data);
		$.ajax(settings).done(function (response) {
				getUserList();
			});
	}
	let delUserHandler = (id) => {
		console.log("Vuoi eliminare l'utente " + id);
		let data = {}
		data.operation="del";
		data.id=id;
		settings.data = data;
		$.ajax(settings).done(function (response) {
				getUserList();
		});
	}
	
	$(document).ready(function() {
		getUserList();
		console.log("ciao")
  });
