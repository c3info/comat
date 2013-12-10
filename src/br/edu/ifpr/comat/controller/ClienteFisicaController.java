package br.edu.ifpr.comat.controller;

import java.util.List;

import br.edu.ifpr.comat.model.ClienteFisica;
import br.edu.ifpr.comat.persistence.dao.ClienteFisicaDAO;

public class ClienteFisicaController {

	public void delete(String cpf) {
		new ClienteController().delete(new ClienteFisicaDAO().selectCpf(cpf)
				.getIdCliente());
	}

	public ClienteFisica search(int id) {
		return new ClienteFisicaDAO().select(id);
	}

	public List<ClienteFisica> search() {
		return new ClienteFisicaDAO().select();
	}

	public ClienteFisica searchCpf(String cpf) {
		return new ClienteFisicaDAO().selectCpf(cpf);
	}

	public List<ClienteFisica> searchStatus(int status) {
		return new ClienteFisicaDAO().selectStatus(status);
	}
}
