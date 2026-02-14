package Spring_Autowiring_Demo.Experiment_5;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class Certification {
  int id;
  String name;
  LocalDate dateofCompletion;
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
  public LocalDate getDateofCompletion() {
	return dateofCompletion;
  }
  public void setDateofCompletion(LocalDate dateofCompletion) {
	this.dateofCompletion = dateofCompletion;
  }

  
  
  
}
