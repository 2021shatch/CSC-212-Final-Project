/**
 * Class for exit info and methods for exit
 *
 * @author Anh Nguyen
 * @author Sabrina Hatch
 * @author Pratima Naroula
 * @version Spring 2022
 */
class Exit {
  /** where exit lead to */
  private Place toPlace;
  /** can the user use this exit */
  private boolean accessible;

  /** constructor */
  public Exit(Place toPlace, boolean accessible) {
    this.toPlace = toPlace;
    this.accessible = accessible;
  }

  /** @return Place get where exit lead to */
  public Place getToPlace() {
    if (accessible) {
      return toPlace;
    } else {
      return null;
    }
  }

  /** set where exit lead to */
  public void setToPlace(Place toPlace) {
    this.toPlace = toPlace;
  }

  /** @return boolean true if user can use this exit */
  public boolean getAccess() {
    return accessible;
  }

  /**
   * set exit accisibility
   * 
   * @arg accesible true if user can use, false otherwis
   */
  public void setAccess(boolean a) {
    this.accessible = a;
  }

  /** @return String string representation */
  public String toString() {
    String str;
    if (toPlace != null) {
      str = "To: " + toPlace + ", Is accessible: " + accessible;
    } else {
      str = "LMAO";
    }
    return str;
  }
}