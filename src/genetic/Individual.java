package genetic;

public class Individual implements Comparable<Individual> {

  private static int size;

  private Match[] genes;

  private int fitness = 0;

  public Individual() {
    genes = new Match[size];
  }

  public Individual(int size) {
    Individual.size = size;
    this.generateIndividual(size);
  }

  // Create a random individual
  public void generateIndividual(int size) {
    genes = new Match[size];
    for (int i = 0; i < size(); i++) {
      Match gene = AllMatches.getMatch((int) (Math.random() * AllMatches.totalMatches()));
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
    String geneString = "";
    for (int i = 0; i < size(); i++) {
      geneString += getGene(i);
    }
    return geneString;
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
}
