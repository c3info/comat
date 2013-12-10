package br.edu.ifpr.comat.controller;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpr.comat.model.Produto;
import br.edu.ifpr.comat.model.ProdutoRelacionado;
import br.edu.ifpr.comat.persistence.dao.ProdutoDAO;

public class ProdutoController {

	private static final String[] STATUS = { "Disponível", "Indisponível", "Fora de linha" };
	private static final String[] UNDS = { "KG", "M2", "M3", "PC", "SC", "MT", "LT"};

	public String[] getStatusList() {
		return STATUS;
	}

	public String[] getUnidadesList() {
		return UNDS;
	}

	public void save(Integer refProduto, String codBarra, String codFabricante,
			String nome, String descricao, String unidade, Double precoCusto,
			Double precoVenda, Double descontoMax, Integer quantidade,
			int status, String marca, Double peso, String categoria) {

		new ProdutoDAO().insert(new Produto(refProduto, codBarra,
				codFabricante, nome, descricao, unidade, precoCusto,
				precoVenda, descontoMax, quantidade, status, marca, peso,
				new CategoriaController().search(categoria)));

	}

	public void alter(Integer refProduto, String codBarra,
			String codFabricante, String nome, String descricao,
			String unidade, Double precoCusto, Double precoVenda,
			Double descontoMax, Integer quantidade, int status, String marca,
			Double peso, String categoria, List<ProdutoRelacionado> relacoes) {

		Produto p = new ProdutoDAO().select(refProduto);

		p.setCodBarra(codBarra);
		p.setCodFabricante(codFabricante);
		p.setNome(nome);
		p.setDescricao(descricao);
		p.setUnidade(unidade);
		p.setPrecoVenda(precoVenda);
		p.setPrecoCusto(precoCusto);
		p.setDescontoMax(descontoMax);
		p.setQuantidade(quantidade);
		p.setStatus(status);
		p.setMarca(marca);
		p.setPeso(peso);

		p.setCategoria(new CategoriaController().search(categoria));

		new ProdutoDAO().update(p);
		new ProdutoRelacionadoController().saveItens(relacoes, refProduto);
	}

	public List<Produto> search() {
		return new ProdutoDAO().select();
	}

	public List<Produto> searchRel() {
		return new ArrayList<Produto>();
	}

	public Produto search(int ref) {
		return new ProdutoDAO().select(ref);
	}

	public List<Produto> searchStatus(String sts) {
		int status = 0;
		for (String s : STATUS) {
			if (s.equals(sts))
				break;
			status++;
		}
		return new ProdutoDAO().selectStatus(status);
	}

	public List<Produto> searchCategoria(String cat) {
		return new ProdutoDAO().selectCategoria(new CategoriaController()
				.search(cat).getIdCategoria());
	}

	public List<Produto> searchStatusCategoria(String sts, String cat) {

		int status = 0;
		for (String s : STATUS) {
			if (s.equals(sts))
				break;
			status++;
		}

		return new ProdutoDAO().selectStatusCategoria(status,
				new CategoriaController().search(cat).getIdCategoria());
	}

	public void delete(int id) {
		new ProdutoDAO().delete(id);
	}
	
	public boolean checkDbByCategoria(int id){		
		if (new ProdutoDAO().checkByCategoria(id) > 0) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public boolean checkUsedId(int id){		
		if (new ProdutoDAO().checkUsed(id) > 0) {
			return false;
		}
		else {
			return true;
		}
	}

}
