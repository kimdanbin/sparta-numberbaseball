package lv1;

public class BaseballGameDisplay {
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
