package br.edu.ifpr.comat.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.edu.ifpr.comat.model.ClienteFisica;

/**
 * @project coma
 * @class ClienteDAO
 * @author Cristhiano Konczak Cardoso <cristhiano@c3info.com.br>
 * @date 26/09/2013
 */
public class ClienteFisicaDAO extends BaseDAO {

	private final Session session;
	private Transaction trns;

	public ClienteFisicaDAO() {
		this.trns = null;
		session = getConnection();
	}

	public void insert(ClienteFisica cli) {
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
			ClienteFisica cli = (ClienteFisica) session.load(
					ClienteFisica.class, new Integer(id));
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

	public void update(ClienteFisica cli) {
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

	public List<ClienteFisica> select() {
		List<ClienteFisica> clientes = new ArrayList<>();
		try {
			trns = session.beginTransaction();
			clientes = session.createQuery("from ClienteFisica").list();
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

	public List<ClienteFisica> selectStatus(int status) {
		List<ClienteFisica> clientes = new ArrayList<>();
		try {
			trns = session.beginTransaction();
			Query query = session
					.createQuery("from ClienteFisica where status = :st");
			query.setInteger("st", status);
			clientes = query.list();			

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

	public ClienteFisica select(Integer id) {
		ClienteFisica cli = null;
		try {
			trns = session.beginTransaction();
			String queryString = "from ClienteFisica where idCliente = :id";
			Query query = session.createQuery(queryString);
			query.setInteger("id", id);
			cli = (ClienteFisica) query.uniqueResult();
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

	public ClienteFisica selectCpf(String cpf) {
		ClienteFisica cli = null;
		try {
			trns = session.beginTransaction();
			String queryString = "from ClienteFisica where cpf = :cpf";
			Query query = session.createQuery(queryString);
			query.setString("cpf", cpf);
			cli = (ClienteFisica) query.uniqueResult();
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
