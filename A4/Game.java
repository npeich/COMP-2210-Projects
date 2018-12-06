import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;

/**
 * My own class that holds the methods described in WordSearchGame that
 * control the whole game.
 *
 * @author Natalie Eichorn (npe0004@auburn.edu)
 * @version 2018-04-02
 * 
 */
public class Game implements WordSearchGame {
   
   //to check that the lexicon was loaded
   private boolean lexLoaded = false;
   
   //sets up sets/lists
   private TreeSet<String> lexicon;
   private List<Integer> path;
   
   //2d arrays
   private boolean[][] visited;
   private String[][] board;
   
   //sizes of arrays
   private int length;
   
   //number of cells around target
   private final int maxNeighbors = 8;
   
   //order of positions visited
   private int order;
   
   
   
   /**
    * Loads the lexicon into a data structure for later use. 
    * 
    * @param fileName A string containing the name of the file to be opened.
    * @throws IllegalArgumentException if fileName is null
    * @throws IllegalArgumentException if fileName cannot be opened.
    */  
   public void loadLexicon(String fileName) {
      if (fileName == null) {
         throw new IllegalArgumentException();
      }
      
      //makes treeset to hold the words
      lexicon = new TreeSet<String>();
      
      try {
         Scanner s = new Scanner(new BufferedReader(new FileReader(new File(fileName))));
         while (s.hasNext()) {
            //adds word to lexicon treeset
            String str = s.next();
            lexicon.add(str);
            s.nextLine();
         }
         //confirms the tree was loaded
         lexLoaded = true;
      }
      catch (Exception e) {
         throw new IllegalArgumentException("Error loading word list: " + fileName + ": " + e);
      }
   }
   
   
   
   
   /**
    * Stores the incoming array of Strings in a data structure that will make
    * it convenient to find words.
    * 
    * @param letterArray This array of length N^2 stores the contents of the
    *     game board in row-major order. Thus, index 0 stores the contents of board
    *     position (0,0) and index length-1 stores the contents of board position
    *     (N-1,N-1). Note that the board must be square and that the strings inside
    *     may be longer than one character.
    * @throws IllegalArgumentException if letterArray is null, or is  not
    *     square.
    */
   public void setBoard(String[] letterArray) {
   
      //checks to make sure array isnt null and is square
      if (letterArray == null || Math.sqrt(letterArray.length) % 2 != 0) {
         throw new IllegalArgumentException();
      }
      
//creates 2d array with letters from given array      
      int n = letterArray.length;
      length = (int)Math.sqrt(n);
   
      board = new String[length][length];
      
      //tracks letter in letterArray
      int spot = 0;
      
      //loads letter into 2d array board
      for (int i = 0; i < length; i++ ) {
         for (int j = 0; j < length; j++) {
            board[i][j] = letterArray[spot];
            spot++;
         }
      }
      //prints out board to test
      //System.out.println("BOARD = " + Arrays.deepToString(board));
      
      
//creates all false visited board      
      visited = new boolean[length][length];
      
      //loads all values with false bc we havent traveled on the board yet
      for (int m = 0; m < length; m++) {
         for (int o = 0; o < length; o++) {
            visited[m][o] = false;
         }
      }
      //prints visited board to test
      //System.out.println("VISITED = " + Arrays.deepToString(visited));
   }
   
   
   
   /**
    * Creates a String representation of the board, suitable for printing to
    *   standard out. Note that this method can always be called since
    *   implementing classes should have a default board.
    */
   public String getBoard() {
      return "";
   }
   
   
   
   /**
    * Retrieves all valid words on the game board, according to the stated game
    * rules.
    * 
    * @param minimumWordLength The minimum allowed length (i.e., number of
    *     characters) for any word found on the board.
    * @return java.util.SortedSet which contains all the words of minimum length
    *     found on the game board and in the lexicon.
    * @throws IllegalArgumentException if minimumWordLength < 1
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public SortedSet<String> getAllValidWords(int minimumWordLength) {
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException();
      } 
      if (!lexLoaded) {
         throw new IllegalStateException();
      }
      
      
      TreeSet<String> sorted = new TreeSet<String>();
      Iterator itr = lexicon.iterator();
      
      //gets words greater than minimum value that are valid
      while (itr.hasNext()) {
         String hold = (String)itr.next();
         
         hold = hold.toUpperCase();
         
         int holdLength = hold.length();
         
         if (holdLength >= minimumWordLength && isValidWord(hold)) {
            sorted.add(hold);
         }
      }
      
      
      //list of coordinates that are on the board
      List<Integer> coordinates;
      
      //empty list to fill with words that arent on list 
      List<String> toRemove = new ArrayList<String>();

      Iterator i = sorted.iterator();
      
      
      while (i.hasNext()) {
         String hold2 = (String)i.next();
         
         coordinates = isOnBoard(hold2);
         
         if (coordinates.size() == 0) {
            toRemove.add(hold2);
         }
      }
      
      //removes all words not on board
      sorted.removeAll(toRemove);
      
      return sorted;
   }
   
   
  /**
   * Computes the cummulative score for the scorable words in the given set.
   * To be scorable, a word must (1) have at least the minimum number of characters,
   * (2) be in the lexicon, and (3) be on the board. Each scorable word is
   * awarded one point for the minimum number of characters, and one point for 
   * each character beyond the minimum number.
   *
   * @param words The set of words that are to be scored.
   * @param minimumWordLength The minimum number of characters required per word
   * @return the cummulative score of all scorable words in the set
   * @throws IllegalArgumentException if minimumWordLength < 1
   * @throws IllegalStateException if loadLexicon has not been called.
   */  
   public int getScoreForWords(SortedSet<String> words, int minimumWordLength) { 
      
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException();
      } 
      if (!lexLoaded) {
         throw new IllegalStateException();
      }
      
      int score = 0;
      Iterator<String> itr = words.iterator();
      
      while (itr.hasNext()) {
         
         //holds word and gets its length
         String hold = itr.next();
         int length = hold.length();
         
         //checks to make sure length is greater than minimum
         if (length >= minimumWordLength) {
            score++;
         }
         
         //gives extra points if the word is longer than minimum length
         length -= minimumWordLength;
         
         //adds up score
         score += length;
      }
      return score;
   }
   
   
   
   
   /**
    * Determines if the given word is in the lexicon.
    * 
    * @param wordToCheck The word to validate
    * @return true if wordToCheck appears in lexicon, false otherwise.
    * @throws IllegalArgumentException if wordToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public boolean isValidWord(String wordToCheck) {   
      if (wordToCheck == null) {
         throw new IllegalArgumentException();
      } 
      if (!lexLoaded) {
         throw new IllegalStateException();
      }
      
      //sets it all to lowercase so it'll be able to comare them
      wordToCheck = wordToCheck.toLowerCase();
            
      if (lexicon.contains(wordToCheck)) {
         return true;
      }   
   
      return false;
   }
   
   
   
   
   /**
    * Determines if there is at least one word in the lexicon with the 
    * given prefix.
    * 
    * @param prefixToCheck The prefix to validate
    * @return true if prefixToCheck appears in lexicon, false otherwise.
    * @throws IllegalArgumentException if prefixToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public boolean isValidPrefix(String prefixToCheck) {
      if (prefixToCheck == null) {
         throw new IllegalArgumentException();
      } 
      if (!lexLoaded) {
         throw new IllegalStateException();
      }
      
      //puts it in lowercase to check it properly
      String prefix = prefixToCheck.toLowerCase();
      
      //tests prefix
      //System.out.println("prefix = " + prefix);
      
      //gets closest word in tree to prefix?
      String word = lexicon.ceiling(prefix);
      
      //sets to true if the prefix matches the beginning of the word or is the word itself
      if (word.startsWith(prefixToCheck) || word.equals(prefixToCheck)) {
         //System.out.println("true for " + prefix);
         return true;
      }
      return false;
   }
     
     
     
      
   /**
    * Determines if the given word is in on the game board. If so, it returns
    * the path that makes up the word.
    * @param wordToCheck The word to validate
    * @return java.util.List containing java.lang.Integer objects with  the path
    *     that makes up the word on the game board. If word is not on the game
    *     board, return an empty list. Positions on the board are numbered from zero
    *     top to bottom, left to right (i.e., in row-major order). Thus, on an NxN
    *     board, the upper left position is numbered 0 and the lower right position
    *     is numbered N^2 - 1.
    * @throws IllegalArgumentException if wordToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public List<Integer> isOnBoard(String wordToCheck) {
      
      String wordSoFar = "";
      path = new ArrayList<Integer>();
   
      if (wordToCheck == null) {
         throw new IllegalArgumentException();
      } 
      if (!lexLoaded) {
         throw new IllegalStateException();
      }  
      
      markAllUnvisited();
      
      //if (isValid()
      
      for (int r = 0; r < length; r++) {
         for (int c = 0; c < length; c++) {
            if (isValid(r, c)) {
               order = 1;
            //dfsRecursive(r, c, wordSoFar, wordToCheck, path);
               dfsIterative((r * length + c), r, c);
         } 
      }
      
      // 
//       Iterator itr = path.iterator();
//       
//       List<Integer> ahh = new ArrayList<Integer>();
//       
//       while (itr.hasNext()) {
//          int hold = (int)itr.next();
//          if (ahh.size() < wordToCheck.length()) {
//             ahh.add(hold);
//          }
//       }
      
      return path;
   }
   }
   
   
   private void dfsIterative(int n, int a, int b) {
      Deque<Integer> stack = new ArrayDeque<>();
      visited[a][b] = true;
      
      stack.addFirst(n);
      
      while(!stack.isEmpty()) {
         Integer position = stack.removeFirst();
         
         process(a, b);
         
         for (Integer neighbor : position.neighbors()) {
            if (!isVisited(neighbor)) {
               visit(neighbor);
            }
         
         }
      }
   
   }
//    
//    
   
   
   
   
   
   /**
    * Depth first search.
    */
   public void dfsRecursive(int a, int b, String s, String wordtocheck, List<Integer> path) {  
      visited[a][b] = true;
         
      s += board[a][b];
      
      if (s.equals(wordtocheck)) {

         for (int k = 0; k < length; k++) {
            for (int m = 0; m < length; m++) {
               if (visited[k][m] == true) {
                  path.add(k * length + m);
               }  
            }
         }
      }
      
      
      if (wordtocheck.startsWith(s)) {
         for (int r = a - 1; r <= a + 1 && r < length; r++) {
            for (int c = b - 1; c <= b + 1 && c < length; c++) {
               if (r >= 0 && c >= 0 && !visited[r][c]) {
                  //path.add(r * length + c);
                  dfsRecursive(r, c, s, wordtocheck, path);
               }
            }
         }
      }

      //path.remove(path.size() - 1);   
      s = s.substring(0, s.length() - 1);
      visited[a][b] = false; 
   }
   
   public void markAllUnvisited() {
      visited = new boolean[length][length];
      for (boolean[] row : visited) {
         Arrays.fill(row, false);
      }
   }
   
   private boolean isValid(int x, int y) {
      return (x >= 0) && (x < width) && (y >= 0) && (y < height);
   }
   
   private void process(int x, int y) {
      grid[x][y] = order++;
   }
   
   private boolean isVisited(Position p) {
      return visited[p.x][p.y];
   }
}
