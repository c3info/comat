package br.edu.ifpr.comat.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.edu.ifpr.comat.model.Estado;

/**
 * @project comat
 * @class EstadoDAO
 * @author Cristhiano Konczak Cardoso <cristhiano@c3info.com.br>
 * @date 26/09/2013
 */
public class EstadoDAO extends BaseDAO {

	private final Session session;
	private Transaction trns;

	public EstadoDAO() {
		this.trns = null;
		session = getConnection();
	}

	public void insert(Estado est) {
		try {
			trns = session.beginTransaction();
			session.save(est);
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

	public void delete(String uf) {
		try {
			trns = session.beginTransaction();
			Estado est = (Estado) session.load(Estado.class, uf);
			session.delete(est);
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

	public void update(Estado est) {
		try {
			trns = session.beginTransaction();
			session.update(est);
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

	public List<Estado> select() {
		List<Estado> estados = new ArrayList<>();
		try {
			trns = session.beginTransaction();
			estados = session.createQuery("from Estado").list();
		} catch (HibernateException hi) {
			if (trns != null) {
				trns.rollback();
			}
			hi.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return estados;
	}

	public Estado select(String uf) {
		Estado est = null;
		try {
			trns = session.beginTransaction();
			String queryString = "from Estado where uf = :uf";
			Query query = session.createQuery(queryString);
			query.setString("uf", uf);
			est = (Estado) query.uniqueResult();
		} catch (HibernateException hi) {
			if (trns != null) {
				trns.rollback();
			}
			hi.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return est;
	}
}
