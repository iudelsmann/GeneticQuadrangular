package genetic;

/**
 * Classe que representa uma populção. Um conjunto de indivíduos e métodos para
 * opera-los.
 */
public class Population {

  /** Indivíduos da população. */
  private Individual[] individuals;

  /**
   * Construtor que recebe o tamanho da população e se deve gerar os indivíduos
   * ou não.
   *
   * @param populationSize
   *          tamanho da população
   * @param initialise
   *          se deve gerar os indivíduos ou não
   */
  public Population(int populationSize, boolean initialise) {
    // Instancia o vetor com o tamanho passado
    individuals = new Individual[populationSize];

    // Gera indivíduos aleatórios se necessário
    if (initialise) {
      for (int i = 0; i < size(); i++) {
        Individual newIndividual = new Individual(true);
        saveIndividual(i, newIndividual);
      }
    }
  }

  public Individual getIndividual(int index) {
    return individuals[index];
  }

  /**
   * Retorna o indivíduo com maior aptidão.
   *
   * @return indivíduo com maior aptidão
   */
  public Individual getFittest() {
    Individual fittest = individuals[0];
    for (int i = 0; i < size(); i++) {
      if (fittest.getFitness() <= getIndividual(i).getFitness()) {
        fittest = getIndividual(i);
      }
    }
    return fittest;
  }

  /**
   * Método auxiliar para calcular estatísticas. Não é necessário par resolver o
   * problema, porém retorna dados importantes para observar o comportamento da
   * execução.
   *
   * @return um vetor com 3 dados. As aptidoẽs dos indivíduo mais e menos aptos,
   *         e a média de toda a população.
   */
  public double[] getStatistics() {
    Individual best = individuals[0];
    Individual worst = individuals[0];

    double sum = 0;

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

  public int size() {
    return individuals.length;
  }

  public void saveIndividual(int index, Individual indiv) {
    individuals[index] = indiv;
  }
}