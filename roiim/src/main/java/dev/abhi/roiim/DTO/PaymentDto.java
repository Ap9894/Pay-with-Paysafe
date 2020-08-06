package dev.abhi.roiim.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentDto {

    private String paymentHandleToken;

    private Long amount;


}
