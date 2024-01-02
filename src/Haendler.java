import java.util.ArrayList;
import java.util.Scanner;

public class Haendler {
    private int goldstuecke;
    private int lebenspunkte;
    private Spiel spiel;
    private Ort standort;
    private ArrayList<Ware> inventar;
    private boolean[] aktionenBooleans;

    Scanner scanner = new Scanner(System.in);

    public Haendler(Ort Startort, Spiel spiel) {
        goldstuecke = 100;
        lebenspunkte = 100;
        this.standort = Startort;
        inventar = new ArrayList<Ware>();
        this.spiel = spiel;
        aktionenBooleans = new boolean[8];
        erlaubeAktionen();
    }

    private void subtrahiereGoldstuecke(int menge){
        goldstuecke -= menge;
    }

    private void addiereGoldstuecke(int menge){
        goldstuecke += menge;
    }

    private void addiereLebenspunkte(int menge){
        lebenspunkte += menge;
    }

    public void subtrahiereLebenspunkte(int menge){
        lebenspunkte -= menge;
    }

    public boolean[] getAktionenBooleans(){
        return aktionenBooleans;
    }

    public int getGoldstuecke() {
        return goldstuecke;
    }
    
    public int getLebenspunkte() {
        return lebenspunkte;
    }
    
    public Ort getStandort() {
        return standort;
    }

    public ArrayList<Ware> getInventar(){
        return inventar;
    }

    public void setStandort(Ort neuerStandort){
        this.standort = neuerStandort;
    }
    
    public void erlaubeAktionen(){
        for (int i = 0; i < aktionenBooleans.length; i++){
            aktionenBooleans[i] = true;
        }
    }

    public int berechneGewichtWagen(){
        if (getInventar().isEmpty()) {
            return 0;
        }
        int gewicht = 0;
        for (Ware ware : inventar) {
            gewicht += ware.getGewicht();
        }
        return gewicht;
    }

    public void loescheWareAusInventar(int index){
        inventar.remove(index);
    }

    public void kaufen(Ware ausgewählteWare){
        if (aktionenBooleans[0] == false || ausgewählteWare.getPreis()>goldstuecke || (ausgewählteWare.getGewicht()+berechneGewichtWagen())>spiel.getMaxGewichtWagen()) {
            return;
        }
        Ware neueWare;
        if (ausgewählteWare.getIstEssbar()) {
            neueWare = new Ware(ausgewählteWare.getName(), ausgewählteWare.getPreis(), ausgewählteWare.getGewicht(), true, getStandort());
        } else {
            neueWare = new Ware(ausgewählteWare.getName(), ausgewählteWare.getPreis(), ausgewählteWare.getGewicht(), getStandort());
        }
        subtrahiereGoldstuecke(neueWare.getPreis());
        inventar.add(neueWare);
        aktionenBooleans[1] = false;
        aktionenBooleans[3] = false;
        aktionenBooleans[5] = false;
        System.out.println("Gegenstand wurde gekauft");
    }

    public void verkaufen(Ware ausgewählteWare){
        if (aktionenBooleans[1] == false || ausgewählteWare == null) {
            return;
        }
        addiereGoldstuecke(ausgewählteWare.berechnePreis(getStandort()));       
        aktionenBooleans[0] = false;
        aktionenBooleans[3] = false;
        aktionenBooleans[5] = false;
    }
    public void taetigkeitAusfuehren(Taetigkeit taetigkeit) {
        if (aktionenBooleans[3]== false) {
            System.out.println("Du darfst nicht arbeiten");
            return;
        }        
        addiereGoldstuecke(taetigkeit.getLohn());
        aktionenBooleans[0] = false;
        aktionenBooleans[1] = false;
        aktionenBooleans[3] = false;
        aktionenBooleans[5] = false;
    }

    public void isstLokal() {
        if(getGoldstuecke()==spiel.getMinGoldstuecke() || getLebenspunkte() == spiel.getMaxLebenspunkte()) {
            System.out.println("Du hast zu wenig Geld oder deine Lebenspunkte sind voll");
            return;
        }
        subtrahiereGoldstuecke(1);;
        addiereLebenspunkte(20);;
    }

    public void isstBohnenMitSpeck() {
        if(getLebenspunkte() == spiel.getMaxLebenspunkte()) {
            System.out.println("Deine Lebenspunkte sind schon voll");
            return;
        }
        for (int i = 0; i < inventar.size(); i++) {
            if (inventar.get(i).getIstEssbar()) {
                addiereLebenspunkte(20);;
                inventar.remove(i);
                return;
            }
            if(i == inventar.size()-1) {
                System.out.println("Keine Bohnen mit Speck im Inventar");
            }
        }
    }

    public void zeigeInventar(){
        if (getInventar().isEmpty()) {
            System.out.println("Inventar ist leer");
            return;
        }
        System.out.println("##################################################");
        System.out.println( "Tag: " + spiel.getTageszähler() + "/" + spiel.getMaxTageszähler() +
                            "\nLebenspunkte: " + getLebenspunkte() + "/" + spiel.getMaxLebenspunkte() +
                            "\nGoldstücke: " + getGoldstuecke() +
                            "\nGewicht des Wagens: " + berechneGewichtWagen() + "/" + spiel.getMaxGewichtWagen() + "\n");
        System.out.println("Ware in deinem Inventar:\n");  
        System.out.format("%5s%-18s%-8s%-8s", "", "Name","|Gewicht", "|Herkunft\n");     
        System.out.println("-----------------------|-------|-------");
        for (int i = 0; i < getInventar().size(); i++) {
            System.out.format("%2$5s%-18s", inventar.get(i).getName(), (i+1) + ") ");
            System.out.println("|" + inventar.get(i).getGewicht() + "\t\b|" +
                                inventar.get(i).getKaufOrt().getName());
        }
        System.out.println("\nDrücke Enter um fortzufahren");
        System.out.println("##################################################");
    }

    public void reisen(Ort zielort) {
        int reisedauer = zielort.berechneEntfernung(standort);
        if (reisedauer == 0) return; 
        for (int i = 0; i < reisedauer; i++) {
            if(getLebenspunkte()<spiel.getMinlebenspunkte()) return;
            while (true) {
                System.out.println("##################################################");
                System.out.println("Tag: " + spiel.getTageszähler() +
                                   "\nLebenspunkte: " + getLebenspunkte() +
                                   "\nverbleibende Reisedauer: " + (reisedauer-i));
                System.out.println("\n1) Essen \n2) weiterreisen");
                System.out.println("##################################################");
                System.out.println("Eingabe:");
                int eingabeScanner;
                try {
                    eingabeScanner = scanner.nextInt();
                } catch (Exception e) {
                    scanner.nextLine();
                    System.out.println("Fehlerhafte Eingabe!");
                    continue;
                } 

                if (eingabeScanner == 1) {
                    isstBohnenMitSpeck();
                } else if (eingabeScanner == 2) {
                    break;
                }
            }
            spiel.beendeTag();   
        }
        setStandort(zielort);
    }
}