import java.util.Random;

public class Ort {
    private String name;
    private boolean istDorf;
    private int positionX;
    private int positionY;
    private int wahrscheinlichkeitTaetigkeit;

    public Ort(String name, boolean istDorf, int positionX, int positionY){
        this.name = name;
        this.istDorf = istDorf;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public boolean getIstDorf() {
        return istDorf;
    }

    public String getName(){
        return name;
    }

    public int getWahrscheinlichkeitTaetigkeit() {
        return wahrscheinlichkeitTaetigkeit;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void berechneWahrscheinlichkeit(){
        Random r = new Random();

        wahrscheinlichkeitTaetigkeit = r.nextInt(2);
    }

    public int berechneEntfernung(Ort standortHaendler){
        int entfernung = (int) Math.round(Math.sqrt(Math.pow(this.positionX-standortHaendler.positionX, 2)+Math.pow(this.positionY-standortHaendler.positionY, 2)));
        return entfernung;
    }
}