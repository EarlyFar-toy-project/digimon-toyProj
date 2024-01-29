package com.ohgiraffers.service;

import com.ohgiraffers.aggregate.Digimon;
import com.ohgiraffers.aggregate.Food;
import com.ohgiraffers.repository.DigimonRepository;

import java.util.ArrayList;

public class DigimonService {

    private static final DigimonRepository dr = new DigimonRepository();

    public void selectDigimon() {
        ArrayList<Digimon> selectedDigimons = dr.selectedDigimon();

        /* 설명. 회원이 한명도 없어서 조회 결과가 없더라도 ArrayList 객체는 넘어온다. (Empty 상태로) */
        if (!selectedDigimons.isEmpty()) {       // 회원이 한명이라도 조회 된다면
//        System.out.println("===== service 까지 잘 반환되어 오나 확인 =====");
            for (Digimon d : selectedDigimons) {
                System.out.println(d);
            }
            return;                             // 이후 코드와 상관 없이 메소드 종료
        }

        /* 설명. 조건이 맞지 않아(회원이 조회되지 않아) 출력을 하는 구문 (위의 조건이 맞으면 실행되지 않음) (feat. else 안 쓰기) */
        System.out.println("등록된 회원이 없습니다.");
    }

    public void feedDigimon(int digiNo, Food selectedFood) {
        if (selectedFood == null) {
            System.out.println("먹이를 주는 것을 취소합니다.");
            return;
        }
        System.out.println("선택하신 먹이는 " + selectedFood + "입니다.");
        boolean result = dr.feedDigimon(digiNo,selectedFood);
        if (result) {
            System.out.println(digiNo + "번 디지몬에게 성공적으로 먹이를 주었습니다.");
            return;
        }
        System.out.println("오류 발생!!! " + digiNo + "번 디지몬에게 먹이를 주지 못했습니다!!!");

    }
}
