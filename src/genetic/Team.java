package genetic;

import java.util.ArrayList;
import java.util.List;

public class Team {

  private int id;

  private SportsEnum sport;

  private String name;

  private List<Integer> restrictions;

  public Team() {
  }

  public Team(int id, String name, SportsEnum sport) {
    this.setId(id);
    this.setName(name);
    this.setSport(sport);
    this.restrictions = new ArrayList<Integer>();
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

  public List<Integer> getRestrictions() {
    return restrictions;
  }

  public void setRestrictions(List<Integer> restrictions) {
    this.restrictions = restrictions;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Team)) {
      return false;
    }
    Team other = (Team) o;
    boolean result = other.name.equals(this.name);
    result &= other.sport.equals(this.sport);
    return result;

  }
}
