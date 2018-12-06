import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.Arrays;
import java.util.List;


public class LineTest {
   
   
   Point a = new Point(1, 2);
   Point b = new Point(1, 7);
   Point c = new Point(2, 2);
   Point d = new Point(2, 5);
   Point e = new Point(3, 1);
   Point f = new Point(4, 4);
   Point g = new Point(5, 3);
   Point h = new Point(5, 6);
   Point i = new Point(6, 6);
   Point j = new Point(7, 1);
   Point k = new Point(7, 3);
   Point l = new Point(7, 4);
   Point m = new Point(7, 9);
   Point n = new Point(8, 8);

   List<Point> line1 = Arrays.asList(a, b, c, d, e);
   List<Point> line2 = Arrays.asList(f, g, h, i, j);
   List<Point> line3 = Arrays.asList(k, l, m, n);
    
   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /** A test that always fails. **/
   @Test public void test1() {
      Line one = new Line(line1);
   
      //Assert.assertEquals(one.first(), one.get(0));
   }
}
