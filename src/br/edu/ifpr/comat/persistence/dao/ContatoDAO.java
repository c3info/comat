package br.edu.ifpr.comat.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.edu.ifpr.comat.model.Contato;

/**
 * @project comat
 * @class ContatoDAO
 * @author Cristhiano Konczak Cardoso <cristhiano@c3info.com.br>
 * @date 26/09/2013
 */
public class ContatoDAO extends BaseDAO {

	private final Session session;
	private Transaction trns;

	public ContatoDAO() {
		this.trns = null;
		session = getConnection();
	}

	public void insert(Contato con) {
		try {
			trns = session.beginTransaction();
			session.save(con);
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

	public void delete(Integer id) {
		try {
			trns = session.beginTransaction();
			Contato con = (Contato) session
					.load(Contato.class, new Integer(id));
			session.delete(con);
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

	public void update(Contato con) {
		try {
			trns = session.beginTransaction();
			session.update(con);
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

	public List<Contato> select() {
		List<Contato> contatos = new ArrayList<>();
		try {
			trns = session.beginTransaction();
			contatos = session.createQuery("from Contato").list();
		} catch (HibernateException hi) {
			if (trns != null) {
				trns.rollback();
			}
			hi.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return contatos;
	}

	public List<Contato> selectCliente(int idCliente) {
		List<Contato> contatos = new ArrayList<>();
		try {
			trns = session.beginTransaction();
			Query query = session
					.createQuery("from Contato where idClienteFk = :id");
			query.setInteger("id", idCliente);
			contatos = query.list();

		} catch (HibernateException hi) {
			if (trns != null) {
				trns.rollback();
			}
			hi.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return contatos;
	}

	public Contato select(Integer id) {
		Contato con = null;
		try {
			trns = session.beginTransaction();
			String queryString = "from Contato where idContato = :id";
			Query query = session.createQuery(queryString);
			query.setInteger("id", id);
			con = (Contato) query.uniqueResult();
		} catch (HibernateException hi) {
			if (trns != null) {
				trns.rollback();
			}
			hi.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return con;
	}
}
