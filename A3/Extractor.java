import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Extractor.java. Implements feature extraction for collinear points in
 * two dimensional data.
 *
 * @author  Natalie Eichorn (npe0004@auburn.edu)
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version 2018-02-27
 *
 */
public class Extractor {
   
   /** raw data: all (x,y) points from source data. */
   private Point[] points;
   
   /** lines identified from raw data. */
   private SortedSet<Line> lines;
  
   /**
    * Builds an extractor based on the points in the file named by filename. 
    */
   public Extractor(String filename) {

      try {
         Scanner scan = new Scanner(new File(filename));
         int numberOfPoints = scan.nextInt();
         
         points = new Point[numberOfPoints];
         
         int i = 0;
         
         while (scan.hasNext()) {
            int x = scan.nextInt();
            int y = scan.nextInt();
            
            Point p = new Point(x, y);
            
            points[i] = p;
            i++;
         }
      }
      catch (Exception e) {
         System.out.println("Error!");
      }
   }
  
   /**
    * Builds an extractor based on the points in the Collection named by pcoll. 
    *
    * THIS METHOD IS PROVIDED FOR YOU AND MUST NOT BE CHANGED.
    */
   public Extractor(Collection<Point> pcoll) {
      points = pcoll.toArray(new Point[]{});
   }
  
   /**
    * Returns a sorted set of all line segments of exactly four collinear
    * points. Uses a brute-force combinatorial strategy. Returns an empty set
    * if there are no qualifying line segments.
    */
   public SortedSet<Line> getLinesBrute() {
      lines = new TreeSet<Line>();
      
      //creating copy to manipulate
      Point[] newPoint = Arrays.copyOf(points, points.length);
            
      //loops through to get at each point and be able to place them back into a list for a line
      for (int i = 3; i < newPoint.length; i++) {
      
         for (int j = 2; j < i; j++) {
         
            for (int k = 1; k < j; k++) {
               
               for (int l = 0; l < k; l++) {
                  
                  //gets slope of each point
                  double slope1 = newPoint[i].slopeTo(points[j]);
                  double slope2 = newPoint[i].slopeTo(points[k]);
                  double slope3 = newPoint[i].slopeTo(points[l]);
                  
                  
                  //makes sure the slopes are the same (collinear)
                  if (slope1 == slope2 && slope2 == slope3) {
                     Line listOfLines = new Line();
                     
                     listOfLines.add(newPoint[i]);
                     listOfLines.add(newPoint[j]);
                     listOfLines.add(newPoint[k]);
                     listOfLines.add(newPoint[l]);
                     
                     lines.add(listOfLines);
                  }
               
               }
            }
         }
      }
      
      return lines;
   }
  
   /**
    * Returns a sorted set of all line segments of at least four collinear
    * points. The line segments are maximal; that is, no sub-segments are
    * identified separately. A sort-and-scan strategy is used. Returns an empty
    * set if there are no qualifying line segments.
    */
   public SortedSet<Line> getLinesFast() {
      lines = new TreeSet<Line>();
      
      //creating copy to manipulate
      Point[] newPoint = Arrays.copyOf(points, points.length);
      
      for (int i = 0; i < points.length; i++) {
      
         //sorted in respect to specific one point
         Arrays.sort(newPoint, points[i].slopeOrder);
         
         //count to keep track of number of points collinear to orignal i
         int count;
         
         
         for (int j = 0; j < points.length - 1; j = j + count) {
            count = 0;
            int k = 0;
            
            //finding the collinear points
            while (j + k < points.length && points[i].slopeOrder.compare(newPoint[j], newPoint[j + k]) == 0) {
               k++;
               count++;
            }
            
            //meaning there are at least 3 points collinear to i making a total of 4
            if (count > 2) {
               Line line2 = new Line();
               line2.add(points[i]);
               
               //adding points to the list
               for (int l = 0; l < count; l++) {
                  line2.add(newPoint[j + l]);
               }
               
               //adding list of points to the total list
               lines.add(line2);
            }
         }
      }

      return lines;
   }
}