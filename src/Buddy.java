import java.util.Arrays;
import java.util.List;

public class Buddy implements BuddyLandBuddies {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String fullName;

    public Buddy(String username, String password, String firstName, String lastName, String email) {
        List<String> args = Arrays.asList(username, password, firstName, lastName, email);

        for (String arg : args) {
            if (arg == null || arg.trim().isEmpty()) {
                throw new IllegalArgumentException("Constructor argument cannot be null or empty");
            }
        }

        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public String getFullname() {
        return this.fullName;
    }

    @Override
    public boolean isSelf(BuddyLandBuddies buddyLandBuddies) throws InvalidBuddyObjectException {
        if (buddyLandBuddies == null) throw new InvalidBuddyObjectException("buddy to compare cant be null");
        return buddyLandBuddies.getUsername().equals(this.username);
    }


    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public boolean setUsername(String username) throws InvalidStringInput {
        if (username == null || username.trim().isEmpty()) {
            throw new InvalidStringInput("Username cannot be null or empty");
        }
        this.username = username;
        return true;
    }

    @Override
    public boolean setPassword(String password) throws InvalidStringInput {
        if (password == null || password.trim().isEmpty()) {
            throw new InvalidStringInput("Password cannot be null or empty");
        }
        this.password = password;
        return true;
    }

    @Override
    public boolean setFirstName(String firstName) throws InvalidStringInput {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new InvalidStringInput("First name cannot be null or empty");
        }
        this.firstName = firstName;
        return true;
    }

    @Override
    public boolean setLastName(String lastName) throws InvalidStringInput {
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new InvalidStringInput("Last name cannot be null or empty");
        }
        this.lastName = lastName;
        return true;
    }

    @Override
    public boolean setEmail(String email) throws InvalidStringInput {
        if (email == null || email.trim().isEmpty() || !email.contains("@")) {
            throw new InvalidStringInput("Email cannot be null, empty, and must contain '@'");
        }
        this.email = email;
        return true;
    }

    @Override
    public String toString() {
        return this.lastName + " " + this.username;
    }
}
