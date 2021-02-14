
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter{
    private String where;
    private String phrase;
    public PhraseFilter(String where, String phrase)
    {
        this.where = where;
        this.phrase = phrase;
    }
    public  boolean satisfies(QuakeEntry qe)
    {
        if( (where.equals("start") && qe.getInfo().startsWith(phrase)) ||
        (where.equals("end") && qe.getInfo().endsWith(phrase)) ||
        (where.equals("any") && qe.getInfo().contains(phrase))
        )
        {
            return true;
        }
        return false;
    }
    public String getName()
    {
        return "PhraseFilter";
    }
}
