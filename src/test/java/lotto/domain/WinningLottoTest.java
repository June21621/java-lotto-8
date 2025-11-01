package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("WinningLotto 클래스 테스트")
class WinningLottoTest {

    // ==================== countMatches() 메서드 테스트 ====================

    @Test
    @DisplayName("모든 번호가 일치하면 6개 반환")
    void testCountMatches_AllMatch() {
        WinningLotto winning = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        assertThat(winning.countMatches(userLotto)).isEqualTo(6);
    }

    @Test
    @DisplayName("5개 번호가 일치")
    void testCountMatches_FiveMatch() {
        WinningLotto winning = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 4, 5, 10));

        assertThat(winning.countMatches(userLotto)).isEqualTo(5);
    }

    @Test
    @DisplayName("4개 번호가 일치")
    void testCountMatches_FourMatch() {
        WinningLotto winning = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 4, 10, 11));

        assertThat(winning.countMatches(userLotto)).isEqualTo(4);
    }

    @Test
    @DisplayName("3개 번호가 일치")
    void testCountMatches_ThreeMatch() {
        WinningLotto winning = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 10, 11, 12));

    }
}