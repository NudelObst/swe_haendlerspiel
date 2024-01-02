import java.util.Random;

public class Ware {
    private String name;
    private int preis;
    private int gewicht;
    private int interesse;
    private Ort kaufOrt;
    private boolean istEssbar;

    public Ware(String name, int preis, int gewicht){
        this(name, preis, gewicht, false, null);
    }

    public Ware(String name, int preis, int gewicht, boolean istEssbar){
        this(name, preis, gewicht, true, null);
    }
    public Ware(String name, int preis, int gewicht, Ort kaufOrt){
        this(name, preis, gewicht, false, kaufOrt);
    }

    public Ware(String name, int preis, int gewicht, boolean istEssbar, Ort kaufOrt) {
        this.name = name;
        this.preis = preis;
        this.gewicht = gewicht;
        this.istEssbar = istEssbar;
        this.kaufOrt = kaufOrt;
    }

    public String getName() {
        return name;
    }

    public int getPreis() {
        return preis;
    }

    public int getGewicht() {
        return gewicht;
    }

    public boolean getIstEssbar(){
        return istEssbar;
    }

    public Ort getKaufOrt() {
        return kaufOrt;
    }

    public void setInteresse(int interesse){
        this.interesse = interesse;
    }

    public int getInteresse(){
        return interesse;
    }

    public void berechneInteresse(){
        Random r = new Random();
        setInteresse(r.nextInt(4));
    }

    public int berechnePreis(Ort standortHaendler) {
        if (standortHaendler.getIstDorf()) {
            switch (getInteresse()) {
                case 0:
                    return 0;
                case 1:
                    return getPreis();
                default:
                    return getPreis()*2;
            }            
        } else {
            int entfernung = kaufOrt.berechneEntfernung(standortHaendler);
            int verkaufspreis = (int) Math.round(getPreis() * (1+0.1*entfernung));
            return verkaufspreis;
        }        
    }
}

class Lebensmittel extends Ware{
    boolean istEssbar;
    
    public Lebensmittel(String name, int preis, int gewicht){
        super(name, preis, gewicht);
    }
}