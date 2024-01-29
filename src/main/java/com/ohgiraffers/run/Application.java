package com.ohgiraffers.run;

import com.ohgiraffers.service.DigimonService;

import java.util.Scanner;

public class Application {

    private static final DigimonService ds = new DigimonService();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while (flag){
            System.out.println("========= 디지몬 선택하기 =========");
            System.out.print("0. 종료\n1. 코로몬\n2. 뿔몬\n3. 모티몬\n");
            System.out.print("디지몬을 선택해 주세요: ");
            int input = sc.nextInt();
            switch (input) {
                case 0:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                case 1:
                    ds.selectDigimon();
                    flag = false;
                    break;

                default:
                    System.out.println("번호를 제대로 다시 입력해주세요.");

            }
        }

        while (true) {
            System.out.println("========= 디지몬 키우기 =========");
            System.out.println("1. 디지몬 선택하기");
            System.out.println("2. 디지몬 선택하기");
            System.out.println("1. 디지몬 선택하기");
            System.out.println("1. 디지몬 선택하기");
            System.out.println("9. 프로그램 종료");
            System.out.print("메뉴를 선택해 주세요: ");

            int input = sc.nextInt();
            switch (input) {
                case 1:
                    ds.selectDigimon();
                    break;
                case 9:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("번호를 제대로 다시 입력해주세요.");

            }
        }
    }
}
