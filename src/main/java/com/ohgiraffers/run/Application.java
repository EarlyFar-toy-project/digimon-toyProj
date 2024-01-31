package com.ohgiraffers.run;

import com.ohgiraffers.aggregate.Digimon;
import com.ohgiraffers.aggregate.Food;
import com.ohgiraffers.service.DigimonService;

import java.util.Scanner;

public class Application {

    private static final DigimonService ds = new DigimonService();

    public static void main(String[] args) {
        /*  작업 리스트
         *   1. 내 디지몬 있는지 확인
         *   ==> if (내 디지몬 파일이 존재)
         *           디지몬 키우기 메뉴 노출
         *       else
         *           디지몬 선택 메뉴 호출
         *
         *   2.디지몬 키우기 메뉴 리스트업
         *   ==> 먹이, 운동, 유기, 잠자기
         *
         *   3. 기능 구현
         *   - 디지몬 리스트 DB 불러오기 메소드
         *   - 먹이 메소드
         *   - 운동 메소드
         *   - 잠자기 메소드
         *   - 유기 메소드
         *   - 게이지 업데이트 메소드
         *
         *   ============================
         *   의견
         *   먹이 - 포만감 게이지
         *   운동 - 활동 게이지
         *   잠자기 - 피로 게이지가 있으면 어떨까요?
         *   -다마고치 컨셉-
         *
         *   일단 Level Enum 삭제해도 될까요?
         * */
//        if (ds.isExistMyDigimon() == 1) {
//            /* 설명. 디지몬 육성 메뉴 */
//            showDigimonMenu(getMyDigimon());
//        } else {
//            /* 설명. 최초 생성 */
//            showSelectMenu();
//        }
        if (ds.isExistMyDigimon() != 1) {
            /* 설명. 최초 생성 */
            showSelectMenu();
        }
        /* 설명. 디지몬 육성 메뉴 */
        showDigimonMenu(getMyDigimon());
    }

    /* 설명. 수정해야합ㄴ디ㅏ.*/
    private static void showSelectMenu() {
        Scanner sc = new Scanner(System.in);
        /* 설명.
         *   digiNo: 선택한 디지몬의 id Number
         * */
        int digiNO = 0;
        while (digiNO == 0) {
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
                case 1: case 2: case 3:
                    ds.selectDigimon(input);
                    digiNO = input;
                    break;
                default:
                    System.out.println("번호를 제대로 다시 입력해주세요.");
            }

        }
    }

    private static Digimon getMyDigimon() {
        return ds.getMyDigimon();
    }

    private static void showDigimonMenu(Digimon myDigimon) {
        if (myDigimon == null){
            System.out.println("디지몬에 대한 정보가 존재하지 않습니다.");
            return;
        }
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("내 디지몬: " + myDigimon.getName());
            System.out.println("========= 디지몬 키우기 =========");
            System.out.println("0. 프로그램 종료");
            System.out.println("1. 디지몬 밥 주기");
            System.out.println("2. 디지몬 상태 확인하기");
            System.out.println("3. 디지몬 삭제");
            System.out.print("메뉴를 선택해 주세요: ");

            int input = sc.nextInt();
            switch (input) {
                case 0:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                case 1:
                    ds.feedDigimon(myDigimon,selectFood());
                    break;
                case 2:
                    System.out.println(myDigimon);
                    break;
                case 3:
                    ds.deleteDigimon();
                    return;
                default:
                    System.out.println("번호를 제대로 다시 입력해주세요.");
            }
            ds.saveMyDigimon(myDigimon);
        }
    }

    private static Food selectFood() {
        Food feedFood = null;
        Scanner sc = new Scanner(System.in);
        while (feedFood == null) {
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
