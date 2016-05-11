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

  /** Restrições, horários que o time não pode jogar no primeiro dia. */
  private List<Integer> dayOneRestrictions;

  /** Restrições, horários que o time não pode jogar no segundo dia. */
  private List<Integer> dayTwoRestrictions;

  /** Restrições, horários que o time não pode jogar no terceiro dia. */
  private List<Integer> dayThreeRestrictions;

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
    this.dayOneRestrictions = new ArrayList<Integer>();
    this.dayTwoRestrictions = new ArrayList<Integer>();
    this.dayThreeRestrictions = new ArrayList<Integer>();
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

  /**
   * Busca as restrições desta equipe no dia passado como parâmetro.
   *
   * @param day
   *          o dia
   * @return as restrições do dia
   */
  public List<Integer> getRestrictions(int day) {
    List<Integer> result;
    switch (day) {
    case 1:
      result = this.dayOneRestrictions;
      break;
    case 2:
      result = this.dayTwoRestrictions;
      break;
    case 3:
      result = this.dayThreeRestrictions;
      break;
    default:
      throw new RuntimeException("Dia inválido");
    }
    return result;
  }

  /**
   * Adiciona uma restrição no dia passado como parâmetro.
   *
   * @param restriction
   *          a restrição
   * @param day
   *          o dia
   */
  public void addRestriction(Integer restriction, int day) {
    switch (day) {
    case 1:
      this.dayOneRestrictions.add(restriction);
      break;
    case 2:
      this.dayTwoRestrictions.add(restriction);
      break;
    case 3:
      this.dayThreeRestrictions.add(restriction);
      break;
    default:
      throw new RuntimeException("Dia inválido");
    }
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
