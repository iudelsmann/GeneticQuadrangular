package genetic;

public class AllMatches {

  private static Match[] matches = new Match[(Constants.NUMBER_OF_TEAMS / 2)
      * SportsEnum.values().length];

  private static Team[] teams = new Team[Constants.NUMBER_OF_TEAMS * SportsEnum.values().length];

  public static void initialize() {

    int counter = 0;

    counter = 0;
    for (int i = 0; i < matches.length; i++) {
      matches[i] = new Match(teams[counter], teams[counter + 1]);
      counter += 2;
    }
  }

  public static Match getMatch(int index) {
    return matches[index];
  }

  public static Team getTeam(int index) {
    return teams[index];
  }

  public static void setTeam(Team team, int index) {
    teams[index] = team;
  }

  public static int totalMatches() {
    return matches.length;
  }

}
