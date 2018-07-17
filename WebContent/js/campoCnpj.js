/*
 * Script retirado de: https://brunobrum.wordpress.com/2010/05/20/mascara-javascript-de-cnpj-e-cpf-no-mesmo-campo-do-formulario/
 * Acesso em 17/07/2018
 * */

function isNum(caractere) {
	var strValidos = "0123456789";
	if (strValidos.indexOf(caractere) == -1)
		return false;
	return true;
}
function validaTecla(campo, event) {
	var BACKSPACE = 8;
	var key;
	var tecla;

	CheckTAB = true;
	if (navigator.appName.indexOf("Netscape") != -1)
		tecla = event.which;
	else
		tecla = event.keyCode;

	key = String.fromCharCode(tecla);
	//alert( 'key: ' + tecla + '  -> campo: ' + campo.value); 

	if (tecla == 13)
		return false;
	if (tecla == BACKSPACE)
		return true;
	return (isNum(key));
}
function FormataCNPJ(el) {
	vr = el.value;
	tam = vr.length;

	if (vr.indexOf(".") == -1) {
		if (tam <= 2)
			el.value = vr;
		if ((tam > 2) && (tam <= 6))
			el.value = vr.substr(0, 2) + '.' + vr.substr(2, tam);
		if ((tam >= 7) && (tam <= 10))
			el.value = vr.substr(0, 2) + '.' + vr.substr(2, 3) + '.'
					+ vr.substr(5, 3) + '/';
		if ((tam >= 11) && (tam <= 18))
			el.value = vr.substr(0, 2) + '.' + vr.substr(2, 3) + '.'
					+ vr.substr(5, 3) + '/' + vr.substr(8, 4) + '-'
					+ vr.substr(12, 2);
	}
	return true;
}
