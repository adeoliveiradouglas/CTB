/*
 * CÃ³digo adaptado do site http://forum.wmonline.com.br/topic/125538-formatar-dados-em-real-r/ acesso em 17/07/2018 
 */

function MascaraCnpj(campo) {// Cada vez que a tecla é liberada...
	// Impedimos entrada de letras...
	SomenteNumeroPontoBarra(campo);
	// Removemos vírgula e ponto da mácara, se tiver...
	var valor = limpar(campo.value, "0123456789");
	// Obtemos o tamanho somente dos números...
	var tamanho = valor.length;
	var pronto = "";

	switch (tamanho) {
	case 3:
		pronto = mascaraCampo(campo, "##.#");
		break;

	case 4:
		pronto = mascaraCampo(campo, "##.##");
		break;

	case 5:
		pronto = mascaraCampo(campo, "##.###");
		break;

	case 6:
		pronto = mascaraCampo(campo, "##.###.#");
		break;

	case 7:
		pronto = mascaraCampo(campo, "##.###.##");
		break;

	case 8:
		pronto = mascaraCampo(campo, "##.###.###");
		break;

	case 9:
		pronto = mascaraCampo(campo, "##.###.###/#");
		break;

	case 10:
		pronto = mascaraCampo(campo, "##.###.###/##");
		break;

	case 11:
		pronto = mascaraCampo(campo, "##.###.###/###");
		break;

	case 12:
		pronto = mascaraCampo(campo, "##.###.###/####");
		break;

	case 13:
		pronto = mascaraCampo(campo, "##.###.###/####-#");
		break;

	case 14:
		pronto = mascaraCampo(campo, "##.###.###/####-##");
		break;
	}
}

//Entrada de números e caracteres específicos apenas
function SomenteNumeroPontoBarra(campo) {
	var digits = "0123456789,/-"
	var campo_temp
	for (var i = 0; i < campo.length; i++) {
		campo_temp = campo.substring(i, i + 1)
		if (digits.indexOf(campo_temp) == -1) {
			campo = campo.substring(0, i);
			break;
		}
	}
}