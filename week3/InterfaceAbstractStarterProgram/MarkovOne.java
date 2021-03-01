
/**
 * Write a description of MarkovOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Random;
import java.util.*;
public class MarkovOne extends AbstractMarkovModel{


    public MarkovOne() {
        myRandom = new Random();
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
    }

    

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        int index = myRandom.nextInt(myText.length()-1);
        String key = myText.substring(index,index+1);
        StringBuilder sb = new StringBuilder();
        sb.append(key);
        for(int k=0; k < numChars-1; k++){
            ArrayList<String> ar  = getFollows(key);
            if(ar.size() == 0)
                break;
            index = myRandom.nextInt(ar.size());
            String next = ar.get(index);
            sb.append(next);
            key = next;
        }
        return sb.toString();
    }
    public String toString() {
        return "MarkovModel of order 1.";
    }
}
