import java.util.*;

/**
 * The RailYard class represents a railyard with yards to sort the cars of a train, 
 * as well as parallel tracks, which is utilized during sorting. This class takes a list
 * or an array of generics, and will sort it in order. 
 * @author Jiaxin He
 */
public class RailYard<T extends Comparable<? super T>> {
  
  /** The yards used during sorting. Also the length of input array to constructor. */
  private ArrayList<ArrayList<LinkedList<T>>> yard;
  
  /**
   * The constructor takes an int array, and the length of the array will be the number of
   * yards, and the value stored at each index will be the number of parallel tracks at that
   * yard. This yard system will be used in this class.
   * @param arr The input integer array that determines the number of yard and tracks.
   */
  public RailYard(int[] arr) {
    
    if (arr.length < 1) {
      System.out.println ("Please think it through and input an acceptable number of yard and tracks.");
    }
    
    if (arr.length == 1 && arr[0] <= 1) {
      System.out.println ("Please think it through and input an acceptable number of yard and tracks.");
    }
    
    yard = new ArrayList<ArrayList<LinkedList <T>>>(arr.length);
    
    for (int i = 0; i<arr.length; i++){
      yard.add(new ArrayList<LinkedList <T>>(arr[i]));
      for (int j = 0; j<arr[i]; j++) {
        yard.get(i).add(new LinkedList<T>());
      }
    }
  }
  
  /**
   * Returns the yard of the class.
   * @return The yard of the class as a two dimensional ArrayList.
   */
  public ArrayList<ArrayList<LinkedList<T>>> getYard() {
    return yard;
  }
  
  /**
   * Takes an array of generic type, and sorts the array. Uses cycle sort algorithm, which
   * places car after preceeding car if of equal or greater value, or the next track if less.
   * @param train The inputting array to sort.
   */
  public void cycleSort(T[] train) {
    int lanes; 
    int length = train.length;
    ArrayList<T> mergedList = new ArrayList<T>(length);
    LinkedList<T> list = new LinkedList<T>();
    
    int lanePosition = 0;
    
    //loops through every yard.
    for (int i = 0; i < getYard().size(); i++) {
      lanes = getYard().get(i).size();
      //loops through every car in the train and finds a track.
      for (int j = 0; j < train.length; j++) {
        
        if (j == 0) {
          getYard().get(i).get(0).add(0, train[j]);
        }
        
        else if (j!=0 && train[j] != null) {
          if (train[j].compareTo(train[j-1]) >= 0) {
            getYard().get(i).get(lanePosition).add(0, train[j]);
          }
          
          else if (train[j].compareTo(train[j-1]) < 0) {
            if (lanePosition+1<lanes) {
              getYard().get(i).get(lanePosition+1).add(0, train[j]);
              lanePosition++;
            }
            else if (lanePosition+1 >=lanes) {
              getYard().get(i).get(0).add(0, train[j]);
              lanePosition = 0;
            }    
          }
        }
      }
      mergedList = merge(i, length);
      for (int q = 0; q <length; q++) {
        train[q] = mergedList.get(q);
      }
    }
  }
  
  /**
   * Takes a list of generic type, and sorts the list. Uses cycle sort algorithm, which
   * places car after preceeding car if of equal or greater value, or the next track if less.
   * @param train The inputting list to sort.
   */
  public void cycleSort(List<T> train) {
    int lanes;
    int length = train.size();
    ArrayList<T> mergedList = new ArrayList<T>(length);
    int count = 0;
    T next;
    T previous = train.get(0);
    int lanePosition = 0;
    
    //loops through every yard.
    for (int i = 0; i < getYard().size(); i++) {
      lanes = getYard().get(i).size();
      
      //iterates over all items in train, starting from first element.
      Iterator <T> iterator = train.iterator();
      while (iterator.hasNext()) {
        next = iterator.next();
        
        if (count == 0) {
          getYard().get(i).get(0).add(0, next);
          count++;
        }
        
        else if (count!=0) {
          if (next.compareTo(previous) >= 0) {
            getYard().get(i).get(lanePosition).add(0, next);
          }
          
          else if (next.compareTo(previous) < 0) {
            if (lanePosition+1<lanes) {
              getYard().get(i).get(lanePosition+1).add(0, next);
              lanePosition++;
            }
            else if (lanePosition+1 >=lanes) {
              getYard().get(i).get(0).add(0, next);
              lanePosition = 0;
            }    
          }
        }
        previous = next;
      }
      mergedList = merge(i, length);
      for (int q = 0; q <length; q++) {
        train.set(q, mergedList.get(q));
      }
      count = 0;
      lanePosition = 0;
    }
  }
  
  /**
   * Takes an array of generic type, and sorts the array. Uses closest sort algorithm, which
   * places car after largest smaller value, or to empty track if smaller than all tail cars.
   * @param train The inputting array to sort.
   */
  public void closestSort(T[] train) {
    int lanes;
    int length = train.length;
    LinkedList<T> list = new LinkedList<T>();
    ArrayList<T> mergedList = new ArrayList<T>(length);
    
    boolean small = false;
    boolean empty = false;
    boolean larger = false;
    boolean smallOnce = false;
    boolean largeOnce = false;
    T smallest = train[0];
    T largest = train[0];
    int indexS = 0;
    int indexE = 0;
    int indexF = 0;
    
    //loops through all yards.
    for (int i = 0; i < getYard().size(); i++) {
      lanes = getYard().get(i).size();
      //loops through every car in the train.
      for (int j = 0; j < train.length; j++) {
        smallest = train[j];
        largest = train[j];
        //loops through every lane to find smallest, largest, and empty track compared to train at j.
        for (int k = 0; k < lanes; k++) {
          if (!getYard().get(i).get(k).equals(list) && getYard().get(i).get(k).get(0) != null) {
            
            if (getYard().get(i).get(k).get(0).compareTo(train[j]) <= 0) {
              if (smallest.equals(train[j]) && !smallOnce) {
                smallest = getYard().get(i).get(k).get(0);
                indexS = k;
                small = true;
                smallOnce = true;
              }  
              else if (smallest.compareTo(getYard().get(i).get(k).getFirst()) <= 0) { 
                smallest = getYard().get(i).get(k).getFirst();
                indexS = k;
                small = true;
              }  
            }
            
            if (this.getYard().get(i).get(k).getFirst().compareTo(train[j]) > 0) {
              if (largest.equals(train[j]) && !largeOnce) {
                largest = getYard().get(i).get(k).getFirst();
                indexF = k;
                larger = true;
                largeOnce = true;
              }  
              else if (largest.compareTo(getYard().get(i).get(k).getFirst()) >= 0) {
                largest = getYard().get(i).get(k).getFirst();
                indexF = k;
                larger = true;
              }
            }
          }
          
          else if (getYard().get(i).get(k).equals(list) && !empty) {
            indexE = k;
            empty = true;
          }
        }        
        if (small == true) {
          getYard().get(i).get(indexS).add(0, train[j]);
        }
        
        else if (empty == true && small == false) {
          getYard().get(i).get(indexE).add(0, train[j]);
        }
        
        else if (larger == true && empty == false && small == false) {
          getYard().get(i).get(indexF).add(0, train[j]);
        }
        
        small = false;
        empty = false;
        larger = false;
        smallOnce = false;
        largeOnce = false;
      }
      mergedList = merge(i, length);
      for (int q = 0; q <length; q++) {
        train[q] = mergedList.get(q);
      }
    }
  }
  
  /**
   * Takes a list of generic type, and sorts the list. Uses closest sort algorithm, which
   * places car after largest smaller value, or to empty track if smaller than all tail cars.
   * @param train The inputting list to sort.
   */
  public void closestSort(List<T> train) {
    T next;
    int lanes;
    int length = train.size();
    LinkedList<T> list = new LinkedList<T>();
    ArrayList<T> mergedList = new ArrayList<T>(length);
    
    boolean small = false;
    boolean empty = false;
    boolean larger = false;
    boolean smallOnce = false;
    boolean largeOnce = false;
    T smallest = train.get(0);
    T largest = train.get(0);
    int indexS = 0;
    int indexE = 0;
    int indexF = 0;
    
    //loops through every yard.
    for (int i = 0; i < getYard().size(); i++) {
      lanes = getYard().get(i).size();
      
      //iterates over every element in the train.
      Iterator <T> iterator = train.iterator();
      while (iterator.hasNext()) {
        next = iterator.next();
        smallest = next;
        largest = next;
        //loops through every lane to find the smallest, largest and empty track compared to train at j.
        for (int k = 0; k < lanes; k++) {
          if (!getYard().get(i).get(k).equals(list)) {
            
            if (getYard().get(i).get(k).getFirst().compareTo(next) <= 0) {
              if (smallest.equals(next) && !smallOnce) {
                smallest = getYard().get(i).get(k).getFirst();
                indexS = k;
                small = true;
                smallOnce = true;
              }  
              else if (smallest.compareTo(getYard().get(i).get(k).getFirst()) <= 0) { 
                smallest = getYard().get(i).get(k).getFirst();
                indexS = k;
                small = true;
              }  
            }
            
            if (this.getYard().get(i).get(k).getFirst().compareTo(next) > 0) {
              if (largest.equals(next) && !largeOnce) {
                largest = getYard().get(i).get(k).getFirst();
                indexF = k;
                larger = true;
                largeOnce = true;
              }  
              else if (largest.compareTo(getYard().get(i).get(k).getFirst()) >= 0) {
                largest = getYard().get(i).get(k).getFirst();
                indexF = k;
                larger = true;
              }
            }
          }
          
          else if (getYard().get(i).get(k).equals(list) && !empty) {
            indexE = k;
            empty = true;
          }
        }
        if (small == true) {
          getYard().get(i).get(indexS).add(0, next);
        }
        
        else if (empty == true && small == false) {
          getYard().get(i).get(indexE).add(0, next);
        }
        
        else if (larger == true && empty == false && small == false) {
          getYard().get(i).get(indexF).add(0, next);
        }
        
        small = false;
        empty = false;
        larger = false;
        smallOnce = false;
        largeOnce = false;
      }
      mergedList = merge(i, length);
      for (int q = 0; q <length; q++) {
        train.set(q, mergedList.get(q));
      }
    }
  }
  
  /**
   * Takes the yard, and length of the train to sort as input and returns the merged tracks as an
   * ArrayList.
   * @param yard The classification yard to sort.
   * @param length The length of the train to sort.
   * @return The merged yard represented as an ArrayList.
   */
  public ArrayList<T> merge (int yard, int length) {
    LinkedList<T> list = new LinkedList<T>();
    int lanes = getYard().get(yard).size();
    T smallest = getYard().get(yard).get(0).getLast();
    int index = 0;
    ArrayList<T> arr = new ArrayList<T>(length);
    
    //loops through the length of the train, because that's how many spots to fill.
    for (int j = 0; j <length; j++) {
      
      //loops through every lane to find the smallest value to add to the ArrayList.
      for (int i = 0; i < lanes; i++) {
        if (getYard().get(yard).get(i).equals(list) == false) {
          if (getYard().get(yard).get(i).size() > 1){
            if (smallest.compareTo(getYard().get(yard).get(i).getLast()) > 0) {
              smallest = getYard().get(yard).get(i).getLast();
              index = i;
            }
          }
          
          else if (getYard().get(yard).get(i).size() == 1 && 
                   getYard().get(yard).get(i).getFirst() != null){
            if (smallest.compareTo(getYard().get(yard).get(i).getFirst()) > 0) {
              smallest = getYard().get(yard).get(i).getFirst();
              index = i;
            }
          }
        }
        
        else if (getYard().get(yard).get(i).equals(list) == true) {
        }
      }
      
      arr.add(smallest); 
      if (getYard().get(yard).get(index).equals(list) == false) {
        getYard().get(yard).get(index).removeLast();
      }
      
      //finds the smallest element among all lanes.
      for (int k = 0; k < lanes; k++) {
        if (getYard().get(yard).get(k).equals(list) == false) {
          smallest = getYard().get(yard).get(k).getLast();
          index = k;
        }
      }
    }
    return arr;
  }
}