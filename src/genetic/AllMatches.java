package genetic;

/**
 * Classe auxiliar para inicializar e guardar todas as partidas e times.
 */
public class AllMatches {

  /** Vetor para guardar todas as partidas. */
  private static Match[] matches;

  // Inicia o vetor de partidas
  static {
    // Combinação 4 dois a dois vezes o número de esportes
    int totalMatches = ((Constants.NUMBER_OF_TEAMS * (Constants.NUMBER_OF_TEAMS - 1)) / 2)
        * SportsEnum.values().length;
    matches = new Match[totalMatches];
  }

  /** Vetor com todas as equipes. */
  private static Team[] teams = new Team[Constants.NUMBER_OF_TEAMS * SportsEnum.values().length];

  /**
   * Preenche o vetor de partidas.
   */
  public static void initialize() {

    int counter = 0;
    int aux = 0;
    int i = 0;
    for (int k = 0; k < SportsEnum.values().length; k++) {
      aux = i;
      for (i = aux; i < aux + Constants.NUMBER_OF_TEAMS; i++) {
        for (int j = i + 1; j < aux + Constants.NUMBER_OF_TEAMS; j++) {
          matches[counter] = new Match(teams[i], teams[j]);
          counter++;
        }
      }
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
