package br.edu.ifpr.comat.controller;

import java.util.List;

import br.edu.ifpr.comat.model.Cidade;
import br.edu.ifpr.comat.persistence.dao.CidadeDAO;

public class CidadeController {

	public String[] getCidadesVetString(String estado) {

		List<Cidade> cidades = new CidadeDAO().selectPorEstado(estado);
		String[] valores = new String[cidades.size()];

		int cont = 0;
		for (Cidade cidade : cidades) {
			valores[cont++] = cidade.getNome();
		}
		return valores;

	}

	public List<Cidade> getCidadesDoEstado(String estado) {
		List<Cidade> cidades = new CidadeDAO().selectPorEstado(estado);
		return cidades;
	}

	public Cidade search(String cidade) {
		return new CidadeDAO().select(cidade);
	}

}
