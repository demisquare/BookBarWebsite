let a = document.querySelector("h1");
let btn = document.querySelector(".toggleRed");
let red = false;
let handleClick = () => {
	if (red) {
		a.classList.remove("red");
	} else {
		a.classList.add("red");
	}
	red = !red;
};

// btn.addEventListener("click", handleClick);
