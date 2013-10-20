package br.edu.ifpr.comat.controller;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.swing.ListModel;

import br.edu.ifpr.comat.dao.ClienteDAO;
import br.edu.ifpr.comat.dao.EstadoDAO;
import br.edu.ifpr.comat.model.Cliente;
import br.edu.ifpr.comat.model.ClienteFisica;
import br.edu.ifpr.comat.model.ClienteJuridica;
import br.edu.ifpr.comat.model.Endereco;
import br.edu.ifpr.comat.model.Estado;

public class ClienteController {
	private final String[] statusList = { "Ativo", "Inativo", "Bloquado" };

	public String[] getStatusList() {
		return statusList;
	}

	public String[] getEstados() {
		List<Estado> estados = new EstadoDAO().select();
		String[] valores = new String[estados.size()];

		int cont = 0;
		for (Estado estado : estados) {
			valores[cont++] = estado.getUf();
		}
		return valores;
	}

	public void salvar(long cpf, long rg, String nome, Date dataNasc,
			String celular, Integer idCliente, Integer status, String email,
			String site, String telefone, String observacoes,
			Date dataCadastro, Endereco endereco, Set contatos) {
		new ClienteDAO().insert(new ClienteFisica(cpf, rg, nome, dataNasc,
				celular, idCliente, status, email, site, telefone, observacoes,
				dataCadastro, endereco, contatos));
	}

	public void salvar(long cnpj, long inscricao, String razao,
			String fantasia, String fax, Integer idCliente, Integer status,
			String email, String site, String telefone, String observacoes,
			Date dataCadastro, Endereco endereco, Set contatos) {
		new ClienteDAO().insert(new ClienteJuridica(cnpj, inscricao, razao,
				fantasia, fax, idCliente, status, email, site, telefone,
				observacoes, dataCadastro, endereco, contatos));

	}	
}
