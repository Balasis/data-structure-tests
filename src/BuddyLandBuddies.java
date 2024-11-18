public interface BuddyLandBuddies {
    String getFullname();
    boolean isSelf(BuddyLandBuddies buddyLandBuddies) throws InvalidBuddyObjectException;
    String getUsername();
    String getPassword();
    String getFirstName();
    String getLastName();
    String getEmail();
    boolean setUsername(String username) throws InvalidStringInput;
    boolean setPassword(String password) throws InvalidStringInput;
    boolean setFirstName(String firstname) throws InvalidStringInput;
    boolean setLastName(String lastname) throws InvalidStringInput;
    boolean setEmail(String email) throws InvalidStringInput;
}
