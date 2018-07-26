/*
 * Código retirado do site http://forum.wmonline.com.br/topic/125538-formatar-dados-em-real-r/ acesso em 17/07/2018 
 */
function MascaraMoeda(campo) {//Cada vez que a tecla é liberada...
	//Impedimos entrada de letras...
	SomenteNumeroPontoVirgula(campo.value);
	//Removemos vírgula e ponto da mácara, se tiver...
	var valor = limpar(campo.value, "0123456789");
	//Obtemos o tamanho somente dos números...
	var tamanho = valor.length;

	switch (tamanho) {
	case 3:
		mascaraCampo(campo, '#,##');
		break;
	case 4:
		mascaraCampo(campo, '##,##');
		break;
	case 5:
		mascaraCampo(campo, '###,##');
		break;
	case 6:
		mascaraCampo(campo, '#.###,##');
		break;
	case 7:
		mascaraCampo(campo, '##.###,##');
		break;
	case 8:
		mascaraCampo(campo, '###.###,##');
		break;
	case 9:
		mascaraCampo(campo, '#.###.###,##');
		break;
	case 10:
		mascaraCampo(campo, '##.###.###,##');
		break;
	case 11:
		mascaraCampo(campo, '###.###.###,##');
		break;
	}
}

//Entrada de números e caracteres específicos apenas
function SomenteNumeroPontoVirgula(campo) {
	var digits = "0123456789,."
	var campo_temp
	for (var i = 0; i < campo.length; i++) {
		campo_temp = campo.substring(i, i + 1)
		if (digits.indexOf(campo_temp) == -1) {
			campo = campo.substring(0, i);
			break;
		}
	}
}

// Limpa um string
function limpar(valor, conjuntoVerdade) {
	var resultado = "";
	for (var a = 0; a < valor.length; a++) {
		if (conjuntoVerdade.indexOf(valor.substring(a, a + 1)) >= 0) {
			resultado += valor.substring(a, a + 1);
		}
	}
	return resultado;
}

// Formatação de mascara
function mascaraCampo(campo, formatoMascara) {
	var valorLimpo = limpar(campo.value, "0123456789");
	var resultado = "";
	var b = 0;
	for (var a = 0; a < formatoMascara.length; a++) {
		if (formatoMascara.substring(a, a + 1) == "#") {
			resultado = resultado + valorLimpo.substring(b, b + 1);
			b++;
			if (b >= valorLimpo.length) {
				break;
			}
		} else {
			resultado = resultado + formatoMascara.substring(a, a + 1);
		}
	}
	campo.value = resultado;
}
