package br.edu.ifpr.comat.dao;

import br.edu.ifpr.comat.model.Cliente;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @project comat
 * @class ClienteDAO
 * @author Cristhiano Konczak Cardoso <cristhiano@c3info.com.br>
 * @date 26/09/2013
 */
public class ClienteDAO extends BaseDAO {

	private final Session session;
	private Transaction trns;

	public ClienteDAO() {
		this.trns = null;
		session = getConnection();
	}

	public int insert(Cliente cli) {
		try {
			trns = session.beginTransaction();
			session.save(cli);
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
		
		return cli.getIdCliente();
	}

	public void delete(Integer id) {
		try {
			trns = session.beginTransaction();
			Cliente cli = (Cliente) session
					.load(Cliente.class, new Integer(id));
			session.delete(cli);
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

	public void update(Cliente cli) {
		try {
			trns = session.beginTransaction();
			session.update(cli);
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

	public List<Cliente> select() {
		List<Cliente> clientes = new ArrayList<>();
		try {
			trns = session.beginTransaction();
			clientes = session.createQuery("from Cliente ORDER BY dataCadastro desc").list();
		} catch (HibernateException hi) {
			if (trns != null) {
				trns.rollback();
			}
			hi.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return clientes;
	}

	public Cliente select(Integer id) {
		Cliente cli = null;
		try {
			trns = session.beginTransaction();
			String queryString = "from Cliente where idCliente = :id";
			Query query = session.createQuery(queryString);
			query.setInteger("id", id);
			cli = (Cliente) query.uniqueResult();
		} catch (HibernateException hi) {
			if (trns != null) {
				trns.rollback();
			}
			hi.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return cli;
	}	
}
