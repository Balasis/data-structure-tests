import java.util.List;

public interface BuddylandList {
   boolean addBuddy(Buddy buddy) throws InvalidBuddyObjectException,BuddyNotInTheListException,CannotAddSelfAsBuddyException;
   boolean removeBuddy(Buddy buddy) throws InvalidBuddyObjectException,BuddyNotInTheListException;
   List<Buddy> searchBuddiesByName(String name) throws InvalidStringInput;
   int getNumberOfBuddies();
   Buddy getBuddyListOwner();
}
