package ad.praktikum2;

public class Stadt{

    private final String name;
    private final int postleitzahl;
    private final double flaeche;
    private final int bevoelkerungGesamt;
    private final int bevoelkerungMaennlich;
    private final int bevoelkerungWeiblich;

    public Stadt(String name, int postleitzahl, double flaeche, int bevoelkerungGesamt, int bevoelkerungMaennlich, int bevoelkerungWeiblich){
        this.name = name;
        this.postleitzahl = postleitzahl;
        this.flaeche = flaeche;
        this.bevoelkerungGesamt = bevoelkerungGesamt;
        this.bevoelkerungMaennlich = bevoelkerungMaennlich;
        this.bevoelkerungWeiblich = bevoelkerungWeiblich;
    }

    public String getName(){
        return name;
    }

    public int getPostleitzahl() {
        return postleitzahl;
    }

    public double getFlaeche() {
        return flaeche;
    }

    public int getBevoelkerungGesamt() { return bevoelkerungGesamt; }
    public int getBevoelkerungMaennlich() {
        return bevoelkerungMaennlich;
    }

    public int getBevoelkerungWeiblich() {
        return bevoelkerungWeiblich;
    }

    @Override
    public String toString() {
        return "Stadt{" +
                "name='" + name + '\'' +
                ", postleitzahl=" + postleitzahl +
                ", flaeche=" + flaeche +
                ", bevoelkerungGesamt=" + bevoelkerungGesamt +
                ", bevoelkerungMaennlich=" + bevoelkerungMaennlich +
                ", bevoelkerungWeiblich=" + bevoelkerungWeiblich +
                '}';
    }
}
