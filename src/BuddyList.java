import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BuddyList implements BuddylandList {
    Buddy owner;
    Buddy[] buddiesDataStructure;
    int size;
    int numberOfRemoves;

    public BuddyList(Buddy owner) {
        this.owner = owner;
        this.buddiesDataStructure = new Buddy[30];
        this.size = 5;
        this.numberOfRemoves = 3;
        buddiesDataStructure[0] = new Buddy("anna001", "pass001", "Anna", "Black", "anna001@example.com");
        buddiesDataStructure[1] = null; // Gap
        buddiesDataStructure[2] = new Buddy("mark002", "pass002", "Mark", "Brown", "mark002@example.com");
        buddiesDataStructure[3] = new Buddy("john003", "pass003", "John", "Doe", "john003@example.com");
        buddiesDataStructure[4] = null; // Gap
        buddiesDataStructure[5] = new Buddy("jane004", "pass004", "Jane", "Smith", "jane004@example.com");
        buddiesDataStructure[6] = null; // Gap
        buddiesDataStructure[7] = new Buddy("sara005", "pass005", "Sara", "White", "sara005@example.com");

    }


    @Override
    public boolean addBuddy(Buddy buddy) throws InvalidBuddyObjectException, CannotAddSelfAsBuddyException, BuddyNotInTheListException {
        if (buddy == null) throw new InvalidBuddyObjectException("Buddy to be added can't be null");
        if (buddy.isSelf(this.owner)) throw new CannotAddSelfAsBuddyException("Buddy can't add themselves into their buddy list");

        int gapIndex = -1; // Track the first gap found
        int insertIndex = -1; // Index to insert the buddy

        // Loop upwards from the bottom
        for (int i = size + numberOfRemoves - 1; i >= 0; i--) {
            Buddy currentBuddy = buddiesDataStructure[i];

            if (currentBuddy == null) {
                if (insertIndex == -1){
                    gapIndex = i; // Mark the gap
                    System.out.println(gapIndex);
                }

            } else if (currentBuddy.getUsername().equals(buddy.getUsername())) {
                throw new InvalidBuddyObjectException("Buddy with this username already exists");
            }  else if (insertIndex == -1 && (currentBuddy.getLastName().compareTo(buddy.getLastName()) < 0 ||
                       currentBuddy.getLastName().compareTo(buddy.getLastName()) == 0)) {
                System.out.println(insertIndex);
                insertIndex = i+1; // Found the correct insertion spot
                System.out.println("Turned to :" + insertIndex);
            }
        }

        // If no insertIndex found, buddy goes at the beginning
        if (insertIndex == -1) insertIndex = 0;
        boolean gapsUsed = false;
        if (gapIndex !=-1){
            gapsUsed = true;
        }
        // If no gaps, the range to shift starts from the insertion point
        int shiftStart = (gapIndex != -1) ? gapIndex : size + numberOfRemoves;


        for (int i = shiftStart; i > insertIndex; i--) {

            buddiesDataStructure[i] = buddiesDataStructure[i - 1];
        }

        buddiesDataStructure[insertIndex] = buddy;
        size++;
        if (gapsUsed) numberOfRemoves--;
        System.out.println("Number of removes is "+  numberOfRemoves +" and size is " + size);
        return true;
    }

    @Override
    public boolean removeBuddy(Buddy buddy) throws InvalidBuddyObjectException, BuddyNotInTheListException {
        if (buddy == null) throw new InvalidBuddyObjectException("Buddy to remove can't be null");

        for (int i = 0; i < size + numberOfRemoves; i++) {
            if (buddiesDataStructure[i] != null && buddiesDataStructure[i].getUsername().equals(buddy.getUsername())) {
                buddiesDataStructure[i] = null;
                size--;
                numberOfRemoves++;
                return true;
            }
        }

        throw new BuddyNotInTheListException("Buddy not found in the list");
    }

    @Override
    public List<Buddy> searchBuddiesByName(String name) throws InvalidStringInput {
        if (name == null || name.trim().isEmpty()) throw new InvalidStringInput("Name cannot be null or empty");

        List<Buddy> result = new ArrayList<>();
        for (Buddy buddy : buddiesDataStructure) {
            if (buddy != null && (buddy.getFirstName().equalsIgnoreCase(name) || buddy.getLastName().equalsIgnoreCase(name))) {
                result.add(buddy);
            }
        }

        return result;
    }

    @Override
    public int getNumberOfBuddies() {
        return this.size;
    }

    @Override
    public Buddy getBuddyListOwner() {
        return this.owner;
    }
}
