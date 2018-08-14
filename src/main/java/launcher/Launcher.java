package launcher;

import model.User;
import services.Server;
import services.Service;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Launcher {

    private  ArrayList<Service> services = new ArrayList<Service>();


    public static void main(String[] args) {
        new Launcher().launchServices();
      //  new Launcher().testBase();
    }

    private void testBase() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Trust");
        EntityManager entityManager =entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List select_c_from_user_c = entityManager.createQuery("select y from User y").getResultList();
        for (Object o : select_c_from_user_c) {
            System.out.println(((User)o).getAge());
        }
    }

    private void launchServices() {

        services.add(new Server());
        for (Service service : services) {
            service.launch();
        }
    }

}
