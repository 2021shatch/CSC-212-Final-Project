import java.util.*;

/**
 * Class to store info and methods for players
 *
 * @author Anh Nguyen
 * @author Sabrina Hatch
 * @author Pratima Naroula
 * @version Spring 2022
 */
class Player {
  /** player's name */
  private String name;

  /** current location */
  private Place location;

  /** hashmap for the player inventory */
  private HashMap<String, Item> inventory = new HashMap<String, Item>();

  /**
   * constructor for the player's name
   * 
   * @param name string containing the player's name
   */
  public Player(String name) {
    this.name = name;
  }

  /**
   * getter for player's current location
   * 
   * @return Place - the player's location
   */
  public Place getPlayerLocation() {
    return location;
  }

  /**
   * setter for player current location
   * 
   * @param location an object of Place
   *
   */
  public void setPlayerLocation(Place location) {
    this.location = location;
  }

  /**
   * @args Item to add to inventory
   */
  public void addInventory(Item i) {
    inventory.put(i.getName(), i);
  }

  /**
   * method to get an item from the user's inventory
   * 
   * @param name String name of item to get
   *             return item that we want to get
   */
  public Item getInventory(String name) {
    return inventory.get(name);
  }

  /**
   * method to remove an item from the user's inventory
   * 
   * @param name String name of item to remove
   * @return N/A
   */
  public void removeInventory(String name) {
    inventory.remove(name);
  }

  /**
   * prints out inventory for user
   * 
   * @return N/A
   */
  public void checkInventory() {
    System.out.println("> Inventory: " + inventory.keySet());
  }

}