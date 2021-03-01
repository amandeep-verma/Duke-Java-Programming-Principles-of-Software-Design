
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Random;
import java.util.*;
public class EfficientMarkovModel extends AbstractMarkovModel{
    private int N;
    private HashMap<String, ArrayList<String>> map;
    EfficientMarkovModel(int n)
    {
        N=n;
        myRandom = new Random();
        map = new HashMap<String, ArrayList<String>>();
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
    }

    public void buildMap(String key)
    {
        ArrayList<String> ar  = getFollows(key);
        if(ar.size()==0)
            ar.add(myText.substring(0,1));
        map.put(key,ar);
    };

    public void printHashMapInfo()
    {
        //System.out.println(map);
        System.out.println("The size of map is "+map.size());
        
        int maxSize=0;
        Set<String> keys = map.keySet(); // returns the keySet of map, which you can work-on
        for(String key:keys)
        {
            if(map.get(key).size()>maxSize)
            {
                maxSize = map.get(key).size();
            }
        }
        System.out.println("The maximum number of keys following a key is "+maxSize);
        System.out.printf("Keys that have the largest ArrayList are ");
        for(String key:keys)
        {
            if(map.get(key).size()==maxSize)
            {
                System.out.printf("%s ,",key);
            }
            
        }
        System.out.println();
    }

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        int index = myRandom.nextInt(myText.length()-N);
        String key = myText.substring(index,index+N);
        StringBuilder sb = new StringBuilder();
        sb.append(key);
        //map.add(myText.substring(myText.length()-1-N,myText.substring(myText.length()-1),);
        for(int k=0; k < numChars- N; k++){
           
            if(!map.containsKey(key))
            {
                buildMap(key);
            }
            ArrayList<String> ar = map.get(key);

            if(ar.size() == 0)
                break;
            index = myRandom.nextInt(ar.size());
            String next = ar.get(index);
            sb.append(next);
            key = key.substring(1)+next;
        }
        printHashMapInfo();
        return sb.toString();
    }

    public String toString() {
        return "EfficientMarkovModel of order "+N+".";
    }
}
