#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>
/* ΝΙΚΟΛΑΟΣ ΜΠΟΥΣΙΟΣ-2394*/
/* Σκοπός Προγράμματος: 
-Υλοποίηση 3 αλγορίθμων ταξινόμησης: Insertionsort, Mergesort, Selectionsort
-Μεγέθη πινάκων από Ν=20.000-200.000 με βήμα 20.000
-Για κάθε Ν δημιουργία 10 τυχαίων στιγμιοτύπων με rand
-Χρονομέτρηση του χρόνου εκτέλεσης κάθε αλγορίθμου
-Υπολογισμός μέσου όρου χρόνων ανά αλγόριθμο
-Αποθήκευση αποτελεσμάτων σε αρχέιο results.csv & δημιουργία γραφήματος */
typedef int item; // Τύπος των στοιχείων

/* Κάποια χρήσιμα pre-defined στοιχεία (macros)*/
#define key(A) (A) // Επιστρέφει την τιμή που χρησιμοποιείται για τη σύκριση. Σε απλές περιπτώσεις είναι η ίδια η τιμή
#define less(A, B) (key(A) < key(B)) // Ελέγχει αν το στοιχείο Α είναι μικρότερο από το Β
#define exch(A, B) { item t = (A); (A) = (B); (B) = t; } // Ανταλάσσει τις τιμές δύο μεταβλητών χρησιμοποιώντας μια προσωρινή μεταβλητή 
#define compexch(A, B) if (less((A), (B))) exch((A), (B)) // Compare and exchange. Συγκρίνει 2 στοιχεία και αν το 1ο είναι μικρότερο από το 2ο, τα ανταλάσσει

/* Πρωτότυπα αλγορίθμων*/
void insertionsort(item *a, int n);
void selectionsort(item *a, int n);
void mergesort(item *a, int l, int r);
void merge(item *a, int l, int m, int r);

/*Βοηθητικές συναρτήσεις*/
void fill_random(item *a, int n);
void copy_array(item *dst, const item *src, int n);
int  is_sorted(const item *a, int n);

/*Global βοηθητικός πίνακας mergesort*/
static item *aux = NULL;

void fill_random(item *a,int N){ // Συνάρτηση για γέμισμα πίνακα με τυχαίους ακεραίους
	int i;
	for(i=0;i<N;i++){
		a[i]=rand();
	}
}

void copy_array(item *dstn, const item *source, int N){ // Συνάρτηση για αντιγραφή πίνακα 
	memcpy(dstn,source,(size_t)N*sizeof(item));
}

int is_sorted(const item *a, int N){ // Έλεγχος αν είναι ταξινομημένος ο πίνακας
    int i;
    for(i = 1; i < N; i++){
        if(a[i] < a[i-1]) return 0;
    }
    return 1;
}	
/* Αυτοί οι κώδικες είναι υλοποιημένοι σε ήδη υπάρχοντα αρχεία, οπότε δεν χρειάζεται να τα αναλύσουμε πολύ*/
/* INSERTION SORT: Για κάθε i, ο αλγ. παίρνει το a[i] και το εισάγει στη σωστή θέση στο ταξινομημένο τμήμα [0..i-1]
Πολυπλοκότητα Ο[n^2]*/
void insertionsort(item *a, int n) {
    int i, j;
    item temp;

    for (i = 1; i < n; i++) {
        compexch(a[i], a[0]);
    }

    for (i = 2; i < n; i++) {
        j = i;
        temp = a[i];

        while (j > 0 && less(temp, a[j - 1])) { // Το j>0 εγγυάται ότι δεν θα προσπελάσουμε το a[-1]
            a[j] = a[j - 1];
            j--;
        }
        a[j] = temp;
    }
}
/* SELECTION SORT: Για κάθε i, ο αλγ.βρίσκει το ελάχιστο στο [i..n-1] και το ανταλλάσει με a[i]
Πολυπλοκότητα Ο[n^2]*/
void selectionsort(item *a, int n) {
	int i, j, min;
	for (i = 0; i <n-1; i++) {
		min = i;
		for (j = i+1; j <n; j++) {
			if (less(a[j], a[min])) 
				min = j;
		}
		exch(a[i], a[min]); 
	}
}
/* MERGESORT: Διαιρεί τον πίνακα, ταξινομεί αναδρομικά και συγχωνεύει
Πολυπλοκότητα Ο[nlogn]*/
void mergesort(item a[], int l, int r) {
	/* τερματισμός αναδρομικής κλήσης */
	if (r <= l) return;
	/* εύρεση σημείο διαχωρισμού */
	int m = (r + l) / 2;
	/* αναδρομική κλήση για το πρώτο μισό */
	mergesort(a, l, m);
	/* αναδρομική κλήση για το δεύτερο μισό */
	mergesort(a, m + 1, r);
	/* συγχώνευση κομματιών */
	merge(a, l, m, r);
}
void merge(item a[], int l, int m, int r) {
	int i, j, k;
	/* αντιγραφή στον βοηθητικό πίνακα του πρώτου υποπίνακα */
	for (i = m + 1; i > l; i--) 
		aux[i - 1] = a[i - 1]; 
	/* αντιγραφή στον βοηθητικό πίνακα του αντιστραμμένου δεύτερου υποπίνακα */
	for (j = m; j < r; j++) 
		aux[r + m - j] = a[j + 1];
	/* συγχώνευση */
	for (k = l; k <= r; k++)
		if (less(aux[j], aux[i])) 
			a[k] = aux[j--]; 
		else a[k] = aux[i++];
}
/* MAIN Συνάρτηση*/

int main() {
	/* Αρχικός αριθμός Ν=20000, Τελικός=200.000 και Βήμα= 20.000*/
	const int startingNUM =20000;
	const int endingNUM  = 200000;
    const int stepNUM = 20000;
    const int trials = 10;
	
	srand((unsigned)time(NULL)); // Συνάρτηση για την αρχικοποίηση της γεννήτριας τυχαίων αριθμών της C

	FILE *csv = fopen("results.csv","w"); // Δημιουργία ενός αρχείου CSV(Αν υπάρχει ήδη, θα καταστραφεί και θα ξαναδημιουργηθεί από την αρχή)
	if (!csv){ // Έλεγχος Σφάλματος
		fprintf(stderr,"Error: Cannot create file where the compilation results will be saved\n");
		return 1;
	}
	fprintf(csv, "N,insertion_avg_s,selection_avg_s,merge_avg_s\n"); // Κεφαλίδα (Πρώτη Γραμμή) του αρχείου
	
	printf("Benchmark: N=%d..%d (step %d), trials=%d\n\n", startingNUM, endingNUM, stepNUM, trials); // Ενημέρωση του χρήστη για τις παραμέτρους της δοκιμής(αρχικός αριθμός, τελικός, βήμα & πλήθος δοκιμών)
    printf("%-8s | %-16s %-16s %-16s\n", "N", "Insertion(s)", "Selection(s)", "Merge(s)"); // Το %-[num]s είναι string formatting. Δεσμεύει num χαρακτήρες για το κείμενο με αριστερή στοίχιση, ώστε οι στήλες στην οθόνη να είναι τελεια ευθυγραμμισμένες σαν πίνακας
    printf("--------------------------------------------------------------------------\n"); // Για αισθητικούς λόγους βάζουμε τερματική διακεκομμένη γραμμή

	int n;
	for (n = startingNUM; n <= endingNUM; n += stepNUM){
		/* Ο κώδικας χρησιμοποιεί malloc για δυναμική δέσμευση μνήμης runtime*/
		item *base = (item *)malloc((size_t)n * sizeof(item)); // Αρχικός πίνακας με τους τυχαίους αριθμούς(πρότυπο) 
        item *a1   = (item *)malloc((size_t)n * sizeof(item)); // Αντίγραφα του base για να ταξινομηθούν από τους 3 αλγορίθμους
        item *a2   = (item *)malloc((size_t)n * sizeof(item)); // Αντίγραφα του base για να ταξινομηθούν από τους 3 αλγορίθμους
        item *a3   = (item *)malloc((size_t)n * sizeof(item)); // Αντίγραφα του base για να ταξινομηθούν από τους 3 αλγορίθμους

        if (!base || !a1 || !a2 || !a3) { // Έλεγχος ύπαρξης των πινάκων
            fprintf(stderr, "Error: malloc failed for n=%d\n", n);
            return 1;
        }
		aux = (item *)malloc((size_t)n * sizeof(item)); // Βοηθητικός πίνακας για τον MergeSort,ο οποίος χρειάζεται έναν επιπλέον χώρο για να ανακατέψει τα στοιχεία κατά τη συγχώνευση
        if (!aux) {
            fprintf(stderr, "Error: malloc failed for aux (n=%d)\n", n); // Έλεγχος ύπαρξης του βοηθητικού πίνακα
            return 1;
        }

        double sum_ins = 0.0; // Το άθροισμα των χρόνων για InsertionSort
		double sum_sel = 0.0; // Το άθροισμα των χρόνων για SelectionSort
		double sum_mer = 0.0; // Το άθροισμα των χρόνων για MergeSort

        /* Μεταβλητές χρονομέτρησης*/
        clock_t start, end;
        double cpu_time_used;
		int t;
		for(t=0;t<trials;t++){
			/* Νέο τυχαίο στιγμιότυπο*/
			fill_random(base,n);
			
			/*Insertionsort*/
			copy_array(a1,base,n); // Αντιγράφουμε τα τυχαία στοιχεία στον πίνακα a1 
			start = clock(); // Παίρνουμε την ώρα ακριβώς πριν την έναρξη
			insertionsort(a1,n); // Εκτελείται η ταξινόμηση
			end =clock(); // Παίρνουμε την ώρα αμέσως μετά τον τερματισμό
			cpu_time_used = ((double)(end - start)) / CLOCKS_PER_SEC;
            sum_ins += cpu_time_used; // Προσθέτουμε τον χρόνο στον "κουμπαρά" του συγκεκριμένου αλγορίθμου
            
            /*Selectionsort*/
            copy_array(a2, base, n); // Ίδια λογική
            start = clock();
            selectionsort(a2, n);
            end = clock();
            cpu_time_used = ((double)(end - start)) / CLOCKS_PER_SEC;
            sum_sel += cpu_time_used;
            
            /* Mergesort*/
            copy_array(a3, base, n);
            start = clock();
            mergesort(a3, 0, n - 1);
            end = clock();
            cpu_time_used = ((double)(end - start)) / CLOCKS_PER_SEC;
            sum_mer += cpu_time_used;
			
			/* Προαιρετικός έλεγχος αν είναι σωστά ταξινομημένοι οι πίνακες*/
			if (!is_sorted(a1, n) || !is_sorted(a2, n) || !is_sorted(a3, n)) {
                fprintf(stderr, "Error: not sorted (n=%d, trial=%d)\n", n, t);
                return 1;
            }
		}
		/* Υπολογισμός των μέσων όρων εκτέλεσης των αλγορίθμων*/
		double avg_ins = sum_ins / trials;
        double avg_sel = sum_sel / trials;
        double avg_mer = sum_mer / trials;
		
		/* Εκτύπωση των μέσων όρων και συγγραφή τους στο αρχείο csv για στατιστική μελέτη*/
		printf("%-8d | %-16.6f %-16.6f %-16.6f\n", n, avg_ins, avg_sel, avg_mer);
        fprintf(csv, "%d,%.9f,%.9f,%.9f\n", n, avg_ins, avg_sel, avg_mer);
		/* Απελευθέρωση δεσμευμένης μνήμης όλων των πινάκων πριν πάμε στο επόμενο n*/
        free(base);
        free(a1);
        free(a2);
        free(a3);
        free(aux);
        aux = NULL;
}
	/* Κλείσιμο του αρχείου*/
	fclose(csv);
    printf("\nThe file results.csv has been created!\n");
    return 0;
}












	


