import java.util.Random;

public class Taetigkeit {
    private String name;
    private int lohn;
    private int wahrscheinlichkeit;     //1 aus wahrscheinlichkeit -> 1/4 = 25%
    private int wahrscheinlichkeitTag;

    public Taetigkeit(String name, int lohn, int wahrscheinlichkeit){
        this.name = name;
        this.lohn = lohn;
        this.wahrscheinlichkeit = wahrscheinlichkeit;
        setWahrscheinlichkeitTag();
    }

    public String getName(){
        return name;
    }

    public int getLohn() {
        return lohn;
    }

    public int getWahrscheinlichkeit() {
        return wahrscheinlichkeit;
    }

    public void setWahrscheinlichkeitTag(){
        Random r = new Random();
        this.wahrscheinlichkeitTag = r.nextInt(wahrscheinlichkeit);
    }

    public boolean getTaetigkeitAngebot(){
        return wahrscheinlichkeitTag==0;
    }
}
