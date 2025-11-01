package lotto.domain;

public enum Prize {
    FIRST(6, 2_000_000_000L, false),
    SECOND(5, 30_000_000L, true),
    THIRD(5, 1_500_000L, false),
    FOURTH(4, 50_000L, false),
    FIFTH(3, 5_000L, false),
    NONE(0, 0L, false);

    private final int matchCount;
    private final long prizeMoney;
    private final boolean hasBonus;

    Prize(int matchCount, long prizeMoney, boolean hasBonus) {
        this.matchCount = matchCount;
        this.prizeMoney = prizeMoney;
        this.hasBonus = hasBonus;
    }

    public int getMatchCount() {
        return matchCount;
    }
    public long getPrizeMoney() {
        return prizeMoney;
    }
    public boolean hasBonus() {
        return hasBonus;
    }

    public static Prize getPrizeBy(int matchCount, boolean bonusMatch) {
        if (matchCount == 6) return FIRST;
        if (matchCount == 5 && bonusMatch)  return SECOND;
        if (matchCount == 5)  return THIRD;
        if (matchCount == 4)  return FOURTH;
        if (matchCount == 3)  return FIFTH;
        return NONE;
    }
}
