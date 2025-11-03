package lotto.service;

import lotto.domain.LottoResult;
import lotto.domain.Prize;

import java.util.List;

public class LottoStatistics {
    private final List<LottoResult> results;
    private final int purchaseAmount;

    public LottoStatistics(List<LottoResult> results, int purchaseAmount) {
        this.results = results;
        this.purchaseAmount = purchaseAmount;
    }

    public int getCountByPrize(Prize prize) {
        return (int) results.stream()
                .filter(result -> result.getPrize() == prize)
                .count();
    }

    public long getTotalPrizeMoney() {
        return results.stream()
                .mapToLong(LottoResult::getPrizeMoney)
                .sum();
    }

    public double getReturnRate() {
        long totalPrizeMoney = getTotalPrizeMoney();
        double rate = (totalPrizeMoney / (double) purchaseAmount) * 100.0;
        return Math.round(rate * 10.0) / 10.0;
    }
}
