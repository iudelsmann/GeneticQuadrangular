package genetic;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um time.
 */
public class Team {

  /** Esporte que este time pratica. */
  private SportsEnum sport;

  /** Nome do time. */
  private String name;

  /** Restrições, horários que o time não pode jogar. */
  private List<Integer> restrictions;

  /**
   * Instancia um time, com nome a modalidade passados como parãmetros.
   *
   * @param name
   *          nome do time
   * @param sport
   *          esporte praticado
   */
  public Team(String name, SportsEnum sport) {
    this.setName(name);
    this.setSport(sport);
    this.restrictions = new ArrayList<Integer>();
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

  public void addRestriction(Integer restriction) {
    this.restrictions.add(restriction);
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

  @Override
  public String toString() {
    return this.getName() + " " + this.getSport().name() + " " + this.getRestrictions().toString();
  }
}
