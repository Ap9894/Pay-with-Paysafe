package dev.abhi.roiim.DTO;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
@NoArgsConstructor
public class ProcessPaymentRequestDto {

    private String merchantRefNum;

    private Long amount;

    private String currencyCode;

    private boolean dupCheck;

    private boolean settleWithAuth;

    private String paymentHandleToken;

    private String customerIp;

    private String description;

    public ProcessPaymentRequestDto(PaymentDto paymentDto) {
        this.merchantRefNum = "merchantRefNum"+gen();
        this.amount = paymentDto.getAmount();
        this.currencyCode = "USD";
        this.dupCheck = true;
        this.settleWithAuth = false;
        this.paymentHandleToken = paymentDto.getPaymentHandleToken();
        this.customerIp = "10.10.12.64";
        this.description = "Magazine subscription";
    }

    private int gen() {
        Random r = new Random( System.currentTimeMillis() );
        return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
    }
}
