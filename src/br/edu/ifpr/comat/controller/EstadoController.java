package br.edu.ifpr.comat.controller;

import java.util.List;

import br.edu.ifpr.comat.model.Estado;
import br.edu.ifpr.comat.persistence.dao.EstadoDAO;

public class EstadoController {

	public String[] getEstadosStringVet() {
		List<Estado> estados = new EstadoDAO().select();
		String[] valores = new String[estados.size()];

		int cont = 0;
		for (Estado estado : estados) {
			valores[cont++] = estado.getUf();
		}
		return valores;
	}

}
