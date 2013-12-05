package br.edu.ifpr.comat.controller;

import br.edu.ifpr.comat.dao.EnderecoDAO;
import br.edu.ifpr.comat.model.Endereco;

public class EnderecoController {

	public Endereco search(int id) {
		return new EnderecoDAO().select(id);
	}

	public void delete(int id) {
		new EnderecoDAO().delete(id);
	}

}
