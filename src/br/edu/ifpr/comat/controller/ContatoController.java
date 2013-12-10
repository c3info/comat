package br.edu.ifpr.comat.controller;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpr.comat.model.Contato;
import br.edu.ifpr.comat.persistence.dao.ContatoDAO;

public class ContatoController {

	public List<Contato> searchCliente(Integer idCliente) {
		if (idCliente != null) {
			return new ContatoDAO().selectCliente(idCliente);
		} else {
			return new ArrayList<Contato>();
		}
	}

	public void save(int idCli, String nome, String telefone, String celular,
			String email, String funcao) {
		new ContatoDAO().insert(new Contato(nome, telefone, celular, email,
				funcao, new ClienteController().search(idCli)));

	}

	public void alter(int idCon, String nome, String telefone, String celular,
			String email, String funcao) {

		Contato ct = new ContatoDAO().select(idCon);

		ct.setNome(nome);
		ct.setTelefone(telefone);
		ct.setCelular(celular);
		ct.setEmail(email);
		ct.setFuncao(funcao);

		new ContatoDAO().update(ct);

	}

	public Contato search(int id) {
		return new ContatoDAO().select(id);
	}

	public void delete(int id) {
		new ContatoDAO().delete(id);
	}

}
