package br.edu.ifpr.comat.controller;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpr.comat.dao.CidadeDAO;
import br.edu.ifpr.comat.dao.EnderecoDAO;
import br.edu.ifpr.comat.dao.ObraDAO;
import br.edu.ifpr.comat.model.Cidade;
import br.edu.ifpr.comat.model.Endereco;
import br.edu.ifpr.comat.model.Obra;

public class ObraController {

	public List<Obra> searchCliente(Integer idCliente) {
		if (idCliente != null) {
			return new ObraDAO().selectCliente(idCliente);
		} else {
			return new ArrayList<Obra>();
		}
	}

	public void save(int idCliente, 
						String nome, 
						String telefone,
						String responsavel, 
						String cep, 
						String endereco, 
						int numero,
						String complemento, 
						String bairro, 
						String estado, 
						String cidade) {

		new ObraDAO().insert(new Obra(null, nome, telefone, responsavel,
				new ClienteController().search(idCliente), new Endereco(null,
						endereco, numero, complemento, bairro, cep,
						new CidadeDAO().select(cidade))));

	}
	
	public void alter (int idObra,
			String nome, 
			String telefone,
			String responsavel, 
			String cep, 
			String endereco, 
			int numero,
			String complemento, 
			String bairro, 
			String estado, 
			String cidade) {
		
		Obra ob = new ObraDAO().select(idObra);
		Endereco en = new EnderecoDAO().select(ob.getEndereco().getIdEndereco());
		Cidade ct = new CidadeDAO().select(cidade);
		
		ob.setNome(nome);
		ob.setTelefone(telefone);
		ob.setResponsavel(responsavel);
		
		en.setCep(cep);
		en.setLogradouro(endereco);
		en.setNumero(numero);
		en.setComplemento(complemento);
		en.setBairro(bairro);
		en.setCidade(ct);
		
		ob.setEndereco(en);
		
		new ObraDAO().update(ob);
	}
	
	public Obra search(int id){
		return new ObraDAO().select(id);
	}

	public void delete(int id){
		int idEndereco = new ObraDAO().select(id).getEndereco().getIdEndereco();
		new ObraDAO().delete(id);		
		new EnderecoDAO().delete(idEndereco);		
	}
}
