package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.WinningLotto;

import java.util.ArrayList;
import java.util.List;

public class LottoGameService {
    private final List<Lotto> userLottos;
    private final WinningLotto winningLotto;

    public LottoGameService(List<Lotto> userLottos, WinningLotto winningLotto) {
        this.userLottos = userLottos;
        this.winningLotto = winningLotto;
    }

    public List<LottoResult> play() {
        List<LottoResult> results = new ArrayList<>();
        for (Lotto lotto : userLottos) {
            results.add(checkLotto(lotto));
        }
        return results;
    }

    private LottoResult checkLotto(Lotto lotto) {
        int matchCount = winningLotto.countMatches(lotto);
        boolean bonusMatch = winningLotto.hasBonusMatch(lotto);
        return new LottoResult(matchCount, bonusMatch);
    }
}
