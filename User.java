package ergasia_b;

public abstract class User {
    protected String username;
    protected String password;
    protected String firstName;
    protected String lastName;
    protected String address;
    protected String phone;
    protected String dateOfBirth;

    public User(String username, String password, String firstName, String lastName,
                String address, String phone, String dateOfBirth) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
    }

    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getUsername() { return username; }
}

