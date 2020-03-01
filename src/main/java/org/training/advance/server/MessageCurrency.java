package org.training.advance.server;

import java.math.BigDecimal;

public class MessageCurrency {

    private String quantity;

    public MessageCurrency(String quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getQuantity() {
        return new BigDecimal(quantity);
    }
}
