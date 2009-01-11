package bursa;

public class OfertaCumparare
{
    private String companie;
    private OfertaVanzare o;
    private int pret;
    public OfertaCumparare(String companie, OfertaVanzare o, int pret)
    {
        this.companie = companie;
        this.o = o;
        this.pret = pret;
    }
    public String getCompanie()
    {
        return this.companie;        
    }
    public int getPret()
    {
        return this.pret;
    }
    public OfertaVanzare getOfertaVanzare()
    {
        return this.o;
    }
}