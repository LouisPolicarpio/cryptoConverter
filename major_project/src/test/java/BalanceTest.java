
import model.balance.Balance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class BalanceTest {
    Balance balance;

    @BeforeEach
    void setup(){
        balance = new Balance(100);
    }

    @Test
    public void subtractBalanceByTestSuccess() {
        assertThat(balance.subtractBalanceBy(99),equalTo((float)1));
    }

    @Test
    public void subtractBalanceByTestSuccessBalanceEqualInput() {
        assertThat(balance.subtractBalanceBy(100),equalTo((float)0));
    }

    @Test
    public void subtractBalanceByTestSuccessFloatInput() {
        float res = 100 - (float) 99.9 ;
        assertThat(balance.subtractBalanceBy((float) 99.9),equalTo(res));
    }

    @Test
    public void subtractBalanceByTestSuccessMulti() {
        assertThat(balance.subtractBalanceBy(10),equalTo((float)90));
        assertThat(balance.subtractBalanceBy(10),equalTo((float)80));
        assertThat(balance.subtractBalanceBy(30),equalTo((float)50));

    }

    @Test
    public void subtractBalanceByTestErrorInputGraterThanBalance() {
        assertThrows(IllegalArgumentException.class, () -> {
            balance.subtractBalanceBy( 101);
        });
    }
}
