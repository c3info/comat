package br.edu.ifpr.comat.controller;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.swing.ListModel;

import br.edu.ifpr.comat.dao.CidadeDAO;
import br.edu.ifpr.comat.dao.ClienteDAO;
import br.edu.ifpr.comat.dao.EnderecoDAO;
import br.edu.ifpr.comat.dao.EstadoDAO;
import br.edu.ifpr.comat.model.Cidade;
import br.edu.ifpr.comat.model.Cliente;
import br.edu.ifpr.comat.model.ClienteFisica;
import br.edu.ifpr.comat.model.ClienteJuridica;
import br.edu.ifpr.comat.model.Endereco;
import br.edu.ifpr.comat.model.Estado;

public class ClienteController {
	private static final String[] statusList = { "Ativo", "Inativo", "Bloquado" };

	public String[] getStatusList() {
		return statusList;
	}

	public void salvar(Date dataCadastro, 
					   int status, 
					   String nome,
					   String cpf, 
					   String rg, 
					   Date dataNasc, 
					   String telefone,					   
					   String email, 
					   String celular, 
					   String site, 
					   String cep,
					   String endereco, 
					   int numero, 
					   String complemento, 
					   String bairro,
					   String estado, 
					   String cidade, 
					   String observacoes, 
					   Set contatos) {		
					
		new ClienteDAO().insert(new ClienteFisica(cpf, rg, nome, dataNasc,
				celular, null, status, email, site, telefone, observacoes,
				dataCadastro, new Endereco(null, endereco, numero, complemento, bairro, cep, new CidadeDAO().select(cidade)), null));	    
	}

	public void salvar(Date dataCadastro, 
					   int status,
					   String razao,
					   String fantasia,
					   String cnpj, 
					   String inscricao, 					    
					   String fax,
					   String email, 
					   String site, 
					   String telefone,
					   String cep,
					   String endereco, 
					   int numero, 
					   String complemento, 
					   String bairro,
					   String estado, 
					   String cidade, 
					   String observacoes,
					   Set contatos) {

		new ClienteDAO().insert(new ClienteJuridica(cnpj, inscricao, razao,
				fantasia, fax, null, status, email, site, telefone,
				observacoes, dataCadastro, new Endereco(null, endereco, numero, complemento, bairro, cep, new CidadeDAO().select(cidade)), null));
		
	}
}
