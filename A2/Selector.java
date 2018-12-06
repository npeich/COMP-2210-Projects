import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Defines a library of selection methods on Collections.
 *
 * @author  Natalie Eichorn (npe0004@auburn.edu)
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version 2018-01-25
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
    * Returns the minimum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the minimum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T min(Collection<T> coll, Comparator<T> comp) {
      if ((coll == null) || (comp == null)) {
         throw new IllegalArgumentException();
      }
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
      if (coll.size() == 0) {
         throw new IllegalArgumentException();
      }
      
      else {
         Iterator itr = coll.iterator();
         
         T min = (T)itr.next();
         
         while (itr.hasNext()) {
            
            T holder = (T)itr.next();
                        
            if (comp.compare(holder, min) < 0) {
               min = holder;
            }
         }
         return min;
      }
   }
      
      
   /**
    * Selects the maximum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the maximum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T max(Collection<T> coll, Comparator<T> comp) {
      if ((coll == null) || (comp == null)) {
         throw new IllegalArgumentException();
      }
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
      if (coll.size() == 0) {
         throw new IllegalArgumentException();
      }
      
      else {
         Iterator itr = coll.iterator();
         
         T max = (T)itr.next();
         
         while (itr.hasNext()) {
            T holder = (T)itr.next();
            
            if (comp.compare(holder, max) > 0) {
               max = holder;
            }
         }
         return max;
      }
   }


   /**
    * Selects the kth minimum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth minimum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth minimum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmin(Collection<T> coll, int k, Comparator<T> comp) {
      if ((coll == null) || (comp == null)) {
         throw new IllegalArgumentException();
      }
      if (coll.isEmpty() || k > coll.size()) {
         throw new NoSuchElementException();
      }
      if (k <= 0) {
         throw new NoSuchElementException();
      }
      else {
      
         Iterator itr = coll.iterator();
         
         ArrayList kminList = new ArrayList<T>();
         
         
         // makes sure the list starts with a value
         T hold = (T)itr.next();
         
         kminList.add(hold);
         
 
         while (itr.hasNext()) {
            
            T val = (T)itr.next();
            
            // checks to make sure value isnt already in list
            if (!kminList.contains(val)) {
            
               kminList.add(val);
            }
         }
         
         // sorts in ascending order
         java.util.Collections.sort(kminList, comp);
         
         
         // checks to make sure k isnt out of bounds
         if (k > kminList.size()) {
            throw new NoSuchElementException();
         }
         
         // spot of kmin
         T kmin = (T)kminList.get(k - 1);
         
         return kmin;
         
      }
   }


   /**
    * Selects the kth maximum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth maximum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth maximum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmax(Collection<T> coll, int k, Comparator<T> comp) {
      if ((coll == null) || (comp == null)) {
         throw new IllegalArgumentException();
      }
      if (coll.isEmpty() || k > coll.size()) {
         throw new NoSuchElementException();
      }
      if (k <= 0) {
         throw new NoSuchElementException();
      }
      else {
         Iterator itr = coll.iterator();
         
         ArrayList kmaxList = new ArrayList<T>();
         
         // makes sure list has a value to begin
         T hold = (T)itr.next();
         
         kmaxList.add(hold);
         
 
         while (itr.hasNext()) {
            
            T val = (T)itr.next();
            
            // checks to make sure value isnt already in list
            if (!kmaxList.contains(val)) {
            
               kmaxList.add(val);
            }
         }
         
         // sorts list in ascending order
         java.util.Collections.sort(kmaxList, comp);
         
         
         // checks to make sure k isnt out of bounds
         if (k > kmaxList.size()) {
            throw new NoSuchElementException();
         }
         
         // gets index of kth value
         T kmax = (T)kmaxList.get(kmaxList.size() - k);
         
         return kmax; 
      }

   }


   /**
    * Returns a new Collection containing all the values in the Collection coll
    * that are greater than or equal to low and less than or equal to high, as
    * defined by the Comparator comp. The returned collection must contain only
    * these values and no others. The values low and high themselves do not have
    * to be in coll. Any duplicate values that are in coll must also be in the
    * returned Collection. If no values in coll fall into the specified range or
    * if coll is empty, this method throws a NoSuchElementException. If either
    * coll or comp is null, this method throws an IllegalArgumentException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the range values are selected
    * @param low     the lower bound of the range
    * @param high    the upper bound of the range
    * @param comp    the Comparator that defines the total order on T
    * @return        a Collection of values between low and high
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> Collection<T> range(Collection<T> coll, T low, T high,
                                         Comparator<T> comp) {
      if ((coll == null) || (comp == null)) {
         throw new IllegalArgumentException();
      }
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
      if (!(comp.compare(low, high) <= 0)) {
         throw new NoSuchElementException();
      }
      
      else {
         Iterator itr = coll.iterator();
         
         // makes new list to hold the correct values
         ArrayList<T> toKeep = new ArrayList<T>();
         
         while (itr.hasNext()) {
            
            T holder = (T)itr.next();
            
            // loops thru list and adds values that are less than or equal to the highest
            // value and values that are greater than or equal to the lowest value
            if ((comp.compare(holder, high) <= 0) && (comp.compare(holder, low) >= 0)) {
               toKeep.add(holder);
            }
         }
         
         // checks to make sure that there are values in the range
         if (toKeep.size() == 0) {
            throw new NoSuchElementException();
         }
         return toKeep;
      }
   }


   /**
    * Returns the smallest value in the Collection coll that is greater than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the ceiling value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the ceiling value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T ceiling(Collection<T> coll, T key, Comparator<T> comp) {      
      if ((coll == null) || (comp == null)) {
         throw new IllegalArgumentException();
      }
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
      
      else {  
         Iterator itr = coll.iterator();
         
         // a list of values greater than or equal the key
         Collection<T> newList = Selector.range(coll, key, Selector.max(coll, comp), comp);
         
         // check to make sure there are values in the list
         if (newList.size() == 0) {
            throw new NoSuchElementException();
         }
         
         // finds smallest value of the values greater than the key
         T ceilingValue = Selector.min(newList, comp);
         
         return ceilingValue;
      }
   }


   /**
    * Returns the largest value in the Collection coll that is less than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the floor value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the floor value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T floor(Collection<T> coll, T key, Comparator<T> comp) {
      if ((coll == null) || (comp == null)) {
         throw new IllegalArgumentException();
      }
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
      
      else {  
         Iterator itr = coll.iterator();
         
         // list of values less than or equal to the key 
         Collection<T> newList = Selector.range(coll, Selector.min(coll, comp), key, comp);
         
         // check to make sure there are values in the list
         if (newList.size() == 0) {
            throw new NoSuchElementException();
         }
         
         // finds biggest value of values less than key
         T floorValue = Selector.max(newList, comp);
         
         return floorValue;
      }
   }
}
