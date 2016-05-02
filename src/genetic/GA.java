package genetic;

public class GA {

  public static void main(String[] args) {

    // Cria todos os jogos
    AllMatches.initialize();

    Individual sol = new Individual();
    sol.generateIndividual(AllMatches.totalMatches());

    FitnessCalc.setSolution(sol.getGenes());

    // Create an initial population
    Population myPop = new Population(50, true);

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
