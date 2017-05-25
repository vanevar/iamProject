package fr.epita.iam.iamcore.test;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
public class TestDataBaseCreation {

  private static final Logger LOGGER = LogManager.getLogger(TestDataBaseCreation.class);
  
  @Inject
  Dao<User> uDao;
  @Inject
  Dao<Identity> iDao;
  @Inject
  Dao<Address> aDao;
  
  @Test
  public void createAdminUser(){
    LOGGER.info("Test Creation of User.");
    int idsBefore = iDao.search(new Identity(null, null, null)).size();
    
    DateFormatManager dfm = new DateFormatManager();
    Identity id = new Identity("Clark Kent", "ckent@dailyplanet.com", dfm.dateFromString("1976-02-29") );
    User user = new User("admin", "admin", id);
    
    uDao.write(user);
    Assert.assertTrue(!uDao.search(new User(null, null, null)).isEmpty());
    
    Assert.assertTrue(iDao.search(new Identity(null, null, null)).size()>idsBefore);
  }
  
  @Test
  public void createIdentities(){
    LOGGER.info("Test Creation of secondary identities.");
    int idsBefore = iDao.search(new Identity(null, null, null)).size();
    int addBefore = aDao.search(new Address(null, null, null, null, null)).size();
    DateFormatManager dfm = new DateFormatManager();
    
    Identity id = new Identity("Loise Lane", "llane@dailyplanet.com", null);
    Address address = new Address(id, "145 Main Avenue", "Metropolis", "10055", "United States");
    
    aDao.write(address);
    Assert.assertTrue(iDao.search(new Identity(null, null, null)).size()>idsBefore);
    Assert.assertTrue(aDao.search(new Address(null, null, null, null, null)).size()>addBefore);
    idsBefore++;
    addBefore++;
    
    id = new Identity("Bruce Wayne", "bwayne@wayne.com", dfm.dateFromString("1969-04-07"));
    address = new Address(id, "1 Wayne Vinewyard", "Gotham City", "07003", "United States");
    
    aDao.write(address);
    Assert.assertTrue(iDao.search(new Identity(null, null, null)).size()>idsBefore);
    Assert.assertTrue(aDao.search(new Address(null, null, null, null, null)).size()>addBefore);
    idsBefore++;
    addBefore++;
    
    id = new Identity("Barbara Gordon", "bgordon@gcpd.com", dfm.dateFromString("1982-09-23"));
    
    iDao.write(id);
    Assert.assertTrue(iDao.search(new Identity(null, null, null)).size()>idsBefore);
    idsBefore++;

  }
  
}
