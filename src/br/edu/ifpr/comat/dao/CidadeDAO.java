package br.edu.ifpr.comat.dao;

import br.edu.ifpr.comat.model.Cidade;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @project comat
 * @class CidadeDAO
 * @author Cristhiano Konczak Cardoso <cristhiano@c3info.com.br>
 * @date 26/09/2013
 */
public class CidadeDAO {

    private final Session session;
    private Transaction trns;

    public CidadeDAO() {
        this.session = SessionController.getSession();
        this.trns = null;
    }

    public void insert(Cidade cit) {
        try {
            trns = session.beginTransaction();
            session.save(cit);
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
            Cidade cit = (Cidade) session.load(Cidade.class, new Integer(id));
            session.delete(cit);
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

    public void update(Cidade cit) {
        try {
            trns = session.beginTransaction();
            session.update(cit);
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

    public List<Cidade> select() {
        List<Cidade> cidades = new ArrayList<>();
        try {
            trns = session.beginTransaction();
            cidades = session.createQuery("from Cidade").list();
        } catch (HibernateException hi) {
            if (trns != null) {
                trns.rollback();
            }
            hi.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return cidades;
    }

    public Cidade select(Integer id) {
        Cidade cit = null;
        try {
            trns = session.beginTransaction();
            String queryString = "from Cidade where idCidade = :id";
            Query query = session.createQuery(queryString);
            query.setInteger("id", id);
            cit = (Cidade) query.uniqueResult();
        } catch (HibernateException hi) {
            if (trns != null) {
                trns.rollback();
            }
            hi.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return cit;
    }
}
