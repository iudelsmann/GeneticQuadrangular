package genetic;

import java.util.Arrays;

public class Algorithm {

  /* Public methods */

  // Evolve a population
  public static Population evolvePopulation(Population pop) {
    Population newPopulation = new Population(pop.size(), false);
    int elitismOffset = 0;

    // Keep our best individual
    if (Constants.ELITISM) {
      newPopulation.saveIndividual(0, pop.getFittest());
      newPopulation.saveIndividual(1, pop.getSecondFittest());
      elitismOffset = 2;
    }

    Arrays.sort(pop.individuals);

    // Crossover population
    // Loop over the population size and create new individuals with
    // crossover
    for (int i = elitismOffset; i < pop.size(); i += 2) {
      Individual indiv1 = tournamentSelection(pop);
      Individual indiv2 = tournamentSelection(pop);
      Individual[] newIndiv = crossover(indiv1, indiv2);
      newPopulation.saveIndividual(i, newIndiv[0]);
      newPopulation.saveIndividual(i + 1, newIndiv[1]);
    }

    // Mutate population
    for (int i = elitismOffset; i < newPopulation.size(); i++) {
      mutate(newPopulation.getIndividual(i));
    }

    return newPopulation;
  }

  // Crossover individuals
  private static Individual[] crossover(Individual indiv1, Individual indiv2) {
    Individual newSol1 = new Individual();
    Individual newSol2 = new Individual();

    int crossPoint = (int) (Math.random() * indiv1.size());

    Match aux;
    // Loop through genes
    for (int i = 0; i < crossPoint; i++) {
      aux = indiv1.getGene(i);
      newSol1.setGene(i, indiv1.getGene(i));
      newSol2.setGene(i, aux);
    }
    for (int i = crossPoint; i < indiv1.size(); i++) {
      aux = indiv2.getGene(i);
      newSol1.setGene(i, indiv2.getGene(i));
      newSol2.setGene(i, aux);
    }
    Individual[] result = { newSol1, newSol2 };
    return result;
  }

  // Mutate an individual
  private static void mutate(Individual indiv) {
    // Loop through genes
    for (int i = 0; i < indiv.size(); i++) {
      if (Math.random() <= Constants.MUTATION_RATE) {
        // Create random gene
        Match gene = AllMatches.getMatch((int) (Math.random() * AllMatches.totalMatches()));
        indiv.setGene(i, gene);
      }
    }
  }

  // Select individuals for crossover
  private static Individual tournamentSelection(Population pop) {
    // Create a tournament population
    Population tournament = new Population(Constants.TOURNAMENT_SIZE, false);
    // For each place in the tournament get a random individual
    for (int i = 0; i < Constants.TOURNAMENT_SIZE; i++) {
      int randomId = (int) (Math.random() * pop.size());
      tournament.saveIndividual(i, pop.getIndividual(randomId));
    }
    // Get the fittest
    Individual fittest = tournament.getFittest();
    return fittest;
  }
}