package genetic;

public class GA {

  public static int numberOfTeams = 4;

  public static void main(String[] args) {

    // Cria todos os jogos
    AllMatches.initialize(numberOfTeams);

    Match[] solution = new Match[AllMatches.totalMatches()];
    for (int i = 0; i < solution.length; i++) {
      solution[i] = AllMatches.getMatch(i);
    }
    FitnessCalc.setSolution(solution);

    // Create an initial population
    Population myPop = new Population(500, true);

    // Evolve our population until we reach an optimum solution
    int generationCount = 0;
    while (myPop.getFittest().getFitness() < FitnessCalc.getMaxFitness()) {
      generationCount++;
      System.out.println(
          "Generation: " + generationCount + " Fittest: " + myPop.getFittest().getFitness());
      myPop = Algorithm.evolvePopulation(myPop);
    }
    System.out.println("Solution found!");
    System.out.println("Generation: " + generationCount);
    System.out.println("Genes:");
    System.out.println(myPop.getFittest());

  }
}
