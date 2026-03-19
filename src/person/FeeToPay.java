package person;

import java.math.BigDecimal;

public interface FeeToPay {
    BigDecimal getOutstandingFees();
    void setOutstandingFees(BigDecimal outstandingFees);
}