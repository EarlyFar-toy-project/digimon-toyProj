package com.ohgiraffers.service;

import com.ohgiraffers.aggregate.Digimon;
import com.ohgiraffers.aggregate.Food;
import com.ohgiraffers.aggregate.Motion;
import com.ohgiraffers.repository.DigimonRepository;

import java.util.ArrayList;

public class DigimonService {

    private static final DigimonRepository dr = new DigimonRepository();

    public void selectDigimon(int digiNo) {
        Digimon digimon = dr.selectDigimon(digiNo);

        if (digimon != null) {
            dr.saveMyDigimon(digimon);
            return;
        }

        System.out.println("해당 디지몬 정보가 없습니다.");
    }

    public void feedDigimon(Digimon myDigimon, Food selectedFood) {
        int currentFeedGage = myDigimon.getFeedGage();
        if(currentFeedGage == Digimon.MAX_GAGE) {
            System.out.println(myDigimon.getName() + "이(가) 배불러 먹이를 먹지 못합니다.");
            return;
        }

        if (selectedFood == null) {
            System.out.println("먹이를 주는 것을 취소합니다.");
            return;
        }
        System.out.println("선택하신 먹이는 " + selectedFood + "입니다.");
        boolean result = dr.feedDigimon(myDigimon, selectedFood);
        if (result) {
            System.out.println(myDigimon.getName() + "에게 성공적으로 먹이를 주었습니다.");
            return;
        }
        System.out.println("오류 발생!!! " + myDigimon + "번 디지몬에게 먹이를 주지 못했습니다!!!");

    }

    public void deleteDigimon() {

        if (dr.deleteDigimon() == 1) {
            System.out.println("디지몬을 삭제하였습니다. 안녕~~~~");
        }
        System.out.println("다시 실행해 주세요 ");
    }

    public void sleepDigimon(Digimon myDigimon) {
        int currentStaminaGage = myDigimon.getStaminaGage();
        int newStaminaGage = currentStaminaGage + Digimon.STAMINA_RECOVERY_GAGE;

        if (newStaminaGage > Digimon.MAX_GAGE) {
            newStaminaGage = Digimon.MAX_GAGE;
        }

        myDigimon.setStaminaGage(newStaminaGage);

        dr.saveMyDigimon(myDigimon);

        for (int i = 0; i < 3; i++) {
            System.out.println("zzz...");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("일어났다!");
    }

    public int isExistMyDigimon() {
        /* 설명. 1 = 존재, 0 = 없음*/
        return dr.isExistMyDigimon() == 1 ? 1 : 0;
    }

    public Digimon getMyDigimon() {
        return dr.getMyDigimon();
    }

    public void saveMyDigimon(Digimon myDigimon){
        dr.saveMyDigimon(myDigimon);
    }

    public void moveDigimon(Digimon myDigimon, Motion motion) {
        if (motion == null) {
            System.out.println("================");
            System.out.println("이제 그만 쉴까요?");
            System.out.println("================");
            System.out.println();
            return;
        }
        boolean result = dr.moveDigomon(myDigimon, motion);
        if (result) {
            System.out.println();
            System.out.println(myDigimon.getName() + "이 " + motion + " 방향으로 한칸 움직였습니다!");
            System.out.println();
        }
    }
}
