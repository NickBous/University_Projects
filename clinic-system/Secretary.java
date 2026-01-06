package ergasia_b;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Secretary extends User {
	private String email;

	public Secretary(String username, String password, String firstName, String lastName, String address, String phone, String dateOfBirth, String email) {
	    super(username, password, firstName, lastName, address, phone,dateOfBirth);
	    this.phone = phone;
	    this.email = email;
	}
	
	public void showMenu(ClinicSystem clinicSystem) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("\nSecretary Menu:");
            System.out.println("1. View Today's Appointments");
            System.out.println("2. View Appointments by Date Range");
            System.out.println("3. Add New Doctor");
            System.out.println("4. Add New Patient");
            System.out.println("5. Add New Appointment");
            System.out.println("0. Logout");
            System.out.print("Choose an option: ");
            choice = Integer.parseInt(scanner.nextLine());

            if (choice == 1) {
                LocalDate today = LocalDate.now();
                ArrayList<Appointment> todaysAppointments = clinicSystem.getAppointmentsByDate(today);
                for (Appointment a : todaysAppointments) {
                    System.out.println(a);
                }
            } else if (choice == 2) {
                System.out.print("Enter start date (YYYY-MM-DD): ");
                LocalDate startDate = LocalDate.parse(scanner.nextLine());
                System.out.print("Enter end date (YYYY-MM-DD): ");
                LocalDate endDate = LocalDate.parse(scanner.nextLine());
                ArrayList<Appointment> appointmentsInRange = clinicSystem.getAppointmentsByDateRange(startDate, endDate);
                for (Appointment a : appointmentsInRange) {
                    System.out.println(a);
                }
            } else if (choice == 3) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                System.out.print("Enter first name: ");
                String firstName = scanner.nextLine();
                System.out.print("Enter last name: ");
                String lastName = scanner.nextLine();
                System.out.print("Enter specialty: ");
                String specialty = scanner.nextLine();
                System.out.print("Enter address: ");
                String address = scanner.nextLine();
                System.out.print("Enter phone: ");
                String phone = scanner.nextLine();
                System.out.print("Enter birth date (YYYY-MM-DD): ");
                String birthDate = scanner.nextLine();

                Doctor doctor = new Doctor(username, password, firstName, lastName, specialty, address, phone, birthDate);
                clinicSystem.addDoctor(doctor);

                System.out.print("Enter number of services to assign: ");
                int serviceCount = Integer.parseInt(scanner.nextLine());
                for (int i = 0; i < serviceCount; i++) {
                    System.out.print("Enter service name: ");
                    String serviceName = scanner.nextLine();
                    Service service = clinicSystem.findServiceByName(serviceName);
                    if (service != null) {
                        service.addDoctor(doctor);
                    } else {
                        service = new Service(serviceName, 30);
                        service.addDoctor(doctor);
                        clinicSystem.addService(service);
                    }
                }
            } else if (choice == 4) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                System.out.print("Enter first name: ");
                String firstName = scanner.nextLine();
                System.out.print("Enter last name: ");
                String lastName = scanner.nextLine();
                System.out.print("Enter birth date (YYYY-MM-DD): ");
                String birthDate = scanner.nextLine();
                System.out.print("Enter address: ");
                String address = scanner.nextLine();
                System.out.print("Enter phone: ");
                String phone = scanner.nextLine();

                Patient patient = new Patient(username, password, firstName, lastName, birthDate, address, phone);
                clinicSystem.addPatient(patient);
            } else if (choice == 5) {
                System.out.print("Enter patient username: ");
                String patientUsername = scanner.nextLine();
                Patient patient = clinicSystem.findPatientByUsername(patientUsername);

                System.out.print("Enter doctor username: ");
                String doctorUsername = scanner.nextLine();
                Doctor doctor = clinicSystem.findDoctorByUsername(doctorUsername);

                System.out.print("Enter service name: ");
                String serviceName = scanner.nextLine();
                Service service = clinicSystem.findServiceByName(serviceName);

                System.out.print("Enter date (YYYY-MM-DD): ");
                LocalDate date = LocalDate.parse(scanner.nextLine());
                System.out.print("Enter time (HH:MM): ");
                LocalTime time = LocalTime.parse(scanner.nextLine());

                if (patient != null && doctor != null && service != null) {
                    Appointment appointment = new Appointment(date, time, patient, doctor, service);
                    clinicSystem.addAppointment(appointment);
                } else {
                    System.out.println("Invalid patient, doctor, or service.");
                }
            } else if (choice == 0) {
                System.out.println("Logging out...");
            } else {
                System.out.println("Invalid option.");
            }
        }
    }
	

}
	

