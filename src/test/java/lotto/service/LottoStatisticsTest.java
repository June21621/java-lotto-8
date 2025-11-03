package lotto.service;

import lotto.domain.LottoResult;
import lotto.domain.Prize;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("LottoStatistics 클래스 테스트")
class LottoStatisticsTest {

    @Test
    @DisplayName("1등 당첨 개수 반환")
    void testGetCountByPrize_FirstPrize() {
        List<LottoResult> results = List.of(
                new LottoResult(6, false),
                new LottoResult(6, false)
        );
        LottoStatistics statistics = new LottoStatistics(results, 2000);
        assertThat(statistics.getCountByPrize(Prize.FIRST)).isEqualTo(2);
    }

    @Test
    @DisplayName("다양한 등급 당첨 개수 반환")
    void testGetCountByPrize_Mixed() {
        List<LottoResult> results = List.of(
                new LottoResult(6, false),     // 1등
                new LottoResult(5, false),     // 3등
                new LottoResult(3, false)      // 5등
        );
        LottoStatistics statistics = new LottoStatistics(results, 3000);
        assertThat(statistics.getCountByPrize(Prize.FIRST)).isEqualTo(1);
        assertThat(statistics.getCountByPrize(Prize.THIRD)).isEqualTo(1);
        assertThat(statistics.getCountByPrize(Prize.FIFTH)).isEqualTo(1);
    }

    @Test
    @DisplayName("총 상금 계산")
    void testGetTotalPrizeMoney() {
        List<LottoResult> results = List.of(
                new LottoResult(3, false),     // 5,000원
                new LottoResult(4, false)      // 50,000원
        );
        LottoStatistics statistics = new LottoStatistics(results, 2000);
        assertThat(statistics.getTotalPrizeMoney()).isEqualTo(55_000L);
    }

    @Test
    @DisplayName("수익률 계산 - 수익 발생")
    void testGetReturnRate_Profit() {
        List<LottoResult> results = List.of(new LottoResult(3, false));
        LottoStatistics statistics = new LottoStatistics(results, 1000);
        // 총 상금 5,000 / 1,000 * 100 = 500%
        assertThat(statistics.getReturnRate()).isEqualTo(500.0);
    }

    @Test
    @DisplayName("수익률 계산 - 손실 발생")
    void testGetReturnRate_Loss() {
        List<LottoResult> results = List.of(new LottoResult(2, false)); // 당첨 없음
        LottoStatistics statistics = new LottoStatistics(results, 1000);
        // 0 / 1000 * 100 = 0%
        assertThat(statistics.getReturnRate()).isEqualTo(0.0);
    }

    @Test
    @DisplayName("수익률 소수점 둘째 자리에서 반올림")
    void testGetReturnRate_RoundedToFirstDecimal() {
        List<LottoResult> results = List.of(new LottoResult(3, false));
        LottoStatistics statistics = new LottoStatistics(results, 8000);
        double expected = Math.round((5000.0 / 8000) * 100 * 10.0) / 10.0; // 62.5%
        assertThat(statistics.getReturnRate()).isEqualTo(expected);
    }

    @Test
    @DisplayName("초대형 상금일 경우 수익률 계산")
    void testGetReturnRate_LargeProfit() {
        List<LottoResult> results = List.of(new LottoResult(6, false));
        LottoStatistics statistics = new LottoStatistics(results, 1000);
        // 2,000,000,000 / 1,000 * 100 = 1,000,000,000%
        assertThat(statistics.getReturnRate()).isEqualTo(1_000_000_000.0);
    }
}
