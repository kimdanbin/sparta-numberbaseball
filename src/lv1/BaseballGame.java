package lv1;

import java.util.*;

public class BaseballGame {
    private ArrayList<Integer> answer;
    int strike = 0;
    int ball = 0;
    private int playTime = 0;

    // 1) 정답 숫자 생성하기
    public BaseballGame() {
        // hashSet으로 중복 없이 정답 받기
        HashSet<Integer> randomNums = new HashSet<>();
        // random으로 숫자 뽑기
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());

        // 숫자 3개 받을 때까지 반복
        while (randomNums.size() != 3) {
            randomNums.add(rand.nextInt(9) + 1);
        }

        // set에서 arraylist로 변경
        answer = new ArrayList<>(randomNums);

        // shuffle함수로 숫자 순서 바꾸기
        Collections.shuffle(answer);
        System.out.println(answer.toString());
    }

    // 게임 시작 메서드
    public int play() {

        // 4) 정답을 맞출 때까지 게임 이어서 하기
        while (true) {
            Scanner sc = new Scanner(System.in);

            // 한번 입력받고 나면 strike와 ball 수 초기화
            strike = 0;
            ball = 0;

            // 2) 정답을 맞추기 위해 숫자를 입력하기
            String str = sc.nextLine();

            // 올바른 입력값을 받았는지 검증
            while (!validateInput(str)) {
                str = sc.nextLine();
            }

            // 게임 진행횟수 증가
            playTime++;

            // 스트라이크, 볼 개수 계산
            strike = countStrike(str);
            ball = countBall(str);

            // 3) 결과값 출력 및 게임 로직 적용하기
            BaseballGameDisplay hint = new BaseballGameDisplay();
            hint.displayHint(strike, ball);
            // 정답여부 확인, 만약 정답이면 break 를 이요해 반복문 탈출
            if (strike == answer.size()) {
                break;
            }
        }
        return playTime;
    }

    // 입력값이 유효한지 검사하는 메서드
    protected boolean validateInput(String input) {
        try {
            // 숫자인지 문자인지 체크
            Integer.parseInt(input);
            // 3자리인지 아닌지 체크
            if (input.length() != 3) {
                System.out.println("올바르지 않은 입력값입니다");
                return false;
            }
            // 0이 있는지 체크
            if (input.contains("0")) {
                System.out.println("올바르지 않은 입력값입니다");
                return false;
            }
            // 중복이 있는지 체크
            if(input.chars().distinct().count() < input.length()) {
                System.out.println("올바르지 않은 입력값입니다");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            System.out.println("올바르지 않은 입력값입니다");
            return false;
        }
    }

    // strike 수를 세는 메서드
    private int countStrike(String input) {
        for (int i = 0; i < answer.size(); i++) {
            if (input.contains(answer.get(i).toString())) {
                if (input.charAt(i) == answer.get(i).toString().charAt(0)) {
                    strike++;
                }
            }
        }
        return strike;
    }

    // ball 수를 세는 메서드
    private int countBall(String input) {
        for (int i = 0; i < answer.size(); i++) {
            if (input.contains(answer.get(i).toString())) {
                if (input.charAt(i) != answer.get(i).toString().charAt(0)) {
                    ball++;
                }
            }
        }
        return ball;
    }
}
