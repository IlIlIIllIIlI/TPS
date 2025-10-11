public class TaskTestData {
    
    // Données valides pour tester la fonctionnalité de base
    public static String[][] getValidTasks() {
        return new String[][] {
            {"Faire les courses", "Acheter légumes et fruits", "HIGH"},
            {"Réviser Java", "Préparer examen POO", "HIGH"},
            {"Nettoyer bureau", "Ranger et organiser espace travail", "MEDIUM"},
            {"Lire livre", "Finir chapitre 5", "LOW"},
            {"Répondre emails", "Traiter boîte mail professionnelle", "MEDIUM"}
        };
    }
    
    // Données problématiques pour tester la robustesse (fail-safe)
    public static String[][] getProblematicTasks() {
        return new String[][] {
            {"Tâche valide", "Description OK", "HIGH"},        // Valide
            {"", "Description vide", "MEDIUM"},                // Titre vide
            {"Titre OK", "", "LOW"},                           // Description vide
            {"Autre tâche", "Description", "INVALID"},         // Priorité invalide
            {"Tâche incomplète", "Description"},               // Données manquantes
            {null, "Description", "HIGH"},                     // Titre null
            {"Titre", "Description", ""},                      // Priorité vide
            {"Dernière valide", "Description finale", "LOW"}   // Valide
        };
    }
    
    // Grande liste pour tester les performances
    public static String[][] getLargeTaskSet() {
        return new String[][] {
            {"Tâche 1", "Description de la tâche 1", "HIGH"},
            {"Tâche 2", "Description de la tâche 2", "MEDIUM"},
            {"Tâche 3", "Description de la tâche 3", "LOW"},
            {"Tâche 4", "Description de la tâche 4", "HIGH"},
            {"Tâche 5", "Description de la tâche 5", "MEDIUM"},
            {"Tâche 6", "Description de la tâche 6", "LOW"},
            {"Tâche 7", "Description de la tâche 7", "HIGH"},
            {"Tâche 8", "Description de la tâche 8", "MEDIUM"},
            {"Tâche 9", "Description de la tâche 9", "LOW"},
            {"Tâche 10", "Description de la tâche 10", "HIGH"}
        };
    }
}