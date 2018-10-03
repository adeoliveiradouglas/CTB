package utilidades;

import java.util.Comparator;

import entity.Usuario;

public class ComparadorUsuario implements Comparator<Usuario> {

	@Override
	public int compare(Usuario usuario1, Usuario usuario2) {
		return usuario1.getNome().compareTo(usuario2.getNome());
	}

}
