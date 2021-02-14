import java.util.*;
/**
 * Write a description of MatchAllFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MatchAllFilter implements Filter {
    private ArrayList<Filter> filters;
    MatchAllFilter()
    {
        filters = new ArrayList<Filter>();
    }
    
    public void addFilter(Filter f)
    {
        filters.add(f);
    }

    public  boolean satisfies(QuakeEntry qe)
    {
        for (Filter f: filters)
        {
            if(!f.satisfies(qe))
                return false;
        }
        return true;
    }
    public String getName()
    {
        String filtersname ="";
        for (Filter f: filters)
        {
            filtersname = filtersname + f.getName() +"  ";
        }
        return filtersname ;
    }
}
