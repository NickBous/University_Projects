package ergasia_b;

import java.util.ArrayList;

public class Doctor extends User {
    private String specialty;
    private ArrayList<Service> services;

    public Doctor(String username, String password, String firstName, String lastName,
                  String address, String phone, String dateOfBirth, String specialty) {
        super(username, password, firstName, lastName, address, phone, dateOfBirth);
        this.specialty = specialty;
        this.services = new ArrayList<>();
    }

    public void addService(Service s) {
        services.add(s);
    }

    public ArrayList<Service> getServices() {
        return services;
    }

    public String getSpecialty() {
        return specialty;
    }
    public String getFullName() {
        return firstName + " " + lastName;
    }

}



