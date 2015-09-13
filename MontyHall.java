import java.util.Random;
import java.util.Scanner;

/**
 * CS180 - Bookwork - MontyHall.java
 * <p>
 * Prompts the user with a choice of three doors, one of them contains a prize. User may choose one door. At that time,
 * Monty will choose a door that he already knows does not have a prize. The user will have the option to change their
 * door choice to the last remaining door or keep their original door. MontyHall class uses basic abstraction
 * (through use of conditionals and boolean statements) to handle different user-to-computer dialogs, and/or a change
 * in the number of doors involved in the game. Different methods, method calls, and recursion allow for an easier
 * view of the different parts of the program.
 *
 * @author Andrew Peterson, peter343@purdue.edu
 * @version 9.11.2015
 */
public class MontyHall {

    Random random = new Random();
    private final static int NUM_DOORS = 3;
    private int goodDoor;
    private int choice;
    private int montyDoor;
    private String decision;
    private int newDoor;

    public MontyHall() {

        goodDoor = random.nextInt(NUM_DOORS); //Choose a random door out of the number available to be the good one

    }

    public void chooseDoor(String strInt) {                         //*Fixed Note: Ask user to choose their door in Main
        choice = 0;
        choice = Integer.parseInt(strInt);
        try {

            if (!checkValidity(choice)) {
                System.out.println("\nSorry, but that isn't a door option!");
                System.out.println("\nPlease try again!\n\nPick a number between 1 and " + NUM_DOORS);
                Scanner failure = new Scanner(System.in);
                String validChoice = failure.nextLine();
                chooseDoor(validChoice);
            } else if (checkValidity(choice)) {
                choice = Integer.parseInt(strInt);

                System.out.println("Great! Let's Play!");
                try{Thread.sleep(700);}
                catch(InterruptedException e){}
                System.out.println("\nYou chose door number " + choice);
                try{Thread.sleep(1500);}
                catch(InterruptedException e){}
                System.out.println("\nLet's see what Monty will pick!");
                try{Thread.sleep(1250);}
                catch(InterruptedException e){}
                System.out.println(".");
                try{Thread.sleep(1250);}
                catch(InterruptedException e){}
                System.out.println(".");
                try{Thread.sleep(1250);}
                catch(InterruptedException e){}
                System.out.println(".");
                montyChoice();                                                               //Retrieves Monty's Choice
                try{Thread.sleep(1250);}
                catch(InterruptedException e){}
                System.out.println("Monty: \'I choose door number " + montyDoor + "!\'");
                try{Thread.sleep(1250);}
                catch(InterruptedException e){}
                System.out.println("\nNow that you know what Monty picked, would you like to: ");  //Prompts user to
                //                                                                  either Keep or Change their door
                try{Thread.sleep(2750);}
                catch(InterruptedException e){}
                System.out.println("\n\nChange your door...?\n\n");
                try{Thread.sleep(900);}
                catch(InterruptedException e){}
                System.out.println("or");
                try{Thread.sleep(700);}
                catch(InterruptedException e){}
                System.out.println("\n\nKeep your door...?");
                Scanner secondChoice = new Scanner(System.in);
                decision = secondChoice.nextLine();

                decision = decision.toLowerCase();                     //changes the response to lower case, to prevent
                //                                                                                             glitches

                                                                //Determines if user decided to keep or change door
                nextChoiceValid();
                                                                    //Makes comparisons to get the results of the users
                //                                                                                              choices

            } else      //prints for some possible break in code
                System.out.println("How did you break this code?");
        } catch (NumberFormatException e) {                                              //Catches if the user inputs a
            //                                                                                               non-number
            System.out.println("Whoops! That's not a number. Try again!");
            Scanner firstChoice = new Scanner(System.in);
            String door = firstChoice.nextLine();

            chooseDoor(door);
        }


    }
    private void nextChoiceValid(){
        try{Thread.sleep(1000);}
        catch(InterruptedException e){}
        if(nextChoice(decision) == 0)
            results();
        else {
            System.out.println("\nEnter your decision: ");
            Scanner retrySecondChoice = new Scanner(System.in);
            decision = retrySecondChoice.nextLine();
            decision = decision.toLowerCase();
            nextChoice(decision);
            nextChoiceValid();
        }
    }
    private void montyChoice() {    //sets montyDoor variable to a remaining garbage door

        while (montyDoor == goodDoor || montyDoor == choice || montyDoor == 0) { //Keep choosing a door until it is
            montyDoor = random.nextInt(NUM_DOORS + 1);                           //for sure a garbage door
        }

    }

    private int nextChoice(String phrase) {     //prints out dialog corresponding to user's choice
        String keep = "keep";
        String change = "change";

        if (phrase.contains(keep)) {

            System.out.println("So, you think you've had the right door all along?");
            try{Thread.sleep(2750);}
            catch(InterruptedException e){}
            System.out.println("\nLet's see what's behind your door!");
            try{Thread.sleep(1000);}
            catch(InterruptedException e){}
            System.out.println(".");
            try{Thread.sleep(1000);}
            catch(InterruptedException e){}
            System.out.println(".");
            try{Thread.sleep(1000);}
            catch(InterruptedException e){}
            System.out.println(".");
            newDoor = choice;
            return 0;
        }
        else if (phrase.contains(change)) {
            newDoor = random.nextInt(NUM_DOORS);
            while (newDoor == montyDoor || newDoor == choice || newDoor == 0)       //calculates the next possible
                newDoor = random.nextInt(NUM_DOORS + 1);                            //door the user can have

            System.out.println("\nYou decided to change your door to door number " + newDoor);
            try{Thread.sleep(2750);}
            catch(InterruptedException e){}
            System.out.println("\n\nLet's see if you made the right choice!");
            try{Thread.sleep(1000);}
            catch(InterruptedException e){}
            System.out.println(".");
            try{Thread.sleep(1000);}
            catch(InterruptedException e){}
            System.out.println(".");
            try{Thread.sleep(1000);}
            catch(InterruptedException e){}
            System.out.println(".");
            return 0;
        }
        else{
            System.out.println("Sorry, it seems you didn't tell us to change or keep your door.\nTry again!");
            return -1;
        }

    }
    public boolean checkValidity(int input) { //method to check if the input can be used in the game


        return input <= NUM_DOORS;     //returns true if input is valid or false if input is invalid
    }

    public String results() {

        if (newDoor != goodDoor) {
            //System.out.println("Show us what's behind door number " + newDoor + "!");

            System.out.println("Oh no! It's garbage!");
            try{Thread.sleep(1700);}
            catch(InterruptedException e){}
            System.out.println("\nThanks for playing anyway! Try again next time!");

        } else {
            System.out.println("Congratulations! You chose the correct door!\n\nYOU WIN!");
            try{Thread.sleep(3000);}
            catch(InterruptedException e){}
            System.out.println("\nThanks for playing! Enjoy your prize!");


        }
        return "Thanks for Playing!";
    }

    public static void main(String[] args) {
        MontyHall game = new MontyHall();
        System.out.println("Welcome to the Game!\nPlease enter a door number between 1 and " + NUM_DOORS);
        Scanner firstChoice = new Scanner(System.in);
        String door = firstChoice.nextLine();

        game.chooseDoor(door);
    }
}
