package genetic;

import java.util.List;
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
      Individual[] elite = pop.getTwoFittest();
      newPopulation.saveIndividual(0, elite[0]);
      newPopulation.saveIndividual(1, elite[1]);
      elitismOffset = 2;
    }

    // Reprodução via crossover e mutação para gerar novos indivíduos
    for (int i = elitismOffset; i < pop.size(); i += 2) {
      // Seleção via campeonato
      Individual indiv1 = tournamentSelection(pop);
      Individual indiv2 = tournamentSelection(pop);
      Individual[] newIndivs = reproduce(indiv1, indiv2);
      newPopulation.saveIndividual(i, mutate(newIndivs[0]));
      newPopulation.saveIndividual(i + 1, mutate(newIndivs[1]));
    }
    return newPopulation;
  }

  /**
   * Reprodução. Recebe dois indivíduos e gera dois descendentes.
   *
   * @param indiv1
   *          indivíduo 1
   * @param indiv2
   *          indivíduo 2
   * @return o novo par de indivíduos gerados
   */
  private static Individual[] reproduce(Individual indiv1, Individual indiv2) {
    Individual[] result = new Individual[2];

    Random rnd = ThreadLocalRandom.current();

    if (rnd.nextDouble() > Constants.CROSSOVER_RATE) {
      result[0] = new Individual(indiv1);
      result[1] = new Individual(indiv2);
      return result;
    }

    Integer startPos = rnd.nextInt(indiv1.size());
    Integer endPos = rnd.nextInt(indiv1.size());

    while (startPos.equals(endPos)) {
      endPos = rnd.nextInt(indiv1.size());
    }

    // Inverte os valores se necessário
    if (startPos.compareTo(endPos) > 0) {
      int aux = startPos;
      startPos = endPos;
      endPos = aux;
    }
    if (CrossoverOptionsEnum.OX.equals(Constants.CROSSOVER_TYPE)) {
      result[0] = crossoverOX(indiv1, indiv2, startPos, endPos);
      result[1] = crossoverOX(indiv2, indiv1, startPos, endPos);
    } else {
      result = crossoverPMX(indiv1, indiv2, startPos, endPos);
    }
    return result;
  }

  /**
   * <p>
   * Executa um ordered crossover e gera um indivíduo.
   * </p>
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
   * @param startPos
   *          posicao do primeiro corte
   * @param endPos
   *          posicao do segundo corte
   * @return o novo indivíduo gerado pelo crossover
   */
  private static Individual crossoverOX(Individual indiv1, Individual indiv2, Integer startPos,
      Integer endPos) {

    Individual newIndiv = new Individual(false);

    // Copia o intervalo do primeiro indivíduo
    for (int i = startPos; i < endPos; i++) {
      newIndiv.setGene(i, indiv1.getGene(i));
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

  private static Individual[] crossoverPMX(Individual indiv1, Individual indiv2, Integer startPos,
      Integer endPos) {

    Individual tour1 = new Individual(indiv1);
    Individual tour2 = new Individual(indiv2);

    // get the size of the tours
    final int size = tour1.size();

    // crossover the section in between the start and end indices
    Helper.swapArray(tour1.getGenes(), tour2.getGenes(), startPos, endPos);

    // get a view of the crossover over sections in each tour
    final List<Match> swappedSectionInTour1 = tour1.getMatchesBetweenIndexes(startPos, endPos);
    final List<Match> swappedSectionInTour2 = tour2.getMatchesBetweenIndexes(startPos, endPos);

    Match currentCity;
    int replacementCityIndex = 0;
    Match replacementCity;

    // iterate over each city in not in the crossed over section
    for (int i = endPos % size; i >= endPos || i < startPos; i = (i + 1) % size) {

      // get the current city being examined in tour 1
      currentCity = tour1.getGene(i);

      // if that city is repeated in the crossed over section
      if (swappedSectionInTour1.contains(currentCity)) {

        // get the index of the city to replace the repeated city (within the
        // swapped section)
        replacementCityIndex = swappedSectionInTour1.indexOf(currentCity);

        // get the city that is intended to replace the repeated city
        replacementCity = swappedSectionInTour2.get(replacementCityIndex);

        // if the repeated city is also contained in the crossed over section
        while (swappedSectionInTour1.contains(replacementCity)) {

          // get the index of the city to replace the repeated city
          replacementCityIndex = swappedSectionInTour1.indexOf(replacementCity);

          // get the city that is intended to replace the repeated city
          replacementCity = swappedSectionInTour2.get(replacementCityIndex);

        }

        // replace the current city with the replacement city
        tour1.setGene(i, replacementCity);
      }

      // get the current city being examined in tour 2
      currentCity = tour2.getGene(i);

      // if that city is repeated in the crossed over section
      if (swappedSectionInTour2.contains(currentCity)) {

        // get the index of the city to replace the repeated city
        replacementCityIndex = swappedSectionInTour2.indexOf(currentCity);

        // get the city that is intended to replace the repeated city
        replacementCity = swappedSectionInTour1.get(replacementCityIndex);

        // if the repeated city is also contained in the crossed over section
        while (swappedSectionInTour2.contains(replacementCity)) {

          // get the index of the city to replace the repeated city
          replacementCityIndex = swappedSectionInTour2.indexOf(replacementCity);

          // get the city that is intended to replace the repeated city
          replacementCity = swappedSectionInTour1.get(replacementCityIndex);
        }

        // replace the current city with the replacement city
        tour2.setGene(i, replacementCity);
      }
    }
    Individual[] result = { tour1, tour2 };
    return result;
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