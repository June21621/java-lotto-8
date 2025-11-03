package lotto.domain;

public class LottoResult {
    private final int matchCount;
    private final boolean bonusMatch;

    public LottoResult(int matchCount, boolean bonusMatch) {
        this.matchCount = matchCount;
        this.bonusMatch = bonusMatch;
    }

    public Prize getPrize() {
        return Prize.getPrizeBy(matchCount, bonusMatch);
    }

    public long getPrizeMoney() {
        return getPrize().getPrizeMoney();
    }
}
