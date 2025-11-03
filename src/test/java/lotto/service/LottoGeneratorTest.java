package lotto.service;

import lotto.domain.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("LottoGenerator 클래스 테스트")
class LottoGeneratorTest {

    @Test
    @DisplayName("5,000원 입력 시 5개 로또 생성")
    void testGenerate_5000Won_ShouldReturn5Lottos() {
        List<Lotto> lottos = LottoGenerator.generate(5000);
        assertThat(lottos).hasSize(5);
    }

    @Test
    @DisplayName("8,000원 입력 시 8개 로또 생성")
    void testGenerate_8000Won_ShouldReturn8Lottos() {
        List<Lotto> lottos = LottoGenerator.generate(8000);
        assertThat(lottos).hasSize(8);
    }

    @Test
    @DisplayName("각 로또는 6개의 번호를 가짐")
    void testGenerate_EachLottoHasSixNumbers() {
        List<Lotto> lottos = LottoGenerator.generate(3000);
        for (Lotto lotto : lottos) {
            assertThat(lotto.getNumbers()).hasSize(6);
        }
    }

    @Test
    @DisplayName("모든 번호는 1~45 범위")
    void testGenerate_AllNumbersInRange() {
        List<Lotto> lottos = LottoGenerator.generate(1000);
        for (Lotto lotto : lottos) {
            for (int number : lotto.getNumbers()) {
                assertThat(number).isBetween(1, 45);
            }
        }
    }

    @Test
    @DisplayName("999원 입력 시 예외")
    void testGenerate_999Won_ShouldThrow() {
        assertThatThrownBy(() -> LottoGenerator.generate(999))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
    }

    @Test
    @DisplayName("음수 입력 시 예외")
    void testGenerate_NegativeAmount_ShouldThrow() {
        assertThatThrownBy(() -> LottoGenerator.generate(-1000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
    }
}
