import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe: quakeData)
        {
            if(qe.getMagnitude()>magMin)
                answer.add(qe);
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        distMax = distMax*1000; // conversion to metres
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe: quakeData)
        {
            double dist = qe.getLocation().distanceTo(from);
            if(dist < distMax)
                answer.add(qe);
        }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> biglist = filterByMagnitude(list, 5);
        System.out.println("the total big quakes are = "+ biglist.size());
        for(QuakeEntry qe: biglist)
        {
            System.out.println(qe);
        }
         
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);
        
        // This location is Bridgeport, CA
         Location city =  new Location(38.17, -118.82);
        
        ArrayList<QuakeEntry> biglist = filterByDistanceFrom(list, 1000, city);
        System.out.println("close to me = "+biglist.size());
        for(QuakeEntry qe: biglist)
        {
            System.out.println(qe);
        }
    }

    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth)
    {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe: quakeData)
        {
            
            if(qe.getDepth() > minDepth && qe.getDepth() < maxDepth)
                answer.add(qe);
        }
        return answer;
    }
    
    public void quakesOfDepth()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        double minDepth=-8000.0;
        double maxDepth=-5000.0;
        System.out.println("# quakes read: " + list.size());
        System.out.println("Find quakes with depth between "+minDepth+" and "+maxDepth);
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe: list)
        {
            
            if(qe.getDepth() > minDepth && qe.getDepth() < maxDepth)
                answer.add(qe);
        }
        for(QuakeEntry qe:  answer)
        {
            System.out.println(qe);
        }
        System.out.println("# quakes read: " + answer.size());
        
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase)
    {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        if(where.equals("start"))
        {
            for(QuakeEntry qe: quakeData)
            {
                
                if(qe.getInfo().startsWith(phrase))
                    answer.add(qe);
            }
        }
        else if(where.equals("end"))
        {
            for(QuakeEntry qe: quakeData)
            {
                
                if(qe.getInfo().endsWith(phrase))
                    answer.add(qe);
            }
        }
        else
        {
            for(QuakeEntry qe: quakeData)
            {
                
                if(qe.getInfo().contains(phrase))
                    answer.add(qe);
            }
        }
        return answer;
    }
    
    
    public void quakesByPhrase()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        
        System.out.println("# quakes read: " + list.size());
        String phrase="Creek";
        String where="any";
        
        ArrayList<QuakeEntry> answer = filterByPhrase(list, where, phrase);
        
        for(QuakeEntry qe:  answer)
        {
            System.out.println(qe);
        }
        System.out.println("Found " + answer.size()+" quakes that match "+ phrase + " at "+where);
    }
    
    
    
    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
}
