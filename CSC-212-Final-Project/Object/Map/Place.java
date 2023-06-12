import java.util.*;

/**
 * Class to store info and methods for places
 *
 * @author Anh Nguyen
 * @author Sabrina Hatch
 * @author Pratima Naroula
 * @version Spring 2022
 */
class Place extends Item {

  /** hashMap<direction, exit> of exits */
  private HashMap<Direction, Exit> exitMap = new HashMap<Direction, Exit>();

  /** hashmap<item name, item> of items in place */
  private HashMap<String, Item> itemsList = new HashMap<String, Item>();

  /** hashmap<door name, door> of doors in place */
  private HashMap<String, Door> doorsList = new HashMap<String, Door>();

  /** hashmap<character name, item> list of character in place */
  private HashMap<String, NPC> NPCList = new HashMap<String, NPC>();

  /** Constructor */
  public Place(String name, String description) {
    super(name, description);
  }

  /**
   * Constructor.
   * 
   * @param row (required) a line from file
   */
  public Place(String row) {
    super(row);
  }

  /**
   * Check if the place has any exit in a direction
   * 
   * @arg dir direction
   */
  public boolean hasExit(Direction dir) {
    return exitMap.containsKey(dir);
  }

  /**
   * Get exit in a direction, if any
   * 
   * @arg dir direction
   * @throw RuntimeException if there is no exit for a direction
   */
  public Exit getExit(Direction dir) {
    return exitMap.get(dir);
  }

  /** Clear all exits */
  public void clearExit() {
    exitMap.clear();
  }

  /**
   * Add exit in a direction
   * 
   * @arg dir direction
   * @arg e exit
   */
  public void addExit(Direction dir, Exit e) {
    exitMap.put(dir, e);
  }

  /**
   * Remove exit in a direction
   * 
   * @arg dir direction
   */
  public void removeExit(Direction dir) {
    exitMap.remove(dir);
  }

  public Item getItem(String itemName) {
    return itemsList.get(itemName);
  }

  /** @arg Item to add to place */
  public void addItem(Item item) {
    itemsList.put(item.getName(), item);
  }

  /** @return Item remove item and return it */
  public void removeItem(String itemName) {
    itemsList.remove(itemName);
  }

  public NPC getNPC(String NPCName) {
    return NPCList.get(NPCName);
  }

  public NPC getOnlyNPC() {
    return NPCList.entrySet().iterator().next().getValue();
  }

  /** @arg Item to add to place */
  public void addNPC(NPC person) {
    NPCList.put(person.getName(), person);
  }

  /** @return Item remove item and return it */
  public void removeNPC(NPC person) {
    NPCList.remove(person);
  }

  public Door getDoor(String doorName) {
    return doorsList.get(doorName);
  }

  /** @arg Item to add to place */
  public void addDoor(Door door) {
    doorsList.put(door.getName(), door);
  }

  /** @return Item remove item and return it */
  public void removeDoor(String doorName) {
    doorsList.remove(doorName);
  }

  /** @return String string representation */
  @Override
  public String toString() {
    return (super.toString()
        + "\n Exit: " + exitMap.toString()
        + "\n Items: " + itemsList.toString()
        + "\n People: " + NPCList.toString()
        + "\n Door: " + doorsList.toString());
  }

  /** Print description to console */
  @Override
  public void desc() {
    System.out.println(new String(new char[50]).replace('\0', '-'));
    System.out.println(
        " Location: " + super.getName());
    // Print available exit
    System.out.println(" Exit: " + exitMap.keySet());
    if (!NPCList.isEmpty()) {
      System.out.println(" People: " + NPCList.keySet());
    }
    System.out.print(" You see: ");
    if (!itemsList.isEmpty()) {
      for (String key : itemsList.keySet()) {
        System.out.print(key + ", ");
      }
    }

    if (!doorsList.isEmpty()) {
      for (String key : doorsList.keySet()) {
        System.out.print(key + ", ");
      }
    }
    System.out.println(" ");
    System.out.println(" ");
    Parser.printText(super.getDescription());
    System.out.println(new String(new char[50]).replace('\0', '-'));
  }
}