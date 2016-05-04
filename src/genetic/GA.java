package genetic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class GA {

  public static void main(String[] args) throws Exception {

    // Se um arquivo foi passado como parâmetro ele é lido, caso contrário,
    // lê-se da entrada padrão
    if (args.length > 0) {
      System.setIn(new FileInputStream(args[0]));
    }

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String input;
    String params[];

    // Itera sobre entrada criando os times que jogarão
    for (int counter = 0; counter < Constants.NUMBER_OF_TEAMS
        * SportsEnum.values().length; counter++) {
      input = br.readLine();
      params = input.split(" ");
      Team team = new Team(params[0], SportsEnum.valueOf(params[1]));

      // Adiciona as restrições
      for (int i = 2; i < params.length; i++) {
        team.addRestriction(Integer.valueOf(params[i]));
      }
      AllMatches.setTeam(team, counter);
    }

    // Cria todos os jogos
    AllMatches.initialize();

    // Cria população inicial
    Population myPop = new Population(Constants.POPULATION_SIZE, true);

    // Gera novas gerações ate a condição de parada imprimindo o melhor, o pior,
    // e a média da função de fitness dessa geração
    for (int generationCount = 1; generationCount <= Constants.MAX_GENERATIONS; generationCount++) {
      double[] values = myPop.getStatistics();
      System.out.println("Generation: " + generationCount + "-> Fittest: " + values[0]
          + " / Least fit: " + values[1] + " / Average: " + values[2]);
      myPop = Algorithm.evolvePopulation(myPop);
    }

    // Imprime a melhor solução encontrada.
    System.out.println("Genes:");
    System.out.println(myPop.getFittest());
  }
}
