package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    // TODO: 추가 기능 구현에 따른 테스트 코드 작성
    @Test
    @DisplayName("로또 번호가 1보다 작으면 예외")
    void testLottoNumbersRange_OutOfLower_ShouldThrow() {
        assertThatThrownBy(() -> new Lotto(List.of(0, 1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
    }

    @Test
    @DisplayName("로또 번호가 45보다 크면 예외")
    void testLottoNumbersRange_OutOfUpper_ShouldThrow() {
        assertThatThrownBy(() -> new Lotto(List.of(41, 42, 43, 44, 45, 46)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
    }

    @Test
    @DisplayName("로또 번호가 1이면 통과")
    void testLottoNumbersRange_MinValid() {
        assertThatCode(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6)))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("로또 번호가 45이면 통과")
    void testLottoNumbersRange_MaxValid() {
        assertThatCode(() -> new Lotto(List.of(40, 41, 42, 43, 44, 45)))
                .doesNotThrowAnyException();
    }

    // -------- 중복 검증 테스트 --------

    @Test
    @DisplayName("로또 번호에 중복이 있으면 예외")
    void testLottoNumbersDuplicate_ShouldThrow() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 1, 3, 4, 5, 6)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
    }

    @Test
    @DisplayName("로또 번호가 모두 다르면 통과")
    void testLottoNumbersNoDuplicate() {
        assertThatCode(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6)))
                .doesNotThrowAnyException();
    }

    // -------- contains() 메서드 테스트 --------

    @Test
    @DisplayName("로또에 특정 숫자가 포함되어 있으면 true 반환")
    void testContains_True() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        assertThat(lotto.contains(3)).isTrue();
    }

    @Test
    @DisplayName("로또에 특정 숫자가 포함되지 않으면 false 반환")
    void testContains_False() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        assertThat(lotto.contains(7)).isFalse();
    }

    @Test
    @DisplayName("로또의 첫 번째 숫자 포함 확인")
    void testContains_FirstNumber() {
        Lotto lotto = new Lotto(List.of(10, 20, 30, 40, 1, 2));
        assertThat(lotto.contains(10)).isTrue();
    }

    @Test
    @DisplayName("로또의 마지막 번호 포함 확인")
    void testContains_LastNumber() {
        Lotto lotto = new Lotto(List.of(10, 20, 30, 40, 1, 45));
        assertThat(lotto.contains(45)).isTrue();
    }

    // -------- getNumbers() 메서드 테스트 --------

    @Test
    @DisplayName("로또 번호 리스트 반환")
    void testGetNumbers() {
        List<Integer> input = List.of(6, 5, 4, 3, 2, 1);
        Lotto lotto = new Lotto(input);
        assertThat(lotto.getNumbers()).containsExactly(6, 5, 4, 3, 2, 1);
    }

    @Test
    @DisplayName("getNumbers()가 6개 반환")
    void testGetNumbers_Size() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        assertThat(lotto.getNumbers()).hasSize(6);
    }

    @Test
    @DisplayName("getNumbers()가 원본 리스트와 같은 순서 유지")
    void testGetNumbers_Order() {
        List<Integer> numbers = List.of(45, 44, 43, 42, 41, 40);
        Lotto lotto = new Lotto(numbers);
        assertThat(lotto.getNumbers()).containsExactly(45, 44, 43, 42, 41, 40);
    }

    // -------- 복합 테스트 --------

    @Test
    @DisplayName("범위 벗어남 + 중복")
    void testLottoNumbers_MultipleErrors() {
        assertThatThrownBy(() -> new Lotto(List.of(0, 1, 1, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
    }

    @Test
    @DisplayName("정상적인 로또 생성 후 여러 메서드 사용")
    void testLottoNormalCase() {
        Lotto lotto = new Lotto(List.of(1, 10, 20, 30, 40, 45));

        assertThat(lotto.contains(1)).isTrue();
        assertThat(lotto.contains(10)).isTrue();
        assertThat(lotto.contains(50)).isFalse();
        assertThat(lotto.getNumbers()).hasSize(6);
    }
}
