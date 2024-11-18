import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BuddyLandProgram {
    Buddy loggedInBuddy ;
    BuddyList friendsOfTheBuddy;

    public BuddyLandProgram() {
        loggedInBuddy = new Buddy("nikos","1234","babisOSougias"
                ,"oPetaloudasOnikos","anEmail@amaDoulepsisNaMeXesis.com");
        friendsOfTheBuddy=new BuddyList(loggedInBuddy);
    }

    public void addBuddy(Buddy theBuddyToBeAdded){
        try {
            friendsOfTheBuddy.addBuddy(theBuddyToBeAdded);
            System.out.println(Arrays.toString(friendsOfTheBuddy.getItRemoveMe()));//open for testing...
        } catch (InvalidBuddyObjectException e) {
            System.out.println(e.getMessage());
        } catch (CannotAddSelfAsBuddyException e) {
            System.out.println(e.getMessage());
        } catch (BuddyNotInTheListException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeBuddy(Buddy theBuddyToBeRemoved){
        try {
            friendsOfTheBuddy.removeBuddy(theBuddyToBeRemoved);
        } catch (InvalidBuddyObjectException e) {
            System.out.println(e.getMessage());
        } catch (BuddyNotInTheListException e) {
            System.out.println(e.getMessage());
        }
    }

    public void searchBuddiesByName(String nameOfTheBuddy){
        try {
            List<Buddy> someList = friendsOfTheBuddy.searchBuddiesByName(nameOfTheBuddy);
            for(Buddy bud : someList){
               System.out.println(bud.getUsername());
            }
        } catch (InvalidStringInput e) {
            System.out.println(e.getMessage());
        }
    }

    public void getNumberOfBuddies(){
        System.out.println(friendsOfTheBuddy.getNumberOfBuddies());
    }


}
