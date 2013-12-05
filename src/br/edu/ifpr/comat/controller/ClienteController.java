package br.edu.ifpr.comat.controller;

import java.util.Date;
import java.util.List;
import br.edu.ifpr.comat.dao.ClienteDAO;
import br.edu.ifpr.comat.model.Cidade;
import br.edu.ifpr.comat.model.Cliente;
import br.edu.ifpr.comat.model.ClienteFisica;
import br.edu.ifpr.comat.model.ClienteJuridica;
import br.edu.ifpr.comat.model.Contato;
import br.edu.ifpr.comat.model.Endereco;
import br.edu.ifpr.comat.model.Obra;

public class ClienteController {

	private static final String[] STATUS = { "Ativo", "Inativo", "Bloqueado" };

	public String[] getClienteStatusVetString() {
		return STATUS;
	}

	public int save(Date dataCadastro, int status, String nome, String cpf,
			String rg, Date dataNasc, String telefone, String email,
			String celular, String site, String cep, String endereco,
			int numero, String complemento, String bairro, String estado,
			String cidade, String observacoes) {

		return new ClienteDAO().insert(new ClienteFisica(cpf, rg, nome,
				dataNasc, celular, null, status, email, site, telefone,
				observacoes, dataCadastro, new Endereco(endereco, numero,
						complemento, bairro, cep, new CidadeController()
								.search(cidade))));
	}

	public int save(Date dataCadastro, int status, String razao,
			String fantasia, String cnpj, String inscricao, String fax,
			String email, String site, String telefone, String cep,
			String endereco, int numero, String complemento, String bairro,
			String estado, String cidade, String observacoes) {

		return new ClienteDAO().insert(new ClienteJuridica(cnpj, inscricao,
				razao, fantasia, fax, null, status, email, site, telefone,
				observacoes, dataCadastro, new Endereco(endereco, numero,
						complemento, bairro, cep, new CidadeController()
								.search(cidade))));

	}

	public void alter(Date dataCadastro, int status, String nome, String cpf,
			String rg, Date dataNasc, String telefone, String email,
			String celular, String site, String cep, String endereco,
			int numero, String complemento, String bairro, String estado,
			String cidade, String observacoes) {

		ClienteFisica cl = new ClienteFisicaController().searchCpf(cpf);
		Endereco en = new EnderecoController().search(cl.getEndereco()
				.getIdEndereco());
		Cidade ct = new CidadeController().search(cidade);

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

	public void alter(Date dataCadastro, int status, String razao,
			String fantasia, String cnpj, String inscricao, String fax,
			String email, String site, String telefone, String cep,
			String endereco, int numero, String complemento, String bairro,
			String estado, String cidade, String observacoes) {

		ClienteJuridica cl = new ClienteJuridicaController().searchCnpj(cnpj);
		Endereco en = new EnderecoController().search(cl.getEndereco()
				.getIdEndereco());
		Cidade ct = new CidadeController().search(cidade);

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

	public Cliente search(int id) {
		return new ClienteDAO().select(id);
	}

	public void delete(int id) {
		int idEndereco = new ClienteDAO().select(id).getEndereco()
				.getIdEndereco();
		
		List<Contato> contatos = new ContatoController().searchCliente(id);

		for (Contato contato : contatos) {
			new ContatoController().delete(contato.getIdContato());
		}
		
		List<Obra> obras = new ObraController().searchCliente(id);
		for (Obra obra : obras) {
			new ObraController().delete(obra.getIdObra());
		}

		new ClienteDAO().delete(id);
		new EnderecoController().delete(idEndereco);
	}

}
