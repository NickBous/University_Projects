package ergasia_b;

import java.util.ArrayList;

public class Patient extends User {
    private ArrayList<String> medicalHistory;

    public Patient(String username, String password, String firstName, String lastName,
                   String address, String phone, String dateOfBirth) {
        super(username, password, firstName, lastName, address, phone, dateOfBirth);
        this.medicalHistory = new ArrayList<>();
    }

    public void addDiagnosis(String diagnosis) {
        medicalHistory.add(diagnosis);
    }

    public ArrayList<String> getMedicalHistory() {
        return medicalHistory;
    }
    public String getFullName() {
        return firstName + " " + lastName;
    }

}

