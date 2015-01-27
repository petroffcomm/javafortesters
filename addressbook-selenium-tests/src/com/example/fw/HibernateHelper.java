package com.example.fw;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;

import com.example.tests.contacts.ContactData;
import com.example.tests.groups.GroupData;
import com.example.utils.SortedListOf;

public class HibernateHelper extends BaseHelper {

	public HibernateHelper(ApplicationManager manager) {
	  super(manager);
	}

	public List<GroupData> listGroups() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		try {
          return new SortedListOf<GroupData>(
              (List<GroupData>) session.createQuery("from GroupData").list());
		} finally {
          trans.commit();
		}
	}
	
	public GroupData getGroupById(int index) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		try {
			return (GroupData) session.createCriteria(GroupData.class).add(Expression.eq("id", Integer.toString(index))).list().get(0);
			
		} finally {
          trans.commit();
		}
	}
	
	public List<ContactData> listContacts() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		try {
          return new SortedListOf<ContactData>(
              (List<ContactData>) session.createQuery("from ContactData").list());
		} finally {
          trans.commit();
		}
	}
}