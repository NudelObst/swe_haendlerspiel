public class Main {
    public static void main(String[] args) throws Exception {
        Spiel spiel = new Spiel();
        
        Ware topfWare = new Ware("Topf", 50, 3);
        Ware geschirrWare = new Ware("Geschirr", 7, 2);
        Ware hackeWare = new Ware("Hacke", 35, 2);
        Ware hammerWare = new Ware("Hammer", 15, 1);
        Ware saegeWare = new Ware("Säge", 40, 1);
        Ware bohnenmitSpeckWare = new Ware("BohnenMitSpeck", 1, 1, true);
        Ware weizenWare = new Ware("Weizen", 3, 1);
        Ware maisWare = new Ware("Mais", 7, 1);
        Ware kartoffelWare = new Ware("Kartoffel", 2, 1);
        Ware ruebeWare = new Ware("Rübe", 2, 1);
        Ort stadt = new Ort("Stadt", false, -1, -1);
        Ort erstesDorf = new Ort("Dorf Eins", true, -2, -3);
        Ort zweitesDorf = new Ort("Dorf Zwei", true, 2, -4);
        Ort drittesDorf = new Ort("Dorf Drei", true, 4, -2);
        Ort viertesDorf = new Ort("Dorf Vier", true, 2, 0);
        Ort fuenftesDorf = new Ort("Dorf Fünf", true, 1, 2);
        Ort sechstesDorf = new Ort("Dorf Sechs", true, -3, 3);
        Taetigkeit postbote = new Taetigkeit("Postbote", 3, 2);
        Taetigkeit manager = new Taetigkeit("Manager", 5, 4);
        Taetigkeit erntehelfer = new Taetigkeit("Erntehelfer", 3, 2);
        Taetigkeit wirt = new Taetigkeit("Wirt", 5, 4);
        Haendler haendler = new Haendler(stadt, spiel);
        //Lebensmittel bohnen = new Lebensmittel(jaNeinAbfrage, eingabeScanner, eingabeScanner);

        spiel.addOrt(stadt);
        spiel.addOrt(erstesDorf);
        spiel.addOrt(zweitesDorf);
        spiel.addOrt(drittesDorf);
        spiel.addOrt(viertesDorf);
        spiel.addOrt(fuenftesDorf);
        spiel.addOrt(sechstesDorf);
        spiel.addWareOrt(weizenWare);
        spiel.addWareOrt(maisWare);
        spiel.addWareOrt(kartoffelWare);
        spiel.addWareOrt(ruebeWare);
        spiel.addWareOrt(bohnenmitSpeckWare);
        spiel.addWareStadt(topfWare);
        spiel.addWareStadt(geschirrWare);
        spiel.addWareStadt(hackeWare);
        spiel.addWareStadt(hammerWare);
        spiel.addWareStadt(saegeWare);
        spiel.addWareStadt(bohnenmitSpeckWare);
        spiel.addTaetigkeitOrt(erntehelfer);
        spiel.addTaetigkeitOrt(wirt);
        spiel.addTaetigkeitStadt(postbote);
        spiel.addTaetigkeitStadt(manager);
        spiel.setHaendler(haendler);
        spiel.run();
    }
}
