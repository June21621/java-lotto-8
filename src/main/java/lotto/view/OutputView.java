package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.Prize;
import lotto.service.LottoStatistics;

import java.util.List;

public class OutputView {

    // 구매한 로또 수량과 번호를 출력 (번호 오름차순으로 출력)
    public static void printPurchaseResult(int count, List<Lotto> lottos) {
        System.out.println(count + "개를 구매했습니다.");
        for (Lotto lotto : lottos) {
            List<Integer> sortedNumbers = lotto.getNumbers().stream()
                    .sorted()
                    .toList();
            System.out.println(sortedNumbers);
        }
    }

    // 당첨 통계와 수익률 출력
    public static void printStatistics(LottoStatistics statistics) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");

        printPrizeCount(statistics, Prize.FIFTH);
        printPrizeCount(statistics, Prize.FOURTH);
        printPrizeCount(statistics, Prize.THIRD);
        printPrizeCountBonus(statistics, Prize.SECOND);
        printPrizeCount(statistics, Prize.FIRST);

        printReturnRate(statistics);
    }

    // 일반 당첨 등급 출력
    private static void printPrizeCount(LottoStatistics statistics, Prize prize) {
        int count = statistics.getCountByPrize(prize);
        System.out.println(prize.getMatchCount() + "개 일치 ("
                + formatPrizeMoney(prize.getPrizeMoney())
                + "원) - " + count + "개");
    }

    // 2등 (보너스 포함) 출력용 메서드
    private static void printPrizeCountBonus(LottoStatistics statistics, Prize prize) {
        int count = statistics.getCountByPrize(prize);
        System.out.println(prize.getMatchCount() + "개 일치, 보너스 볼 일치 ("
                + formatPrizeMoney(prize.getPrizeMoney())
                + "원) - " + count + "개");
    }

    // 수익률 출력
    private static void printReturnRate(LottoStatistics statistics) {
        double rate = statistics.getReturnRate();
        System.out.printf("총 수익률은 %.1f%%입니다.%n", rate);
    }

    // 에러 메시지 출력
    public static void printErrorMessage(String message) {
        System.out.println(message);
    }

    // 상금 금액을 3자리마다 콤마 찍어서 출력용 문자열로 변환
    private static String formatPrizeMoney(long money) {
        return String.format("%,d", money);
    }
}
