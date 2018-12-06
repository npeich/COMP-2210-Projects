import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class SelectorTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /** A test that always fails. **/
   @Test public void Test() {
      int[] a1 = {2, 8, 7, 3, 4};
      int[] a2 = {5, 9, 1, 7, 3};
      int[] a3 = {8, 7, 6, 5, 4};
      int[] a4 = {2, 8, 8, 7, 3, 3, 4};
      
      
      //testing KMIN
      Assert.assertEquals(2, Selector.kmin(a1, 1));
      Assert.assertEquals(5, Selector.kmin(a2, 3));
      Assert.assertEquals(8, Selector.kmin(a3, 5));
      Assert.assertEquals(4, Selector.kmin(a4, 3));
      
      //testing KMAX
      Assert.assertEquals(8, Selector.kmax(a1, 1));
      Assert.assertEquals(5, Selector.kmax(a2, 3));
      Assert.assertEquals(4, Selector.kmax(a3, 5));
      Assert.assertEquals(4, Selector.kmax(a4, 3));
   }
   
   @Test public void KMaxTest2() {
      int[] a = {7};
      int[] b = {4, 4};
      int[] c = {3, 7, 3, 3, 1, 9, 1, 1, 1, 5};
      
      Assert.assertEquals(7, Selector.kmax(a, 1));
      Assert.assertEquals(4, Selector.kmax(b, 1));
      Assert.assertEquals(9, Selector.kmax(c, 1));
   }
   
   @Test public void KMinTest2() {
      int[] a = {7};
      int[] b = {-4, -4};
      int[] c = {3, 7, 3, 3, 1, 9, 1, 1, 1, 5};
      
      Assert.assertEquals(7, Selector.kmax(a, 1));
      Assert.assertEquals(-4, Selector.kmin(b, 2));
      Assert.assertEquals(5, Selector.kmax(c, 3));
   }
   
   @Test public void moreTests() {
      int[] a = {3, 7, 3, 3, 1, 9, 1, 1, 1, 5};
      
      Assert.assertEquals(5, Selector.kmin(a, 3));
   }
}
