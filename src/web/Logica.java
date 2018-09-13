package web;

import javax.servlet.http.HttpServletRequest;

public interface Logica {
	String executa(HttpServletRequest pedido) throws Exception;
}
