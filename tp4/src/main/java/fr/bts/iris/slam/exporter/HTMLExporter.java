package fr.bts.iris.slam.exporter;

import fr.bts.iris.slam.model.Session;
import fr.bts.iris.slam.model.Slide;
import java.io.*;
import java.nio.file.*;

/**
 * Exporte une session en fichier HTML visualisable dans un navigateur.
 * Utilise un template HTML séparé pour la présentation.
 * 
 * ⚠️ IMPORTANT - Chemins d'images :
 * Les chemins d'images dans les Slide doivent être RELATIFS au fichier HTML généré.
 * 
 * Exemple :
 *   ✅ new Slide("Intro", "#FF0000", "intro.jpg")              // chemin relatif
 *   ❌ new Slide("Intro", "#FF0000", "C:\\Photos\\intro.jpg")  // chemin absolu
 * 
 * Organisation des fichiers pour que ça fonctionne :
 *   presentation.html  ← fichier HTML généré
 *   intro.jpg          ← images au même niveau
 *   slide1.png
 */

public class HTMLExporter {
    
    private static final String TEMPLATE_PATH = "slide_template.html"; //le fichier slide_template.html doit être dans les ressources du projet !
    
    /**
     * Exporte une session complète en fichier HTML.
     * @param session La session à exporter
     * @param outputPath Le chemin du fichier de sortie (ex: "presentation.html")
     */
    public void export(Session session, String outputPath) {
        // Étapes suggérées :
        // 1. Charger le template avec loadTemplate()
        // 2. Remplacer {{SESSION_NAME}} par session.getName() (à l'aide de .replace())
        // 3. Générer le HTML de tous les slides à l'aide de buildSlideHTML() et les concaténer
        // 4. Remplacer {{SLIDES_CONTENT}} par ce HTML concaténé
        // 5. Écrire le résultat dans outputPath avec writeToFile()

       String template =  this.loadTemplate();

       template = template.replace("{{SESSION_NAME}}",session.getName());

       StringBuilder slidesContent = new StringBuilder();

       for (Slide slide : session.getSlides()){
           slidesContent.append(this.buildSlideHTML(slide));
       }
       template = template.replace("{{SLIDES_CONTENT}}",slidesContent);


    writeToFile(template,outputPath);
    }
    
    /**
     * Écrit le contenu HTML dans un fichier.
     * @param htmlFileContent Le fichier HTML à écrire
     * @param outputPath Le chemin du fichier de sortie
     */
    private void writeToFile(String htmlFileContent, String outputPath) {
        try (FileWriter writer = new FileWriter(outputPath)) {
            writer.write(htmlFileContent);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write file: " + outputPath, e);
        }
    }
    
    /**
     * Génère le HTML pour un slide individuel.
     * @param slide Le slide à convertir en HTML
     * @return Une chaîne HTML représentant le slide
     */
    private String buildSlideHTML(Slide slide) {
        // Structure suggérée :
        // <div class="slide" style="background-image: url('...')">
        //     <h2 style="color: ...">Titre</h2>
        // </div>
        //
        // Le CSS et le JavaScript du template gèrent l'affichage et la navigation
        // Vous n'avez qu'à générer les divs avec les bonnes infos
        //
        // Utilisez les getters de Slide pour construire votre balisage

        return "<div class=\"slide\" style=\"background-image: url('"+slide.getImagePath()+"')\">\n" +
                "<h2 style=\"color: "+slide.getTitleColor()+"\">"+slide.getTitle()+"</h2>\n" +
                "</div>";
    }
    
    /**
     * Charge le template HTML depuis le fichier.
     * @return Le contenu du template comme String
     */
    private String loadTemplate() {
        try {
            // Chercher d'abord à la racine du projet
            Path templatePath = Paths.get(TEMPLATE_PATH);
            if (Files.exists(templatePath)) {
                return Files.readString(templatePath);
            }
            
            // Sinon chercher dans les resources
            InputStream is = getClass().getClassLoader().getResourceAsStream(TEMPLATE_PATH);
            if (is != null) {
                return new String(is.readAllBytes());
            }
            
            throw new FileNotFoundException("Template not found: " + TEMPLATE_PATH);
            
        } catch (IOException e) {
            throw new RuntimeException("Failed to load template", e);
        }
    }
}