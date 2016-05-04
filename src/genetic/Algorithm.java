package genetic;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Classe que implementa métodos que realmente executam a lógica de um algoritmo
 * genético.
 */
public class Algorithm {

  /**
   * Evolui uma população, retornando a nova população gerada.
   *
   * @param pop
   *          a população atual
   * @return a nova população, ou geração
   */
  public static Population evolvePopulation(Population pop) {
    // Instancia uma nova população de mesmo tamanho
    Population newPopulation = new Population(pop.size(), false);

    // Caso elitísmo esteja habilidade já salva o melhor indivíduo
    int elitismOffset = 0;
    if (Constants.ELITISM) {
      newPopulation.saveIndividual(0, pop.getFittest());
      elitismOffset = 1;
    }

    // Reprodução via crossover e mutação para gerar novos indivíduos
    for (int i = elitismOffset; i < pop.size(); i++) {
      // Seleção via campeonato
      Individual indiv1 = tournamentSelection(pop);
      Individual indiv2 = tournamentSelection(pop);
      Individual newIndiv = crossover(indiv1, indiv2);
      newPopulation.saveIndividual(i, mutate(newIndiv));
    }
    return newPopulation;
  }

  /**
   * Reprodução via crossover. Crossover diferente do tradicional, uma vez que
   * não gera indivíduos inválidos.<br>
   * <br>
   *
   * Este crossover funciona da seguinte forma:<br>
   * Escolhe-se dois pontos do primeiro indivíduo e copia-se os valores entre
   * estes pontos para o filho. O restante é preenchido a partir do segundo
   * indivíduo, adicionando os genes ainda não presentes na ordem em que
   * aparecem. Dessa forma um gene nunca aparecerá duas vezes e nenhum faltará,
   * todos os jogos são cobertos e o indivíduo gerado é sempre válido.
   *
   * @param indiv1
   *          indivíduo 1
   * @param indiv2
   *          indivíduo 2
   * @return o novo indivíduo gerado
   */
  private static Individual crossover(Individual indiv1, Individual indiv2) {
    Random rnd = ThreadLocalRandom.current();

    Individual newIndiv = new Individual(false);

    int startPos = rnd.nextInt(indiv1.size());
    int endPos = rnd.nextInt(indiv1.size());

    // Inverte os valores se necessário
    if (startPos > endPos) {
      int aux = startPos;
      startPos = endPos;
      endPos = aux;
    }

    // Copia o intervalo do primeiro indivíduo
    for (int i = startPos; i < endPos; i++) {
      newIndiv.setGene(i, newIndiv.getGene(i));
    }

    int pos = 0;

    // Copia os valores do segundo indivíduo
    for (int i = 0; i < indiv2.size(); i++) {
      // Se o filho ainda não tem o gene desta iteração
      if (!newIndiv.containsGene(indiv2.getGene(i))) {
        // Itera sobre o filho ate encontrar um gene vazio
        for (int j = pos; j < newIndiv.size(); j++) {
          if (newIndiv.getGene(j) == null) {
            newIndiv.setGene(j, indiv2.getGene(i));
            pos = j;
            break;
          }
        }
      }
    }
    return newIndiv;
  }

  /**
   * Processo de mutação. Funciona por swap, o que garante que nenhuma partida
   * será duplicada e nenhum faltará.
   *
   * @param indiv
   *          o indivíduo a ser mutado
   * @return o indivíduo mutado
   */
  private static Individual mutate(Individual indiv) {
    Random rnd = ThreadLocalRandom.current();
    // Muta apenas alguns indivíduos de acordo com a porcentagem
    if (rnd.nextDouble() <= Constants.MUTATION_RATE) {
      // Executa um swap entre posições aleatórias
      int swap1 = rnd.nextInt(indiv.size());
      int swap2 = rnd.nextInt(indiv.size());
      Match aux = indiv.getGene(swap1);
      indiv.setGene(swap1, indiv.getGene(swap2));
      indiv.setGene(swap2, aux);
    }
    return indiv;
  }

  /**
   * Seleção de indivíduos via torneio.
   *
   * @param pop
   *          a população
   * @return o indivíduo selecionado
   */
  private static Individual tournamentSelection(Population pop) {
    Random rnd = ThreadLocalRandom.current();
    // Instancia uma nova população de tamanho igual a defino para o torneio
    Population tournament = new Population(Constants.TOURNAMENT_SIZE, false);

    // Preenche a população com indivíduos selecionados aleatoriamente
    for (int i = 0; i < Constants.TOURNAMENT_SIZE; i++) {
      int randomId = rnd.nextInt(pop.size());
      tournament.saveIndividual(i, pop.getIndividual(randomId));
    }
    // Retorna o indivíduo mais apto dentre os participantes
    return tournament.getFittest();
  }
}