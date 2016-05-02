package genetic;

public class Team {

  private int id;

  private SportsEnum sport;

  private String name;

  public Team() {
  }

  public Team(int id, String name, SportsEnum sport) {
    this.setId(id);
    this.setName(name);
    this.setSport(sport);
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public SportsEnum getSport() {
    return sport;
  }

  public void setSport(SportsEnum sport) {
    this.sport = sport;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
