package lotto.domain;

public enum Prize {
    FIRST(6, 2000000000, "6개 일치"),            // 1등
    SECOND(5, 30000000, "5개 일치, 보너스 볼 일치"),      // 2등
    THIRD(5, 1500000, "5개 일치"),                // 3등
    FOURTH(4, 50000, "4개 일치"),                 // 4등
    FIFTH(3, 5000, "3개 일치"),                   // 5등
    NONE(0,0, "꽝");

    private final int matchCount;        // 등급 순위
    private final int prizeMoney; // 상금
    private final String description; // 등급 설명

    Prize(int matchCount, int prizeMoney, String description) {
        this.matchCount = matchCount;
        this.prizeMoney = prizeMoney;
        this.description = description;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        if (matchCount >= 3) {
            return description + " (" + prizeMoney + "원) - ";
        }
        return "";
    }
}
