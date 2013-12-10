package br.edu.ifpr.comat.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.edu.ifpr.comat.model.Orcamento;

/**
 * @project comat
 * @class OrcamentoDAO
 * @author Cristhiano Konczak Cardoso <cristhiano@c3info.com.br>
 * @date 20/11/2013
 */
public class OrcamentoDAO extends BaseDAO {

	private final Session session;
	private Transaction trns;

	public OrcamentoDAO() {
		this.trns = null;
		session = getConnection();
	}

	public int insert(Orcamento orc) {
		try {
			trns = session.beginTransaction();
			session.save(orc);
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
		return orc.getIdOrcamento();
	}

	public void delete(Integer id) {
		try {
			trns = session.beginTransaction();
			Orcamento orc = (Orcamento) session.load(Orcamento.class, new Integer(id));
			session.delete(orc);
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

	public void update(Orcamento orc) {
		try {
			trns = session.beginTransaction();
			session.update(orc);
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

	public List<Orcamento> select() {
		List<Orcamento> orc = new ArrayList<>();
		try {
			trns = session.beginTransaction();
			orc = session.createQuery("from Orcamento ORDER BY idOrcamento DESC").list();
		} catch (HibernateException hi) {
			if (trns != null) {
				trns.rollback();
			}
			hi.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return orc;
	}
	
	public List<Orcamento> select(int status) {
		List<Orcamento> orc = new ArrayList<>();
		try {							
			trns = session.beginTransaction();
			Query query = session.createQuery("from Orcamento where status = :st ORDER BY idOrcamento DESC");
			query.setInteger("st", status);
			orc = query.list();
			
		} catch (HibernateException hi) {
			if (trns != null) {
				trns.rollback();
			}
			hi.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return orc;
	}

	public Orcamento select(Integer id) {
		Orcamento orc = null;
		try {
			trns = session.beginTransaction();
			String queryString = "from Orcamento where idOrcamento = :id";
			Query query = session.createQuery(queryString);
			query.setInteger("id", id);
			orc = (Orcamento) query.uniqueResult();
		} catch (HibernateException hi) {
			if (trns != null) {
				trns.rollback();
			}
			hi.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return orc;
	}
	
	public int checkByCliente(int id) {
		int res = 0;
		try {
			trns = session.beginTransaction();
			String queryString = "select count(*) from Orcamento where idClienteFk = :id";
			Query query = session.createQuery(queryString);
			query.setInteger("id", id);
			res = ((Number) query.uniqueResult()).intValue();
		} catch (HibernateException hi) {
			if (trns != null) {
				trns.rollback();
			}
			hi.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return res;
	}
	
	public int checkByObra(int id) {
		int res = 0;
		try {
			trns = session.beginTransaction();
			String queryString = "select count(*) from Orcamento where idObraFk = :id";
			Query query = session.createQuery(queryString);
			query.setInteger("id", id);
			res = ((Number) query.uniqueResult()).intValue();
		} catch (HibernateException hi) {
			if (trns != null) {
				trns.rollback();
			}
			hi.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return res;
	}
}
