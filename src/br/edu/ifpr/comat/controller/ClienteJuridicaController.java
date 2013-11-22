package br.edu.ifpr.comat.controller;

import java.util.List;

import br.edu.ifpr.comat.dao.ClienteJuridicaDAO;
import br.edu.ifpr.comat.model.ClienteJuridica;

public class ClienteJuridicaController {
	
	public void delete(String cnpj) {
		new ClienteController().delete(new ClienteJuridicaDAO().selectCnpj(cnpj).getIdCliente());		
	}

	public ClienteJuridica search(int id) {
		return new ClienteJuridicaDAO().select(id);
	}	
	
	public List<ClienteJuridica> search(){
		return new ClienteJuridicaDAO().select();		
	}
	
	public List<ClienteJuridica> searchStatus(int status){
		return new ClienteJuridicaDAO().selectStatus(status);		
	}
}
