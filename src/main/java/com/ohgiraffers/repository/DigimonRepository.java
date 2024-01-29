package com.ohgiraffers.repository;

import com.ohgiraffers.aggregate.Digimon;
import com.ohgiraffers.aggregate.Level;

import java.io.*;
import java.util.ArrayList;

public class DigimonRepository {

    private final String filePath = "src/main/java/com/ohgiraffers/db/digimonData.dat";

    private ArrayList<Digimon> digimonList = new ArrayList<>();

    public DigimonRepository() {
        ArrayList<Digimon> digimons = new ArrayList<>();

        File file = new File(filePath);
        if (!file.exists()) {
            digimons.add(new Digimon(1, "코로몬", 0));
            digimons.add(new Digimon(2, "뿔몬", 0));
            digimons.add(new Digimon(3, "모티몬", 0));

            saveDigimons(digimons);
        }
    }

    private void saveDigimons(ArrayList<Digimon> digimons) {
        ObjectOutputStream oos = null;

        try {
            oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filePath)));

            for (Digimon d : digimons){
                oos.writeObject(d);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (oos!=null) oos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public ArrayList<Digimon> selectedDigimon() {
        return null;
    }
}
