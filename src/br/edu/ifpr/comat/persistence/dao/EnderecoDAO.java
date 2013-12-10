package br.edu.ifpr.comat.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.edu.ifpr.comat.model.Endereco;

/**
 * @project comat
 * @class EnderecoDAO
 * @author Cristhiano Konczak Cardoso <cristhiano@c3info.com.br>
 * @date 26/09/2013
 */
public class EnderecoDAO extends BaseDAO {

	private final Session session;
	private Transaction trns;

	public EnderecoDAO() {
		this.trns = null;
		session = getConnection();
	}

	public int insert(Endereco end) {
		try {
			trns = session.beginTransaction();
			session.save(end);
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
		return end.getIdEndereco();
	}

	public void delete(Integer id) {
		try {
			trns = session.beginTransaction();
			Endereco end = (Endereco) session.load(Endereco.class, new Integer(
					id));
			session.delete(end);
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

	public void update(Endereco end) {
		try {
			trns = session.beginTransaction();
			session.update(end);
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

	public List<Endereco> select() {
		List<Endereco> enderecos = new ArrayList<>();
		try {
			trns = session.beginTransaction();
			enderecos = session.createQuery("from Endereco").list();
		} catch (HibernateException hi) {
			if (trns != null) {
				trns.rollback();
			}
			hi.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return enderecos;
	}

	public Endereco select(Integer id) {
		Endereco end = null;
		try {
			trns = session.beginTransaction();
			String queryString = "from Endereco where idEndereco = :id";
			Query query = session.createQuery(queryString);
			query.setInteger("id", id);
			end = (Endereco) query.uniqueResult();
		} catch (HibernateException hi) {
			if (trns != null) {
				trns.rollback();
			}
			hi.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return end;
	}

}
