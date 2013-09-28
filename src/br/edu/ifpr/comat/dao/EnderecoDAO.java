package br.edu.ifpr.comat.dao;

import br.edu.ifpr.comat.model.Endereco;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @project comat
 * @class EnderecoDAO
 * @author Cristhiano Konczak Cardoso <cristhiano@c3info.com.br>
 * @date 26/09/2013
 */
public class EnderecoDAO {

    private final Session session;
    private Transaction trns;

    public EnderecoDAO() {
        this.session = SessionController.getSession();
        this.trns = null;
    }

    public void insert(Endereco end) {
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
    }

    public void delete(Integer id) {
        try {
            trns = session.beginTransaction();
            Endereco end = (Endereco) session.load(Endereco.class, new Integer(id));
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