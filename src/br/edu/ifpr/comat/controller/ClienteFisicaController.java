package br.edu.ifpr.comat.controller;

import java.util.List;

import br.edu.ifpr.comat.dao.ClienteFisicaDAO;
import br.edu.ifpr.comat.model.ClienteFisica;

public class ClienteFisicaController {
	
	public void delete(String cpf) {		
		new ClienteController().delete(new ClienteFisicaDAO().selectCpf(cpf).getIdCliente());
	}

	public ClienteFisica search(int id) {
		return new ClienteFisicaDAO().select(id);
	}	
	
	public List<ClienteFisica> search(){
		return new ClienteFisicaDAO().select();		
	}	
	
	public List<ClienteFisica> searchStatus(int status){
		return new ClienteFisicaDAO().selectStatus(status);		
	}
}
