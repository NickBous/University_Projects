package ergasia_b;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ClinicSystem clinicSystem = new ClinicSystem();


        // Προσθέτουμε τυχαίους γιατρούς
        Doctor doctor1 = new Doctor("drsmith", "pass123", "John", "Smith", "Cardiology", "123 Main St", "1234567890", "1975-05-20");
        Doctor doctor2 = new Doctor("drjones", "pass456", "Emily", "Jones", "Cardiology", "456 Elm St", "9876543210", "1980-10-15");

        clinicSystem.addDoctor(doctor1);
        clinicSystem.addDoctor(doctor2);

        // Προσθέτουμε τυχαίες υπηρεσίες
        Service service1 = new Service("General Checkup", 30);
        service1.addDoctor(doctor1);
        clinicSystem.addService(service1);

        Service service2 = new Service("Cardiac Graph", 30);
        service2.addDoctor(doctor2);
        clinicSystem.addService(service2);
        
        // Προσθέτουμε τυχαίους ασθενείς
        Patient patient1 = new Patient("user1", "123", "Alice", "Brown", "1990-01-01", "789 Oak St", "1111111111");
        patient1.addDiagnosis("Arrythmia - 2023-12-01");

        Patient patient2 = new Patient("user2", "456", "Bob", "White", "1985-07-15", "321 Pine St", "2222222222");
        patient2.addDiagnosis("Coronary Heart Disease - 2023-12-02");

        Patient patient3 = new Patient("user3", "789", "Charlie", "Green", "1978-09-23", "654 Maple St", "3333333333");
        patient3.addDiagnosis("Rapid Heartbeat - 2023-12-03");

        clinicSystem.addPatient(patient1);
        clinicSystem.addPatient(patient2);
        clinicSystem.addPatient(patient3);

        // Προσθέτουμε τυχαίες γραμματείες
        Secretary secretary1 = new Secretary("sec1", "1234", "Maria", "Papadopoulou", "2101234567", "6974674345", "1991-04-01", "maria.papadopoulou@example.com");
        Secretary secretary2 = new Secretary("sec2", "5678", "Eleni", "Georgiou", "2107654321", "6978789896", "1988-07-04", "eleni.georgiou@example.com");

        clinicSystem.addSecretary(secretary1);
        clinicSystem.addSecretary(secretary2);

        // Προσθέτουμε τυχαία ραντεβού
        Appointment appt1 = new Appointment(LocalDate.now(), LocalTime.of(10, 0), patient1, doctor1, service1);
        Appointment appt2 = new Appointment(LocalDate.now(), LocalTime.of(11, 0), patient2, doctor2, service2);
        Appointment appt3 = new Appointment(LocalDate.now(), LocalTime.of(12, 0), patient3, doctor2, service2);

        clinicSystem.addAppointment(appt1);
        clinicSystem.addAppointment(appt2);
        clinicSystem.addAppointment(appt3);

        // Δημιουργούμε ένα απλό login system
        System.out.println("Welcome to the Clinic Appointment System!");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Ελέγχουμε αν το login είναι από γιατρό
        Doctor loggedInDoctor = null;
        for (Doctor doc : clinicSystem.getDoctors()) {
            if (doc.login(username, password)) {
                loggedInDoctor = doc;
                break;
            }
        }

        // Ελέγχουμε αν το login είναι από ασθενή
        Patient loggedInPatient = null;
        for (Patient pat : clinicSystem.getPatients()) {
            if (pat.login(username, password)) {
                loggedInPatient = pat;
                break;
            }
        }

        // Ελέγχουμε αν το login είναι από γραμματεία
        Secretary loggedInSecretary = null;
        for (Secretary sec : clinicSystem.getSecretaries()) {
            if (sec.login(username, password)) {
                loggedInSecretary = sec;
                break;
            }
        }

        // Εμφάνιση ανάλογου μενού
        if (loggedInDoctor != null) {
            System.out.println("Doctor logged in successfully.");
            System.out.println("Today's Appointments:");
            ArrayList<Appointment> todaysAppointments = clinicSystem.getAppointmentsByDate(LocalDate.now());
            for (Appointment a : todaysAppointments) {
                if (a.getDoctor().equals(loggedInDoctor)) {
                    System.out.println(a);
                }
            }

        } else if (loggedInSecretary != null) {
            System.out.println("Secretary logged in successfully.");
            System.out.println("All appointments for today:");
            loggedInSecretary.showMenu(clinicSystem);

        } else if (loggedInPatient != null) {
            System.out.println("Patient logged in successfully.");
            System.out.println("Your appointments for today:");
            ArrayList<Appointment> todaysAppointments = clinicSystem.getAppointmentsByDate(LocalDate.now());
            boolean patientFound = false;
            for (Appointment a : todaysAppointments) {
                if (a.getPatient().equals(loggedInPatient)) {
                    System.out.println(a);
                    patientFound = true;
                }
            }
            if (!patientFound) {
                System.out.println("You have no appointments today.");
            }

        } else {
            System.out.println("Login failed. Invalid username or password.");
        }

        scanner.close();
    }
}



