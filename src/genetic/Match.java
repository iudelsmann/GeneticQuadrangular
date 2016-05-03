package genetic;

public class Match {

  private int id;

  private SportsEnum sport;

  private Team team1;

  private Team team2;

  public Match() {
  }

  public Match(int id, Team team1, Team team2) {
    this.setId(id);
    this.setTeam1(team1);
    this.setTeam2(team2);
    if (!team1.getSport().equals(team2.getSport())) {
      throw new RuntimeException("Invalid match");
    }
    this.setSport(team1.getSport());
  }

  public SportsEnum getSport() {
    return sport;
  }

  public void setSport(SportsEnum sport) {
    this.sport = sport;
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

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Team getTeam1() {
    return team1;
  }

  public void setTeam1(Team team1) {
    this.team1 = team1;
  }

  public Team getTeam2() {
    return team2;
  }

  public void setTeam2(Team team2) {
    this.team2 = team2;
  }

}
