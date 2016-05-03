package genetic;

public class GA {

  public static void main(String[] args) {

    // Cria todos os jogos
    AllMatches.initialize();

    int[] times = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

    for (int i = 0; i < Constants.NUMBER_OF_TEAMS * SportsEnum.values().length; i++) {
      int totalRestrictions = (int) (Math.random() * 10);
      Helper.shuffleArray(times);
      Team team = AllMatches.getTeam(i);
      for (int j = 0; j < totalRestrictions; j++) {
        team.getRestrictions().add(Integer.valueOf(times[j]));
      }
    }

    // Create an initial population
    Population myPop = new Population(50, true);

    // Evolve our population until we reach an optimum solution
    int generationCount = 0;
    while (generationCount < Constants.MAX_GENERATIONS) {
      generationCount++;
      double[] values = myPop.getStatistics();
      System.out.println("Generation: " + generationCount + "-> Fittest: " + values[0]
          + "/ Least fit: " + values[1] + "/ Average: " + values[2]);
      myPop = Algorithm.evolvePopulation(myPop);
    }
    System.out.println("Solution found!");
    System.out.println("Generation: " + generationCount);
    System.out.println("Genes:");
    System.out.println(myPop.getFittest());

  }
}
