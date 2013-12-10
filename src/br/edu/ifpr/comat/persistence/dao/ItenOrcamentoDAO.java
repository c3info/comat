package br.edu.ifpr.comat.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.edu.ifpr.comat.model.ItenOrcamento;

/**
 * @project comat
 * @class ItensOrcamentoDAO
 * @author Cristhiano Konczak Cardoso <cristhiano@c3info.com.br>
 * @date 20/11/2013
 */
public class ItenOrcamentoDAO extends BaseDAO {

	private final Session session;
	private Transaction trns;

	public ItenOrcamentoDAO() {
		this.trns = null;
		session = getConnection();
	}

	public void insert(ItenOrcamento it) {
		try {
			trns = session.beginTransaction();
			session.save(it);
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
	
	public void delete(ItenOrcamento it) {
		try {
			trns = session.beginTransaction();			
			session.delete(it);
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
	
	public void update(ItenOrcamento it) {
		try {
			trns = session.beginTransaction();
			session.update(it);
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
		
	public ItenOrcamento select(int idOrc, int refProduto) {
		ItenOrcamento it = null;
		try {
			trns = session.beginTransaction();
			String queryString = "from ItenOrcamento where idOrcamentoFk = :id and refProdutoFk = :ref";
			Query query = session.createQuery(queryString);
			query.setInteger("id", idOrc);
			query.setInteger("ref", refProduto);
			it = (ItenOrcamento) query.uniqueResult();
		} catch (HibernateException hi) {
			if (trns != null) {
				trns.rollback();
			}
			hi.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return it;
	}
	
	public List<ItenOrcamento> select(int idOrc) {
		List<ItenOrcamento> its = new ArrayList<>();
		try {
			trns = session.beginTransaction();
			Query query = session.createQuery("from ItenOrcamento where idOrcamentoFk = :id");
			query.setInteger("id", idOrc);
			its = query.list();
		} catch (HibernateException hi) {
			if (trns != null) {
				trns.rollback();
			}
			hi.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return its;
	}	
}
