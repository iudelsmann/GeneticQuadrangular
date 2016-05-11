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

  /**
   * Troca o intervalo entre as posições pasadas como parâmetro de dois vetores.
   *
   * @param array1
   *          primeiro vetor
   * @param array2
   *          segundo vetor
   * @param startPos
   *          inicio do techo a ser invertido
   * @param endPos
   *          fim do trecho
   */
  public static void swapArray(Object[] array1, Object[] array2, Integer startPos, Integer endPos) {
    for (int i = startPos; i < endPos; i++) {
      Object aux = array1[i];
      array1[i] = array2[i];
      array2[i] = aux;
    }
  }
}
