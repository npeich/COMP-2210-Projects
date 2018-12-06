import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import java.util.stream.Collectors;

/**
 * Provides an implementation of the WordLadderGame interface. The lexicon
 * is stored as a HashSet of Strings.
 *
 * @author Natalie Eichorn (npe0004@auburn.edu)
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 2018-04-06
 */
public class Doublets implements WordLadderGame {

    // The word list used to validate words.
    // Must be instantiated and populated in the constructor.
   private HashSet<String> lexicon;


    /**
     * Instantiates a new instance of Doublets with the lexicon populated with
     * the strings in the provided InputStream. The InputStream can be formatted
     * in different ways as long as the first string on each line is a word to be
     * stored in the lexicon.
     */
   public Doublets(InputStream in) {
      try {
         lexicon = new HashSet<String>();
         Scanner s = new Scanner(new BufferedReader(new InputStreamReader(in)));
         
         while (s.hasNext()) {
            String str = s.next();
            //adds to lexicon
            lexicon.add(str.toUpperCase());
            s.nextLine();
         }
         in.close();
      }
      
      catch (java.io.IOException e) {
         System.err.println("Error reading from InputStream.");
         System.exit(1);
      }
   }
 
    /**
     * Returns the Hamming distance between two strings, str1 and str2. The
     * Hamming distance between two strings of equal length is defined as the
     * number of positions at which the corresponding symbols are different. The
     * Hamming distance is undefined if the strings have different length, and
     * this method returns -1 in that case. See the following link for
     * reference: https://en.wikipedia.org/wiki/Hamming_distance
     *
     * @param  str1 the first string
     * @param  str2 the second string
     * @return      the Hamming distance between str1 and str2 if they are the
     *                  same length, -1 otherwise
     */
   public int getHammingDistance(String str1, String str2) {
      //strings have different lengths
      if (str1.length() != str2.length()) {
         return -1;
      }
      

      // now you know the strings are the same length so you can just loop through the 
      // string and find any discrepencies
      // when one is found, the hamming distance increases
            
      int distance = 0;
      
      for (int i = 0; i < str1.length(); i++) {
         if (str1.toUpperCase().charAt(i) != str2.toUpperCase().charAt(i)) {
            distance++;
         }
      }

      return distance;
   }


   /**
    * Returns a minimum-length word ladder from start to end. If multiple
    * minimum-length word ladders exist, no guarantee is made regarding which
    * one is returned. If no word ladder exists, this method returns an empty
    * list.
    *
    * Breadth-first search must be used in all implementing classes.
    *
    * @param  start  the starting word
    * @param  end    the ending word
    * @return        a minimum length word ladder from start to end
    */
   public List<String> getMinLadder(String start, String end) {
     
      List<String> empty = new ArrayList<>();
      List<String> ladder = new ArrayList<String>();
      Deque<ArrayDeque<String>> q = new ArrayDeque<ArrayDeque<String>>();
      
      //builds ladder
      if (start.equals(end)) {
         ladder.add(start);
         return ladder;
      }
      
      //catches "exceptions"
      if (!isWord(start) || !isWord(end)) {
         return empty;
      }
      if (start.length() != end.length()) {
         return empty;
      }
      
      
      //builds original ladder
      if (start.equals(end)) {
         ladder.add(start);
         return ladder;
      }
      if (getHammingDistance(start, end) == 1) {
         ladder.add(start);
         ladder.add(end);
         return ladder;
      }
      
      
      List<String> visited = new ArrayList<String>();
      visited.add(start);
      
      for (int i = 0; i < start.length(); i++) {
         for (char letter = 'a'; letter <= 'z'; letter++) {
            ArrayDeque<String> stack = new ArrayDeque<String>();
            stack.push(start);
            
            char c = start.charAt(i);
            String word = swap(start, c, letter);
            
            if (!isWord(word)) {
               continue;
            }
            if (word.equals(start)) {
               continue;
            }
            
            stack.push(word);
            q.addLast(stack);
            visited.add(word);  
         }
      }
      
      ArrayDeque<String> result = bfs(q, end, visited);
      
      if (result == null) {
         return ladder;
      }
      
      while (!result.isEmpty()) {
         ladder.add(result.removeLast());
      }
      
      //length of 1 case
      
      
      
      //length of 2 case
      // if (getHammingDistance(start, end) == 1) {
//          ladder.add(start);
//          ladder.add(end);
//          return ladder;
//       }
//       
//       
     
      
      
      
      
      
//       
//       //length >2 case
//       Deque<Node> dq = new ArrayDeque<>();
//       HashSet<String> hs = new HashSet<>();
//       
//       hs.add(start);
//       dq.addLast(new Node(start, null));
//       
//       while (!dq.isEmpty()) {
//          Node n = dq.removeFirst();
//          String position = n.position;
//                   
//          for (String neighbor : getNeighbors(position)) {
//             if (!hs.contains(neighbor)) {
//                hs.add(neighbor);
//                dq.addLast(new Node(neighbor, n));
//             }
//             
//             if (neighbor.equals(end)) {
//                Node n2 = dq.removeLast();
//                while (n2 != null) {
//                   ladder.add(0, n2.position);
//                   n2 = n2.predecessor;
//                }
//                return ladder;
//             }
//          }
//       }
       return ladder;
   }


    /**
     * Returns all the words that have a Hamming distance of one relative to the
     * given word.
     *
     * @param  word the given word
     * @return      the neighbors of the given word
     */
   public List<String> getNeighbors(String word) {
      
      List<String> neighbors = new ArrayList<String>();
      //List<String> ahh = new ArrayList<String>();
      //Iterator itr = lexicon.iterator();
      
      //goes thru lexicon and adds words with ham. distance of 1
      for (String w : lexicon) {
         if (getHammingDistance(w, word) == 1) {
            neighbors.add(w);
         }
      }

      //System.out.println(neighbors);
      return neighbors;
   }


    /**
     * Returns the total number of words in the current lexicon.
     *
     * @return number of words in the lexicon
     */
   public int getWordCount() {
      return lexicon.size();
   } 


    /**
     * Checks to see if the given string is a word.
     *
     * @param  str the string to check
     * @return     true if str is a word, false otherwise
     */
   public boolean isWord(String str) {
      if (lexicon.contains(str.toUpperCase())) {
         return true;
      }
      return false;
   }


    /**
     * Checks to see if the given sequence of strings is a valid word ladder.
     *
     * @param  sequence the given sequence of strings
     * @return          true if the given sequence is a valid word ladder,
     *                       false otherwise
     */
   public boolean isWordLadder(List<String> sequence) {
            
      if (sequence.isEmpty() || sequence == null) {
         return false;
      }
      
      if (sequence.size() == 1) {
         return true;
      }
      
      for (int i = 0; i < sequence.size() - 1; i++) {
         if (!(isWord(sequence.get(i + 1)) && isWord(sequence.get(i)))) {
            return false;
         }
         if (getHammingDistance(sequence.get(i + 1), sequence.get(i)) != 1) {
            return false;
         }
      }

      return true;
   }
   
   
   //this is for making nodes
   private class Node {
      String position;
      Node predecessor;
   
      public Node(String p, Node pred) {
         position = p;
         predecessor = pred;
      }
   }
   
   //bfs method
   public ArrayDeque<String> bfs(Deque<ArrayDeque<String>> queue, String end, List<String> visited) {
      
      while (!queue.isEmpty()) {
         ArrayDeque<String> next = queue.removeFirst();
         String first = next.peekFirst();
         
         for (int i = 0; i < first.length(); i++) {
            for (char letter = 'a'; letter <= 'z'; letter++) {
               ArrayDeque<String> stack = next.clone();
               
               char c = first.charAt(i);
               String nextWord = swap(first, c, letter);
               
               if (!isWord(nextWord)) {
                  continue;
               }
               
               if (visited.contains(nextWord)) {
                  continue;
               }
               
               stack.push(nextWord);
               
               if (getHammingDistance(nextWord, end) == 1) {
                  stack.push(end);
                  return stack;
               }
               
               visited.add(nextWord);
               queue.addLast(stack);
            
            }
         }
      }
      return null;
   }
   
   public String swap(String start, char c1, char c2) {
      int spot = start.indexOf(c1);
      StringBuilder word = new StringBuilder();
      
      word.append(start.substring(0, spot));
      word.append(c2);
      word.append(start.substring(spot + 1));
      
      return word.toString();
   }

}

