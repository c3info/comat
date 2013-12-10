package br.edu.ifpr.comat.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.edu.ifpr.comat.model.Obra;

/**
 * @project comat
 * @class ObraDAO
 * @author Cristhiano Konczak Cardoso <cristhiano@c3info.com.br>
 * @date 20/11/2013
 */
public class ObraDAO extends BaseDAO {

	private final Session session;
	private Transaction trns;

	public ObraDAO() {
		this.trns = null;
		session = getConnection();
	}

	public int insert(Obra obra) {
		try {
			trns = session.beginTransaction();
			session.save(obra);
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
		return obra.getIdObra();
	}

	public void delete(Integer id) {
		try {
			trns = session.beginTransaction();
			Obra obra = (Obra) session.load(Obra.class, new Integer(id));
			session.delete(obra);
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

	public void update(Obra obra) {
		try {
			trns = session.beginTransaction();
			session.update(obra);
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

	public List<Obra> select() {
		List<Obra> obras = new ArrayList<>();
		try {
			trns = session.beginTransaction();
			obras = session.createQuery("from Obra").list();
		} catch (HibernateException hi) {
			if (trns != null) {
				trns.rollback();
			}
			hi.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return obras;
	}

	public List<Obra> selectCliente(int idCliente) {
		List<Obra> obras = new ArrayList<>();
		try {
			trns = session.beginTransaction();
			Query query = session
					.createQuery("from Obra where idClienteFk = :id");
			query.setInteger("id", idCliente);
			obras = query.list();

		} catch (HibernateException hi) {
			if (trns != null) {
				trns.rollback();
			}
			hi.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return obras;
	}

	public Obra select(Integer id) {
		Obra obra = null;
		try {
			trns = session.beginTransaction();
			String queryString = "from Obra where idObra = :id";
			Query query = session.createQuery(queryString);
			query.setInteger("id", id);
			obra = (Obra) query.uniqueResult();
		} catch (HibernateException hi) {
			if (trns != null) {
				trns.rollback();
			}
			hi.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return obra;
	}
}
