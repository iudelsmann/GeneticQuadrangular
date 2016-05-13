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
   * Retorna o indivíduo com maior aptidão. O critério de desempate é o número
   * de restrições violadas.
   *
   * @return indivíduo com maior aptidão
   */
  public Individual getFittest() {
    Individual fittest = individuals[0];
    for (int i = 0; i < size(); i++) {
      if (fittest.getFitness() < getIndividual(i).getFitness()) {
        fittest = getIndividual(i);
      } else if (fittest.getFitness() == getIndividual(i).getFitness()
          && fittest.getViolated() > getIndividual(i).getFitness()) {
        fittest = getIndividual(i);
      }
    }
    return fittest;
  }

  /**
   * Busca os dois indivíduos como maior aptidão.
   *
   * @return os dois indivíduos com maior aptidão
   */
  public Individual[] getTwoFittest() {
    Individual first = individuals[0];
    Individual second = individuals[0];
    for (int i = 0; i < size(); i++) {
      if (first.getFitness() <= getIndividual(i).getFitness()) {
        second = first;
        first = getIndividual(i);
      }
    }
    Individual[] result = { first, second };
    return result;
  }

  /**
   * Método auxiliar para calcular estatísticas. Não é necessário par resolver o
   * problema, porém retorna dados importantes para observar o comportamento da
   * execução.
   *
   * @return um vetor com 4 dados. As aptidoẽs dos indivíduo mais e menos aptos,
   *         , a média de toda a população e o número de violações de restrições
   *         do melhor indivíduo.
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
    double[] result = { best.getFitness(), worst.getFitness(), avg, best.getViolated() };
    return result;
  }

  /**
   * Retorna o tamanho da população.
   *
   * @return o tamanho da população.
   */
  public int size() {
    return individuals.length;
  }

  /**
   * Salva um indivíduo na posição passada como parâmetro.
   *
   * @param index
   *          o indice
   * @param indiv
   *          o indivíduo
   */
  public void saveIndividual(int index, Individual indiv) {
    individuals[index] = indiv;
  }
}