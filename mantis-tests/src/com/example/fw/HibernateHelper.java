package com.example.fw;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import com.example.tests.UserData;

public class HibernateHelper extends BaseHelper {

	public HibernateHelper(ApplicationManager manager) {
	  super(manager);
	}

	protected SimpleExpression equalByLogin(String login) {
		return Restrictions.eq("login", login);
	}

	public boolean doesUserExists(String login) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		try {			
			return !(session.createCriteria(UserData.class)
							.add(equalByLogin(login))
							.list()
							.isEmpty());			
		} finally {
			trans.commit();
		}
	}

	public boolean deleteUser(String login) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		try {
			UserData user = (UserData)session.createCriteria(UserData.class)
								.add(equalByLogin(login))
								.list()
								.get(0);
			session.delete(user);
			return true;			
		} finally {
			trans.commit();
		}	
	}
	
}
