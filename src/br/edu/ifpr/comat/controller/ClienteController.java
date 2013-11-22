package br.edu.ifpr.comat.controller;

import java.util.Date;
import java.util.Set;

import br.edu.ifpr.comat.dao.CidadeDAO;
import br.edu.ifpr.comat.dao.ClienteDAO;
import br.edu.ifpr.comat.dao.ClienteFisicaDAO;
import br.edu.ifpr.comat.dao.ClienteJuridicaDAO;
import br.edu.ifpr.comat.dao.EnderecoDAO;
import br.edu.ifpr.comat.model.Cidade;
import br.edu.ifpr.comat.model.Cliente;
import br.edu.ifpr.comat.model.ClienteFisica;
import br.edu.ifpr.comat.model.ClienteJuridica;
import br.edu.ifpr.comat.model.Contato;
import br.edu.ifpr.comat.model.Endereco;

public class ClienteController {	

	private static final String[] STATUS = { "Ativo", "Inativo", "Bloqueado" };

	public String[] getStatusList() {
		return STATUS;				
	}

	public int save(Date dataCadastro, 
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
					   String observacoes) {		
					
		return new ClienteDAO().insert(new ClienteFisica(cpf, rg, nome, dataNasc,
				celular, null, status, email, site, telefone, observacoes,
				dataCadastro, new Endereco(null, endereco, numero, complemento, bairro, cep, new CidadeDAO().select(cidade))));	    
	}

	public int save(Date dataCadastro, 
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
					   String observacoes) {

		return new ClienteDAO().insert(new ClienteJuridica(cnpj, inscricao, razao,
				fantasia, fax, null, status, email, site, telefone,
				observacoes, dataCadastro, new Endereco(null, endereco, numero, complemento, bairro, cep, new CidadeDAO().select(cidade))));
		
	}
	
	public void alter(Date dataCadastro, 
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
					   Set<Contato> contatos) {	
		
		ClienteFisica cl = new ClienteFisicaDAO().selectCpf(cpf);
		Endereco en = new EnderecoDAO().select(cl.getEndereco().getIdEndereco());
		Cidade ct = new CidadeDAO().select(cidade);
				
		cl.setStatus(status);
		cl.setNome(nome);		
		cl.setRg(rg);
		cl.setDataNasc(dataNasc);
		cl.setTelefone(telefone);
		cl.setEmail(email);
		cl.setCelular(celular);
		cl.setSite(site);
		
		en.setCep(cep);
		en.setLogradouro(endereco);
		en.setNumero(numero);
		en.setComplemento(complemento);
		en.setBairro(bairro);
		en.setCidade(ct);
		
		cl.setEndereco(en);
	
		new ClienteDAO().update(cl);					
	}

	public void alter(Date dataCadastro, 
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
					   Set<Contato> contatos) {
		
		ClienteJuridica cl = new ClienteJuridicaDAO().selectCnpj(cnpj);
		Endereco en = new EnderecoDAO().select(cl.getEndereco().getIdEndereco());
		Cidade ct = new CidadeDAO().select(cidade);
				
		cl.setStatus(status);
		cl.setRazao(razao);	
		cl.setFantasia(fantasia);		
		cl.setInscricao(inscricao);
		cl.setFax(fax);
		cl.setEmail(email);
		cl.setSite(site);
		cl.setTelefone(telefone);		
		
		en.setCep(cep);
		en.setLogradouro(endereco);
		en.setNumero(numero);
		en.setComplemento(complemento);
		en.setBairro(bairro);
		en.setCidade(ct);
		
		cl.setEndereco(en);
	
		new ClienteDAO().update(cl);	
	}	
	
	public Cliente search(int id){
		return new ClienteDAO().select(id);		
	}	
	
	public void delete(int id){
		int idEndereco = new ClienteDAO().select(id).getEndereco().getIdEndereco();
		new ClienteDAO().delete(id);
		new EnderecoDAO().delete(idEndereco);		
	}
	
	
	
}
