package first_task;

import java.util.*; //Εισάγουμε όλα τα στοιχεία της κλάσης java.util
// Κλάση Contest που διαχειρίζεται προσφορές που δόθηκαν σε διαγωνσιμό
public class Contest {
    private static int contestCounter = 0; // Μετρητής διαγωνισμών 
    private ArrayList<Offer> offers = new ArrayList<>(); // Πίνακας που θα αποθηκεύει τις προσφορές
    private int contestNumber; // Αριθμός διαγωνισμού
    private String title; // Τίτλος διαγωνισμού

    public Contest(String title) { //Αρχικοποίηση τίτλου και αύξηση μετρητή κατά 1 στον κατασκευαστή της κλάσης
        this.title = title;
        this.contestNumber = ++contestCounter;
    }
    public  ArrayList<Offer> getOffers(){
    	return offers;
    }

    public void addOffer(Offer offer) { //Προσθήκη δοσμένης προσφοράς στον πίνακα 
        offers.add(offer);
    }
    public Offer getBestOffer() { // Συνάρτηση υπολογισμού βέλτιστης προσφοράς και επιστροφής της
        if (offers.isEmpty()) return null; // Επιστροφή μηδενικής τιμής σε περίπτωση κενής λίστας

        Offer bestOffer = offers.get(0); // Αρχικοποίηση του πρώτου στοιχείου ως την καλύτερη προσφορά και υπολογσιμός αλγοριθμικά
        for (Offer offer : offers) {
            if (offer.getFinalEvaluation() > bestOffer.getFinalEvaluation()) {
                bestOffer = offer;
            }
        }
        return bestOffer;
    }
    
    public Offer getLowestFinancialCost() {  // Συνάρτηση εύρεσης χαμηλότερου οικονομικού κόστους προσφορών
        if (offers.isEmpty()) return null; //Επιστροφή μηδενικής τιμής σε περίπτωση άδειου πίνακα προσφορών

        Offer lowest = offers.get(0); //Αρχικοποίηση πρώτου στοιχείου ως το χαμηλότερο και αλγοριθμική εύρεσή του
        for (Offer offer : offers) {
            if (offer.getFinancialOffer() < lowest.getFinancialOffer()) {
                lowest = offer;
            }
        }
        return lowest;
    }
    
    public Offer getTopTechEvaluation() { // Συνάρτηση εύρεσης καλύτερης τεχνολογικής αξιολόγησης
        if (offers.isEmpty()) return null; //Επιστροφή μηδενικής τιμής σε περίπτωση άδειου πίνακα προσφορών

        Offer top = offers.get(0); //Αρχικοποίηση πρώτου στοιχείου ως το βέλτιστο και αλγοριθμική εύρεσή του 
        for (Offer offer : offers) {
            if (offer.getTechEvaluation() > top.getTechEvaluation()) {
                top = offer;
            }
        }
        return top;
    }
    public void evaluateOffers() {
    	double maxTechnicalScore = 1; //Αρχικοποίηση πρώτου στοιχείου ως το βέλτιστο και αλγοριθμική εύρεσή του
    	double minFinancialOffer = 1; //Αρχικοποίηση πρώτου στοιχείου ως το βέλτιστο και αλγοριθμική εύρεσή του
    	for (Offer offer : offers) {
    	    if (offer.getFinalEvaluation() > maxTechnicalScore) {
    	    	 maxTechnicalScore = offer.getFinalEvaluation();
    	    }
    	}
    	for (Offer offer : offers) {
    	    if (offer.getFinalEvaluation()<  minFinancialOffer) {
    	    	 minFinancialOffer = offer.getFinalEvaluation();
    	    }
    	}

        for (Offer offer : offers) {
            offer.calculateFinalEvaluation(maxTechnicalScore, minFinancialOffer);
        }
    }
    
    public Offer getOfferbyId(int id) { // Συνάρτηση εύρεσης προσφοράς με βάση το ID της και επιστροφής του
        for (Offer offer : offers) {
            if (offer.getId() == id) {
                return offer;
            }
        }
        return null;
    }
    
    public Offer getOfferByCompanyName(String name) { // Συνάρτηση εύρεσης προσφοράς με βάση το όνομα εταιρίας και επιστροφής του
        for (Offer offer : offers) {
            if (offer.getCompanyName().equalsIgnoreCase(name)) { // Σύγκριση ομοιότητας ονομάτων εταιριών αγνοώντας το case sensitivity(ίδιοι χαρακτήρες με ενδεχόμενες διαφορές μόνο σε πεζά-κεφαλαία
                return offer;
            }
        }
        return null;
    }
    
    public ArrayList<Offer> getOffersAboveGivenNum(float givenNum) { //Συνάρτηση εύρεσης προσφοράς με τελική αξιολόγηση πάνω από δοσμένο αριθμό 
        ArrayList <Offer> results = new ArrayList<>(); // Δημιουργούμε νέο ArrayList που θα κρατά τις προσφορές που έχουν τελική αξιολόγηση πάνω από δοσμένο αριθμό 
          for (Offer offer : offers) {
              if (offer.getFinalEvaluation() > givenNum) {
                  results.add(offer);
              }
          }
          return results;
      }
   
    public void printOffers() { //Συνάρτηση τύπωσης του πίνακα με τις προσφορές
        for (Offer offer : offers) {
            System.out.println(offer);
        }
    }

   

    public int getcontestNumber() { // Μέθοδος επιστροφής του ονόματος του αριθμού του διαγωνισμού 
    	return contestNumber; 
    }
}
