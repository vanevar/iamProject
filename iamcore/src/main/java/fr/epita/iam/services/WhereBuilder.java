package fr.epita.iam.services;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import fr.epita.iam.datamodel.Address;
import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.datamodel.User;

public class WhereBuilder {
  
  private static final Logger LOGGER = LogManager.getLogger(WhereBuilder.class);

  public Query identityWhere(Session session, Identity identity)
  {
    Map<String, Object> values = new LinkedHashMap<String, Object>();
    String queryString = "from Identity as i where 1 = 1 ";
    String queryHelper = "";
    if(0L != identity.getUid())
    {
      queryHelper += concatenate(queryHelper, " i.uid = :uid ");
      values.put("uid", identity.getUid());
    }
    
    if(identity.getDisplayName() != null)
    {
      queryHelper += concatenate(queryHelper, " lower(i.displayName) like lower(:displayName) " );
      values.put("displayName", identity.getDisplayName());
    }
    
    if(identity.getEmail() != null)
    {
      queryHelper += concatenate(queryHelper, " lower(i.email) like lower(:email) ");
      values.put("email", identity.getEmail());
    }
      
    if(identity.getBirthdate() != null)
    {
      queryHelper+= concatenate(queryHelper, " i.birthdate = :birthday ");
      values.put("birthday", identity.getBirthdate());
    }
    queryString+= appendHelper (queryHelper);  
    LOGGER.info("Build Identity where: {}", queryString);
    Query query = session.createQuery(queryString);
    String[] namedParameters = query.getNamedParameters();
    for (String parameter : namedParameters) {
      if("birthday".equals(parameter))
      {
        query.setDate(parameter, (Date) values.get(parameter));
      }
      else if("uid".equals(parameter))
      {
        query.setLong(parameter, (Long) values.get(parameter));
      }
      else
      {
      query.setParameter(parameter, "%"+values.get(parameter)+"%");
      }
    }
    return query;
  }

  public Query addrWhere(Session session, Address addr)
  {
    String idColumn = "id_Id";
    Map<String, Object> values = new LinkedHashMap<String, Object>();
    String queryString = "from Address as a where 1 = 1 ";
    String queryHelper = "";
    if(0L != addr.getaId())
    {
      queryHelper += concatenate(queryHelper, " a.aId = :aid ");
      values.put("aid", addr.getaId());
    }
    else if(addr.getFirstLine() != null)
    {
      queryHelper += concatenate(queryHelper, " lower(a.firstLine) like lower(:firstLine) ");
      values.put("firstLine", addr.getFirstLine());
    }
    else if(addr.getCity() != null)
    {
      queryHelper += concatenate(queryHelper, " lower(a.city) like lower(:city) ");
      values.put("city", addr.getCity());
    }
    else if(addr.getPostalCode() != null)
    {
      queryHelper += concatenate(queryHelper, " lower(a.postalCode) like lower(:postalCode) ");
      values.put("postalCode", addr.getPostalCode());
    }
    else if(addr.getCountry() != null)
    {
      queryHelper += concatenate(queryHelper, " lower(a.country) like lower(:country) ");
      values.put("country", addr.getCountry());
    }
    else if(addr.getIdentity() != null)
    {
      queryHelper += concatenate(queryHelper, " a.id_Id = :id_Id ");
      values.put(idColumn, addr.getIdentityId());
    }
    queryString+= appendHelper (queryHelper); 
    LOGGER.info("Build where: {}", queryString);
    Query query = session.createQuery(queryString);
    String[] namedParameters = query.getNamedParameters();
    for (String parameter : namedParameters) {
      if("aid".equals(parameter) || parameter.equals(idColumn) )
      {
        query.setParameter(parameter, values.get(parameter));
      }
      else
      {
      query.setParameter(parameter, "%"+values.get(parameter)+"%");
      }
    }
    return query;
  }

  public Query userWhere(Session session, User user)
  {
    Map<String, Object> values = new LinkedHashMap<String, Object>();
    String queryString = "from User as u where 1 = 1 ";
    if(0L != user.getId())
    {
      queryString += " and u.id = :id ";
      values.put("id", user.getId());
    }
    
    if(user.getLogin() != null)
    {
      queryString+=" and u.login like :login ";
      values.put("login", user.getLogin());
    }
      
    if(user.getPass() != null)
    {
      queryString+=" and u.pass like :pass ";
      values.put("pass", user.getPass());
    }
    
    if(user.getIdentity() != null)
    {
      queryString+=" and u.id_Id = :id_Id) ";
      values.put("id_Id", user.getIdentityId());
    }
    LOGGER.info("Build where: {}", queryString);
    Query query = session.createQuery(queryString);
    String[] namedParameters = query.getNamedParameters();
    for (String parameter : namedParameters) {
      query.setParameter(parameter, "%"+values.get(parameter)+"%");
    }
    return query;
  }

  public String concatenate(String queryHelper, String cmd)
  {
    return queryHelper.isEmpty() ? cmd : " or " + cmd;
  }
  
  public String appendHelper(String helperQuery)
  {
    return helperQuery.isEmpty() ? "" : " and ( " + helperQuery + " )";
  }
}
