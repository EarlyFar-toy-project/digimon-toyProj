package com.ohgiraffers.run;

import com.ohgiraffers.aggregate.Food;
import com.ohgiraffers.service.DigimonService;

import java.util.Scanner;

public class Application {

    private static final DigimonService ds = new DigimonService();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        /* 설명.
        *   digiNo: 선택한 디지몬의 id Number
        * */
        int digiNO = 0;
        while (digiNO == 0){
            System.out.println("========= 디지몬 선택하기 =========");
            System.out.print(
                    "0. 종료\n" +
                    "1. 코로몬\n" +
                    "2. 뿔몬\n" +
                    "3. 모티몬\n"
            );
            System.out.print("디지몬을 선택해 주세요: ");
            int input = sc.nextInt();
            switch (input) {
                case 0:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                case 1:                         // 코로몬
                    ds.selectDigimon();
                    digiNO = input;
                    break;
                case 2:                         // 뿔몬
                    ds.selectDigimon();
                    digiNO = input;
                    break;
                case 3:                         // 모티몬
                    ds.selectDigimon();
                    digiNO = input;
                    break;
                default:
                    System.out.println("번호를 제대로 다시 입력해주세요.");

            }

        }

        while (true) {
            System.out.println("========= 디지몬 키우기 =========");
            System.out.println("0. 프로그램 종료");
            System.out.println("1. 디지몬 선택하기");
            System.out.println("2. 디지몬 밥 주기");
            System.out.println("3. 디지몬 상태 확인하기");
            System.out.print("메뉴를 선택해 주세요: ");

            int input = sc.nextInt();
            switch (input) {
                case 1:
                    ds.selectDigimon();
                    break;
                case 2:
                    ds.feedDigimon(digiNO,selectFood());
                    break;
                case 9:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("번호를 제대로 다시 입력해주세요.");

            }
        }
    }

    private static Food selectFood() {
        Food feedFood = null;
        Scanner sc = new Scanner(System.in);
        while(feedFood == null){
            System.out.println("======== 먹이 목록 ========");
            System.out.println("0. Meat");
            System.out.println("1. Fish");
            System.out.println("2. Vegetable");
            System.out.println("3. Pill");
            System.out.println("4. SuperFood");
            System.out.println("9. 취소한다.");
            System.out.print("주고자하는 먹이의 번호를 입력하세요: ");
            int inputFood = sc.nextInt();
            switch (inputFood) {
                case 0:
                    feedFood = Food.Meat;
                    break;
                case 1:
                    feedFood = Food.Fish;
                    break;
                case 2:
                    feedFood = Food.Vegetable;
                    break;
                case 3:
                    feedFood = Food.Pill;
                    break;
                case 4:
                    feedFood = Food.SuperFood;
                    break;
                case 9:
                    return feedFood;
                default:
                    System.out.println("번호를 제대로 다시 입력해주세요.");
            }
        }
        return feedFood;
    }
}
