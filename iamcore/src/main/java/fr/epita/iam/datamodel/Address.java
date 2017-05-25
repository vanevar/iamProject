package fr.epita.iam.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="Address")
public class Address {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="aId")
  private long aId;
  
  @ManyToOne()
  @Cascade({CascadeType.SAVE_UPDATE})
  @JoinColumn(name="id_Id")
  private Identity identity;
  

  @Column(name="firstLine")
  private String firstLine;
  
  @Column(name="city")
  private String city;
  
  @Column(name="postalCode")
  private String postalCode;
  
  @Column(name="country")
  private String country;
  
  
  public Address(){
    //Mandatory constructor
  }
  public Address(Identity identity, String firstLine, String city, String postalCode, String country) {
    this.identity = identity;
    this.firstLine = firstLine;
    this.city = city;
    this.postalCode = postalCode;
    this.country = country;
  }

  public Long getaId() {
    return aId;
  }
  
  public Identity getIdentity() {
    return identity;
  }
  
  public void setIdentity(Identity identity) {
    this.identity = identity;
  }
  
  public String getFirstLine() {
    return firstLine;
  }
  public void setFirstLine(String firstLine) {
    this.firstLine = firstLine;
  }
  public String getCity() {
    return city;
  }
  public void setCity(String city) {
    this.city = city;
  }
  public String getPostalCode() {
    return postalCode;
  }
  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }
  public String getCountry() {
    return country;
  }
  public void setCountry(String country) {
    this.country = country;
  }
  
  public Long getIdentityId(){
    return this.identity.getUid();
  }
  
  @Override
  public String toString(){
    return "Address [ aid = " + this.aId + ", uId = "+ this.identity.getUid() + ", firstLine = " + this.firstLine +  
        ", city = " + this.city + ", postalCode = " + this.postalCode + ", country = "+ this.country + " ]";

  }

}
