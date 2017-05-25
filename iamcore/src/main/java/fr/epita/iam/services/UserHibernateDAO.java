package fr.epita.iam.services;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import fr.epita.iam.datamodel.User;
import fr.epita.iam.exceptions.DaoInitializationException;

public class UserHibernateDAO implements Dao<User>{

  @Inject
  @Named("sFactory")
  private SessionFactory sf;
  
  private static final Logger LOGGER = LogManager.getLogger(UserHibernateDAO.class);
  
  private UserHibernateDAO() throws SQLException {
    
  }
  
  public void write(User instance) {
    LOGGER.debug("=> writeUser : tracing the input : {}", instance.toString());

    Session session = null;
    Transaction tx = null;
    try {
      session = sf.openSession();
      tx = session.beginTransaction();
      session.save(instance);
      tx.commit();
    } catch (Exception e) {
      if(tx != null)
      {
        tx.rollback();
      }
      DaoInitializationException die = new DaoInitializationException("A problem was encountered when trying to save the user on the database.");
      die.initCause(e);
      throw die;
    } finally {
      if (session != null)
        try {
          session.close();
        } catch (Exception e) {
          LOGGER.error("FAILED: writeUser Statement close. {}", e);
        }
    }
    LOGGER.debug("<= writeUser : Leaving method with no error.");
  }

  public void update(User instance) {
    LOGGER.debug("=> updateUser : tracing the input : {}", instance.toString());
    Transaction tx = null;
    Session session = null;
    try{
      session = sf.openSession();
      tx = session.beginTransaction();
      session.update(instance);
      tx.commit();
    } catch (Exception e) {
      if(tx != null)
      {
        tx.rollback();
      }
      DaoInitializationException die = new DaoInitializationException("A problem was encountered when trying to update the User to the database.");
      die.initCause(e);
      throw die;
    } finally {
      if (session != null)
        try {
          session.close();
        } catch (Exception e) {
          LOGGER.error("FAILED: updateUser. {}", e);
        }
    }
    LOGGER.debug("<= readUser : Leaving with no errors");
  }

  public void delete(User instance) {
    LOGGER.debug("=> deleteUser : tracing the input : {}", instance);
    Session session = null;
    Transaction tx = null;
    try{
      session = sf.openSession();
      tx = session.beginTransaction();
      session.delete(instance);
      tx.commit();
    }  catch (Exception e) {
      if(tx != null)
      {
        tx.rollback();
      }
      DaoInitializationException die = new DaoInitializationException("A problem was encountered when trying to remove the user from the database");
      die.initCause(e);
      throw die;
    }finally {
      if (session != null)
        try {
          session.close();
        } catch (Exception e) {
          LOGGER.error("FAILED: deleteUser. {}", e);
        }
    }
    LOGGER.debug("<= deleteUser");
  }

  public List<User> search(User instance) {
    LOGGER.debug("=> searchUser : tracing the input : {}", instance);
    Session session = null;
    List<User> userList = null;
    try{
      WhereBuilder wb = new WhereBuilder();
      session = sf.openSession();
      Query query = wb.userWhere(session, instance);
      userList = query.list();
      if(userList.isEmpty())
      {
        LOGGER.info("No user found with the given criteria.");
      }
    } catch(Exception e){
      DaoInitializationException die = new DaoInitializationException("A problem was encountered when trying to read the user to the database.");
      die.initCause(e);
      throw die;
    } finally {
      if (session != null)
        try {
          session.close();
        } catch (Exception e) {
          LOGGER.error("FAILED: readUser close connection. {}", e);
        }
    }
    LOGGER.debug("<= searchUser : Leaving with no errors");
    return userList;
  }

}
