package br.edu.ifpr.comat.dao;

import org.hibernate.Session;

import br.edu.ifpr.comat.util.HibernateUtil;

/**
 * @project comat
 * @class DAOBase
 * @author Cristhiano Konczak Cardoso <cristhiano@c3info.com.br>
 * @date 20/09/2013
 */
public class BaseDAO {

	public Session getConnection() {
		return HibernateUtil.getSessionFactory().openSession();
	}

}
