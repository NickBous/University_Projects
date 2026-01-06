package ergasia_b;

import java.util.ArrayList;

public class Service {
    private String description;
    private int durationMinutes;
    private ArrayList<Doctor> doctors;
    

    public Service(String description, int durationMinutes) {
        this.description = description;
        this.durationMinutes = durationMinutes;
        this.doctors = new ArrayList<>();
    }

    public void addDoctor(Doctor d) {
        doctors.add(d);
    }

    public String getDescription() {
        return description;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }
}


