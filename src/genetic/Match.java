package genetic;

public class Match {

  private int id;

  private SportsEnum sport;

  private int team1;

  private int team2;

  public SportsEnum getSport() {
    return sport;
  }

  public void setSport(SportsEnum sport) {
    this.sport = sport;
  }

  public int getTeam1() {
    return team1;
  }

  public void setTeam1(int team1) {
    this.team1 = team1;
  }

  public int getTeam2() {
    return team2;
  }

  public void setTeam2(int team2) {
    this.team2 = team2;
  }

  @Override
  public String toString() {
    return String.format("%s: %d x %d\n", sport.name(), team1, team2);
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

}
