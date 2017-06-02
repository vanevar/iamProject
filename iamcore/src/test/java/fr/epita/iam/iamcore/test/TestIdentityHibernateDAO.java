package fr.epita.iam.iamcore.test;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.epita.iam.datamodel.Address;
import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.services.Dao;
import fr.epita.iam.services.DateFormatManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
public class TestIdentityHibernateDAO {

  private static final Logger LOGGER = LogManager.getLogger(TestIdentityHibernateDAO.class);
  
  
  @Inject
  Dao<Identity> dao;
  
  @Inject
  SessionFactory sFactory;
  
  
  @Test
  public void testHibernate(){
    List<Identity> identities = dao.search(new Identity(null, null, null));
    int before = identities.size();
    
    LOGGER.info("Before : {} ", identities);
    
    DateFormatManager dfm = new DateFormatManager();
    Identity id = new Identity("Carlos Diez", "cdm@gmailcom", dfm.dateFromString("1980-12-24"));
    dao.write(id);
    
    identities = dao.search(new Identity(null, null, null));
    for(Identity id2 : identities)
    {
      System.out.println(id2.toString());
     }
    Assert.assertTrue(identities.size()>before);
    
    LOGGER.info("Search by Display Name");
    identities = dao.search(new Identity("cArLos", null, null));
    System.out.println(identities);
    Assert.assertTrue(identities.size() >= 1);
    
    LOGGER.info("Update Identity");    
    id = dao.search(new Identity("dIeZ", null, null)).get(0);
    id.setDisplayName("Pepe");
    dao.update(id);
    identities = dao.search(new Identity("pePE", null, null));
    System.out.println(identities);
    Assert.assertTrue(identities.size() >= 1);
    
    LOGGER.info("Search identity by Date");
    List<Identity> res = dao.search(new Identity(null, null, dfm.dateFromString("1980-12-24")));
    Assert.assertTrue(!res.isEmpty());
    
    LOGGER.info("Delete Identity");    
    before = dao.search(new Identity(null, null, null)).size();
    dao.delete(id);
    Assert.assertTrue(dao.search(new Identity(null, null, null)).size()<before);
  
  }
  
  @Test
  public void testHQL(){
    String hqlQuery = "from Identity i where lower(i.displayName) like lower(:dispName)";
    Session session = sFactory.openSession();
    Transaction tx = session.beginTransaction();
    DateFormatManager dfm = new DateFormatManager();
    String displayName = "CDM";
    session.save(new Identity(displayName, "cdm@gmailcom", dfm.dateFromString("1980-12-24")));
    tx.commit();
    Query query = session.createQuery(hqlQuery);
    query.setParameter("dispName", displayName);
    List<Identity> result = query.list();
    
    Assert.assertTrue(!result.isEmpty());
  }
  
  @Test
  public void testAddress(){
    Set<Address> addresses = dao.search(new Identity(null, null, null)).get(0).getAddresses();
    for(Address add : addresses )
    {
      System.out.println(add.toString());
    }
  }
  
//  @Test
//  public void testWhereBuilder() throws IllegalArgumentException, IllegalAccessException{
//    WhereBuilder wb = new WhereBuilder();
//    DateFormatManager dfm = new DateFormatManager();
//    wb.getWhereClause(new Identity("Carlos Diez", 0, 
//        "cdm@gmailcom", dfm.dateFromString("1980-12-24")));
//  }
}
