
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */ 
import java.util.*;

import edu.duke.*;
import java.util.*;
public class Tester {

    public void testGetFollows()
    {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne mo = new MarkovOne ();
        mo.setTraining(st);
        ArrayList<String> ar = mo.getFollows("th");
        System.out.println(ar.size());
        System.out.println(ar);
    }
}
