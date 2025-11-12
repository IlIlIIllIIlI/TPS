package fr.bts.iris.slam;

import fr.bts.iris.slam.dao.SessionDAO;
import fr.bts.iris.slam.dao.SlideDAO;
import fr.bts.iris.slam.exporter.HTMLExporter;
import fr.bts.iris.slam.model.Session;
import fr.bts.iris.slam.model.Slide;

import javax.swing.text.html.HTML;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:slideshow.db")) {
            SessionDAO sessionDAO = new SessionDAO(conn); // Crée la table sessions
            SlideDAO slideDAO = new SlideDAO(conn); // Crée la table slides
            Scanner sc = new Scanner(System.in);
            HTMLExporter exporter= new HTMLExporter();
            while (true){
                System.out.print("""
                    === SLIDESHOW CREATOR ===
                    1. Créer nouvelle session
                    2. Charger session
                    3. Lister sessions
                    
                    0. Quitter
                    
                    :""");
                int input;
                do {
                    input = sc.nextInt();
                }while (input<0 || input>3);
                sc.nextLine();
                if (input ==0){
                    return;
                }

                if (input == 1) {
                    String name = "";
                    System.out.print("Session Name : ");
                    name = sc.nextLine();
                    try {
                        sessionDAO.save(new Session(name));
                        System.out.println("Session Successfully created");
                    } catch (IllegalArgumentException e) {
                        System.err.println("Can't create session : " + e.getMessage());
                    }

                }

                if (input == 2) {
                    String name = "";
                    System.out.print("Session Name : ");
                        name = sc.nextLine();

                    Optional<Session> session = sessionDAO.findByNameWithSlides(name);

                    if (session.isEmpty()) {
                        System.out.println("This session doesn't exist");
                    }else {

                        boolean active = true;

                        while (active){
                            System.out.print("--- Session : "+ session.get().getName()+" ---\n" +
                                    "4. Ajouter slide\n" +
                                    "5. Afficher slides\n" +
                                    "6. Sauvegarder\n" +
                                    "7. Fermer session\n" +
                                    "8. Exporter en HTML"+
                                    ":");

                            do {
                                input = sc.nextInt();
                            }while (input<4 || input>8);
                            sc.nextLine();
                            if (input == 4){
                                String title = " ";
                                System.out.print("Slide Title  : ");
                                    title  = sc.nextLine();
                                String titleColor = " ";
                                System.out.print("Slide TitleColor  : ");
                                    titleColor  = sc.nextLine();
                                String imagePath = " ";
                                System.out.print("Slide Image Path  : ");
                                    imagePath  = sc.nextLine();
                                int position = 0;
                                System.out.print("Slide position  : ");
                                    position  = sc.nextInt();
                                    sc.nextLine();
                                try {
                                    session.get().addSlide(new Slide(title,titleColor,imagePath,position));
                                    System.out.println("Session Successfully created");
                                } catch (IllegalArgumentException e) {
                                    System.err.println("Can't create session : " + e.getMessage());
                                }
                            }

                            if (input == 5) {
                                ArrayList<Slide> slides = slideDAO.findBySessionId(session.get().getId());
                                System.out.println(slides.size());
                                for (Slide slide : slides){
                                    System.out.println(slide.display());
                                }
                            }

                            if (input == 6) {
                                sessionDAO.save(session.get());
                                session = sessionDAO.findByName(name);
                            }

                            if (input == 7) {
                                active = false;
                            }

                            if (input == 8) {
                                String outputPath;
                                System.out.print("Output path  : ");
                                outputPath  = sc.nextLine();
                                exporter.export(session.get(),outputPath);
                                System.out.println("Export complete file : "+outputPath);
                            }
                        }
                    }
                }

                if (input == 3) {
                    List<Session> sessions = sessionDAO.findAll();

                    for (Session session : sessions){
                        System.out.println(session.getId() + " :  " + session.getName());
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }
}
