package lotto;

import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.WinningLotto;
import lotto.service.LottoGameService;
import lotto.service.LottoGenerator;
import lotto.service.LottoStatistics;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        int purchaseAmount = getPurchaseAmountWithRetry();

        List<Lotto> userLottos = LottoGenerator.generate(purchaseAmount);
        OutputView.printPurchaseResult(userLottos.size(), userLottos);

        List<Integer> winningNumbers = getWinningNumbersWithRetry();
        int bonusNumber = getBonusNumberWithRetry(winningNumbers);

        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);
        LottoGameService gameService = new LottoGameService(userLottos, winningLotto);
        List<LottoResult> results = gameService.play();

        LottoStatistics statistics = new LottoStatistics(results, purchaseAmount);
        OutputView.printStatistics(statistics);
    }

    private static int getPurchaseAmountWithRetry() {
        while (true) {
            try {
                return InputView.getPurchaseAmount();
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage("[ERROR] 구입 금액은 1,000원 단위의 양수여야 합니다.");
            }
        }
    }

    private static List<Integer> getWinningNumbersWithRetry() {
        while (true) {
            try {
                return InputView.getWinningNumbers();
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage("[ERROR] 당첨 번호는 1부터 45 사이의 쉼표로 구분된 6개 숫자여야 합니다.");
            }
        }
    }

    private static int getBonusNumberWithRetry(List<Integer> winningNumbers) {
        while (true) {
            try {
                int bonusNumber = InputView.getBonusNumber();
                validateBonusNumber(bonusNumber, winningNumbers);
                return bonusNumber;
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage("[ERROR] 보너스 번호는 1부터 45 사이의 숫자이며, 당첨 번호에 포함되지 않아야 합니다.");
            }
        }
    }

    private static void validateBonusNumber(int bonusNumber, List<Integer> winningNumbers) {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1부터 45 사이여야 합니다.");
        }
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호에 포함될 수 없습니다.");
        }
    }
}
