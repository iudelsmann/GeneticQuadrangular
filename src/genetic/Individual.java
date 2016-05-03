package genetic;

import java.util.ArrayList;
import java.util.List;

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

    Helper.shuffleArray(match);

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
    fitness = 0;
    List<Match> visited = new ArrayList<Match>();
    for (int i = 0; i < genes.length; i++) {
      if (visited.contains(genes[i])) {
        fitness--;
      } else {
        if (!genes[i].getTeam1().getRestrictions().contains(Integer.valueOf(i))) {
          fitness++;
        }
        if (!genes[i].getTeam2().getRestrictions().contains(Integer.valueOf(i))) {
          fitness++;
        }
        if (i > 0 && genes[i - 1].getSport().equals(genes[i].getSport())) {
          fitness++;
        }
        visited.add(genes[i]);
      }
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

  public Match[] getGenes() {
    return genes;
  }
}
