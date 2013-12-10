package br.edu.ifpr.comat.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import br.edu.ifpr.comat.model.Cliente;
import br.edu.ifpr.comat.model.ItenOrcamento;
import br.edu.ifpr.comat.model.Orcamento;
import br.edu.ifpr.comat.persistence.dao.OrcamentoDAO;

public class OrcamentoController {

	private static final String[] STATUS = { "Aberto", "Fechado", "Cancelado" };

	public String[] getStatusList() {
		return STATUS;
	}

	private int recStatus(String status) {
		int i = 0;
		for (String s : STATUS) {
			if (s.equals(status))
				break;
			i++;
		}
		return i;
	}

	public int save(Date data, String status, Date validade,
			String observacoes, BigDecimal total, int cliente, Integer obra,
			List<ItenOrcamento> produtos) {

		int sts = recStatus(status);
		int idOrc;

		if (obra == null) {
			idOrc = new OrcamentoDAO().insert(new Orcamento(data, sts,
					validade, observacoes, total, new ClienteController()
							.search(cliente), null));

		} else {
			idOrc = new OrcamentoDAO()
					.insert(new Orcamento(data, sts, validade, observacoes,
							total, new ClienteController().search(cliente),
							new ObraController().search(obra)));
		}

		new ItenOrcamentoController().saveItens(produtos, idOrc);

		return idOrc;
	}

	public void alter(Integer idOrc, int status) {
		Orcamento or = new OrcamentoDAO().select(idOrc);
		or.setStatus(status);

		new OrcamentoDAO().update(or);
	}

	public void alter(Integer idOrc, String status, Date validade,
			String observacoes, BigDecimal total, int cliente, Integer obra,
			List<ItenOrcamento> produtos) {
		Orcamento or = new OrcamentoDAO().select(idOrc);

		int sts = recStatus(status);
		or.setStatus(sts);
		or.setValidade(validade);
		or.setObservacoes(observacoes);
		or.setTotal(total);
		or.setCliente(new ClienteController().search(cliente));

		if (obra != null) {
			or.setObra(new ObraController().search(obra));
		} else {
			or.setObra(null);
		}

		new OrcamentoDAO().update(or);
		new ItenOrcamentoController().updateItens(produtos, idOrc);

	}

	public void alter(Integer idOrc, String status, Date validade,
			String observacoes, BigDecimal total, int cliente, Integer obra) {
		Orcamento or = new OrcamentoDAO().select(idOrc);

		int sts = recStatus(status);
		or.setStatus(sts);
		or.setValidade(validade);
		or.setObservacoes(observacoes);
		or.setTotal(total);
		or.setCliente(new ClienteController().search(cliente));

		if (obra != null) {
			or.setObra(new ObraController().search(obra));
		} else {
			or.setObra(null);
		}

		new OrcamentoDAO().update(or);

	}

	public List<Orcamento> search() {
		return new OrcamentoDAO().select();
	}

	public Orcamento search(Integer id) {
		return new OrcamentoDAO().select(id);
	}

	public List<Orcamento> searchStatus(int status) {
		return new OrcamentoDAO().select(status);
	}

	public void delete(int id) {
		new OrcamentoDAO().delete(id);
	}

	public void gerarObra(int idOrc) {
		Orcamento o = new OrcamentoController().search(idOrc);
		Cliente c = new ClienteController().search(o.getCliente()
				.getIdCliente());

		int obra = new ObraController().save(c, "Obra "
				+ c.getEndereco().getLogradouro(), c.getTelefone(), "Mesmo",
				c.getEndereco());

		o.setObra(new ObraController().search(obra));

		new OrcamentoDAO().update(o);

	}
	
	public boolean checkDbByCliente(int id){		
		if (new OrcamentoDAO().checkByCliente(id) > 0) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public boolean checkDbByObra(int id){		
		if (new OrcamentoDAO().checkByObra(id) > 0) {
			return false;
		}
		else {
			return true;
		}
	}

}
