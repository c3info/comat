package br.edu.ifpr.comat.controller;

import java.util.List;

import br.edu.ifpr.comat.model.ItenOrcamento;
import br.edu.ifpr.comat.model.ItenOrcamentoId;
import br.edu.ifpr.comat.persistence.dao.ItenOrcamentoDAO;

public class ItenOrcamentoController {

	public void saveItens(List<ItenOrcamento> produtos, int idOrc) {
		for (ItenOrcamento item : produtos) {
			saveItem(idOrc, item);
		}
	}

	public void updateItens(List<ItenOrcamento> produtos, int idOrc) {

		deleteItens(idOrc);

		for (ItenOrcamento item : produtos) {
			if (item.getId() == null) {
				saveItem(idOrc, item);
			} else {
				new ItenOrcamentoDAO().insert(item);
			}
		}
	}

	private void saveItem(int id, ItenOrcamento item) {
		item.setId(new ItenOrcamentoId(id, item.getProduto().getRefProduto()));
		item.setOrcamento(new OrcamentoController().search(id));

		new ItenOrcamentoDAO().insert(item);
	}

	public void deleteItens(int idOrc) {
		for (ItenOrcamento item : search(idOrc)) {
			new ItenOrcamentoDAO().delete(item);
		}
	}

	public List<ItenOrcamento> search(int idOrc) {
		return new ItenOrcamentoDAO().select(idOrc);
	}
}
