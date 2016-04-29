package genetic;

public class AllMatches {

  private static Match[] matches;

  public static void initialize(int numberOfTeams) {
    matches = new Match[numberOfTeams * (numberOfTeams - 1) * SportsEnum.values().length];
    for (int i = 0; i < matches.length; i++) {
      matches[i] = new Match();
      matches[i].setId(i);
    }

    int counter = 0;

    SportsEnum[] sports = SportsEnum.values();

    for (SportsEnum sport : sports) {
      for (int i = 0; i < numberOfTeams; i++) {
        for (int j = 0; j < numberOfTeams; j++) {
          if (i == j) {
            continue;
          }
          matches[counter].setTeam1(i);
          matches[counter].setTeam2(j);
          matches[counter].setSport(sport);
          counter++;
        }
      }
    }
  }

  public static Match getMatch(int index) {
    return matches[index];
  }

  public static int totalMatches() {
    return matches.length;
  }

}
