package br.edu.ifpr.comat.dao;

import br.edu.ifpr.comat.model.ClienteJuridica;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @project coma
 * @class ClienteDAO
 * @author Cristhiano Konczak Cardoso <cristhiano@c3info.com.br>
 * @date 26/09/2013
 */
public class ClienteJuridicaDAO extends BaseDAO {

	private final Session session;
	private Transaction trns;

	public ClienteJuridicaDAO() {
		this.trns = null;
		session = getConnection();
	}

	public void insert(ClienteJuridica cli) {
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
	}

	public void delete(Integer id) {
		try {
			trns = session.beginTransaction();
			ClienteJuridica cli = (ClienteJuridica) session.load(
					ClienteJuridica.class, new Integer(id));
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

	public void update(ClienteJuridica cli) {
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

	public List<ClienteJuridica> select() {
		List<ClienteJuridica> clientes = new ArrayList<>();
		try {
			trns = session.beginTransaction();
			clientes = session.createQuery("from ClienteJuridica").list();
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

	public ClienteJuridica select(Integer id) {
		ClienteJuridica cli = null;
		try {
			trns = session.beginTransaction();
			String queryString = "from ClienteJuridica where idCliente = :id";
			Query query = session.createQuery(queryString);
			query.setInteger("id", id);
			cli = (ClienteJuridica) query.uniqueResult();
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
	
	public ClienteJuridica selectCnpj(String cnpj) {
		ClienteJuridica cli = null;
		try {
			trns = session.beginTransaction();
			String queryString = "from ClienteJuridica where cnpj = :cnpj";
			Query query = session.createQuery(queryString);
			query.setString("cnpj", cnpj);
			cli = (ClienteJuridica) query.uniqueResult();
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
