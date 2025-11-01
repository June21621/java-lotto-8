package lotto.domain;

import java.util.List;

public class WinningLotto {
    private final Lotto lotto;
    private final int bonusNumber;

    public WinningLotto(List<Integer> numbers, int bonusNumber) {
        this.lotto = new Lotto(numbers);
        validateBonusNumber(bonusNumber, numbers);
        this.bonusNumber = bonusNumber;
    }

    // ==================== 검증 메서드 ====================

    private void validateBonusNumber(int bonusNumber, List<Integer> numbers) {
        validateBonusNumberRange(bonusNumber);
        validateBonusNotInWinningNumbers(bonusNumber, numbers);
    }

    private void validateBonusNumberRange(int bonusNumber) {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }

    private void validateBonusNotInWinningNumbers(int bonusNumber, List<Integer> numbers) {
        if (numbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호에 포함될 수 없습니다.");
        }
    }

    // ==================== 핵심 메서드 ====================

    /**
     * 사용자 로또와 당첨 번호의 일치 개수를 계산
     * @param userLotto 사용자의 로또
     * @return 일치하는 번호의 개수 (0~6)
     */
    public int countMatches(Lotto userLotto) {
        int count = 0;
        for (int number : userLotto.getNumbers()) {
            if (lotto.contains(number)) {
                count++;
            }
        }
        return count;
    }

    /**
     * 사용자 로또가 보너스 번호를 포함하는지 확인
     * @param userLotto 사용자의 로또
     * @return 보너스 번호를 포함하면 true, 아니면 false
     */
    public boolean hasBonusMatch(Lotto userLotto) {
        return userLotto.contains(bonusNumber);
    }
}
