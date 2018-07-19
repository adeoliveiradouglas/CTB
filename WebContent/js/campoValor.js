/*
 * Código retirado do site http://forum.wmonline.com.br/topic/125538-formatar-dados-em-real-r/ acesso em 17/07/2018 
 */
function MascaraMoeda(campo){//Cada vez que a tecla é liberada...
    //Impedimos entrada de letras...
    SomenteNumeroPontoVirgula(campo);
    //Removemos vírgula e ponto da mácara, se tiver...
    var valor = limpar(campo.value,"0123456789");
    //Obtemos o tamanho somente dos números...
    var tamanho    = valor.length;

    if(tamanho == 3){
        mascaraCampo(campo,'#,##');
    }

    if(tamanho == 4){
        mascaraCampo(campo,'##,##');
    }

    if(tamanho == 5){
        mascaraCampo(campo,'###,##');
    }

    if(tamanho == 6){
        mascaraCampo(campo,'#.###,##');
    }

    if(tamanho == 7){
        mascaraCampo(campo,'##.###,##');
    }

    if(tamanho == 8){
        mascaraCampo(campo,'###.###,##');
    }
    
    if(tamanho == 9){
        mascaraCampo(campo,'#.###.###,##');
    }
    
    if(tamanho == 10){
        mascaraCampo(campo,'##.###.###,##');
    }
    
    if(tamanho == 11){
        mascaraCampo(campo,'###.###.###,##');
    }
}

//Entrada de números e caracteres específicos apenas
function SomenteNumeroPontoVirgula(campo){
    var digits="0123456789,."
    var campo_temp
    for (var i=0;i<campo.value.length;i++){
        campo_temp=campo.value.substring(i,i+1)
        if (digits.indexOf(campo_temp)==-1){
            campo.value = campo.value.substring(0,i);
            break;
        }
    }
}

// Limpa um string
function limpar(valor,conjuntoVerdade) {
    var resultado = "";
    for( var a = 0; a < valor.length ; a++ ) {
        if ( conjuntoVerdade.indexOf(valor.substring(a,a+1)) >= 0 ) {
            resultado += valor.substring(a,a+1);
        }
    }
    return resultado;
}

// Formatação de mascara
function mascaraCampo(campo,formatoMascara) {
    var valorLimpo = limpar(campo.value,"0123456789");
    var resultado = "";
    var b = 0;
    for( var a = 0; a < formatoMascara.length ; a++ ) {
        if ( formatoMascara.substring(a,a+1) == "#" ) {
            resultado = resultado + valorLimpo.substring(b,b+1);
            b++;
            if ( b >= valorLimpo.length ) {
                break;
            }
        } else {
            resultado = resultado + formatoMascara.substring(a,a+1);
        }
    }
    campo.value = resultado;
}

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