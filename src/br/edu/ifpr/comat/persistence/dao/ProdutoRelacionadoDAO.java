package br.edu.ifpr.comat.persistence.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.edu.ifpr.comat.model.ProdutoRelacionado;

/**
 * @project comat
 * @class ItensOrcamentoDAO
 * @author Cristhiano Konczak Cardoso <cristhiano@c3info.com.br>
 * @date 20/11/2013
 */
public class ProdutoRelacionadoDAO extends BaseDAO {

	private final Session session;
	private Transaction trns;

	public ProdutoRelacionadoDAO() {
		this.trns = null;
		session = getConnection();
	}

	public void insert(ProdutoRelacionado pr) {
		try {
			trns = session.beginTransaction();
			session.save(pr);
			session.getTransaction().commit();
		} catch (HibernateException hi) {
			if (trns != null) {
				trns.rollback();
			}
			hi.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}		
	}	
	
}
