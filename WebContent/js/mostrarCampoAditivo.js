function aditivo() {
	var checkBox = document.getElementById("aditivoChkBox");
	// Get the output text
	var valor = document.getElementById("valorAditivo");
	var tipo = document.getElementById("tipoAditivo");

	// If the checkbox is checked, display the output text
	if (checkBox.checked == true) {
		valor.style.display = "block";
		tipo.style.display = "block";
	} else {
		valor.style.display = "none";
		tipo.style.display = "none";
	}
}