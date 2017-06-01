package fr.epita.iam.services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import fr.epita.iam.datamodel.Address;
import fr.epita.iam.datamodel.Identity;

@Repository
public class IdentityDataService {
  
  private static final Logger LOGGER = LogManager.getLogger(IdentityDataService.class);
  
  @Inject
  Dao<Identity> idDao;
  
  @Inject
  Dao<Address> addrDao;

  public void write( Identity identity ) {
    LOGGER.info("=> write Identity");
    idDao.write(identity);
  }
  public void update( Identity identity ) {
    LOGGER.info("=> update Identity");
    idDao.update(identity);
  }
  public void delete( Identity identity ) {
    LOGGER.info("=> delete Identity");
    idDao.delete(identity);
  }
  public List<Identity> search( Identity identity ){
    LOGGER.info("=> search Identity");
    return idDao.search(identity);
  }
  
  public List<Identity> searchByAddress(Address address){
    LOGGER.info("=> searchByAddress : tracing the input : {}", address);
    List<Identity> results = new ArrayList<Identity>();
    List<Address> addresses = addrDao.search(address);
    if(!addresses.isEmpty()){
      for(Address addr : addresses)
      {
        if(addr.getIdentity() != null)
          results.add(addr.getIdentity());
      }
    }
    LOGGER.info("<= searchByAddress : leaving method without errors.");
    return results;
  }
  
}
