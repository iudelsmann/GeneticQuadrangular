package genetic;

public class AllMatches {

  private static Match[] matches;

  private static Team[] teams;

  public static void initialize() {

    teams = new Team[Constants.NUMBER_OF_TEAMS * SportsEnum.values().length];

    SportsEnum[] sports = SportsEnum.values();
    int counter = 0;

    for (SportsEnum sport : sports) {
      for (int i = 0; i < Constants.NUMBER_OF_TEAMS; i++) {
        teams[counter] = new Team(counter, sport.name() + i, sport);
        counter++;
      }
    }

    matches = new Match[(Constants.NUMBER_OF_TEAMS / 2) * SportsEnum.values().length];

    counter = 0;
    for (int i = 0; i < matches.length; i++) {
      matches[i] = new Match(i, teams[counter], teams[counter + 1]);
      counter += 2;
    }
  }

  public static Match getMatch(int index) {
    return matches[index];
  }

  public static int totalMatches() {
    return matches.length;
  }

}
