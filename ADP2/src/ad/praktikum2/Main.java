package ad.praktikum2;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args){

        Suchkriterium kriteria1 = new Suchkriterium(Parameter.BEVGESAMT, 0, 1000000);
        Suchkriterium kriteria2 = new Suchkriterium(Parameter.BEVWEIBLICH, 300000,350000);

        //Suchkriterium hauptkriterium = new Suchkriterium(kriteria1, kriteria2);

        Utils.find(1000,kriteria1, kriteria2);
    }
}
