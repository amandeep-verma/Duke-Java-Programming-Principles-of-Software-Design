
import java.util.*;
import edu.duke.*;
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LargestQuakes {
    public void findLargestQuakes()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("# quakes read: " + list.size());
        /*for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
        System.out.println("Largest Index " + indexOfLargest(list) +" and magnitude " + list.get(indexOfLargest(list)).getMagnitude());
        */
       
       ArrayList<QuakeEntry> ans = getLargest(list, 10);
       for(QuakeEntry qe: ans)
       {
           System.out.println(qe);
       }
    }
     
    
    public int indexOfLargest(ArrayList<QuakeEntry> data)
    {
        int largestIndex =0;
        for(int k=0; k< data.size(); k++) {
            if(data.get(largestIndex).getMagnitude() < data.get(k).getMagnitude() )
            {
                largestIndex = k;
            }
        }
        return largestIndex;
    }
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany)
    {
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        
        for (int i = 0; i < howMany; i++)
        {
            int maxIndex = indexOfLargest(copy);
            
            ret.add(copy.get(maxIndex));
            copy.remove(maxIndex);
        }
        return ret;
    }
   
}
