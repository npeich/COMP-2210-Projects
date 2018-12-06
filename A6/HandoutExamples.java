/**
 * HandoutExamples.java
 * Generates examples from the assignment handout.
 *
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 2018-04-17
 *
 */
public class HandoutExamples {

    /** Drives execution. */
    public static void main(String[] args) {
        String sourceText = "agggcagcgggcg";
        int k = 2;
        MarkovModel model = new MarkovModel(k, sourceText);
        //change buildModel back to private!
        //model.buildModel(2, sourceText);
        //System.out.println(model.getIndex(k, "ag", sourceText));
        
        System.out.println("k = " + k + ", source text: " + sourceText);
        System.out.println("The first kgram: " + model.getFirstKgram());
        System.out.println("A random kgram: " + model.getRandomKgram());
        System.out.println("All kgrams: " + model.getAllKgrams());
        System.out.println("The Markov model: ");
        System.out.println(model);
        
        
        // System.out.println("----------------------------------");
//         
//         MarkovModel model2 = new MarkovModel(k, "ABCDE");
//         model2.getNextChar("AB");
//         model2.getNextChar("BC");
//         model2.getNextChar("CD");
//         model2.getNextChar("DE");
//         System.out.println(model2);
        
        
        //TestGenerator text = new TextGenerator("ABCDE");
        
//         
        //System.out.println("");
        
        //model.getNextChar("gg");
        //System.out.println("");
        //model.getNextChar("gc");
    }
}


/*

RUNTIME OUTPUT:

k = 2, source text: agggcagcgggcg
The first kgram: ag
A random kgram: cg
All kgrams: [gg, cg, ag, gc, ca]
The Markov model:
{gg=gcgc, cg=g, ag=gc, gc=agg, ca=g}

 */

