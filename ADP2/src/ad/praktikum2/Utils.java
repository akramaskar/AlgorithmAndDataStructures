package ad.praktikum2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static ad.praktikum2.Parameter.*;

public class Utils {

    public static LinkedList<Stadt> load (int anzahl){
        final String csvFile = "/home/akram/GitRepo/HAW/ADP2/src/ad/StaedteStatistik.CSV";
        BufferedReader br = null;
        String line = "";
        String csvSplitBy = ";";

        LinkedList<Stadt> staedteListe = new LinkedList<>();

        try {
            br = new BufferedReader(new FileReader(csvFile));

            while(anzahl > 0) {
                line = br.readLine();
                String[] cities = line.split(csvSplitBy);

                String name = cities[0];
                //String stadtTitel = cities[0].split(",")[1];
                int plz = Integer.parseInt(cities[1]);
                double flaeche = Double.parseDouble(cities[2].replaceAll("\\s+","").replace(",","."));
                int bevG = Integer.parseInt(checkForUnknown(cities[3].replaceAll("\\s+","")));
                int bevM = Integer.parseInt(checkForUnknown(cities[4].replaceAll("\\s+","")));
                int bevW = Integer.parseInt(checkForUnknown(cities[5].replaceAll("\\s+","")));

/*
                System.out.println("name: " + name);
                System.out.println("Stadttitel " + stadtTitel);
                System.out.println("PLZ: " + plz);
                System.out.println("Fleache: " + flaeche);
                System.out.println("BevG: "+ bevG);
                System.out.println("BevM: "+ bevM);
                System.out.println("BevW: "+ bevW);
*/
                Stadt neueStadt = new Stadt(name, plz,flaeche,bevG,bevM,bevW);
                staedteListe.addLast(neueStadt);
                anzahl--;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return staedteListe;
    }

    public static void find(int anzahl, Suchkriterium... kriteria){

        HashMap<String, Stadt> result = new HashMap<>();
        LinkedList<Stadt> gesamtStaedteListe = Utils.load(anzahl);
        LinkedList<Stadt> tempStadt = new LinkedList<>();

        BST tree = new BST();
        for( Stadt stadt : gesamtStaedteListe){
            tree.insert(stadt, kriteria[0].param);
        }
        tree.findFromTo(tree.getRoot(),kriteria[0].getVon(), kriteria[0].getBis(), tempStadt);

        for( int i = 1; i< kriteria.length; i++){
            tree.reset();
            for(Stadt stadt : tempStadt) {
                tree.insert(stadt,kriteria[i].param);
            }
            tempStadt.clear();
            tree.findFromTo(tree.getRoot(), kriteria[i].getVon(), kriteria[i].getBis(), tempStadt);
        }
        for(Stadt temp : tempStadt){
            result.put(temp.getName(), temp);
        }

        for(Map.Entry<String, Stadt> entry : result.entrySet()){
            System.out.println(entry.getValue().toString());
        }
        System.out.println("Für die folgenden Parameter:");
        for( Suchkriterium k : kriteria) {
            System.out.println(k.toString());
        }
        System.out.println("Wir haben insgesamt " + result.size() + " Einträge gefunden!");

    }

    private static String checkForUnknown(String s) {
        if(s.contains("/")){
            return "0";
        }
        return s;
    }
}
