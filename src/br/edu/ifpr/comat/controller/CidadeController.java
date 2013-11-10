package br.edu.ifpr.comat.controller;

import java.util.List;

import br.edu.ifpr.comat.dao.CidadeDAO;
import br.edu.ifpr.comat.model.Cidade;

public class CidadeController {

	public String[] getCidadesPorEstado(String estado) {
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

}
