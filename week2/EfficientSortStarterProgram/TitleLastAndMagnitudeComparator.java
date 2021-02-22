
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    
    public String lastWordInString(String a)
    {
        return a.substring(a.lastIndexOf(' ')+1,a.length());
    }
    
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String q1LastWord = lastWordInString(q1.getInfo());
        String q2LastWord = lastWordInString(q2.getInfo());
        int val = q1LastWord.compareTo(q2LastWord);
        if(val!=0)
            return val;
        return Double.compare(q1.getMagnitude(), q2.getMagnitude());
        
    }
}
