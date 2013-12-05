package br.edu.ifpr.comat.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.edu.ifpr.comat.model.Produto;

/**
 * @project comat
 * @class ProdutoDAO
 * @author Cristhiano Konczak Cardoso <cristhiano@c3info.com.br>
 * @date 26/09/2013
 */
public class ProdutoDAO extends BaseDAO {

	private final Session session;
	private Transaction trns;

	public ProdutoDAO() {
		this.trns = null;
		session = getConnection();
	}

	public int insert(Produto prod) {
		try {
			trns = session.beginTransaction();
			session.save(prod);
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

		return prod.getRefProduto();
	}

	public void delete(Integer ref) {
		try {
			trns = session.beginTransaction();
			Produto prod = (Produto) session.load(Produto.class, new Integer(
					ref));
			session.delete(prod);
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

	public void update(Produto prod) {
		try {
			trns = session.beginTransaction();
			session.update(prod);
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

	public List<Produto> select() {
		List<Produto> produtos = new ArrayList<>();
		try {
			trns = session.beginTransaction();
			produtos = session.createQuery("from Produto ORDER BY nome ASC")
					.list();
		} catch (HibernateException hi) {
			if (trns != null) {
				trns.rollback();
			}
			hi.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return produtos;
	}

	public List<Produto> select(int idCategoria, int status) {
		List<Produto> produtos = new ArrayList<>();
		try {
			trns = session.beginTransaction();
			Query query = session
					.createQuery("from Produto where idCategoriaFk = :cat and status = :sts");
			query.setInteger("cat", idCategoria);
			query.setInteger("sts", status);
			produtos = query.list();
		} catch (HibernateException hi) {
			if (trns != null) {
				trns.rollback();
			}
			hi.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return produtos;
	}

	public Produto select(Integer ref) {
		Produto prod = null;
		try {
			trns = session.beginTransaction();
			String queryString = "from Produto where refProduto = :ref";
			Query query = session.createQuery(queryString);
			query.setInteger("ref", ref);
			prod = (Produto) query.uniqueResult();
		} catch (HibernateException hi) {
			if (trns != null) {
				trns.rollback();
			}
			hi.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return prod;
	}

	public List<Produto> selectStatus(int status) {
		List<Produto> produtos = new ArrayList<>();
		try {
			trns = session.beginTransaction();
			Query query = session
					.createQuery("from Produto where status = :sts");
			query.setInteger("sts", status);
			produtos = query.list();
		} catch (HibernateException hi) {
			if (trns != null) {
				trns.rollback();
			}
			hi.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return produtos;
	}

	public List<Produto> selectCategoria(int cat) {
		List<Produto> produtos = new ArrayList<>();
		try {
			trns = session.beginTransaction();
			Query query = session
					.createQuery("from Produto where idCategoriaFk = :cat");
			query.setInteger("cat", cat);
			produtos = query.list();
		} catch (HibernateException hi) {
			if (trns != null) {
				trns.rollback();
			}
			hi.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return produtos;
	}

	public List<Produto> selectStatusCategoria(int status, int cat) {
		List<Produto> produtos = new ArrayList<>();
		try {
			trns = session.beginTransaction();
			Query query = session
					.createQuery("from Produto where status = :sts and idCategoriaFk = :cat");
			query.setInteger("sts", status);
			query.setInteger("cat", cat);
			produtos = query.list();
		} catch (HibernateException hi) {
			if (trns != null) {
				trns.rollback();
			}
			hi.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return produtos;
	}
}
