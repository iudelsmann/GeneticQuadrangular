package genetic;

public class Constants {

  /** Número de times em cada modalidade. */
  static int NUMBER_OF_TEAMS = 4;

  /** Porcentagem de mutação. */
  static final double MUTATION_RATE = 0.25;

  /** Tamanho dos torneios de seleção. */
  static final int TOURNAMENT_SIZE = 5;

  /** Se deve ser usado elitismo ou não. */
  static final boolean ELITISM = true;

  /** Tamanho da popualção. */
  static final int POPULATION_SIZE = 500;

  /** Número máximo de gerações. */
  static final int MAX_GENERATIONS = 500;

  /** Vetor auxiliar para formatação da saída apenas. */
  static final String[] times = { "08:00h: ", "09:30h: ", "11:00h: ", "12:30h: ", "14:00h: ",
      "15:30h: ", "17:00h: ", "18:30h: ", "20:00h: ", "21:30h: " };

}
