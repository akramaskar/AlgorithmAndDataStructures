package ad.praktikum2;

public class Suchkriterium {

    Parameter param;
    int von;
    int bis;

    public Suchkriterium(Parameter p, int von, int bis) throws IllegalArgumentException{

        if(von > bis) throw new IllegalArgumentException("Von darf nicht größer als bis sein!");

        this.param = p;
        this.von = von;
        this.bis = bis;
    }

    public int getVon() {
        return von;
    }
    public int getBis() {
        return bis;
    }

    @Override
    public String toString() {
        return  param +
                " von: " + von +
                " bis: " + bis ;
    }
}
