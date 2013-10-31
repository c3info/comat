package br.edu.ifpr.comat.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.edu.ifpr.comat.model.Categoria;

/**
 * @project comat
 * @class CategoriaDAO
 * @author Cristhiano Konczak Cardoso <cristhiano@c3info.com.br>
 * @date 26/09/2013
 */
public class CategoriaDAO extends BaseDAO {

	private final Session session;
	private Transaction trns;

	public CategoriaDAO() {
		this.trns = null;
		session = getConnection();
	}

	public int insert(Categoria cat) {
		try {
			trns = session.beginTransaction();
			session.save(cat);
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
		return cat.getIdCategoria();
	}

	public void delete(Integer id) {
		try {
			trns = session.beginTransaction();
			Categoria cat = (Categoria) session.load(Categoria.class,
					new Integer(id));
			session.delete(cat);
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

	public void update(Categoria cat) {
		try {
			trns = session.beginTransaction();
			session.update(cat);
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

	public List<Categoria> select() {
		List<Categoria> categorias = new ArrayList<>();
		try {
			trns = session.beginTransaction();
			categorias = session.createQuery("from Categoria").list();
		} catch (HibernateException hi) {
			if (trns != null) {
				trns.rollback();
			}
			hi.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return categorias;
	}

	public Categoria select(Integer id) {
		Categoria cat = null;
		try {
			trns = session.beginTransaction();
			String queryString = "from Categoria where idCategoria = :id";
			Query query = session.createQuery(queryString);
			query.setInteger("id", id);
			cat = (Categoria) query.uniqueResult();
		} catch (HibernateException hi) {
			if (trns != null) {
				trns.rollback();
			}
			hi.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return cat;
	}
}
