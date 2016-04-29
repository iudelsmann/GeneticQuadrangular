package genetic;

public class FitnessCalc {

  static Match[] solution = new Match[GA.numberOfTeams * (GA.numberOfTeams - 1)
      * SportsEnum.values().length];

  /* Public methods */
  // Set a candidate solution as a byte array
  public static void setSolution(Match[] newSolution) {
    solution = newSolution;
  }

  // Calculate inidividuals fittness by comparing it to our candidate solution
  static int getFitness(Individual individual) {
    int fitness = 0;
    // Loop through our individuals genes and compare them to our cadidates
    for (int i = 0; i < individual.size() && i < solution.length; i++) {
      if (individual.getGene(i).equals(solution[i])) {
        fitness++;
      }
    }
    return fitness;
  }

  // Get optimum fitness
  static int getMaxFitness() {
    int maxFitness = solution.length;
    return maxFitness;
  }
}
