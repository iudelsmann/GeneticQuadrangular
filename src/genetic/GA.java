package genetic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe main.
 */
public class GA {

  public static void main(String[] args) throws Exception {

    readArguments();

    // Se um arquivo foi passado como parâmetro ele é lido, caso contrário,
    // lê-se da entrada padrão
    if (args.length > 0) {
      System.setIn(new FileInputStream(args[0]));
    }

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String input;
    String params[];

    // Regex para isolar restrições
    Pattern p = Pattern.compile("\\[(.*?)\\]");

    // Itera sobre entrada criando os times que jogarão
    for (int counter = 0; counter < Constants.NUMBER_OF_TEAMS
        * SportsEnum.values().length; counter++) {
      input = br.readLine();
      params = input.split(" ");
      Team team = new Team(params[0], SportsEnum.valueOf(params[1]));

      // Adiciona as restrições de cada dia
      Matcher m = p.matcher(input);
      int day = 1;
      while (m.find()) {
        String[] restrictions = m.group(1).split(",[ ]*");
        for (int i = 0; i < restrictions.length; i++) {
          team.addRestriction(Integer.valueOf(restrictions[i]), day);
        }
        day++;
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
      System.out
          .println("Generation: " + generationCount + "-> Fittest: " + values[0] + " / Least fit: "
              + values[1] + " / Average: " + values[2] + " / Violations: " + values[3]);
      myPop = Algorithm.evolvePopulation(myPop);
    }

    // Imprime a melhor solução encontrada.
    System.out.println("\nGenes:");
    System.out.println(myPop.getFittest());
  }

  /**
   * Le os argumentos para configurar a execução.
   *
   * @throws IOException
   *           Em caso de erro na leitura.
   */
  private static void readArguments() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    System.out.println(
        "Configure os dados da execução. Obs: Caso aperte enter sem preencher o default será utilizado");

    System.out.print("Tamanho da população (default 400): ");
    String temp = br.readLine();

    if (!temp.isEmpty())
      Constants.POPULATION_SIZE = Integer.parseInt(temp);

    System.out.print("Numero de gerações (default 200): ");
    temp = br.readLine();

    if (!temp.isEmpty())
      Constants.MAX_GENERATIONS = Integer.parseInt(temp);

    System.out.print("Tamanho dos torneios (default 5): ");
    temp = br.readLine();

    if (!temp.isEmpty())
      Constants.TOURNAMENT_SIZE = Integer.parseInt(temp);

    System.out.print("Taxa de crossover (default 0.75): ");
    temp = br.readLine();

    if (!temp.isEmpty())
      Constants.CROSSOVER_RATE = Double.parseDouble(temp);

    System.out.print("Taxa de mutação (default 0.25): ");
    temp = br.readLine();

    if (!temp.isEmpty())
      Constants.MUTATION_RATE = Double.parseDouble(temp);

    System.out.print("Elitism 'true' ou 'false'? (default true): ");
    temp = br.readLine();

    if (!temp.isEmpty())
      Constants.ELITISM = Boolean.parseBoolean(temp);

    System.out.print("Crossover 'OX' ou 'PMX'? (default OX): ");
    temp = br.readLine();

    if (!temp.isEmpty())
      Constants.CROSSOVER_TYPE = CrossoverOptionsEnum.valueOf(temp.toUpperCase());

  }
}
