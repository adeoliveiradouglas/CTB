function aditivo() {
	var checkBox = document.getElementById("aditivoChkBox");
	var aditivo = document.getElementById("infoAditivo");

	// If the checkbox is checked, display the output text
	if (checkBox.checked) {
		aditivo.style.display = "block";
	} else {
		aditivo.style.display = "none";
	}
}