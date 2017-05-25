package fr.epita.iam.iamcore.test;

import java.util.List;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.epita.iam.datamodel.Address;
import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.datamodel.User;
import fr.epita.iam.services.Dao;
import fr.epita.iam.services.DateFormatManager;
import fr.epita.iam.services.UserHibernateDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
public class TestUserDAO {

private static final Logger LOGGER = LogManager.getLogger(TestUserDAO.class);
  
  @Inject 
  UserHibernateDAO userDao;
  
  @Inject
  Dao<Identity> idDao;
  
  @Test
  public void testAddressHibernate(){
    LOGGER.info("Creating Identity for User");
    DateFormatManager dfm = new DateFormatManager();
    Identity id = new Identity("Carlos Diez", "cdm@gmailcom"
        , dfm.dateFromString("1980-12-24"));
  
    LOGGER.info("Test Writing User");
    User user = new User("cdm", "c", id);
    userDao.write(user);
    
    LOGGER.info("Test Identity was persited by User DAO");
    List<Identity >identities = idDao.search(new Identity(null, null, null));
    for(Identity id2 : identities)
    {
      System.out.println(id2.toString());
    }
  
    LOGGER.info("Test User Search");
    List<User> users = userDao.search(new User (null, null, null ));
    for(User usr : users)
    {
      System.out.println(usr.toString());
    }
    
    LOGGER.info("Test User Search by login");
    users = userDao.search(new User (null, "c", null ));
    for(User usr : users)
    {
      System.out.println(usr.toString());
    }
    
    LOGGER.info("Test Update user");
    User modifiedUser = users.get(0);
    modifiedUser.setPass("cd");
    userDao.update(modifiedUser);
    System.out.println(userDao.search(new User(null, "cd", null)));
    
    LOGGER.info("Delete user");
    int before = userDao.search(new User(null, null, null)).size();
    userDao.delete(modifiedUser);
    Assert.assertTrue(userDao.search(new User(null, null, null)).size()<before);
    
  }
  
  
}
