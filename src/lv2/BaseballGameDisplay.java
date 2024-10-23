package lv2;

public class BaseballGameDisplay {
    // 처음 시작할 때 출력되는 메서드
    public static void displayStart() {
        System.out.println("환영합니다! 원하시는 번호를 입력해주세요");
        System.out.println("1. 게임 시작하기 2. 게임 기록 보기 3. 종료하기");
    }

    // 게임이 시작될 때 출력되는 메서드
    public static void displayGameStart() {
        System.out.println("< 게임을 시작합니다 >");
        System.out.println("숫자를 입력하세요");
    }

    // strike, ball 개수 출력하는 메서드
    public void displayHint(int strike, int ball) {
        if (strike == 3) {
            System.out.println("정답입니다!");
        } else if (strike == 0 && ball == 0) {
            System.out.println("아웃");
        } else {
            System.out.println(strike + "스트라이크 " + ball + "볼");
        }
    }
}
