import java.util.Scanner;
import java.util.ArrayList;

public class Spiel {
    private int tageszähler;
    private final int maxGewichtWagen;
    private final int maxTageszaehler;
    private final int maxLebenspunkte;
    private final int minLebenspunkte;
    private final int minGoldstuecke;
    private String[] aktionenName;
    private ArrayList<Ort> ortsliste;
    private ArrayList<Taetigkeit> taetigkeitslisteOrt;
    private ArrayList<Taetigkeit> taetigkeitslisteStadt;
    private ArrayList<Ware> warenlisteOrt;
    private ArrayList<Ware> warenlisteStadt;
    private Haendler haendler;

    public Spiel() {
        tageszähler = 0;
        maxGewichtWagen = 1000;
        maxTageszaehler = 100;
        maxLebenspunkte = 100;
        minLebenspunkte = 0;
        minGoldstuecke = 0;
        aktionenName = new String[8];
        setAktionenName();
        ortsliste = new ArrayList<>();
        taetigkeitslisteOrt = new ArrayList<>();
        taetigkeitslisteStadt = new ArrayList<>();
        warenlisteOrt = new ArrayList<>();
        warenlisteStadt = new ArrayList<>();
    }

    public void setHaendler(Haendler haendler){
        this.haendler = haendler;
    }
    
    public void addOrt(Ort ort) {
        ortsliste.add(ort);
    }

    public void addTaetigkeitOrt(Taetigkeit taetigkeit) {
        taetigkeitslisteOrt.add(taetigkeit);
    }

    public void addTaetigkeitStadt(Taetigkeit taetigkeit) {
        taetigkeitslisteStadt.add(taetigkeit);
    }

    public void addWareOrt(Ware ware) {
        warenlisteOrt.add(ware);
    }

    public void addWareStadt(Ware ware) {
        warenlisteStadt.add(ware);
    }

    private void setAktionenName(){
        aktionenName[0] = "Kaufen";
        aktionenName[1] = "Verkaufen";
        aktionenName[2] = "Inventar ansehen";
        aktionenName[3] = "Tätigkeit ausführen";
        aktionenName[4] = "Essen";
        aktionenName[5] = "Reisen";
        aktionenName[6] = "Tag beenden";
        aktionenName[7] = "Spiel beenden";
    }

    public int getTageszähler() {
        return tageszähler;
    }
    
    public int getMaxTageszähler() {
        return maxTageszaehler;
    }

    public int getMaxGewichtWagen() {
        return maxGewichtWagen;
    }

    public int getMaxLebenspunkte() {
        return maxLebenspunkte;
    }

    public int getMinlebenspunkte() {
        return minLebenspunkte;
    }

    public int getMinGoldstuecke(){
        return minGoldstuecke;
    }

    public void inkrementiereTageszaehler(){
        tageszähler++;
    }

    public void zeigeEssensoptionen(){
        System.out.println("##################################################");
        System.out.println( "Tag: " + getTageszähler() + "/" + getMaxTageszähler() +
                            "\nLebenspunkte: " + haendler.getLebenspunkte() + "/" + getMaxLebenspunkte() +
                            "\nGoldstücke: " + haendler.getGoldstuecke() +
                            "\nGewicht des Wagens: " + haendler.berechneGewichtWagen() + "/" + getMaxGewichtWagen() + "\n");
        System.out.println("Optionen zum Essen:\n");
        System.out.println("1) lokal Essen für 1 Goldstück");
        System.out.println("2) Bohnen mit Speck essen");
        System.out.println("\nEingaben ungleich eines Index führen zum Abbruch!");
        System.out.println("##################################################");                    
    }

    private ArrayList<Integer> zeigeTeatigkeiten(){
        ArrayList<Integer> tempIndex = new ArrayList<>();
        if (haendler.getStandort().getIstDorf()) {
            System.out.println("##################################################");
            System.out.println( "Tag: " + getTageszähler() + "/" + getMaxTageszähler() +
                                "\nLebenspunkte: " + haendler.getLebenspunkte() + "/" + getMaxLebenspunkte() +
                                "\nGoldstücke: " + haendler.getGoldstuecke() +
                                "\nGewicht des Wagens: " + haendler.berechneGewichtWagen() + "/" + getMaxGewichtWagen() + "\n");
            System.out.println("Tätigkeiten im Dorf:\n");
            System.out.format("%5s%-18s%-8s", "", "Name", "|Lohn"); 
            System.out.println("\n-----------------------|-------");
            for (int i = 0; i < taetigkeitslisteOrt.size(); i++) {
                if (taetigkeitslisteOrt.get(i).getTaetigkeitAngebot()) {    //if(!true) continue;
                    tempIndex.add(i);
                    System.out.format("%2$5s%-18s", taetigkeitslisteOrt.get(i).getName(), tempIndex.size()+") ");
                    System.out.println("|" + taetigkeitslisteOrt.get(i).getLohn());
                }
            }
            System.out.println("\nEingaben ungleich eines Index führen zum Abbruch!");
            System.out.println("##################################################");
        } else {
            System.out.println("##################################################");
            System.out.println( "Tag: " + getTageszähler() + "/" + getMaxTageszähler() +
                                "\nLebenspunkte: " + haendler.getLebenspunkte() + "/" + getMaxLebenspunkte() +
                                "\nGoldstücke: " + haendler.getGoldstuecke() +
                                "\nGewicht des Wagens: " + haendler.berechneGewichtWagen() + "/" + getMaxGewichtWagen() + "\n");
            System.out.println("Tätigkeiten in der Stadt:\n");
            System.out.format("%5s%-18s%-8s", "", "Name", "|Lohn"); 
            System.out.println("\n-----------------------|-------");
            for (int i = 0; i < taetigkeitslisteStadt.size(); i++) {
                if (taetigkeitslisteStadt.get(i).getTaetigkeitAngebot()) {    //if(!true) continue;
                    tempIndex.add(i);
                    System.out.format("%2$5s%-18s", taetigkeitslisteStadt.get(i).getName(), tempIndex.size()+") ");
                    System.out.println("|" + taetigkeitslisteStadt.get(i).getLohn());
                }
            }
            System.out.println("\nEingaben ungleich eines Index führen zum Abbruch!");
            System.out.println("##################################################");
        }
        return tempIndex;
    }

    private void zeigeOrte(){
        System.out.println("##################################################");
        System.out.println( "Tag: " + tageszähler + "/" + getMaxTageszähler() +
                            "\nLebenspunkte: " + haendler.getLebenspunkte() + "/" + getMaxLebenspunkte() +
                            "\nGoldstücke: " + haendler.getGoldstuecke() +
                            "\nGewicht des Wagens: " + haendler.berechneGewichtWagen() + "/" + getMaxGewichtWagen() + "\n");

        System.out.println("Du befindest dich in: " + haendler.getStandort().getName() + "\n");
        for (int i = 0; i < ortsliste.size(); i++) {
            //System.out.println("1234567812345678123456781234567812345678123456781234567812345678");
            System.out.format("%2$5s%-11s", ortsliste.get(i).getName(), (i+1)+ ") ");
            System.out.format("%-20s", "|Koordinaten " + ortsliste.get(i).getPositionX() + "/" + ortsliste.get(i).getPositionY());
            System.out.println("\t\b|Entfernung " + ortsliste.get(i).berechneEntfernung(haendler.getStandort()));
        }
        System.out.println("\nEingaben ungleich eines Index führen zum Abbruch!");
        System.out.println("##################################################");
    }

    private void zeigeWareKaufen(){
        if (haendler.getStandort().getIstDorf()) {
            System.out.println("##################################################");
            System.out.println( "Tag: " + getTageszähler() + "/" + getMaxTageszähler() +
                                "\nLebenspunkte: " + haendler.getLebenspunkte() + "/" + getMaxLebenspunkte() +
                                "\nGoldstücke: " + haendler.getGoldstuecke() +
                                "\nGewicht des Wagens: " + haendler.berechneGewichtWagen() + "/" + getMaxGewichtWagen() + "\n");
            System.out.println("Ware im Dorf:\n");  
            System.out.format("%5s%-18s%-8s%-8s", "", "Name", "|Preis", "|Gewicht\n"); 
            System.out.println("-----------------------|-------|-------");
            for (int i = 0; i < warenlisteOrt.size(); i++) {
                System.out.format("%2$5s%-18s", warenlisteOrt.get(i).getName(), (i+1)+") ");
                System.out.println("|" + warenlisteOrt.get(i).getPreis() + "\t\b|" +
                                        warenlisteOrt.get(i).getGewicht());
            }
            System.out.println("\nEingaben ungleich eines Index führen zum Abbruch!");  
            System.out.println("##################################################");   
        } else {
            System.out.println("##################################################");
            System.out.println( "Tag: " + getTageszähler() + "/" + getMaxTageszähler() +
                                "\nLebenspunkte: " + haendler.getLebenspunkte() + "/" + getMaxLebenspunkte() +
                                "\nGoldstücke: " + haendler.getGoldstuecke() +
                                "\nGewicht des Wagens: " + haendler.berechneGewichtWagen() + "/" + getMaxGewichtWagen() + "\n");
            System.out.println("Ware in der Stadt:\n");              
            System.out.format("%5s%-18s%-8s%-8s", "", "Name", "|Preis", "|Gewicht\n");     
            System.out.println("-----------------------|-------|-------");
            for (int i = 0; i < warenlisteStadt.size(); i++) {
                System.out.format("%2$5s%-18s", warenlisteStadt.get(i).getName(), (i+1)+") ");
                System.out.println("|" + warenlisteStadt.get(i).getPreis() + "\t\b|" +
                                        warenlisteStadt.get(i).getGewicht());
            }
            System.out.println("\nEingaben ungleich eines Index führen zum Abbruch!"); 
            System.out.println("##################################################");    
        }
    }

    private ArrayList<Integer> zeigeWareVerkaufen(ArrayList<Ware> inventar){
        ArrayList<Integer> tempIndex = new ArrayList<>();
        if (haendler.getStandort().getIstDorf()) {
            System.out.println("##################################################");
            System.out.println( "Tag: " + getTageszähler() + "/" + getMaxTageszähler() +
                                "\nLebenspunkte: " + haendler.getLebenspunkte() + "/" + getMaxLebenspunkte() +
                                "\nGoldstücke: " + haendler.getGoldstuecke() +
                                "\nGewicht des Wagens: " + haendler.berechneGewichtWagen() + "/" + getMaxGewichtWagen() + "\n");
            System.out.println("Ware zum Verkauf im Dorf:\n");                 
            System.out.format("%5s%-18s%-8s%-8s", "", "Name", "|Preis", "|Gewicht\n");     
            System.out.println("-----------------------|-------|-------");
            for (int i = 0; i < inventar.size(); i++) {
                if (!inventar.get(i).getKaufOrt().getIstDorf() && !inventar.get(i).getIstEssbar()) {
                    tempIndex.add(i);
                    System.out.format("%2$5s%-18s", inventar.get(i).getName(), tempIndex.size() + ") ");
                    System.out.println("|" + inventar.get(i).berechnePreis(haendler.getStandort()) + "\t\b|" +
                                        inventar.get(i).getGewicht());
                }
            }
            System.out.println("\nEingaben ungleich eines Index führen zum Abbruch!");
            System.out.println("##################################################");
        } else {
            System.out.println("##################################################");
            System.out.println( "Tag: " + getTageszähler() + "/" + getMaxTageszähler() +
                                "\nLebenspunkte: " + haendler.getLebenspunkte() + "/" + getMaxLebenspunkte() +
                                "\nGoldstücke: " + haendler.getGoldstuecke() +
                                "\nGewicht des Wagens: " + haendler.berechneGewichtWagen() + "/" + getMaxGewichtWagen() + "\n");
            System.out.println("Ware zum Verkauf in der Stadt:\n");  
            System.out.format("%5s%-18s%-8s%-8s", "", "Name", "|Preis", "|Gewicht\n");     
            System.out.println("-----------------------|-------|-------");
            for (int i = 0; i < inventar.size(); i++) {
                if (inventar.get(i).getKaufOrt().getIstDorf() && !inventar.get(i).getIstEssbar()) {
                    tempIndex.add(i);
                    System.out.format("%2$5s%-18s", inventar.get(i).getName(), tempIndex.size() + ") ");
                    System.out.println("|" + inventar.get(i).berechnePreis(haendler.getStandort()) + "\t\b|" +
                                        inventar.get(i).getGewicht());
                }
            }
            System.out.println("\nEingaben ungleich eines Index führen zum Abbruch!");
            System.out.println("##################################################");
        }
        return tempIndex;
    }

    private void beendeSpiel(){
        System.out.println("Das Spiel ist zu Ende an Tag: " + getTageszähler());
        System.out.println("Deine finalen Goldstücke waren: " + haendler.getGoldstuecke());
    }

    public void beendeTag(){
        for (Ware ware : haendler.getInventar()) {
            ware.berechneInteresse();
        }
        for (Ort ort : ortsliste) {
            ort.berechneWahrscheinlichkeit();
        }
        for (Taetigkeit taetigkeit : taetigkeitslisteOrt) {
            taetigkeit.setWahrscheinlichkeitTag();
        }
        for (Taetigkeit taetigkeit : taetigkeitslisteStadt) {
            taetigkeit.setWahrscheinlichkeitTag();
        }
        haendler.erlaubeAktionen();
        haendler.subtrahiereLebenspunkte(20);
        inkrementiereTageszaehler();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int eingabeScanner = 0;
        String jaNeinAbfrage = "";
        ArrayList<Integer> tempIndex = new ArrayList<>();
        
        boolean run = false;

        System.out.println("Herzlich Willkommen zu unserem Spiel!\n" + // Erklärung des Spieles hier einfügen
                            "Du startest als Händler mit 100 Goldstücken in der Stadt und hast das Ziel so viele Goldstücke wie möglich zu sammeln.\n" +
                            "Die Steuerung des Spieles läuft über die Kommandozeile. Durch die angegebenen Zahlen können Entscheidungen getroffen werden.\n" +
                            "Drücke Enter, um mit dem Spiel zu beginnen.");

        if (scanner.hasNextLine()) {
            run = true;
        }

        while (run) {
            System.out.println("##################################################");
            System.out.println( "Tag: " + tageszähler + "/" + maxTageszaehler +
                                "\nLebenspunkte: " + haendler.getLebenspunkte() + "/" + maxLebenspunkte +
                                "\nGoldstücke: " + haendler.getGoldstuecke() +
                                "\nGewicht des Wagens: " + haendler.berechneGewichtWagen() + "/" + maxGewichtWagen + "\n");

            System.out.println("Du befindest dich in: " + haendler.getStandort().getName() + "\n\nWas möchtest du machen?");

            tempIndex.clear();
            for (int i = 0; i < aktionenName.length; i++) {
                if (haendler.getAktionenBooleans()[i]) {
                    tempIndex.add(i+1);
                    System.out.format("%1$5s%2$s", tempIndex.size() + ") ",aktionenName[i] + "\n");
                }
            }
            System.out.println("##################################################");
            System.out.println("Eingabe:");
            try {
                eingabeScanner = scanner.nextInt();
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("Fehlerhafte Eingabe!");
                continue;
            }            
            
            if (eingabeScanner<0 || eingabeScanner>tempIndex.size()) {
                System.out.println("Eingabe entspricht nicht den erlaubten Aktionen! Versuche es nochmal");
                continue;
            }            

            switch (tempIndex.get(eingabeScanner-1)) {   
                case 1:
                    zeigeWareKaufen();
                    System.out.println("Eingabe:");
                    try {
                        eingabeScanner = scanner.nextInt();
                    } catch (Exception e) {
                        scanner.nextLine();
                        System.out.println("Fehlerhafte Eingabe!");
                        continue;
                    }
                    if (haendler.getStandort().getIstDorf() && eingabeScanner>=0 && eingabeScanner<=warenlisteOrt.size()) {
                        Ware tempIndexWare = warenlisteOrt.get(eingabeScanner-1);
                        System.out.println("Wie viele Waren sollen gekauft werden?");
                        try {
                            eingabeScanner = scanner.nextInt();
                        } catch (Exception e) {
                            scanner.nextLine();
                            System.out.println("Fehlerhafte Eingabe!");
                            continue;
                        } 
                        if (eingabeScanner<=0) continue;
                        for (int j = 0; j < eingabeScanner; j++) {
                            haendler.kaufen(tempIndexWare);
                        }
                    } else if(eingabeScanner>0 && eingabeScanner<=warenlisteStadt.size()){
                        Ware tempIndexWare = warenlisteStadt.get(eingabeScanner-1);
                        System.out.println("Wie viele Waren sollen gekauft werden?");
                        try {
                            eingabeScanner = scanner.nextInt();
                        } catch (Exception e) {
                            scanner.nextLine();
                            System.out.println("Fehlerhafte Eingabe!");
                            continue;
                        } 
                        if (eingabeScanner<0) continue;
                        for (int j = 0; j < eingabeScanner; j++) {
                            haendler.kaufen(tempIndexWare);
                        }
                    }
                    break;
                case 2:
                    while (true) {
                        tempIndex = zeigeWareVerkaufen(haendler.getInventar());
                        if (tempIndex.size()==0) {
                            System.out.println("Es können keine Waren verkauft werden");
                            break;
                        }
                        System.out.println("Eingabe:");
                        try {
                            eingabeScanner = scanner.nextInt();
                        } catch (Exception e) {
                            scanner.nextLine();
                            System.out.println("Fehlerhafte Eingabe!");
                            continue;
                        } 
                        scanner.nextLine();
                        if (eingabeScanner<=0 || eingabeScanner>tempIndex.size()) {
                            break;
                        }
                        haendler.verkaufen(haendler.getInventar().get(tempIndex.get(eingabeScanner-1)));
                        haendler.loescheWareAusInventar(tempIndex.get(eingabeScanner-1));
                        System.out.println("Weitere Ware verkaufen? j/n");
                        jaNeinAbfrage = scanner.nextLine().toLowerCase();
                        if (!(jaNeinAbfrage.equals("j") || jaNeinAbfrage.equals("ja") || jaNeinAbfrage.equals("y") || jaNeinAbfrage.equals("yes"))){
                            break;
                        }
                    }                
                    break;
                case 3:
                    haendler.zeigeInventar();
                    scanner.nextLine();
                    if (haendler.getInventar().isEmpty() || scanner.hasNextLine()) {
                        continue;
                    }
                    break;
                case 4:
                    tempIndex = zeigeTeatigkeiten();
                    if (tempIndex.isEmpty()) {
                        System.out.println("Es werden keine Tätigkeiten angeboten");
                        continue;
                    }
                    System.out.println("Eingabe:");
                    try {
                        eingabeScanner = scanner.nextInt();
                    } catch (Exception e) {
                        scanner.nextLine();
                        System.out.println("Fehlerhafte Eingabe!");
                        continue;
                    } 
                    if (eingabeScanner<=0 || eingabeScanner>tempIndex.size()) break;
                    if (haendler.getStandort().getIstDorf()) {
                        haendler.taetigkeitAusfuehren(taetigkeitslisteOrt.get(tempIndex.get(eingabeScanner-1)));
                    } else {
                        haendler.taetigkeitAusfuehren(taetigkeitslisteStadt.get(tempIndex.get(eingabeScanner-1)));
                    }
                    break;
                case 5:
                    zeigeEssensoptionen();
                    try {
                        eingabeScanner = scanner.nextInt();
                    } catch (Exception e) {
                        scanner.nextLine();
                        System.out.println("Fehlerhafte Eingabe!");
                        continue;
                    } 
                    if (eingabeScanner == 1) {
                        haendler.isstLokal();
                    } else if (eingabeScanner == 2){
                        haendler.isstBohnenMitSpeck();
                    }
                    break;
                case 6:
                    zeigeOrte();
                    System.out.println("Eingabe:");
                    try {
                        eingabeScanner = scanner.nextInt();
                    } catch (Exception e) {
                        scanner.nextLine();
                        System.out.println("Fehlerhafte Eingabe!");
                        continue;
                    } 
                    if(eingabeScanner > 0 && eingabeScanner<=ortsliste.size()){
                        haendler.reisen(ortsliste.get(eingabeScanner-1));
                    }                    
                    break;
                case 7:
                    beendeTag();
                    break;
                case 8:
                    run = false;
                    break;
                default:
                    System.out.println("Falsche Eingabe, bitte wiederholen!");
                    break;
            }
            if(!run || getTageszähler() == 100 || haendler.getLebenspunkte()<getMinlebenspunkte()) {beendeSpiel(); break;}
        }
        scanner.close();
    }
}
