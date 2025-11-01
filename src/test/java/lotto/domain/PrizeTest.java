package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class PrizeTest {
    @Test
    void testFirstPrizeInfo() {
        Prize first = Prize.FIRST;
        assertThat(first.getMatchCount()).isEqualTo(6);
        assertThat(first.getPrizeMoney()).isEqualTo(2_000_000_000L);
    }

    @Test
    void testSecondPrizeInfo() {
        Prize second = Prize.SECOND;
        assertThat(second.getMatchCount()).isEqualTo(5);
        assertThat(second.getPrizeMoney()).isEqualTo(30_000_000L);
    }

    @Test
    void testThirdPrizeInfo() {
        Prize third = Prize.THIRD;
        assertThat(third.getMatchCount()).isEqualTo(5);
        assertThat(third.getPrizeMoney()).isEqualTo(1_500_000L);
    }

    @Test
    void testFourthPrizeInfo() {
        Prize fourth = Prize.FOURTH;
        assertThat(fourth.getMatchCount()).isEqualTo(4);
        assertThat(fourth.getPrizeMoney()).isEqualTo(50_000L);
    }

    @Test
    void testFifthPrizeInfo() {
        Prize fifth = Prize.FIFTH;
        assertThat(fifth.getMatchCount()).isEqualTo(3);
        assertThat(fifth.getPrizeMoney()).isEqualTo(5_000L);
    }

    @Test
    void testNonePrizeInfo() {
        Prize NONE = Prize.NONE;
        assertThat(NONE.getMatchCount()).isEqualTo(0);
        assertThat(NONE.getPrizeMoney()).isEqualTo(0);
    }
}
