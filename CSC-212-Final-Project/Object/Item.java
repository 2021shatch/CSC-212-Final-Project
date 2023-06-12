/**
 * Class to store info and methods for items
 *
 * @author Anh Nguyen
 * @author Sabrina Hatch
 * @author Pratima Naroula
 * @version Spring 2022
 */
class Item {
  /** name of place */
  private String name;

  /** text file with description */
  private String description;

  /**
   * constructor for each item object
   * 
   * @param name and description, both strings containing information about the
   *             particular object
   * 
   */
  public Item(String name, String description) {
    this.name = name;
    this.description = description;
  }

  /**
   * Constructor to create an item by reading in from the text file
   * 
   * @param row (required) a line from file
   */
  public Item(String row) {
    // this splits the string by commas, but ignores a comma if it's enclosed by a
    // pair of parentheses
    String[] parse = row.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

    // sets each attribute to an index of the string array "row"
    this.name = parse[0];
    this.description = parse[1];
  }

  /** @returns name of place/room */
  public String getName() {
    return name;
  }

  /**
   * Sets name of the object
   * 
   * @param string containing the name of the object
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Get description
   *
   * @param N/A
   * @return String containing the description of the object
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets description
   * 
   * @param description string of text filename
   * @return N/A
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * prints our name of the location
   * 
   * @param N/A
   * @return String with the name of the location
   */
  public String toString() {
    String str = "Name: " + name;
    return str;
  }

  /**
   * Prints out description associated with location
   * 
   * @return N/A
   */

  public void desc() {
    // Prints out description associated with location
    Parser.printText(description);
  }
}