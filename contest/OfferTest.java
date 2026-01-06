package first_task;
import java.util.*;

public class OfferTest {
	 public static void main(String[] args) {
	        Scanner input = new Scanner(System.in);
	        ArrayList<Contest> contests = new ArrayList<>();

	        while (true) { // Μενού επιλογής δυνατοτήτων
	            System.out.println("1: Εισαγωγή νέου διαγωνισμού");
	            System.out.println("2: Εισαγωγή νέας προσφοράς");
	            System.out.println("3: Αναζήτηση προσφοράς");
	            System.out.println("4: Εκτύπωση πιο συμφέρουσας προσφοράς");
	            System.out.println("0: Έξοδος"); // Επιλογή τερματισμού while loop 
	            System.out.print("Επιλογή αριθμού: ");
	            int choice = input.nextInt();
	            input.nextLine();

	            switch (choice) {
	                case 1 -> { //Σύνταξη στην Java 17 για αποφυγή χρήσης των keywords "break" και "default"
	                    System.out.print("Τίτλος διαγωνισμού: "); 
	                    String title = input.nextLine(); // Εισαγωγή τίτλου διαγωνισμού από το πληκτρολόγιο
	                    System.out.print("Πλήθος εταιρειών: ");
	                    int companyNum = input.nextInt(); // Εισαγωγή πλήθους εταιριών συμμετοχής στον διαγωνισμό από το πληκτρολόγιο
	                    Contest contest = new Contest(title);
	                    for (int i = 0; i < companyNum; i++) { // Προσθήκη σε κάθε διαγωνισμό τόσο πλήθος προσφορών, όσες και οι εταιρίες
	                        Offer offer = new Offer();
	                        contest.addOffer(offer);
	                    }
	                    contests.add(contest); // Προσθήκη διαγωνισμού στον πίνακα με τους διαγωνισμούς
	                    contest.evaluateOffers();
	                    System.out.println("Ο διαγωνισμός έχει προστεθεί.");
	                }
	                case 2 -> {
	                    if (contests.size() <2) { // Σε περίπτωση που υπάρχουν 0 ή 1 διαγωνισμοί
	                        System.out.println("Νέα προσφορά ΜΟΝΟ στον δεύτερο διαγωνισμο.");
	                        break;
	                    }
	                    else {
	                    	
	                         Offer newOffer = new Offer();
	                         contests.get(1).addOffer(newOffer);
	                         contests.get(1).evaluateOffers();
	                        System.out.println("Προστέθηκε μία νέα προσφορά."); 
	                    }
	                }
	                case 3 -> {
	                    if (contests.isEmpty()) {
	                        System.out.println("Δεν υπάρχουν διαγωνισμοί.");
	                        break;
	                    }
	                    System.out.print("Αναζήτηση με: 1) ID ή 2) Όνομα εταιρείας ");
	                    int option = input.nextInt();
	                    input.nextLine();
	                    Contest last = contests.get(contests.size() - 1); // Αρίθμηση από 0-size-1, επιλογή του τελευταίου (τρέχοντος) διαγωνισμού
	                    if (option == 1) {
	                        System.out.print(" Δώσε το ID: ");
	                        int ID = input.nextInt();
	                        for(Offer offer: last.getOffers()){
	                        	if (offer.getId()==ID) {
	                        		System.out.printf("Βρέθηκε προσφορά με βάση το ID και είναι της εταιρίας %s με τελική αξιολόγηση %.2f\n",offer.getCompanyName(),offer.getFinalEvaluation());
	                        	}
	                        	else {
	                        		System.out.println("Δεν βρέθηκε προσφορά με βάση το ID");
	                        	}
	                        }
	                      
	                    } else if (option==2){
	                        System.out.print("Όνομα εταιρείας: ");
	                        String name = input.nextLine();
	                        for(Offer offer: last.getOffers()){
	                        	if (offer.getCompanyName()==name) {
	                        		System.out.printf("Βρέθηκε προσφορά με βάση το όνομα εταιρίας %s με τελική αξιολόγηση %.2f\n",offer.getCompanyName(),offer.getFinalEvaluation());
	                        	}
	                        	else {
	                        		System.out.println("Δεν βρέθηκε προσφορά με βάση το όνομα εταιρίας");
	                        	}
	                        }
	                    }else {
	                    	System.out.print("Ο αριθμός πρέπει να είναι 1 ή 2");
	                    }
	                }
	                
	                   
	                case 4 -> {
	                    if (contests.isEmpty()) {
	                        System.out.println("Δεν υπάρχουν διαγωνισμοί.");
	                        break;
	                    }
	                    Contest last = contests.get(contests.size() - 1);
	                    Offer best = last.getBestOffer(); // Χρησιμοποιούμε την συνάρτηση getBestOffer της κλάσης Offer για να βρούμε την καλύτερη προσφορά
	                    System.out.printf("Η πλέον συμφέρουσα προσφορά είναι της εταιρίας %s με τελική αξιολόγηση %.2f\n",best.getCompanyName(),best.getFinalEvaluation());
	                }
	                case 5 -> { // Χρησιμοποιούμε την συνάρτηση getOffersAboveGivenNum() με όρισμα 50 της κλάσης Contest για να βρούμε τις προσφορές με FinalEvaluation > 50
	                	if (contests.isEmpty()) {
	                        System.out.println("Δεν υπάρχουν διαγωνισμοί.");
	                        break;
	                    }
	                    Contest last = contests.get(contests.size() - 1);
	                    ArrayList<Offer> OffersAbove50 = last.getOffersAboveGivenNum(50); 
	                    for (Offer offer : OffersAbove50) {
	                        System.out.println(offer);
	                    }
	                }
	            }
	        }
	 }
}
