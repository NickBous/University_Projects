package ergasia_b;

import java.time.LocalDate;
import java.util.ArrayList;

public class ClinicSystem {
    private ArrayList<Doctor> doctors;
    private ArrayList<Patient> patients;
    private ArrayList<Service> services;
    private ArrayList<Appointment> appointments;
    private ArrayList<Secretary> secretaries;

    public ClinicSystem() {
        doctors = new ArrayList<>();
        patients = new ArrayList<>();
        services = new ArrayList<>();
        appointments = new ArrayList<>();
        secretaries = new ArrayList<>();
    }

    public void addDoctor(Doctor d) { doctors.add(d); }
    public void addPatient(Patient p) { patients.add(p); }
    public void addSecretary(Secretary s) { secretaries.add(s); }
    public void addService(Service s) { services.add(s); }
    public void addAppointment(Appointment a) { appointments.add(a); }

    public ArrayList<Doctor> getDoctors() { return doctors; }
    public ArrayList<Patient> getPatients() { return patients; }
    public ArrayList<Service> getServices() { return services; }
    public ArrayList<Appointment> getAppointments() { return appointments; }
    public ArrayList<Secretary> getSecretaries() { return secretaries; }

    public ArrayList<Appointment> getAppointmentsByDate(LocalDate date) {
        ArrayList<Appointment> result = new ArrayList<>();
        for (Appointment a : appointments) {
            if (a.getDate().equals(date)) {
                result.add(a);
            }
        }
        return result;
    }
    public ArrayList<Appointment> getAppointmentsByDateRange(LocalDate startDate, LocalDate endDate) {
        ArrayList<Appointment> result = new ArrayList<>();
        for (Appointment a : appointments) {
            if ((a.getDate().isEqual(startDate) || a.getDate().isAfter(startDate)) &&
                (a.getDate().isEqual(endDate) || a.getDate().isBefore(endDate))) {
                result.add(a);
            }
        }
        return result;
    }

    public Service findServiceByName(String name) {
        for (Service service : services) {
            if (service.getDescription().equalsIgnoreCase(name)) {
                return service;
            }
        }
        return null;
    }
    public User login(String username, String password) {
        for (Doctor d : doctors) if (d.login(username, password)) return d;
        for (Secretary s : secretaries) if (s.login(username, password)) return s;
        for (Patient p : patients) if (p.login(username, password)) return p;
        return null;
    }

    public void loadInitialData() {
        Doctor doctor1 = new Doctor("doctor1", "1234", "Maria", "Papadopoulou", "Athens", "6980000001", "1980-05-20", "Cardiologist");
        Doctor doctor2 = new Doctor("doctor2", "1234", "Nikos", "Kostas", "Athens", "6980000002", "1975-07-14", "Cardiologist");

        Service cardiology = new Service("Cardiology Check", 30);
        cardiology.addDoctor(doctor1);
        cardiology.addDoctor(doctor2);
        doctor1.addService(cardiology);
        doctor2.addService(cardiology);

        Patient patient1 = new Patient("patient1", "pass1", "Eleni", "Nikolaou", "Athens", "6900000001", "1990-02-10");
        patient1.addDiagnosis("Hypertension");

        Patient patient2 = new Patient("patient2", "pass2", "Giorgos", "Papadakis", "Athens", "6900000002", "1985-03-15");
        patient2.addDiagnosis("Arrhythmia");

        Patient patient3 = new Patient("patient3", "pass3", "Anna", "Lazarou", "Athens", "6900000003", "2000-11-25");
        patient3.addDiagnosis("Chest pain");

        Secretary secretary1 = new Secretary("sec1", "1234", "Maria", "Papadopoulou", "2101234567", "6974674345","1991-04-01" ,"maria.papadopoulou@example.com");
        Secretary secretary2 = new Secretary("sec2", "5678", "Eleni", "Georgiou", "2107654321","6978789896" ,"1988-07-04" , "eleni.georgiou@example.com");

        Appointment appointment1= new Appointment(LocalDate.now(), java.time.LocalTime.of(10, 0), patient1, doctor1, cardiology);
        Appointment appointment2 = new Appointment(LocalDate.now(), java.time.LocalTime.of(11, 0), patient2, doctor2, cardiology);

        addDoctor(doctor1); addDoctor(doctor2);
        addService(cardiology);
        addPatient(patient1); addPatient(patient2); addPatient(patient3);
        addSecretary(secretary1);
        addSecretary(secretary2);
        addAppointment(appointment1); addAppointment(appointment2);
    }

	public Patient findPatientByUsername(String patientUsername) {
		for (Patient p :patients) {
			if (p.username.equals(patientUsername)) {
				return p;
			}
		}
		return null;
	}

	public Doctor findDoctorByUsername(String doctorUsername) {
		for (Doctor d:doctors) {
			if (d.username.equals(doctorUsername)) {
				return d;
			}
		}
		return null;
	}
}


