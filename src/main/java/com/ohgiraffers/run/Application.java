package com.ohgiraffers.run;

import com.ohgiraffers.aggregate.Digimon;
import com.ohgiraffers.aggregate.Food;
import com.ohgiraffers.aggregate.Motion;
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
         *   ==> 먹이, 운동, 삭제, 잠자기
         *
         *   3. 기능 구현
         *   - 디지몬 리스트 DB 불러오기 메소드
         *   - 먹이 메소드
         *   - 운동 메소드
         *   - 잠자기 메소드
         *   - 삭제 메소드
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

    /* 설명. 수정해야합니다.*/
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
            System.out.println("4. 디지몬 움직이기");
            System.out.println("5. 잠자기");
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
                case 4:
                    ds.moveDigimon(myDigimon,selectMotion());
                    break;
                case 5:
                    ds.sleepDigimon(myDigimon);
                    break;
                default:
                    System.out.println("번호를 제대로 다시 입력해주세요.");
            }
            ds.saveMyDigimon(myDigimon);
        }
    }

    /* 설명. 선택된 디지 */
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

    /* 설명. 선택된 디지몬의 움직임을 제어할 수 있는 선택지 뷰를 보여주는 메소드입니다. */
    private static Motion selectMotion() {
        Motion motion = null;
        Scanner sc = new Scanner(System.in);
        while (motion == null) {
            System.out.println("======== 모션 컨트롤러 ========");
            System.out.println("좌:J, 우:L, 위:I, 아래:K, 취소: E");
            System.out.print("어느 방향으로 가볼까요? ");
            String inputMotion = sc.next().toUpperCase(); // 입력값 대문자로 변환합니다
            char motionChar = inputMotion.charAt(0);      // 문자열의 첫 번째 문자를 읽어와서 char로 변환

            switch (motionChar) {
                case 'J':
                    motion = Motion.Left;
                    break;
                case 'L':
                    motion = Motion.Right;
                    break;
                case 'I':
                    motion = Motion.Upward;
                    break;
                case 'K':
                    motion = Motion.Downward;
                    break;
                case 'E':
                    return motion;
                default:
                    System.out.println("다른 동작은 아직 이른거 같은데??");
            }
        }
        return motion;
    }


}
