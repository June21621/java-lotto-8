package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("LottoResult 클래스 테스트")
class LottoResultTest {

    @Test
    @DisplayName("6개 일치 시 1등")
    void testGetPrize_SixMatch_ShouldReturnFirst() {
        LottoResult result = new LottoResult(6, false);
        assertThat(result.getPrize()).isEqualTo(Prize.FIRST);
    }

    @Test
    @DisplayName("5개 + 보너스 시 2등")
    void testGetPrize_FiveWithBonus_ShouldReturnSecond() {
        LottoResult result = new LottoResult(5, true);
        assertThat(result.getPrize()).isEqualTo(Prize.SECOND);
    }

    @Test
    @DisplayName("5개 (보너스 없음) 시 3등")
    void testGetPrize_FiveWithoutBonus_ShouldReturnThird() {
        LottoResult result = new LottoResult(5, false);
        assertThat(result.getPrize()).isEqualTo(Prize.THIRD);
    }

    @Test
    @DisplayName("4개 일치 시 4등")
    void testGetPrize_FourMatch_ShouldReturnFourth() {
        LottoResult result = new LottoResult(4, false);
        assertThat(result.getPrize()).isEqualTo(Prize.FOURTH);
    }

    @Test
    @DisplayName("3개 일치 시 5등")
    void testGetPrize_ThreeMatch_ShouldReturnFifth() {
        LottoResult result = new LottoResult(3, false);
        assertThat(result.getPrize()).isEqualTo(Prize.FIFTH);
    }

    @Test
    @DisplayName("2개 이하 일치 시 당첨 없음")
    void testGetPrize_TwoMatch_ShouldReturnNone() {
        LottoResult result = new LottoResult(2, false);
        assertThat(result.getPrize()).isEqualTo(Prize.NONE);
    }

    @Test
    @DisplayName("3개 일치 시 5,000원 반환")
    void testGetPrizeMoney_ThreeMatch() {
        LottoResult result = new LottoResult(3, false);
        assertThat(result.getPrizeMoney()).isEqualTo(5_000L);
    }

    @Test
    @DisplayName("6개 일치 시 20억 반환")
    void testGetPrizeMoney_FirstPrize() {
        LottoResult result = new LottoResult(6, false);
        assertThat(result.getPrizeMoney()).isEqualTo(2_000_000_000L);
    }
}
