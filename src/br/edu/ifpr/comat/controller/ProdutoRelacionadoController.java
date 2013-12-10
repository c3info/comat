package br.edu.ifpr.comat.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.edu.ifpr.comat.model.Produto;
import br.edu.ifpr.comat.model.ProdutoRelacionado;
import br.edu.ifpr.comat.model.ProdutoRelacionadoId;
import br.edu.ifpr.comat.persistence.dao.ProdutoRelacionadoDAO;

public class ProdutoRelacionadoController {

	public List<ProdutoRelacionado> processaRelacionamento(
			boolean[] selecionados, List<Produto> lista) {

		List<ProdutoRelacionado> rels = new ArrayList<>();
		
		int i = 0;

		for (boolean b : selecionados) {				
			
			if (b) {
				ProdutoRelacionado p = new ProdutoRelacionado();
				p.setProdutoByRefProdutoRelacFk(lista.get(i));

				rels.add(p);
			}
			i++;
		}		
		
		
		return rels;
	}

	public void saveItens(List<ProdutoRelacionado> produtos, int ref) {
		for (ProdutoRelacionado produto : produtos) {
			saveItem(ref, produto);
		}

	}

	private void saveItem(int ref, ProdutoRelacionado produto) {
		produto.setId(new ProdutoRelacionadoId(ref, produto
				.getProdutoByRefProdutoRelacFk().getRefProduto()));
		produto.setProdutoByRefProdutoRelFk(new ProdutoController().search(ref));
		produto.setData(new Date());

		new ProdutoRelacionadoDAO().insert(produto);
	}

}
