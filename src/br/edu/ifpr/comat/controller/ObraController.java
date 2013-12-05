package br.edu.ifpr.comat.controller;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpr.comat.dao.ObraDAO;
import br.edu.ifpr.comat.model.Cidade;
import br.edu.ifpr.comat.model.Cliente;
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
	
	public int save(Cliente cliente, String nome, String telefone, String responsavel, Endereco endereco){		
		return new ObraDAO().insert(new Obra(nome, telefone, responsavel, cliente, endereco));
	}

	public int save(int idCliente, String nome, String telefone,
			String responsavel, String cep, String endereco, int numero,
			String complemento, String bairro, String estado, String cidade) {

		return new ObraDAO().insert(new Obra(nome, telefone, responsavel,
				new ClienteController().search(idCliente), new Endereco(
						endereco, numero, complemento, bairro, cep,
						new CidadeController().search(cidade))));

	}

	public void alter(int idObra, String nome, String telefone,
			String responsavel, String cep, String endereco, int numero,
			String complemento, String bairro, String estado, String cidade) {

		Obra ob = new ObraDAO().select(idObra);
		Endereco en = new EnderecoController().search(ob.getEndereco()
				.getIdEndereco());
		Cidade ct = new CidadeController().search(cidade);

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

	public Obra search(int id) {
		return new ObraDAO().select(id);
	}

	public void delete(int id) {
		int idEndereco = new ObraDAO().select(id).getEndereco().getIdEndereco();
		new ObraDAO().delete(id);
		new EnderecoController().delete(idEndereco);
	}
}
