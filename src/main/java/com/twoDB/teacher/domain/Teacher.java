package com.twoDB.teacher.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.Column;

@Entity
@Table(name = "teacher")
public class Teacher {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  private String lastName;
  private String firstName;

  @Column(name = "updateTime")
  @UpdateTimestamp
  private Timestamp updateTime;

  protected Teacher() {}

  public Teacher(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public Long getId() {
    return id;
  }

  public String getLastName(){
    return this.lastName;
  }

  public String getFirstName(){
    return this.firstName;
  }

  public void setLastName(String lastName){
    this.lastName = lastName;
  }

  public void setFirstName(String firstName){
    this.firstName = firstName;
  }
}
