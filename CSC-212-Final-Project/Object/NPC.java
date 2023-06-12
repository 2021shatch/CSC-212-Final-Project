
/**
 * Class to store info and methods for places
 *
 * @author Anh Nguyen
 * @author Sabrina Hatch
 * @author Pratima Naroula
 * @version Spring 2022
 */
import java.io.*;
import java.util.*;

class NPC extends Item {
  /** counter for number of interaction */
  private int counterTalk;

  /** counter for number of interaction */
  private int counterGuess;

  /** stores dialogue */
  private ArrayList<String> dialogueList = new ArrayList<String>();

  /** store correct items */
  private String item;

  /** store correct items */
  private boolean playing;

  /**
   * method to add lines from dialogue text to arraylist
   *
   * @param dialogue a string containing the character's dialogue
   * @return N/A
   */
  private void setDialogue(String dialogue) {
    try {
      File file = new File(dialogue);
      Scanner sc = new Scanner(file);
      while (sc.hasNextLine()) {
        dialogueList.add(sc.nextLine());
      }
      sc.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * constructor for the NPC
   * 
   * @param name        string containing the name of the character
   * @param description string containing the description of the character
   * @param dialogue    string containing the character's dialogue
   * @param item        string containing the item they need to be won over
   */
  public NPC(String name, String description, String dialogue, String item) {
    super(name, description);
    setDialogue(dialogue);
    counterTalk = 2;
    counterGuess = 0;
    this.item = item;
    this.playing = true;
  }

  /**
   * method that splits the string by commas, but ignores a comma if it's enclosed
   * by a pair of parentheses
   * 
   * @param row a string of text to be formatted
   */
  public NPC(String row) {
    super(row);
    String[] parse = row.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
    setDialogue(parse[3]);
    counterTalk = 2;
    counterGuess = 0;
    this.item = parse[4];
    this.playing = true;
  }

  /**
   * counter setter
   * 
   * @param newCounter, a number representing how many times you've talked with a
   *                    specific character
   * @return N/A
   */
  public void setCounter(int newCounter) {
    this.counterTalk = newCounter;
  }

  /**
   * counter getter
   * 
   * @param N/A
   * @return int that represents the number of times you've talked to a specific
   *         character
   */
  public int getCounter() {
    return counterTalk;
  }

  /**
   * boolean to switch to end of game
   * 
   * @param N/A
   * @return boolean false if game is ending, true otherwise
   */
  public boolean getEndGame() {
    return playing;
  }

  /**
   * logic to count the number of times you talk with a specific NPC
   * 
   * @param N/A
   * @return N/A
   */
  public void talk() {
    if (counterTalk < 4) {
      System.out.println("> " + dialogueList.get(counterTalk));
      counterTalk = counterTalk + 1;
    } else {
      System.out.println("> " + dialogueList.get(4));
    }
  }

  /**
   * method to deal with an NPC accepting an item from the character - typically
   * deals with the end of the game
   * 
   * @param repsonse a string containing the reponse after accepting an NPC
   *                 accepts an item from the player
   * @return N/A
   */
  public boolean acceptItem(String response) {
    if (response.equals(item)) {
      System.out.println("> " + dialogueList.get(0));
      playing = false;
      return true;
    } else {
      if (counterGuess < 3) {
        System.out.println("> " + dialogueList.get(1));
        counterGuess = counterGuess + 1;
        playing = true;
        return false;
      } else {
        System.out.println("> " + dialogueList.get(5));
        playing = false;
        return false;
      }
    }
  }
}