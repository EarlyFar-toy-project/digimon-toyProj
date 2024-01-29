package com.ohgiraffers.repository;

import com.ohgiraffers.aggregate.Digimon;
import com.ohgiraffers.aggregate.Food;
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
            digimons.add(new Digimon(1, "코로몬", Level.BABY.ordinal()));
            digimons.add(new Digimon(2, "뿔몬", Level.BABY.ordinal()));
            digimons.add(new Digimon(3, "모티몬", Level.BABY.ordinal()));

            saveDigimons(digimons);
        }
        loadDigimons(digimons);
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

    private void loadDigimons(ArrayList<Digimon> digimons) {
        ObjectInputStream ois = null;

        try {
            ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filePath)));

            while(true){
                digimonList.add((Digimon) (ois.readObject()));
            }
        } catch (EOFException e){
            System.out.println("디지몬 정보 모두 로딩됨...");
        } catch(IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ois!=null) ois.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public ArrayList<Digimon> selectedDigimon() {
        return digimonList;
    }

    public Digimon selectDigimon(int digiNo){
        for (Digimon d: digimonList){
            if (d.getId() == digiNo) return d;
        }
        return null;
    }

    public boolean feedDigimon(int digiNo, Food selectedFood) {
        selectDigimon(digiNo).setFeedGage(selectedFood.getPoint());
        return true;
    }
}
