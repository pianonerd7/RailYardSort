import org.junit.*;
import static org.junit.Assert.*;

/**
 * Tests the RailYard class.
 * @author Jiaxin He
 */
public class RailYardTester {
  
  /**
   * TESTING REPORT
   * The tests should consist of Test 0, Test 1, Test many, representing the input array into
   * the constructor as an empty array, an array of length one, and an array of length greater
   * than 1. Also testing the values at each position of the array with numbers of 1 or greater.
   * Test first, Test middle, and Test last represents the different locations to sort of the
   * inputting train. If we divided the sorting train to 3 sections, scrambling the first section
   * while the other 2 sections remain sorted, is testing first. Scrambing the middle section
   * while the outside 2 sections are sorted, is testing middle. And scrambling the last section
   * while the first 2 sections are sorted, is testing last. And also scrambling the entire train,
   * and randomizing the entire train. There also should be at least two types of data for each
   * test, in Integers and String.
   */
  
  /**
   * ADDITIONAL REQUIREMENT
   * 
   * railyard and train where cycle sort is able, closest sort fails.
   * railyard and train where cycle sort fail, closest sort able.
   */
  @Test
  public void additionalRequirement() {
    
    //railyard and train where cycle sort fail, closest sort able.
    int[] arr = {3, 3};
    RailYard<Integer> s = new RailYard<Integer>(arr);
    Integer[] trainUnsorted = {33, 50, 76, 52, 80, 70, 90, 1, 20, 3, 14, 2};
    Integer[] trainSorted = {1, 2, 3, 14, 20, 33, 50, 52, 70, 76, 80, 90};
    s.cycleSort(trainUnsorted);
    
    assertNotSame(trainSorted, trainUnsorted);
    
    int[] arr1 = {3, 3};
    RailYard<Integer> s1 = new RailYard<Integer>(arr1);
    Integer[] trainUnsorted1 = {33, 50, 76, 52, 80, 70, 90, 1, 20, 3, 14, 2};
    Integer[] trainSorted1 = { 1, 2, 3, 14, 20, 33, 50, 52, 70, 76, 80, 90 };
    s1.closestSort(trainUnsorted1);
    
    assertArrayEquals(trainSorted1, trainUnsorted1);
    
    /**
     * railyard and train where cycle sort is able, closest sort fails.
     * 
     * I don't believe that there is a configuration where the cycle sort is able
     * to sort the train where the closest sort is not. There are definitely certain
     * configurations that the closest sort will not be able to sort, but I don't
     * believe that cycle sort can sorted while closest can't. The cycle sort is just
     * a less efficient algorithm of closest sort. It checks the current track to see
     * if the train is larger than the tail train. If the train is larger, then it 
     * is placed onto the next track, which may or may not be appropriate. But the 
     * closest sort finds the next appropriate place for the train. Therefore, 
     * I don't believe there is a configuration of tracks and yards such that cycle 
     * sort is capable of sorting an array whereas the closest sort is not.
     */  
  }
  
  /**
   * Tests the cycle Sort algorithm from the RailYard class.
   */
  @Test
  public void cycleSort() {
    
    //TEST INTEGER
    
    //Test 0 for constructor array.
    int[] arr = new int[0];
    RailYard<Integer> s = new RailYard<Integer>(arr);
    Integer[] trainUnsorted = {3, 1, 4, 1, 5, 9, 6};
    Integer[] trainSorted = {3, 1, 4, 1, 5, 9, 6};
    s.cycleSort(trainUnsorted);
    
    assertArrayEquals(trainSorted, trainUnsorted);
    
    //Test 1 for constructor array.
    int[] arr1 = {1};
    RailYard<Integer> s1 = new RailYard<Integer>(arr1);
    Integer[] trainUnsorted1 = {3, 1, 4, 1, 5, 9, 6};
    Integer[] trainSorted1 = {3, 1, 4, 1, 5, 9, 6};
    s1.cycleSort(trainUnsorted1);
    
    assertArrayEquals(trainSorted1, trainUnsorted1);
    
    //Test many for constructor array.
    int[] arr2 = {2, 3};
    RailYard<Integer> s2 = new RailYard<Integer>(arr2);
    
    Integer[] trainUnsorted2 = {3, 1, 4, 1, 5, 9, 6};
    Integer[] trainSorted2 = { 1, 1, 3, 4, 5, 6, 9};
    s2.cycleSort(trainUnsorted2);
    
    assertArrayEquals(trainSorted2, trainUnsorted2);
    
    //FIRST, MIDDLE, LAST
    
    //Test first (first section of array scrambled)
    int[] arr3 = {2, 3};
    RailYard<Integer> s3 = new RailYard<Integer>(arr3);
    Integer[] trainUnsorted3 = {3, 1, 4, 1, 5, 9, 6, 7, 7, 7, 7, 7, 7, 7, 7};
    Integer[] trainSorted3 = { 1, 1, 3, 4, 5, 6, 7, 7, 7, 7, 7, 7, 7, 7, 9 };
    s3.cycleSort(trainUnsorted3);
    
    assertArrayEquals(trainSorted3, trainUnsorted3);
    
    //Test middle (middle section of array scrambled)
    int[] arr4 = {2, 3};
    RailYard<Integer> s4 = new RailYard<Integer>(arr4);
    Integer[] trainUnsorted4 = {7, 7, 7, 7, 3, 1, 4, 1, 5, 9, 6, 7, 7, 7, 7};
    Integer[] trainSorted4 = { 1, 1, 3, 4, 5, 6, 7, 7, 7, 7, 7, 7, 7, 7, 9 };
    s4.cycleSort(trainUnsorted4);
    
    assertArrayEquals(trainSorted4, trainUnsorted4);
    
    //Test last (last section of array scrambled)
    int[] arr5 = {2, 3};
    RailYard<Integer> s5 = new RailYard<Integer>(arr5);
    
    Integer[] trainUnsorted5 = {7, 7, 7, 7, 7, 7, 7, 7, 3, 1, 4, 1, 5, 9, 6};
    Integer[] trainSorted5 = { 1, 1, 3, 4, 5, 6, 7, 7, 7, 7, 7, 7, 7, 7, 9 };
    s5.cycleSort(trainUnsorted5);
    
    assertArrayEquals(trainSorted5, trainUnsorted5);
    
    //Test all (all section of array scrambled)
    int[] arr6 = {2, 3, 5};
    RailYard<Integer> s6 = new RailYard<Integer>(arr6);
    Integer[] trainUnsorted6 = {3, 71, 4, 1, 55, 9, 6, 21, 1, 6, 47, 2, 84, 2, 1};
    Integer[] trainSorted6 = { 1, 1, 1, 2, 2, 3, 4, 6, 6, 9, 21, 47, 55, 71, 84 };
    s6.cycleSort(trainUnsorted6);
    
    assertArrayEquals(trainSorted1, trainUnsorted1);
    
    //Test none (array in order)
    int[] arr7 = {2, 3};
    RailYard<Integer> s7 = new RailYard<Integer>(arr7);
    
    Integer[] trainUnsorted7 = {1, 2, 3, 40, 56, 86, 89};
    Integer[] trainSorted7 = { 1, 2, 3, 40, 56, 86, 89 };
    s7.cycleSort(trainUnsorted7);
    
    assertArrayEquals(trainSorted7, trainUnsorted7);
    
    //Test 1 for sorting array.
    int[] arr9 = {4,3};
    RailYard<Integer> s9 = new RailYard<Integer>(arr9);
    
    Integer[] trainUnsorted9 = {3};
    Integer[] trainSorted9 = {3};
    s9.cycleSort(trainUnsorted9);
    
    assertArrayEquals(trainSorted9, trainUnsorted9);
    
    //Test many for sorting array.
    int[] arr10 = {4,4};
    RailYard<Integer> s10 = new RailYard<Integer>(arr10);
    Integer[] trainUnsorted10 = {3, 1, 4, 1, 5, 9, 6};
    Integer[] trainSorted10 = {1, 1, 3, 4, 5, 6, 9 };
    s10.cycleSort(trainUnsorted10);
    
    assertArrayEquals(trainSorted10, trainUnsorted10);
    
    //TEST String
    
    //Test 0 for constructor array.
    int[] ar = new int[0];
    RailYard<String> b = new RailYard<String>(ar);
    String[] trainUnsort = {"abc", "abb", "a", "ab"};
    String[] trainSort = {"abc", "abb", "a", "ab"};
    b.cycleSort(trainUnsort);
    
    assertArrayEquals(trainSort, trainUnsort);
    
    //Test 1 for constructor array.
    int[] ar1 = {1};
    RailYard<String> b1 = new RailYard<String>(ar1);
    String[] trainUnsort1 = {"abc", "abb", "a", "ab"};
    String[] trainSort1 = {"abc", "abb", "a", "ab"};
    b1.cycleSort(trainUnsort1);
    
    assertArrayEquals(trainSort1, trainUnsort1);
    
    //Test many for constructor array.
    int[] ar2 = {2, 3};
    RailYard<String> b2 = new RailYard<String>(ar2);
    
    String[] trainUnsort2 = {"abc", "abb", "a", "ab"};
    String[] trainSort2 = { "a", "ab", "abb", "abc"};
    b2.cycleSort(trainUnsort2);
    
    assertArrayEquals(trainSort2, trainUnsort2);
    
    //FIRST, MIDDLE, LAST
    
    //Test first (first section of array scrambled)
    int[] ar3 = {2, 3};
    RailYard<String> b3 = new RailYard<String>(ar3);
    String[] trainUnsort3 = {"abc", "abb", "a", "ab", "ppp", "qqt", "why", "zzz"};
    String[] trainSort3 = { "a", "ab", "abb", "abc", "ppp", "qqt", "why", "zzz"};
    b3.cycleSort(trainUnsort3);
    
    assertArrayEquals(trainSort3, trainUnsort3);
    
    //Test middle (middle section of array scrambled)
    int[] ar4 = {5, 5};
    RailYard<String> b4 = new RailYard<String>(ar4);
    String[] trainUnsort4 = {"ppp", "qqt","abc", "abb", "a", "ab", "why", "zzz"};
    String[] trainSort4 = { "a", "ab", "abb", "abc", "ppp", "qqt", "why", "zzz"};
    b4.cycleSort(trainUnsort4);
    
    assertArrayEquals(trainSort4, trainUnsort4);
    
    //Test last (last section of array scrambled)
    int[] ar5 = {2, 3};
    RailYard<String> b5 = new RailYard<String>(ar5);
    
    String[] trainUnsort5 = { "a", "ab", "abb", "abc", "ppp","zzz", "qqt", "why"};
    String[] trainSort5 = { "a", "ab", "abb", "abc", "ppp", "qqt", "why", "zzz"};
    b5.cycleSort(trainUnsort5);
    
    assertArrayEquals(trainSort5, trainUnsort5);
    
    //Test all (all section of array scrambled)
    int[] ar6 = {2, 3, 5};
    RailYard<String> b6 = new RailYard<String>(ar6);
    String[] trainUnsort6 = { "qqt", "ab", "abb","zzz", "abc", "ppp", "why", "a"};
    String[] trainSort6 = { "a", "ab", "abb", "abc", "ppp", "qqt", "why", "zzz"};
    b6.cycleSort(trainUnsort6);
    
    assertArrayEquals(trainSort1, trainUnsort1);
    
    //Test none (array in order)
    int[] ar7 = {2, 3};
    RailYard<String> b7 = new RailYard<String>(ar7);
    
    String[] trainUnsort7 = { "a", "ab", "abb", "abc", "ppp", "qqt", "why", "zzz"};
    String[] trainSort7 = { "a", "ab", "abb", "abc", "ppp", "qqt", "why", "zzz"};
    b7.cycleSort(trainUnsort7);
    
    assertArrayEquals(trainSort7, trainUnsort7);
    
    //Test 1 for sorting array.
    int[] ar9 = {4,3};
    RailYard<String> b9 = new RailYard<String>(ar9);
    
    String[] trainUnsort9 = {"a"};
    String[] trainSort9 = {"a"};
    b9.cycleSort(trainUnsort9);
    
    assertArrayEquals(trainSort9, trainUnsort9);
    
    //Test many for sorting array.
    int[] ar10 = {4,3};
    RailYard<String> b10 = new RailYard<String>(ar10);
    String[] trainUnsort10 = {"it", "is", "the", "last", "project"};
    String[] trainSort10 = { "is", "it", "last", "project", "the"};
    b10.cycleSort(trainUnsort10);
    
    assertArrayEquals(trainSort10, trainUnsort10);
    
  }
  
  /**
   * Tests the clostest Sort algorithm from the RailYard class.
   */
  @Test
  public void closestSort() {
    
    //TEST INTEGER
    
    //Test 0 for constructor array.
    int[] arr = new int[0];
    RailYard<Integer> s = new RailYard<Integer>(arr);
    Integer[] trainUnsorted = {3, 1, 4, 1, 5, 9, 6};
    Integer[] trainSorted = {3, 1, 4, 1, 5, 9, 6};
    s.closestSort(trainUnsorted);
    
    assertArrayEquals(trainSorted, trainUnsorted);
    
    //Test 1 for constructor array.
    int[] arr1 = {1};
    RailYard<Integer> s1 = new RailYard<Integer>(arr1);
    Integer[] trainUnsorted1 = {3, 1, 4, 1, 5, 9, 6};
    Integer[] trainSorted1 = {3, 1, 4, 1, 5, 9, 6};
    s1.closestSort(trainUnsorted1);
    
    assertArrayEquals(trainSorted1, trainUnsorted1);
    
    //Test many for constructor array.
    int[] arr2 = {2, 3};
    RailYard<Integer> s2 = new RailYard<Integer>(arr2);
    
    Integer[] trainUnsorted2 = {3, 1, 4, 1, 5, 9, 6};
    Integer[] trainSorted2 = { 1, 1, 3, 4, 5, 6, 9};
    s2.closestSort(trainUnsorted2);
    
    assertArrayEquals(trainSorted2, trainUnsorted2);
    
    //FIRST, MIDDLE, LAST
    
    //Test first (first section of array scrambled)
    int[] arr3 = {2, 3};
    RailYard<Integer> s3 = new RailYard<Integer>(arr3);
    Integer[] trainUnsorted3 = {3, 1, 4, 1, 5, 9, 6, 7, 7, 7, 7, 7, 7, 7, 7};
    Integer[] trainSorted3 = { 1, 1, 3, 4, 5, 6, 7, 7, 7, 7, 7, 7, 7, 7, 9 };
    s3.closestSort(trainUnsorted3);
    
    assertArrayEquals(trainSorted3, trainUnsorted3);
    
    //Test middle (middle section of array scrambled)
    int[] arr4 = {2, 3};
    RailYard<Integer> s4 = new RailYard<Integer>(arr4);
    Integer[] trainUnsorted4 = {7, 7, 7, 7, 3, 1, 4, 1, 5, 9, 6, 7, 7, 7, 7};
    Integer[] trainSorted4 = { 1, 1, 3, 4, 5, 6, 7, 7, 7, 7, 7, 7, 7, 7, 9 };
    s4.closestSort(trainUnsorted4);
    
    assertArrayEquals(trainSorted4, trainUnsorted4);
    
    //Test last (last section of array scrambled)
    int[] arr5 = {2, 3};
    RailYard<Integer> s5 = new RailYard<Integer>(arr5);
    
    Integer[] trainUnsorted5 = {7, 7, 7, 7, 7, 7, 7, 7, 3, 1, 4, 1, 5, 9, 6};
    Integer[] trainSorted5 = { 1, 1, 3, 4, 5, 6, 7, 7, 7, 7, 7, 7, 7, 7, 9 };
    s5.closestSort(trainUnsorted5);
    
    assertArrayEquals(trainSorted5, trainUnsorted5);
    
    //Test all (all section of array scrambled)
    int[] arr6 = {2, 3, 5};
    RailYard<Integer> s6 = new RailYard<Integer>(arr6);
    Integer[] trainUnsorted6 = {3, 71, 4, 1, 55, 9, 6, 21, 1, 6, 47, 2, 84, 2, 1};
    Integer[] trainSorted6 = { 1, 1, 1, 2, 2, 3, 4, 6, 6, 9, 21, 47, 55, 71, 84 };
    s6.closestSort(trainUnsorted6);
    
    assertArrayEquals(trainSorted1, trainUnsorted1);
    
    //Test none (array in order)
    int[] arr7 = {2, 3};
    RailYard<Integer> s7 = new RailYard<Integer>(arr7);
    
    Integer[] trainUnsorted7 = {1, 2, 3, 40, 56, 86, 89};
    Integer[] trainSorted7 = { 1, 2, 3, 40, 56, 86, 89 };
    s7.closestSort(trainUnsorted7);
    
    assertArrayEquals(trainSorted7, trainUnsorted7);
    
    //Test 1 for sorting array.
    int[] arr9 = {4,3};
    RailYard<Integer> s9 = new RailYard<Integer>(arr9);
    
    Integer[] trainUnsorted9 = {3};
    Integer[] trainSorted9 = {3};
    s9.closestSort(trainUnsorted9);
    
    assertArrayEquals(trainSorted9, trainUnsorted9);
    
    //Test many for sorting array.
    int[] arr10 = {4,3};
    RailYard<Integer> s10 = new RailYard<Integer>(arr10);
    Integer[] trainUnsorted10 = {3, 1, 4, 1, 5, 9, 6};
    Integer[] trainSorted10 = { 1, 1, 3, 4, 5, 6, 9 };
    s10.closestSort(trainUnsorted10);
    
    assertArrayEquals(trainSorted10, trainUnsorted10);
    
    //TEST String
    
    //Test 0 for constructor array.
    int[] ar = new int[0];
    RailYard<String> b = new RailYard<String>(ar);
    String[] trainUnsort = {"abc", "abb", "a", "ab"};
    String[] trainSort = {"abc", "abb", "a", "ab"};
    b.closestSort(trainUnsort);
    
    assertArrayEquals(trainSort, trainUnsort);
    
    //Test 1 for constructor array.
    int[] ar1 = {1};
    RailYard<String> b1 = new RailYard<String>(ar1);
    String[] trainUnsort1 = {"abc", "abb", "a", "ab"};
    String[] trainSort1 = {"abc", "abb", "a", "ab"};
    b1.closestSort(trainUnsort1);
    
    assertArrayEquals(trainSort1, trainUnsort1);
    
    //Test many for constructor array.
    int[] ar2 = {2, 3};
    RailYard<String> b2 = new RailYard<String>(ar2);
    
    String[] trainUnsort2 = {"abc", "abb", "a", "ab"};
    String[] trainSort2 = { "a", "ab", "abb", "abc"};
    b2.closestSort(trainUnsort2);
    
    assertArrayEquals(trainSort2, trainUnsort2);
    
    //FIRST, MIDDLE, LAST
    
    //Test first (first section of array scrambled)
    int[] ar3 = {2, 3};
    RailYard<String> b3 = new RailYard<String>(ar3);
    String[] trainUnsort3 = {"abc", "abb", "a", "ab", "ppp", "qqt", "why", "zzz"};
    String[] trainSort3 = { "a", "ab", "abb", "abc", "ppp", "qqt", "why", "zzz"};
    b3.closestSort(trainUnsort3);
    
    assertArrayEquals(trainSort3, trainUnsort3);
    
    //Test middle (middle section of array scrambled)
    int[] ar4 = {2, 3};
    RailYard<String> b4 = new RailYard<String>(ar4);
    String[] trainUnsort4 = {"ppp", "qqt","abc", "abb", "a", "ab", "why", "zzz"};
    String[] trainSort4 = { "a", "ab", "abb", "abc", "ppp", "qqt", "why", "zzz"};
    b4.closestSort(trainUnsort4);
    
    assertArrayEquals(trainSort4, trainUnsort4);
    
    //Test last (last section of array scrambled)
    int[] ar5 = {2, 3};
    RailYard<String> b5 = new RailYard<String>(ar5);
    
    String[] trainUnsort5 = { "a", "ab", "abb", "abc", "ppp","zzz", "qqt", "why"};
    String[] trainSort5 = { "a", "ab", "abb", "abc", "ppp", "qqt", "why", "zzz"};
    b5.closestSort(trainUnsort5);
    
    assertArrayEquals(trainSort5, trainUnsort5);
    
    //Test all (all section of array scrambled)
    int[] ar6 = {2, 3, 5};
    RailYard<String> b6 = new RailYard<String>(ar6);
    String[] trainUnsort6 = { "qqt", "ab", "abb","zzz", "abc", "ppp", "why", "a"};
    String[] trainSort6 = { "a", "ab", "abb", "abc", "ppp", "qqt", "why", "zzz"};
    b6.closestSort(trainUnsort6);
    
    assertArrayEquals(trainSort1, trainUnsort1);
    
    //Test none (array in order)
    int[] ar7 = {2, 3};
    RailYard<String> b7 = new RailYard<String>(ar7);
    
    String[] trainUnsort7 = { "a", "ab", "abb", "abc", "ppp", "qqt", "why", "zzz"};
    String[] trainSort7 = { "a", "ab", "abb", "abc", "ppp", "qqt", "why", "zzz"};
    b7.closestSort(trainUnsort7);
    
    assertArrayEquals(trainSort7, trainUnsort7);
    
    //Test 1 for sorting array.
    int[] ar9 = {4,3};
    RailYard<String> b9 = new RailYard<String>(ar9);
    
    String[] trainUnsort9 = {"a"};
    String[] trainSort9 = {"a"};
    b9.closestSort(trainUnsort9);
    
    assertArrayEquals(trainSort9, trainUnsort9);
    
    //Test many for sorting array.
    int[] ar10 = {4,3};
    RailYard<String> b10 = new RailYard<String>(ar10);
    String[] trainUnsort10 = {"it", "is", "the", "last", "project"};
    String[] trainSort10 = { "is", "it", "last", "project", "the"};
    b10.closestSort(trainUnsort10);
    
    assertArrayEquals(trainSort10, trainUnsort10);
    
  }   
}