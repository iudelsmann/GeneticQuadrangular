package genetic;

import java.util.Arrays;

/**
 * Classe que representa um indivíduo de uma população. Contem um vetor de jogos
 * que representa o horário que cado jogo deve ser jogado e métodos para gerar
 * indivíduo aleatório e calcular fitness.
 */
public class Individual {

  /** Genes. Vetor de jogos onde o índice indica o horário do jogo. */
  private Match[] genes = new Match[AllMatches.totalMatches()];

  /** Para cache da fitness deste indivíduo. */
  private int fitness = -1;

  /**
   * Construtor.
   *
   * @param initialize
   *          indica se deve-se gerar este indivíduo aleatoriamente
   */
  public Individual(boolean initialize) {
    if (initialize) {
      this.generateIndividual();
    }
  }

  /**
   * Cria um indivíduo VÁLIDO aleatoriamente.
   */
  public void generateIndividual() {
    // Vetor auxiliar com lista de indices de jogos
    int[] match = new int[genes.length];
    for (int i = 0; i < match.length; i++) {
      match[i] = i;
    }

    // Cria a aleatoriariedade misturando a lista de indices
    Helper.shuffleArray(match);

    // Adiciona os jogos na ordem embaralhada
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

    // Apaga a cache da função de fitness
    fitness = -1;
  }

  public int size() {
    return genes.length;
  }

  public Match[] getGenes() {
    return genes;
  }

  public boolean containsGene(Match gene) {
    return Arrays.asList(genes).contains(gene);
  }

  /**
   * Calcula o fitness deste indivíduo de acordo com a seguinte função:<br>
   * <br>
   *
   * <p>
   * - +2pts caso o horário não esteja na lista de restrições do time 1
   * </p>
   * <p>
   * - +2pts caso o horário não esteja na lista de restrições do time 2
   * </p>
   * <p>
   * - +1pt caso o jogo anterior seja do mesmo esporte
   * </p>
   * <p>
   * - -4pts caso o time 1 já tenha um jogo avaliado para este mesmo dia
   * </p>
   * <p>
   * - -4pts caso o time 2 já tenha um jogo avaliado para este mesmo dia
   * </p>
   *
   * @return o fitness deste indivíduo
   */
  public int getFitness() {
    // Se tiver cache não reexecuta os cálculos
    if (fitness == -1) {
      fitness = 0;
      // Itera sobre os jogos verificando as restrições e o esporte anterior,
      // incrementando o fitness de acordo
      for (int i = 0; i < genes.length; i++) {
        int day = (i / 10) + 1;
        if (!genes[i].getTeam1().getRestrictions(day).contains(Integer.valueOf(i))) {
          fitness += 2;
        }
        if (!genes[i].getTeam2().getRestrictions(day).contains(Integer.valueOf(i))) {
          fitness += 2;
        }
        if (i % 10 > 0 && genes[i - 1].getSport().equals(genes[i].getSport())) {
          fitness++;
        }
        // Se algum time já tem jogo avaliado para este dia
        if (this.hasGameAtDay(genes[i].getTeam1(), day, i)) {
          fitness -= 4;
        }
        if (this.hasGameAtDay(genes[i].getTeam2(), day, i)) {
          fitness -= 4;
        }
      }
    }
    return fitness;
  }

  public boolean hasGameAtDay(Team team, int day, int until) {
    for (int i = (day - 1) * 10; i < until; i++) {
      if (genes[i].teamPlaying(team)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public String toString() {
    StringBuilder geneString = new StringBuilder();
    for (int day = 1; day <= 3; day++) {
      geneString.append("\nDia " + day + ":\n");
      for (int i = 0; i < size() / 3; i++) {
        geneString.append(Constants.times[i]);
        geneString.append(getGene(i + 10 * (day - 1)));
      }
    }
    return geneString.toString();
  }
}
