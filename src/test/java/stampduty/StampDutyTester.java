package stampduty;

import org.assertj.core.data.Offset;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StampDutyTester {

    private static final Offset<Double> ALLOWED_DISCREPANCY = Offset.offset(0.001);

    @Test(expected = RuntimeException.class)
    public void testNegativeTaxValue() {
        final StampDutyCalculator calculator = new StampDutyCalculator();
        calculator.calculate(-1);
    }

    @Test
    public void testFirstBracket() {
        final StampDutyCalculator calculator = new StampDutyCalculator();

        assertThat(calculator.calculate(0))
                .isEqualTo(0.0, ALLOWED_DISCREPANCY);

        assertThat(calculator.calculate(125000))
                .isEqualTo(0.0, ALLOWED_DISCREPANCY);
    }

    @Test
    public void test_185000() {
        final StampDutyCalculator calculator = new StampDutyCalculator();
        final double tax = calculator.calculate(185000);
        assertThat(tax).isEqualTo(1200.0, ALLOWED_DISCREPANCY);
    }

    @Test
    public void test_2100000() {
        final StampDutyCalculator calculator = new StampDutyCalculator();
        final double tax = calculator.calculate(2100000);
        assertThat(tax).isEqualTo(165750.0, ALLOWED_DISCREPANCY);
    }

    @Test
    public void test_2000000() {
        final StampDutyCalculator calculator = new StampDutyCalculator();
        final double tax = calculator.calculate(2000000);
        assertThat(tax).isEqualTo(153750.0, ALLOWED_DISCREPANCY);
    }

    @Test
    public void test_400000() {
        final StampDutyCalculator calculator = new StampDutyCalculator();
        final double tax = calculator.calculate(400000);
        assertThat(tax).isEqualTo(10000.0, ALLOWED_DISCREPANCY);
    }

    @Test
    public void testMansion() {
        final StampDutyCalculator calculator = new StampDutyCalculator();
        final double tax = calculator.calculate(5000000);
        assertThat(tax).isEqualTo(513750.0, ALLOWED_DISCREPANCY);
    }

    @Test
    public void testCanCallMultipleTimes() {
        final StampDutyCalculator calculator = new StampDutyCalculator();

        final double tax = calculator.calculate(185000);
        assertThat(tax).isEqualTo(1200.0, ALLOWED_DISCREPANCY);

        final double tax2 = calculator.calculate(185000);
        assertThat(tax2).isEqualTo(1200.0, ALLOWED_DISCREPANCY);

        final double tax3 = calculator.calculate(2000000);
        assertThat(tax3).isEqualTo(153750.0, ALLOWED_DISCREPANCY);
    }
}
