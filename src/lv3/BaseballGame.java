package lv3;

import java.util.*;

public class BaseballGame {
    private ArrayList<Integer> answer;
    int strike = 0;
    int ball = 0;
    private int playTime = 0;
    private int gameCount = 0;
    private Map<Integer, Integer> gameRecord = new HashMap<>();

    // 1) 정답 숫자 생성하기
    public BaseballGame() {
        // 랜덤으로 정답 생성
        randomAnswer();
    }

    // 출력 개선 (lv2)
    public void startGame() {
        Scanner sc = new Scanner(System.in);
        boolean isContinue = true;
        // 1, 2, 3 이외의 입렵값에 대해서 오류 메시지 노출
        while (isContinue) {
            boolean isValidSelect = false;
            int select = 0;
            while (!isValidSelect) {
                try {
                    BaseballGameDisplay.displayStart();
                    select = sc.nextInt();
                    isValidSelect = true;
                } catch (InputMismatchException e) {
                    // 문자가 입력됐을 때
                    System.out.println("올바른 숫자를 입력해주세요!\n");
                    sc.nextLine();
                }
            }
            switch (select) {
                case 1:
                    BaseballGameDisplay.displayGameStart();
                    play();
                    break;
                case 2:
                    // 게임 기록 통계 나타나는 부분 (lv3)
                    BaseballGameDisplay display = new BaseballGameDisplay();
                    display.showGameRecord(gameRecord);
                    break;
                case 3:
                    isContinue = false;
                    // 게임 기록 초기화
                    gameCount = 0;
                    gameRecord.clear();
                    System.out.println("< 숫자 야구 게임을 종료합니다 >\n");
                    break;
                default:
                    // 1, 2, 3 이외의 숫자가 들어왔을 때
                    System.out.println("올바른 숫자를 입력해주세요!\n");
                    break;
            }
        }
    }

    // 게임 시작 메서드
    public int play() {
        // 플레이마다 정답이 랜덤으로 변경됨
        randomAnswer();
        playTime = 0;
        gameCount++;

        // 4) 정답을 맞출 때까지 게임 이어서 하기
        while (true) {
            Scanner sc = new Scanner(System.in);

            // 한번 입력받고 나면 strike와 ball 수 초기화
            strike = 0;
            ball = 0;

            // 2) 정답을 맞추기 위해 숫자를 입력하기
            String str = sc.nextLine();

            // 입력값이 유효한지 검사하기
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
                gameRecord.put(gameCount, playTime);
                break;
            }
        }
        return playTime;
    }

    // 입력값이 유효한지 검사하는 메서드 (lv2)
    protected boolean validateInput(String input) {
        try {
            // 입력값에 숫자만 포함되어 있는지 검사합니다.
            Integer.parseInt(input);
            // 3자리 수인지 자릿수를 검사합니다.
            if (input.length() != 3) {
                System.out.println("올바르지 않은 입력값입니다");
                return false;
            }
            // 0이 있는지 체크
            if (input.contains("0")) {
                System.out.println("올바르지 않은 입력값입니다");
                return false;
            }
            // 중복된 숫자가 없는지 중복 숫자를 검사합니다.
            if (input.chars().distinct().count() < input.length()) {
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

    // 랜덤으로 정답을 만드는 메서드
    private void randomAnswer() {
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
}
