package genetic;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Individual implements Comparable<Individual> {

  private Match[] genes;

  private int fitness = 0;

  public Individual() {
    genes = new Match[AllMatches.totalMatches()];
  }

  public Individual(int size) {
    this.generateIndividual(size);
  }

  // Cria um indivíduo VÁLIDO aleatoriamente
  public void generateIndividual(int size) {
    int[] match = new int[size];
    for (int i = 0; i < match.length; i++) {
      match[i] = i;
    }

    this.shuffleArray(match);

    genes = new Match[size];
    for (int i = 0; i < size(); i++) {
      Match gene = AllMatches.getMatch(match[i]);
      genes[i] = gene;
    }
  }

  public Match getGene(int index) {
    return genes[index];
  }

  public void setGene(int index, Match value) {
    genes[index] = value;
    fitness = 0;
  }

  /* Public methods */
  public int size() {
    return genes.length;
  }

  public int getFitness() {
    if (fitness == 0) {
      fitness = FitnessCalc.getFitness(this);
    }
    return fitness;
  }

  @Override
  public String toString() {
    StringBuilder geneString = new StringBuilder();
    for (int i = 0; i < size(); i++) {
      geneString.append(Constants.times[i] + "h: ");
      geneString.append(getGene(i));
    }
    return geneString.toString();
  }

  @Override
  public int compareTo(Individual o) {
    int thisFitness = this.getFitness();
    int otherFitness = o.getFitness();

    if (thisFitness > otherFitness) {
      return 1;
    } else if (thisFitness < otherFitness) {
      return -1;
    } else {
      return 0;
    }
  }

  // Implementing Fisher–Yates shuffle
  private void shuffleArray(int[] ar) {
    Random rnd = ThreadLocalRandom.current();
    for (int i = ar.length - 1; i > 0; i--) {
      int index = rnd.nextInt(i + 1);
      // Simple swap
      int a = ar[index];
      ar[index] = ar[i];
      ar[i] = a;
    }
  }

  public Match[] getGenes() {
    return genes;
  }
}
