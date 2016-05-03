package genetic;

public class Population {

  Individual[] individuals;

  /*
   * Constructors
   */
  // Create a population
  public Population(int populationSize, boolean initialise) {
    individuals = new Individual[populationSize];
    // Initialise population
    if (initialise) {
      // Loop and create individuals
      for (int i = 0; i < size(); i++) {
        Individual newIndividual = new Individual(AllMatches.totalMatches());
        saveIndividual(i, newIndividual);
      }
    }
  }

  /* Getters */
  public Individual getIndividual(int index) {
    return individuals[index];
  }

  public Individual getFittest() {
    Individual fittest = individuals[0];
    // Loop through individuals to find fittest
    for (int i = 0; i < size(); i++) {
      if (fittest.getFitness() <= getIndividual(i).getFitness()) {
        fittest = getIndividual(i);
      }
    }
    return fittest;
  }

  public Individual getSecondFittest() {
    Individual fittest = individuals[0];
    Individual second = individuals[0];
    // Loop through individuals to find fittest
    for (int i = 0; i < size(); i++) {
      if (fittest.getFitness() <= getIndividual(i).getFitness()) {
        second = fittest;
        fittest = getIndividual(i);
      }
    }
    return second;
  }

  public double[] getStatistics() {
    Individual best = individuals[0];
    Individual worst = individuals[0];

    double sum = 0;

    // Loop through individuals to find fittest
    for (int i = 0; i < size(); i++) {
      int currentFitness = getIndividual(i).getFitness();
      sum += currentFitness;
      if (best.getFitness() <= currentFitness) {
        best = getIndividual(i);
      } else if (worst.getFitness() > currentFitness) {
        worst = getIndividual(i);
      }
    }
    double avg = sum / size();
    double[] result = { best.getFitness(), worst.getFitness(), avg };
    return result;
  }

  /* Public methods */
  // Get population size
  public int size() {
    return individuals.length;
  }

  // Save individual
  public void saveIndividual(int index, Individual indiv) {
    individuals[index] = indiv;
  }
}