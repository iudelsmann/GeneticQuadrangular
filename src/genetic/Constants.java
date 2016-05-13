package genetic;

public class Constants {

  /** Número de times em cada modalidade. Não alterar por enquanto. */
  static final int NUMBER_OF_TEAMS = 4;

  /** Porcentagem de mutação. */
  static double MUTATION_RATE = 0.25;

  /** Taxa de crossover. */
  static double CROSSOVER_RATE = 0.75;

  static CrossoverOptionsEnum CROSSOVER_TYPE = CrossoverOptionsEnum.OX;

  /** Tamanho dos torneios de seleção. */
  static int TOURNAMENT_SIZE = 5;

  /** Se deve ser usado elitismo ou não. */
  static boolean ELITISM = true;

  /** Tamanho da popualção. */
  static int POPULATION_SIZE = 400;

  /** Número máximo de gerações. */
  static int MAX_GENERATIONS = 200;

  /** Vetor auxiliar para formatação da saída apenas. */
  static final String[] times = { "08:00h: ", "09:30h: ", "11:00h: ", "12:30h: ", "14:00h: ",
      "15:30h: ", "17:00h: ", "18:30h: ", "20:00h: ", "21:30h: " };

}
