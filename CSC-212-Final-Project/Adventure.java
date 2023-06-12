import java.io.File;
import java.util.*;

/**
 * Class to store game
 * /*
 * 
 * @author Anh Nguyen
 * @author Sabrina Hatch
 * @author Pratima Naroula
 * @version Spring 2022
 */
class Adventure {

  private static Scanner sc = new Scanner(System.in);

  /**
   * starts the game
   *
   * @param N/A
   * @return N/A
   */
  public static void start() {
    // Initialise player
    Player player = new Player("Sabrina");

    // Initialises map and drops player to starting location
    Init map = new Init();
    Place location = map.getStart();

    // Prints intro text
    Parser.printText("Data/UtilityText/intro.txt");
    Parser.promptEnterKey(sc);
    Parser.clearScreen();

    Parser.printText("Data/UtilityText/help.txt");
    Parser.promptEnterKey(sc);
    Parser.clearScreen();

    // Switch to end the game
    boolean playing = true;

    // loop to start the game
    while (playing) {

      // Switch to change room
      boolean changeRoom = false;
      location.desc();

      // loop to keep actions going within a specific room
      while (!changeRoom) {

        // takes in the user input and then reads it
        String input = sc.nextLine();

        // Gets rid of captitalisation and random characters
        input = input
            .toLowerCase()
            .replaceAll("[^A-Za-z\\s]", "")
            .replaceAll("\\s{2,}", " ");

        // Splits the input into verb and object
        String[] words = input.split(" ");

        // Parser assumes correct grammar position in sentence
        // MOVING COMMAND
        if (words[0].equals("north") ||
            words[0].equals("walk") &&
                words[1].equals("north")) {
          try {
            Place newLocation = location
                .getExit(Direction.NORTH)
                .getToPlace();
            if (newLocation != null) {
              location = newLocation;
              changeRoom = true;
            } else {
              System.err.println("> Can't go there!");
            }
          } catch (Exception e) {
            System.err.println("> You hit a wall! Ouch!");
          }
        }

        else if (words[0].equals("south") ||
            words[0].equals("walk") &&
                words[1].equals("south")) {
          try {
            Place newLocation = location
                .getExit(Direction.SOUTH)
                .getToPlace();
            if (newLocation != null) {
              location = newLocation;
              changeRoom = true;
            } else {
              System.err.println("> Can't go there!");
            }
          } catch (Exception e) {
            System.err.println("> You hit a wall! Ouch!");
          }

        }

        else if (words[0].equals("east") ||
            words[0].equals("walk") &&
                words[1].equals("east")) {
          try {
            Place newLocation = location
                .getExit(Direction.EAST)
                .getToPlace();
            if (newLocation != null) {
              location = newLocation;
              changeRoom = true;
            } else {
              System.err.println("> Can't go there!");
            }
          } catch (Exception e) {
            System.err.println("> You hit a wall! Ouch!");
          }
        }

        else if (words[0].equals("west") ||
            words[0].equals("walk") &&
                words[1].equals("west")) {
          try {
            Place newLocation = location
                .getExit(Direction.WEST)
                .getToPlace();
            if (newLocation != null) {
              location = newLocation;
              changeRoom = true;
            } else {
              System.err.println("> Can't go there!");
            }
          } catch (Exception e) {
            System.err.println("> You hit a wall! Ouch!");
          }
        }

        // ITEM COMMAND

        // allows you to examine an item
        else if (words[0].equals("examine")) {
          if (location.getItem(words[1]) != null) {
            location.getItem(words[1]).desc();
          } else if (location.getDoor(words[1]) != null) {
            Place newLocation = location
                .getDoor(words[1]).useDoor();
            if (newLocation != null) {
              location = newLocation;
              changeRoom = true;
            } else {
              System.out.println("> It's locked");
            }
          } else if (location.getNPC(words[1]) != null) {
            try {
              location.getOnlyNPC().desc();
            } catch (Exception e) {
              System.out.println("> To whom?");
            }
          } else {
            System.out.println("> No such item!");
          }
        }

        // allows you to take an item
        else if (words[0].equals("take")) {
          if (location.getItem(words[1]) != null) {
            // adds item to inventory
            player.addInventory(
                location.getItem(words[1]));
            // takes item out of the room
            location.removeItem(words[1]);
            System.out.println(
                "> You pick up " + words[1]);
          } else {
            System.out.println("> No such item!");
          }
        }

        // allows you to drop an item
        else if (words[0].equals("drop")) {
          if (player.getInventory(words[1]) != null) {
            // adds item to room
            location.addItem(
                player.getInventory(words[1]));
            // removes item from player inventory
            player.removeInventory(words[1]);
            System.out.println(
                "> You drop " + words[1]);
          } else {
            System.err.println("> Can't drop that!");
          }
        }
        // hard-coded responses for special item interactions
        else if (words[0].equals("smash")) {
          System.out.println("> Welp... you smashed it. What a mess you've made.");
        }

        else if (words[0].equals("listen")) {
          System.out.println("> Really? My chemical romance?");
        }

        else if (words[0].equals("eat")) {
          player.removeInventory(words[1]);
          System.out.println("> Yum!");
        }

        else if (words[0].equals("wear")) {
          // removes item from the room
          location.removeItem(words[1]);
          // adds item to your inventory
          player.addInventory(location.getItem(words[1]));
          System.out.println("> Cute, but at what cost?");
        }

        else if (words[0].equals("read")) {
          System.out.println("> The pages have illegible runes, you really think you can crack the code?");
        }

        // prints inventory to the screen
        else if (words[0].equals("check") &&
            words[1].equals("inventory")) {
          player.checkInventory();
        }

        // NPC COMMANDS

        else if (words[0].equals("talk")) {
          try {
            location.getOnlyNPC().talk();
          } catch (Exception e) {
            System.out.println("> To whom?");
          }

        }

        else if (words[0].equals("give")) {
          if (player.getInventory(words[1]) != null) {
            try {
              // check if the item is correct
              boolean correctItem = location.getOnlyNPC().acceptItem(words[1]);
              // removes item from player inventory if yes
              if (correctItem) {
                player.removeInventory(words[1]);
                if (location.getName().equals("Transylvania")) {
                  player.addInventory(new Item("ticket", "Data/CharacterData/CreepyFigure/ticket.txt"));
                  location.getExit(Direction.WEST).setAccess(true);
                }
              }
              // END GAME
              playing = location.getOnlyNPC().getEndGame();
              if (!playing) {
                Parser.promptEnterKey(sc);
                break;
              }
            } catch (Exception e) {
              System.out.println("> To whom?");
            }
          } else {
            System.err.println("> No such item in inventory!");
          }
        }

        // UTILITY COMMAND

        // allows you to acess the help menu to understand directions
        else if (words[0].equals("help")) {
          Parser.clearScreen();
          Parser.printText("Data/UtilityText/help.txt");
          Parser.promptEnterKey(sc);
          // Move back to game
          changeRoom = true;
        }

        else {
          System.out.println("> I don't understand that!");
        }

      }
      Parser.clearScreen();
    }

    System.out.println("> Game over!");
  }

}
