import java.io.File;
import java.util.HashMap;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import java.util.*;
/**
 * MarkovModel.java Creates an order K Markov model of the supplied source
 * text. The value of K determines the size of the "kgrams" used to generate
 * the model. A kgram is a sequence of k consecutive characters in the source
 * text.
 *
 * @author     Natalie Eichorn (npe0004@auburn.edu)
 * @author     Dean Hendrix (dh@auburn.edu)
 * @version    2018-04-17
 *
 */
public class MarkovModel {

   // Map of <kgram, chars following> pairs that stores the Markov model.
   private HashMap<String, String> model;

   // add other fields as you need them ...
   private List<String> list = new ArrayList<String>();



   /**
    * Reads the contents of the file sourceText into a string, then calls
    * buildModel to construct the order K model.
    *
    * DO NOT CHANGE THIS CONSTRUCTOR.
    *
    */
   public MarkovModel(int K, File sourceText) {
      model = new HashMap<>();
      try {
         String text = new Scanner(sourceText).useDelimiter("\\Z").next();
         buildModel(K, text);
      }
      catch (IOException e) {
         System.out.println("Error loading source text: " + e);
      }
   }


   /**
    * Calls buildModel to construct the order K model of the string sourceText.
    *
    * DO NOT CHANGE THIS CONSTRUCTOR.
    *
    */
   public MarkovModel(int K, String sourceText) {
      model = new HashMap<>();
      buildModel(K, sourceText);
   }


   /**
    * Builds an order K Markov model of the string sourceText.
    */
   public void buildModel(int K, String sourceText) {
   
      //builds list of kGrams
      for (int i = K; i <= sourceText.length(); i++) {
         String hold = sourceText.substring(i - K, i);
         
         if (!list.contains(hold)) {
            list.add(hold);
         }
      }
      
      //System.out.println(list);
   
   
      //builds map
      
      //loop thru kgrams
      for (int j = 0; j < list.size(); j++) {
         //System.out.println("\nnew kgram " + list.get(j));
         List<String> follows = new ArrayList<String>();
      
         int indexLength = getIndex(K, list.get(j), sourceText).size();
         
         //loop through places where k gram is 
         for (int l = 0; l < indexLength; l++) {
            //index of the Kgram
            int index = getIndex(K, list.get(j), sourceText).get(l);
            
            //makes sure it wont overload
            if ((index + K + 1) <= sourceText.length()) {
               follows.add(sourceText.substring(index + K, index + K + 1));
            }
         }
         //string builder
         String followers = getString(follows);
         
         //add to HashMap
         model.put(list.get(j), followers);
      }
   
   }
   
   
   
   //finds the spot where the kgram starts in the list
   public List<Integer> getIndex(int K, String s, String sourceText) {
      int spot = 0;
      List<Integer> spots = new ArrayList<Integer>();
      
      for (int i = K; i < sourceText.length(); i++) {
         if (sourceText.substring(i - K, i).equals(s)) {
            spots.add(i - K);
         }
      }
      return spots;
   }
   
   
   
   //string builder for the following letters of Kgram
   public String getString(List<String> letters) {
      StringBuilder builder = new StringBuilder(letters.size());
      for (String l : letters) {
         builder.append(l);
      }
      return builder.toString();
   }
   


   /** Returns the first kgram found in the source text. */
   public String getFirstKgram() {
      return list.get(0);
   }


   /** Returns a kgram chosen at random from the source text. */
   public String getRandomKgram() {
      Random rando = new Random();
      int r = rando.nextInt(list.size());
      return list.get(r);
   }


   /**
    * Returns the set of kgrams in the source text.
    *
    * DO NOT CHANGE THIS METHOD.
    *
    */
   public Set<String> getAllKgrams() {
      return model.keySet();
   }


   /**
    * Returns a single character that follows the given kgram in the source
    * text. This method selects the character according to the probability
    * distribution of all characters that follow the given kgram in the source
    * text.
    */
   public char getNextChar(String kgram) {
      
      //to find random number to find probabilities
      Random rand = new Random();
      String empt = "";
      int z = 0;
      
      //loops thru map and returns the key w the right probability
      for(String n: model.keySet()) {
         if (n.equals(kgram)) {
            empt = model.get(kgram);
            int i = empt.length();
            if (i > 0) {
               z = rand.nextInt(i) + 1;
            }
         }
      }
      
      int q = z - 1;
      if (!empt.equals("")) {
         return empt.charAt(q);
      }
      
      return '\u0000';
   }


   /**
    * Returns a string representation of the model.
    * This is not part of the provided shell for the assignment.
    *
    * DO NOT CHANGE THIS METHOD.
    *
    */
   @Override
    public String toString() {
      return model.toString();
   }

}
