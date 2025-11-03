package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Prize;
import lotto.domain.WinningLotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("LottoGameService 테스트")
class LottoGameServiceTest {

    @Test
    @DisplayName("모든 로또가 1등일 경우")
    void testPlay_AllFirstPrize() {
        List<Lotto> userLottos = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(1, 2, 3, 4, 5, 6))
        );
        WinningLotto winning = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);

        LottoGameService gameService = new LottoGameService(userLottos, winning);
        List<LottoResult> results = gameService.play();

        assertThat(results).hasSize(userLottos.size());
        for (LottoResult result : results) {
            assertThat(result.getPrize()).isEqualTo(Prize.FIRST);
        }
    }

    @Test
    @DisplayName("혼합된 당첨 결과")
    void testPlay_Mixed() {
        List<Lotto> userLottos = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),      // 1등
                new Lotto(List.of(1, 2, 3, 4, 5, 10)),     // 3등
                new Lotto(List.of(10, 11, 12, 13, 14, 15)) // 당첨 없음
        );
        WinningLotto winning = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);

        LottoGameService gameService = new LottoGameService(userLottos, winning);
        List<LottoResult> results = gameService.play();

        assertThat(results).hasSize(userLottos.size());
        assertThat(results.get(0).getPrize()).isEqualTo(Prize.FIRST);
        assertThat(results.get(1).getPrize()).isEqualTo(Prize.THIRD);
        assertThat(results.get(2).getPrize()).isEqualTo(Prize.NONE);
    }

    @Test
    @DisplayName("모든 로또가 당첨되지 않을 경우")
    void testPlay_NoWin() {
        List<Lotto> userLottos = List.of(
                new Lotto(List.of(10, 11, 12, 13, 14, 15)),
                new Lotto(List.of(20, 21, 22, 23, 24, 25))
        );
        WinningLotto winning = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);

        LottoGameService gameService = new LottoGameService(userLottos, winning);
        List<LottoResult> results = gameService.play();

        for (LottoResult result : results) {
            assertThat(result.getPrize()).isEqualTo(Prize.NONE);
        }
    }
}
