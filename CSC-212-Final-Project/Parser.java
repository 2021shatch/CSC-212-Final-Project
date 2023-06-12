import java.io.File;
import java.util.*;

/**
 * Class to read in and print text from console and file for game
 *
 * @author Anh Nguyen
 * @author Sabrina Hatch
 * @author Pratima Naroula
 * @version Spring 2022
 */
class Parser {

  /**
   * Prints text from .txt file
   * 
   * @param filename name of file to print
   * @return N/A
   */
  public static void printText(String filename) {
    try {
      File file = new File(filename);
      Scanner sc = new Scanner(file);
      while (sc.hasNextLine()) {
        System.out.println("> " + sc.nextLine());
      }
      sc.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Clears console
   * 
   * @param N/A
   * @return N/A
   */
  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  /**
   * Prompts the user to enter or anykey to continue
   * 
   * @param scanner Scanner to read in user input
   * @return N/A
   */
  public static void promptEnterKey(Scanner scanner) {
    System.out.println("> Press \"ENTER\" to continue...");
    scanner.nextLine();
  }
}