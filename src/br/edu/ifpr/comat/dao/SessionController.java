package br.edu.ifpr.comat.dao;

import br.edu.ifpr.comat.util.HibernateUtil;
import org.hibernate.Session;

/**
 * @project comat
 * @class CreateSession
 * @author Cristhiano Konczak Cardoso <cristhiano@c3info.com.br>
 * @date 28/09/2013
 */
public class SessionController {

    public static Session getSession() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session;
    }
}
