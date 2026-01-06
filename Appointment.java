package ergasia_b;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
    private LocalDate date;
    private LocalTime time;
    private Patient patient;
    private Doctor doctor;
    private Service service;

    public Appointment(LocalDate date, LocalTime time, Patient patient, Doctor doctor, Service service) {
        this.date = date;
        this.time = time;
        this.patient = patient;
        this.doctor = doctor;
        this.service = service;
    }

    public LocalDate getDate() { return date; }
    public LocalTime getTime() { return time; }
    public Patient getPatient() { return patient; }
    public Doctor getDoctor() { return doctor; }
    public Service getService() { return service; }

    @Override
    public String toString() {
        return "Date: " + date + ", Time: " + time + ", Doctor: " + doctor.getFullName() +
               ", Patient: " + patient.getFullName() + ", Service: " + service.getDescription();
    }
}

