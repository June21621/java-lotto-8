package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.Lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoGenerator {
    private static final int LOTTO_PRICE = 1000;
    private static final int LOTTO_SIZE = 6;
    private static final int MIN = 1;
    private static final int MAX = 45;

    public static List<Lotto> generate(int purchaseAmount) {
        validatePurchaseAmount(purchaseAmount);
        int count = purchaseAmount / LOTTO_PRICE;
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(createLotto());
        }
        return lottos;
    }

    private static void validatePurchaseAmount(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("[ERROR] 로또 구입 금액은 0보다 커야 합니다.");
        }
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 로또 구입 금액은 1,000원 단위여야 합니다.");
        }
    }

    private static Lotto createLotto() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(MIN, MAX, LOTTO_SIZE);
        return new Lotto(numbers);
    }
}
