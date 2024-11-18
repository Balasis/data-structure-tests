import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BuddyList implements BuddylandList {
    private Buddy owner;
    private Buddy[] buddiesDataStructure;
    private int size;
    private int numberOfRemoves;

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
        //It loops upwords checking if username exists, the insertIndex of the buddy and the closer gap(null) to it
        loopUpwards(gapIndex, insertIndex, buddy);

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



    private void loopUpwards(int gapIndex, int insertIndex, Buddy buddy) throws InvalidBuddyObjectException {
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


//
//    public List<Buddy> searchBuddiesByName(String name) throws InvalidStringInput {
//        if (name == null || name.trim().isEmpty()) {
//            throw new InvalidStringInput("Name cannot be null or empty");
//        }
//
//        List<Buddy> result = new ArrayList<>();
//        int left = 0;
//        int right = size + numberOfRemoves - 1;
//
//        while (left <= right) {
//            int mid = left + (right - left) / 2;
//
//            // Find the nearest non-null value
//            mid = findNearestNonNull(mid, left, right);
//
//            if (mid == -1) break; // No valid non-null values in range
//
//            Buddy currentBuddy = buddiesDataStructure[mid];
//            int comparison = currentBuddy.getLastName().compareToIgnoreCase(name);
//
//            if (comparison == 0 || currentBuddy.getFirstName().equalsIgnoreCase(name)) {
//                // Add matches and look both sides for duplicates
//                collectMatches(result, mid, name);
//                break;
//            } else if (comparison < 0) {
//                left = mid + 1;
//            } else {
//                right = mid - 1;
//            }
//        }
//
//        return result;
//    }
//
//    // Helper to find the nearest non-null value
//    private int findNearestNonNull(int mid, int left, int right) {
//        int originalMid = mid;
//        while (mid >= left && buddiesDataStructure[mid] == null) mid--;
//        if (mid >= left) return mid;
//
//        mid = originalMid + 1;
//        while (mid <= right && buddiesDataStructure[mid] == null) mid++;
//        return mid <= right ? mid : -1;
//    }
//
//    // Helper to collect matches on both sides of the found buddy
//    private void collectMatches(List<Buddy> result, int index, String name) {
//        // Collect left side
//        for (int i = index; i >= 0 && buddiesDataStructure[i] != null; i--) {
//            Buddy buddy = buddiesDataStructure[i];
//            if (buddy.getFirstName().equalsIgnoreCase(name) || buddy.getLastName().equalsIgnoreCase(name)) {
//                result.add(buddy);
//            }
//        }
//
//        // Collect right side
//        for (int i = index + 1; i < buddiesDataStructure.length && buddiesDataStructure[i] != null; i++) {
//            Buddy buddy = buddiesDataStructure[i];
//            if (buddy.getFirstName().equalsIgnoreCase(name) || buddy.getLastName().equalsIgnoreCase(name)) {
//                result.add(buddy);
//            }
//        }
//    }



    public List<Buddy> searchBuddiesByName(String name) throws InvalidStringInput {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidStringInput("Name cannot be null or empty");
        }

        List<Buddy> result = new ArrayList<>();
        char targetInitial = name.toLowerCase().charAt(0);

        // Perform binary search to locate the first occurrence
        int low = 0;
        int high = size + numberOfRemoves - 1;
        int foundIndex = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            Buddy buddy = buddiesDataStructure[mid];

            if (buddy != null) {
                char currentInitial = buddy.getLastName().toLowerCase().charAt(0);
                if (currentInitial == targetInitial) {
                    foundIndex = mid; // Possible match found
                    break;
                } else if (currentInitial < targetInitial) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            } else {
                high--; // Skip nulls
            }
        }

        // If no match found, return an empty result
        if (foundIndex == -1) {
            return result;
        }

        // Expand outward from the found index
        int left = foundIndex;
        int right = foundIndex;

        // Check left side
        while (left >= 0) {
            Buddy buddy = buddiesDataStructure[left];
            if (buddy != null && buddy.getLastName().toLowerCase().startsWith(name.toLowerCase())) {
                result.add(0, buddy); // Add to the front to maintain order
            } else {
                break; // Stop when no longer matching
            }
            left--;
        }

        // Check right side
        while (right < size + numberOfRemoves) {
            Buddy buddy = buddiesDataStructure[right];
            if (buddy != null && buddy.getLastName().toLowerCase().startsWith(name.toLowerCase())) {
                result.add(buddy); // Add to the end
            } else {
                break; // Stop when no longer matching
            }
            right++;
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

    public Buddy[] getItRemoveMe(){
        return this.buddiesDataStructure;
    }
}
