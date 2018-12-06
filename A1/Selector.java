import java.util.Arrays;
/**
* Defines a library of selection methods
* on arrays of ints.
*
* @author   Natalie Eichorn (npe0004@auburn.edu)
* @author   Dean Hendrix (dh@auburn.edu)
* @version  2018-01-15
*
*/

public final class Selector {

   /**
    * Can't instantiate this class.
    *
    * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
    *
    */
   private Selector() { }


   /**
    * Selects the minimum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    */
   public static int min(int[] a) {
      if ((a == null) || (a.length == 0)) {
         throw new IllegalArgumentException();
      }
      else {
         int min = a[0];
         
         for (int i = 0; i < a.length; i++) {
            if (a[i] <= min) {
               min = a[i];
            }
         }
         return min;
      }
   }


   /**
    * Selects the maximum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    */
   public static int max(int[] a) {
      if ((a == null) || (a.length == 0)) {
         throw new IllegalArgumentException();
      }
      else {
         int max = a[0];
         
         for (int i = 0; i < a.length; i++) {
            if (a[i] >= max) {
               max = a[i];
            }
         }
         return max;
      }
   }


   /**
    * Selects the kth minimum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth minimum value. Note that there is no kth
    * minimum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method. 
    
    * Note that there is no kth minimum value if k is less than 1, k is greater 
    than the number of elements in the array, or if k is greater than the number
   of distinct values in the array. 
    */
   public static int kmin(int[] a, int k) {
      if ((a == null) || (a.length == 0) || (k < 1) || (k > a.length)) {
         throw new IllegalArgumentException();
      }
      else {
         int[] aCopy = Arrays.copyOf(a, a.length);
         int dupCount = 0;
         int spot = 1;
         
         //sorts list low to high
         Arrays.sort(aCopy);
         
         
         //finding number of duplicates occuring in list
         for (int i = 1; i < aCopy.length; i++) {
            if (aCopy[i] == aCopy[i - 1]) {
               dupCount++;
            }
         }
         
         
         //incase all the nums are the same 
         if (dupCount == aCopy.length - 1) {
            return aCopy[0];
         }
         
         
         //when there are duplicates to be removed
         else if (dupCount > 0) {
         
            //making new list
            int newLength = aCopy.length - dupCount;
            int[] aFinal = new int[newLength];
            
            //making sure the first num is in list
            aFinal[0] = aCopy[0];
   
               
            //removing doubles for new list
            for (int i = 1; i < aCopy.length; i++) {
               if (aCopy[i] != aCopy[i - 1]) {
                  aFinal[spot] = aCopy[i];
                  spot++;
               }
            }
               
            int knew = k - 1;
            return aFinal[knew];
         }
      
         else {
            //when there are no duplicates
            int knew = k - 1;
            return aCopy[knew];
         }
      }
   }


   /**
    * Selects the kth maximum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth maximum value. Note that there is no kth
    * maximum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    */
   public static int kmax(int[] a, int k) {
      if ((a == null) || (a.length == 0) || (k < 1) || (k > a.length)) {
         throw new IllegalArgumentException();
      }
      else {
         int[] aCopy = Arrays.copyOf(a, a.length);
         int dupCount = 0;
         int spot = 1;
         
         //sorts list low to high
         Arrays.sort(aCopy);
         
//System.out.println(Arrays.toString(aCopy));         
         //finding number of duplicates occuring in list
         for (int i = 1; i < aCopy.length; i++) {
            if (aCopy[i] == aCopy[i - 1]) {
               dupCount++;
            }
         }

//System.out.println(dupCount);
         
         if (dupCount == aCopy.length - 1 && k > aCopy[0]) {
            return aCopy[0];
         }

         //when there are duplicates to be removed
         else if (dupCount > 0) {
         
            //making new list
            int newLength = aCopy.length - dupCount;
            int[] aFinal = new int[newLength];
               
            //making sure the first num is in list
            aFinal[0] = aCopy[0];
//System.out.println(Arrays.toString(aFinal));   
               
            //removing doubles for new list
            for (int i = 1; i < aCopy.length; i++) {
               if (aCopy[i] != aCopy[i - 1]) {
                  aFinal[spot] = aCopy[i];
                  spot++;
               }
            }
//System.out.println(Arrays.toString(aFinal));               
            int knew = newLength - k;
            return aFinal[knew];
         }
         
         //when there are no duplicates
         else {
            int kspot = aCopy.length - k;
            return aCopy[kspot];
         }
      } 
   }


   /**
    * Returns an array containing all the values in a in the
    * range [low..high]; that is, all the values that are greater
    * than or equal to low and less than or equal to high,
    * including duplicate values. The length of the returned array
    * is the same as the number of values in the range [low..high].
    * If there are no qualifying values, this method returns a
    * zero-length array. Note that low and high do not have
    * to be actual values in a. This method throws an
    * IllegalArgumentException if a is null or has zero length.
    * The array a is not changed by this method.
    */
   public static int[] range(int[] a, int low, int high) {
      int count = 0;
      int spot = 0;
      
      if ((a == null) || (a.length == 0)) {
         throw new IllegalArgumentException();
      }
      else {
         for (int i = 0; i < a.length; i++) {
            if (a[i] <= high && a[i] >= low) {
               count++;
            }
         }
         
         int[] range = new int[count];
         //System.out.println(range);
         
         for (int j = 0; j < a.length; j++) {
            if (a[j] <= high && a[j] >= low) {
               range[spot] = a[j];
               spot++;
            }
         }
         return range;
      }
   }


   /**
    * Returns the smallest value in a that is greater than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static int ceiling(int[] a, int key) {
      if ((a == null) || (a.length == 0)) {
         throw new IllegalArgumentException();
      }
      else {
         int min = -99;
                  
         for (int i = 0; i < a.length; i++) {
            if (a[i] == key) {
               return a[i];
            }
            else if (a[i] > key) {
               min = a[i];
            } 
         }
         
         if (min == -99) {
            throw new IllegalArgumentException();
         }
         
         for (int i = 0; i < a.length; i++) {
            if (a[i] <= min && a[i] >= key) {
               min = a[i];
            }
         }
         
         return min;
      }
   }
      

   /**
    * Returns the largest value in a that is less than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static int floor(int[] a, int key) {
      if ((a == null) || (a.length == 0)) {
         throw new IllegalArgumentException();
      }
      else {
         int max = -99;
         
         for (int i = 0; i < a.length; i++) {
            if (a[i] == key) {
               return a[i];
            }
            else if (a[i] < key) {
               max = a[i];
            }
         }
         
         if (max == -99) {
            throw new IllegalArgumentException();
         }
         
         for (int i = 0; i < a.length; i++) {
            if (a[i] > max && a[i] <= key) {
               max = a[i];
            }
         }
         
         return max;
      }
   }
   
}  
