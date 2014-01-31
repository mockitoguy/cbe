import java.math.BigDecimal;

public class Money {

    private BigDecimal value;
    private Currency currency = Currency.EUR;

    public Money(BigDecimal value) {
        this.value = value;
    }

    public Money(BigDecimal value, Currency currency) {
        this(value);
        this.currency = currency;
    }

    public enum Currency {EUR}
}
