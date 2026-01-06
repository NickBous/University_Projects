package first_task;
import java.math.*;
// Κλάση Offer που αντιπροσωπεύει μία προσφορά
public class Offer {
    private static int IdCount = 0; // Μετρητής για την αυτόματη εκχώρηση αριθμού πρωτοκόλλου
    private int id; // Ο αριθμός πρωτοκόλλου κάθε προσφοράς
    private String CompanyName; // Το όνομα της εταιρίας
    private double TechEvaluation; // Η τεχνική αξιολόγηση
    private double FinancialOffer; // Η οικονομική προσφορά
    private double FinalEvaluation; // Η τελική αξιολόγηση
    
    // Κατασκευαστής με τυχαία αρχικοποίηση των δεδομένων
    public Offer() {
        this.id = IdCount++; // Κάθε φορά που δημιουργείται ένα αντικείμενο, ο μετρητής που έιναι ο αριθμ. πρωτοκόλλου αυξάνεται κατά 1
        this. CompanyName = createRandomName();
        this.TechEvaluation = (Math.random() * (100 - 1 + 1)) + 1; // Αντιστοιχίζουμε έναν τυχαίο ακέραιο από 1-100
        this.FinancialOffer = (Math.random() * (120000 - 1000 + 1)) + 1000; // Αντιστοιχίζουμε έναν τυχαίο ακέραιο από 1000-120000
    }    
    
    private String createRandomName() { // Δημιουργία τυχαίου ονόματος εταιρίας
    	StringBuilder CompanyName = new StringBuilder(); // Built-in class της String 
        int LengthOfName = (int) (Math.random() * 10) + 3; // Αντιστοιχίζουμε έναν τυχαίο ακέραιο από 3-13, ο οποίος θα είναι το ενδεχόμενο μήκος του ονόματος της εταιρίας
        
        // Δημιουργούμε τυχαίους αγγλικούς χαρακτήρες (A-Z) χρησιμοποιώντας δομή επανάληψης 
        for (int i = 0; i < LengthOfName; i++) {
            char randomChar = (char) ('Α' + (int) (Math.random() * 26)); // Χρησιμοποιόυμε τυχαία τον χαρακτήρα "A" ως σταθερά και κάνουμε generate άλλους αγγλικούς χαρακτήρες μέχρι όλο το μήκος της συμβολοσειράς.
            CompanyName.append(randomChar);
        }
        
        // Βάζουμε και μία τυχαία κατάληξη στην εταιρία μας
        String[] potentialSuffixes = {" Corp.", " L.L.C.", " A.G.", " Inc.", " Ltd."};
        CompanyName.append(potentialSuffixes[(int) (Math.random() * potentialSuffixes.length)]); // Προσθήκη τυχαίου στοιχείου από τις ενδεχόμενες καταλήξεις ονόματος για εταιρία (δική μας προσέγγιση)
        
        return CompanyName.toString();
	}
	// Μέθοδος για τον υπολογισμό της τελικής βαθμολογίας με βάση τον δοσμένο τύπο
    public double calculateFinalEvaluation(double maxTech, double minFinancial) {
        this.FinalEvaluation = (80 * (this.TechEvaluation / maxTech)) + (20 * (minFinancial / this.FinancialOffer));
        return this.FinalEvaluation;
    }
    
    // Μέθοδοι Getters για προσπέλαση δεδομένων με private access indentifier
    public int getId() { 
    	return id; 
    }
    public String getCompanyName() { 
    	return CompanyName; 
    }
    public double getTechEvaluation() { 
    	return TechEvaluation; 
    }
    public double getFinancialOffer() { 
    	return FinancialOffer; 
    }
    public double getFinalEvaluation() { 
    	return FinalEvaluation; 
    }
    
    // Εμφάνιση δεδομένων της προσφοράς
    public void displayInfo() {
        System.out.printf("ID: %d, Όνομα Εταιρείας: %s, Τεχνική Αξιολόγηση: %.2f, Οικονομική Προσφορά: €%.2f, Τελική Αξιολόγηση: %.2f\n", 
                          id, CompanyName, TechEvaluation, FinancialOffer, FinalEvaluation);
    }
}