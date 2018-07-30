function aditivo() {
	var checkBox = document.getElementById("aditivoChkBox");
	// Get the output text
	var aditivo = document.getElementById("infoAditivo");

	// If the checkbox is checked, display the output text
	if (checkBox.checked == true) {
		aditivo.style.display = "block";
	} else {
		aditivo.style.display = "none";
	}
}