import java.util.*;
import java.io.*;

/**
 * Class to initialise place and exit as a graph
 *
 * @author Anh Nguyen
 * @author Sabrina Hatch
 * @author Pratima Naroula
 * @version Spring 2022
 */
class Init {

  // Starting position
  private Place start;

  /* ArrayList that stores all the instances of Place */
  private HashMap<String, Place> placeMap = new HashMap<String, Place>();

  /*
   * Reads file then creates and adds a Place from each line in the file
   *
   * @param filename the string to read in that contains all the info for the room
   * 
   * @return N/A
   */
  public void createRoom(String filename) {
    Scanner file = null;
    try {
      file = new Scanner(new File(filename));
    } catch (FileNotFoundException e) {
      System.err.println("Cannot locate file.");
      System.exit(-1);
    }
    while (file.hasNextLine()) {
      // take each line in CSV and save to line
      String line = file.nextLine();
      Place p = new Place(line);
      placeMap.put(p.getName(), p);
    }
    file.close();
  }

  /*
   * Reads file then creates and adds an Item from each line in the file
   *
   * @param string filename contains the name of the item to be added
   * 
   * @return N/A
   */
  public void createItem(String filename) {
    Scanner file = null;
    try {
      file = new Scanner(new File(filename));
    } catch (FileNotFoundException e) {
      System.err.println("Cannot locate file.");
      System.exit(-1);
    }
    while (file.hasNextLine()) {
      // take each line in CSV and save to line
      String line = file.nextLine();
      Item item = new Item(line);
      // Add item to rooms
      String[] parse = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
      placeMap.get(parse[2]).addItem(item);
    }
    file.close();
  }

  /*
   * Reads file then creates and adds a NPC from each line in the file
   *
   * @param filename string that contains the name of the NPC to make
   * 
   * @return N/A
   */
  public void createNPC(String filename) {
    Scanner file = null;
    try {
      file = new Scanner(new File(filename));
    } catch (FileNotFoundException e) {
      System.err.println("Cannot locate file.");
      System.exit(-1);
    }
    while (file.hasNextLine()) {
      // take each line in CSV and save to line
      String line = file.nextLine();
      NPC npc = new NPC(line);
      // Add npc to rooms
      String[] parse = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
      placeMap.get(parse[2]).addNPC(npc);
    }
    file.close();
  }

  /*
   * Reads file then creates and adds an Exit from each line in the file
   *
   * @param filename String with information to create a new exit
   * 
   * @return N/A
   *
   */
  public void createExit(String filename) {
    Scanner file = null;
    try {
      file = new Scanner(new File(filename));
    } catch (FileNotFoundException e) {
      System.err.println("Cannot locate file.");
      System.exit(-1);
    }
    // skip header
    file.nextLine();
    while (file.hasNextLine()) {
      // take each line in CSV and save to line
      String line = file.nextLine();
      String[] parse = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
      Place source = placeMap.get(parse[0]);

      boolean access = true;

      if (!(parse[1].isEmpty())) {
        String loc = parse[1];
        if (loc.contains("(")) {
          access = false;
          loc = loc.replaceAll("[()]", "");
        }
        if (placeMap.get(loc) == null) {
          throw new RuntimeException("Can't find" + loc);
        }
        source.addExit(Direction.NORTH, new Exit(placeMap.get(loc), access));
      }

      if (!(parse[2].isEmpty())) {
        String loc = parse[2];
        if (loc.contains("(")) {
          access = false;
          loc = loc.replaceAll("[()]", "");
        }
        if (placeMap.get(loc) == null) {
          throw new RuntimeException("Can't find" + loc);
        }
        source.addExit(Direction.SOUTH, new Exit(placeMap.get(loc), access));
      }

      if (!parse[3].isEmpty()) {
        String loc = parse[3];
        if (loc.contains("(")) {
          access = false;
          loc = loc.replaceAll("[()]", "");
          System.out.println("Changed" + loc);
        }
        if (placeMap.get(loc) == null) {
          throw new RuntimeException("Can't find" + loc);
        }

        source.addExit(Direction.EAST, new Exit(placeMap.get(parse[3]), access));
      }

      if (!parse[4].isEmpty()) {
        String loc = parse[4];
        if (loc.contains("(")) {
          access = false;
          loc = loc.replaceAll("[()]", "");
        }
        if (placeMap.get(loc) == null) {
          throw new RuntimeException("Can't find" + loc);
        }
        source.addExit(Direction.WEST, new Exit(placeMap.get(loc), access));
      }
    }
    file.close();
  }

  /*
   * Initialises game objects
   * constructor
   */
  public Init() {
    // Create room from .txt file
    createRoom("Data/LocationData/placeData.txt");
    this.start = placeMap.get("Living Room");

    // Create exits between rooms
    createExit("Data/LocationData/exitData.txt");

    // Create item from .txt file and add to room
    createItem("Data/LocationData/itemData.txt");

    // Create NPC from .txt file and add to room
    createNPC("Data/LocationData/NPCData.txt");

    // Add door
    Door d = new Door("mirror", "Data/CharacterData/Batcula/MirrorBat.txt", placeMap.get("Transylvania"), true);
    placeMap.get("Dungeon").addDoor(d);
  }

  /*
   * Method to return the place of starting position
   * 
   * @param N/A
   * 
   * @return returns the room
   */
  public Place getStart() {
    return start;
  }

  /*
   * adds one place object to the collection
   * 
   * @param Place p, the name of the room
   * 
   * @return N/A
   */
  public void addPlace(Place p) {
    placeMap.put(p.getName(), p);
  }

  /*
   * returns the Place stored in the collection
   *
   * @param name String containing the name of the room
   * 
   * @return returns a an object place
   */
  public Place getPlace(String name) {
    return placeMap.get(name);
  }

  /*
   * deletes the Place stored in the collection
   *
   * @param name String containing the name of the room you want to remove
   * 
   * @return N/A
   */
  public void removePlace(String name) {
    placeMap.remove(name);
  }

  /*
   * return the total number of Places stored in the collection.
   * 
   * @param N/A
   * 
   * @return int which represents the number of places stored in the collection.
   */
  public int size() {
    return placeMap.size();
  }

}