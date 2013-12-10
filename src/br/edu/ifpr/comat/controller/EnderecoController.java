package br.edu.ifpr.comat.controller;

import br.edu.ifpr.comat.model.Endereco;
import br.edu.ifpr.comat.persistence.dao.EnderecoDAO;

public class EnderecoController {

	public Endereco search(int id) {
		return new EnderecoDAO().select(id);
	}

	public void delete(int id) {
		new EnderecoDAO().delete(id);
	}

}
