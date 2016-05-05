package genetic;

/**
 * Classe que representa uma partida.
 */
public class Match {

  /** Modalidade da partida. */
  private SportsEnum sport;

  /** Time 1. */
  private Team team1;

  /** Time 2. */
  private Team team2;

  /**
   * Instancia uma partida.
   *
   * @param team1
   *          time 1
   * @param team2
   *          time 2
   */
  public Match(Team team1, Team team2) {
    this.team1 = team1;
    this.team2 = team2;

    // Se os times não forem da mesma modalide gera exceção
    if (!team1.getSport().equals(team2.getSport())) {
      throw new RuntimeException("Invalid match");
    }
    this.sport = team1.getSport();
  }

  public SportsEnum getSport() {
    return sport;
  }

  public Team getTeam1() {
    return team1;
  }

  public Team getTeam2() {
    return team2;
  }

  public boolean teamPlaying(Team team) {
    return this.team1.equals(team) || this.team2.equals(team);
  }

  @Override
  public String toString() {
    return String.format("%s: %s x %s\n", sport.name(), team1.getName(), team2.getName());
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Match)) {
      return false;
    }
    Match other = (Match) o;
    boolean result = other.team1.equals(this.team1);
    result &= other.team2.equals(this.team2);
    result &= other.sport.equals(this.sport);
    return result;
  }

}
