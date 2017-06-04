package fr.epita.iam.datamodel;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import fr.epita.iam.services.DateFormatManager;

@Entity
@Table(name="Identities")
public class Identity {
  // Properties
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="UId")
  private long uid;
  
  @Column(name="DisplayName")
  private String displayName;

  @Column(name="Email")
  private String email;
  
  @Column(name="Birthday")
  private Date birthdate;
  
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "identity")
  @Cascade({CascadeType.ALL})
  private Set<Address> addresses;
  
  //Constructors
  public Identity (){
    //Mandatory constructor
  }

  public Identity( String displayName, String email, Date birthdate){
    this.displayName = displayName;
    this.email = email;
    this.birthdate = birthdate;
  }
  
  // Getters & Setters
  public String getDisplayName() {
    return displayName;
  }
  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }
  public long getUid() {
    return uid;
  }
  public void setUid( long id) {
    this.uid = id;
  }

  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public Date getBirthdate() {
    return birthdate;
  }
  public void setBirthdate(Date birthdate) {
    this.birthdate = birthdate;
  }
  
  public Set<Address> getAddresses() {
    return addresses;
  }

  public void setAddresses(Set<Address> addresses) {
    this.addresses = addresses;
  }

  public int countAddresses()
  {
    return this.addresses == null ? this.addresses.size() : 0;
  }
  
  @Override
  public String toString()
  {
    DateFormatManager dfm = new DateFormatManager();
    String bd = this.birthdate != null ? dfm.stringFromDate(this.birthdate) : "NA";
    String identity = "Identity [ uid = " + this.uid + ", displayName = " + this.displayName +  
        ", email = " + this.email + ", birthdate = " + bd;    
    return identity + "]";
        
  }
}
