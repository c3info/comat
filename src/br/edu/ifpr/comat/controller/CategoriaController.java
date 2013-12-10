package br.edu.ifpr.comat.controller;

import java.util.List;

import br.edu.ifpr.comat.model.Categoria;
import br.edu.ifpr.comat.persistence.dao.CategoriaDAO;

public class CategoriaController {

	public String[] getCategoriasVetString() {

		List<Categoria> categorias = new CategoriaDAO().select();
		String[] valores = new String[categorias.size()];

		int cont = 0;
		for (Categoria categoria : categorias) {
			valores[cont++] = categoria.getNomeCategoria();
		}
		return valores;

	}

	public List<Categoria> search() {
		return new CategoriaDAO().select();
	}

	public Categoria search(int id) {
		return new CategoriaDAO().select(id);
	}

	public Categoria search(String cat) {
		return new CategoriaDAO().select(cat);
	}

	public void save(String nome) {
		new CategoriaDAO().insert(new Categoria(nome));
	}

	public void alter(int id, String nome) {
		Categoria c = new CategoriaDAO().select(id);
		c.setNomeCategoria(nome);
		new CategoriaDAO().update(c);
	}

	public void delete(int id) {
		new CategoriaDAO().delete(id);
	}
}
