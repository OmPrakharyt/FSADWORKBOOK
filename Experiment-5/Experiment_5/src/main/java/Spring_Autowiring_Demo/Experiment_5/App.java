package Spring_Autowiring_Demo.Experiment_5;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages="package Spring_Autowiring_Demo.Experiment_5;")
class AppConfig{
	
}
public class App {
    public static void main(String[] args) {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        Student stud = context.getBean(Student.class);

       
       stud.setId(32676);
       stud.setName("Kumar Priyanshu");
       stud.setGender("Male");
       
       Certification certificate = stud.getCertificate();
       certificate.setId(32676);
       certificate.setName("Java full stack");
       certificate.setDateofCompletion(java.time.LocalDate.of(2025, 2, 11));
       
       System.out.println("Details of Student");
       System.out.println("ID: " + stud.getId());
       System.out.println("Name: " + stud.getName());
       System.out.println("Gender: " + stud.getGender());

      
       System.out.println("\nCertification Details");
       System.out.println("Certificate ID: " + certificate.getId());
       System.out.println("Certificate Name: " + certificate.getName());
       System.out.println("Completion Date: " + certificate.getDateofCompletion());
       
       
    }
}
