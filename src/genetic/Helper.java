package genetic;

import java.util.List;
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
   * Swap the element at the specified index between the two specified Lists.
   *
   * @param list2
   *          A List.
   * @param list1
   *          Another List.
   * @param index
   *          The index of the element to swap.
   * @param <E>
   *          The type of element in the List to swap.
   * @param <L>
   *          The type of Lists to swap between.
   */
  public static <E, L extends List<E>> void swap(final L list1, final L list2, final int index) {

    final E temp = list1.get(index);
    list1.set(index, list2.get(index));
    list2.set(index, temp);

  }

  /**
   * Swap each element in the specified Lists between the specified start and
   * end indices.
   *
   * @param list1
   *          A List.
   * @param list2
   *          Another List.
   * @param start
   *          The index of the first element to swap.
   * @param end
   *          The index of the last element to swap.
   * @param <E>
   *          The type of elements in the List to swap.
   * @param <L>
   *          The type of Lists to swap between.
   */
  public static <E, L extends List<E>> void swap(final L list1, final L list2, final int start,
      final int end) {
    for (int i = start; i < end; i++) {
      swap(list1, list2, i);
    }
  }

}
