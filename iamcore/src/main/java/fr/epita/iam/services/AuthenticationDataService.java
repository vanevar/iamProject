package fr.epita.iam.services;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import fr.epita.iam.datamodel.User;

@Repository
public class AuthenticationDataService {
  @Inject
  Dao<User> uDao;
  
  private static final Logger LOGGER = LogManager.getLogger(AuthenticationDataService.class);
  
  
  public User authenticate(String login, String pass)
  {
    LOGGER.info(" => Authenticate with params : {}, {}", login, pass);
    User result;
    result = uDao.search(new User(login, pass, null)).get(0);
    return result;
  }
}
