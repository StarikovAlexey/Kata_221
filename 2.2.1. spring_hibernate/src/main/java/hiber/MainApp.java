package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("John", "Smith", "email1@mail.com");
      User user2 = new User("Mike", "Felps", "email2@mail.com");
      User user3 = new User("Josh", "Wee", "email3@mail.com");
      User user4 = new User("Sam", "Black", "email3@mail.com");

      Car car1 = new Car("Liancha", 1);
      Car car2 = new Car("Ford", 2);
      Car car3 = new Car("BMW", 3);
      Car car4 = new Car("Lada", 4);

      userService.add(user1.setCar(car1).setUser(user1));
      userService.add(user2.setCar(car2).setUser(user2));
      userService.add(user3.setCar(car3).setUser(user3));
      userService.add(user4.setCar(car4).setUser(user4));


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
      }

      System.out.println(userService.getUserByCar("BMW", 3));

      try {
         User notFoundUser = userService.getUserByCar("Lancia", 6);
      } catch (NoResultException e) {
         System.out.println("User not found");
      }

      context.close();
   }
}
