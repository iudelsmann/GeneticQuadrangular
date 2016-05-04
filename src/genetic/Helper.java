package genetic;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Classe com métodos auxiliares necessários.
 */
public class Helper {

  /**
   * Embaralha um vetor de inteiros. Baseado no algoritmo de Fisher–Yates
   *
   * @param array
   *          o vetor de inteiros
   */
  public static void shuffleArray(int[] array) {
    Random rnd = ThreadLocalRandom.current();
    for (int i = array.length - 1; i > 0; i--) {
      int index = rnd.nextInt(i + 1);
      // Simple swap
      int a = array[index];
      array[index] = array[i];
      array[i] = a;
    }
  }

}
