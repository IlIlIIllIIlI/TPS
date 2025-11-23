public class MovieSlotTestData {
    
    // Données valides pour tester la fonctionnalité de base
    // Format : [title, startTime, duration, room]
    public static String[][] getValidMovieSchedule() {
        return new String[][] {
            {"Avatar", "14h00", "120", "Salle 1"},
            {"Inception", "16h30", "150", "Salle 2"},
            {"Top Gun", "19h00", "105", "Salle 3"},
            {"Dune", "20h00", "130", "Salle 4"},
            {"John Wick", "21h30", "95", "Salle 5"}
        };
    }
    
    // Données problématiques pour tester fail-fast
    // La première erreur doit arrêter tout le processus
    public static String[][] getProblematicMovieSchedule() {
        return new String[][] {
            {"Avatar", "14h00", "120", "Salle 1"},     // Valide
            {"Inception", "16h30", "150", "Salle 2"},  // Valide
            {"", "19h00", "105", "Salle 3"},           // title vide - ERREUR
            {"Dune", "20h00", "130", "Salle 4"},       // Ne sera jamais traité (fail-fast)
            {"John Wick", "21h30", "95", "Salle 5"}    // Ne sera jamais traité (fail-fast)
        };
    }
    
    // Données avec doublons pour tester la règle "un film par jour"
    public static String[][] getDuplicateMovieSchedule() {
        return new String[][] {
            {"Avatar", "14h00", "120", "Salle 1"},     // Première programmation d'Avatar
            {"Inception", "16h30", "150", "Salle 2"},  // OK
            {"Top Gun", "19h00", "105", "Salle 3"},    // OK
            {"Avatar", "21h00", "120", "Salle 4"},     // DOUBLON - Avatar déjà programmé
            {"Dune", "22h00", "130", "Salle 5"}        // Ne sera jamais traité (fail-fast)
        };
    }
    
    // Données avec conflits de salles/horaires
    public static String[][] getConflictingSchedule() {
        return new String[][] {
            {"Avatar", "14h00", "120", "Salle 1"},     // Film de 14h à 16h en Salle 1
            {"Inception", "16h30", "150", "Salle 2"},  // OK - Salle différente
            {"Top Gun", "15h00", "105", "Salle 1"},    // CONFLIT - Salle 1 occupée jusqu'à 16h
            {"Dune", "19h00", "130", "Salle 3"},       // Ne sera jamais traité (fail-fast)
            {"John Wick", "21h00", "95", "Salle 4"}    // Ne sera jamais traité (fail-fast)
        };
    }
    
    // Données avec valeurs invalides
    public static String[][] getInvalidMovieSchedule() {
        return new String[][] {
            {"Avatar", "14h00", "120", "Salle 1"},     // Valide
            {"Inception", "16h30", "-30", "Salle 2"},  // duration négative - ERREUR
            {"Top Gun", "19h00", "abc", "Salle 3"},    // duration non numérique - ERREUR
            {"Dune", "format", "120", "Salle 4"},      // Format startTime invalide - ERREUR
            {"John Wick", "20h00", "150", ""},         // room vide - ERREUR
        };
    }
    
    // Grande liste pour tester les performances
    public static String[][] getLargeMovieSchedule() {
        return new String[][] {
            {"Film Matin 1", "09h00", "120", "Salle 1"},
            {"Film Matin 2", "11h30", "105", "Salle 2"},
            {"Film Après-midi 1", "14h00", "130", "Salle 3"},
            {"Film Après-midi 2", "16h30", "95", "Salle 4"},
            {"Film Soirée 1", "19h00", "140", "Salle 1"},
            {"Film Soirée 2", "19h30", "110", "Salle 2"},
            {"Film Soirée 3", "21h00", "125", "Salle 3"},
            {"Film Soirée 4", "21h30", "100", "Salle 4"},
            {"Film Tardif 1", "22h00", "90", "Salle 5"},
            {"Film Tardif 2", "22h30", "95", "Salle 6"}
        };
    }
}