package com.ohgiraffers.repository;

import com.ohgiraffers.aggregate.Digimon;
import com.ohgiraffers.aggregate.Food;
import com.ohgiraffers.aggregate.Level;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DigimonRepository {

    /* 설명. 초기 생성 디지몬에 대한 리스트 DB 파일입니다. */
    private final String filePath = "src/main/java/com/ohgiraffers/db/digimonData.dat";

    /* 설명. 선택한 디지몬에 대한 데이터 저장 파일입니다. MyData.dat*/
    private final String myPath = "src/main/java/com/ohgiraffers/db/MyData.dat";

    private ArrayList<Digimon> digimonList = new ArrayList<>();

    public DigimonRepository() {
        ArrayList<Digimon> digimons = new ArrayList<>();

        File file = new File(filePath);

//        if (!file.exists()) {
            digimons.add(new Digimon(1, "코로몬", Level.BABY.ordinal()));
            digimons.add(new Digimon(2, "뿔몬", Level.BABY.ordinal()));
            digimons.add(new Digimon(3, "모티몬", Level.BABY.ordinal()));

            saveDigimons(digimons);
//        }
        loadDigimons();
    }

    private void saveDigimons(ArrayList<Digimon> digimons) {
        ObjectOutputStream oos = null;

        try {
            oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filePath)));

            for (Digimon d : digimons) {
                oos.writeObject(d);
            }
            oos.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (oos != null) oos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void saveMyDigimon(Digimon digimon) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(myPath)))) {

            oos.writeObject(digimon);

            oos.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadDigimons() {
        ObjectInputStream ois = null;

        try {
            ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filePath)));

            while (true) {
                digimonList.add((Digimon) (ois.readObject()));
            }
        } catch (EOFException e) {
            System.out.println("디지몬 정보 모두 로딩됨...");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ois != null) ois.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public ArrayList<Digimon> selectedDigimon() {
        return digimonList;
    }

    public Digimon selectDigimon(int digiNo) {
        for (Digimon d : digimonList) {
            if (d.getId() == digiNo) return d;
        }
        System.out.println("해당하는 디지몬이 없습니다. (repo)");
        return null;
    }

    public boolean feedDigimon(Digimon myDigimon, Food selectedFood) {
        myDigimon.setFeedGage(selectedFood.getPoint());
        return true;
    }


    /* 설명. 내 디지몬이 있는지 확인하는 메소드 */
    public int isExistMyDigimon() {
        File file = new File(myPath);
        return file.exists() ? 1 : 0;
    }

    /* 설명. 키우던 디지몬 정보 삭제(유기) */
    public int deleteDigimon() {
//        File myDigimon = new File(myPath);
        Path myDigimonFilePath = Paths.get(myPath);
        try {
            Files.deleteIfExists(myDigimonFilePath);       //해당 path 에 파일이 있으면 삭제, 없으면 false 반환
            return 1;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Digimon getMyDigimon() {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new BufferedInputStream(new FileInputStream(myPath)))
        ) {
            return (Digimon) (ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
}
