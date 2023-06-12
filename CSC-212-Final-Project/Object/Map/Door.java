class Door extends Item {
  /** where exit lead to */
  private Place toPlace;

  /** can the user use this exit */
  private boolean accessible;

  public Door(String name, String description, Place toPlace, boolean accessible) {
    super(name, description);
    this.toPlace = toPlace;
    this.accessible = accessible;
  }

  /** @return Place get where exit lead to */
  public Place useDoor() {
    if (accessible) {
      return toPlace;
    } else {
      return null;
    }
  }

  /** get where exit lead to */
  public Place getToPlace() {
    return toPlace;
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
  public void setAccess(boolean accessible) {
    this.accessible = accessible;
  }
}