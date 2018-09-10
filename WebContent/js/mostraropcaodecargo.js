function segundoCargo() {
	var checkBox = document.getElementById("chkBox");

	if (checkBox.checked == true) {
		document.getElementById("cargoselectopcional").style.display = "block";
	} else {
		document.getElementById("cargoselectopcional").style.display = "none";
	}
}