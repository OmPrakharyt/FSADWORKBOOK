package Spring_Autowiring_Demo.Experiment_5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Student {
 @Autowired
 Certification certificate;
 int id;
 String name;
 String gender;
 
 
 public Certification getCertificate() {
	return certificate;
 }
 public void setCertificate(Certification certificate) {
	this.certificate = certificate;
 }
 public int getId() {
	return id;
 }
 public void setId(int id) {
	this.id = id;
 }
 public String getName() {
	return name;
 }
 public void setName(String name) {
	this.name = name;
 }
 public String getGender() {
	return gender;
 }
 public void setGender(String gender) {
	this.gender = gender;
 }
 


 
 
}
