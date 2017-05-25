package fr.epita.iam.datamodel;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="IamUser")
public class User {
  @Id
  //@GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id")
  private long id;
  
  @Column(name="login")
  private String login;
  
  @Column(name="pass")
  private String pass;
  
  @OneToOne(cascade = CascadeType.ALL)
  //@JoinColumn(name="id_Id")
  @MapsId
  private Identity identity;
  
  public User() {
    super();
  }
  public User(String login, String pass, Identity identity) {
    super();
    this.login = login;
    this.pass = pass;
    this.identity = identity;
  }
  
  public long getId() {
    return id;
  }
  public String getLogin() {
    return login;
  }
  public void setLogin(String login) {
    this.login = login;
  }
  public String getPass() {
    return pass;
  }
  public void setPass(String pass) {
    this.pass = pass;
  }
  public Identity getIdentity() {
    return identity;
  }
  public void setIdentity(Identity identity) {
    this.identity = identity;
  }
  
  public long getIdentityId()
  {
    return this.identity.getUid();
  }
  
  @Override
  public String toString()
  {
    return "User [ id = " + this.id + ", login = " + this.login +  
        ", pass = " + this.pass + ", identity = " + this.identity.toString();
  }
  
}
