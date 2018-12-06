
/**
 * Provides a factory method for creating word search games. 
 *
 * @author Natalie Eichorn (npe0004@auburn.edu)
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 2018-04-02
 */
public class WordSearchGameFactory {

   /**
    * Returns an instance of a class that implements the WordSearchGame
    * interface.
    */
   public static WordSearchGame createGame() {
      // You must return an instance of your solution class here instead of null.
      return new Game();
   }

}
