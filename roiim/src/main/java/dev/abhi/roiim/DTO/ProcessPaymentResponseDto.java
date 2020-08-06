package dev.abhi.roiim.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProcessPaymentResponseDto {

    private String id;

    private String merchantRefNum;

    private Long amount;

    private String currencyCode;

    private boolean dupCheck;

    private boolean settleWithAuth;

    private String txnTime;

    private String paymentType;

    private String status;

    private Long availableToSettle;

    private GatewayResponse gatewayResponse;

    private String paymentHandleToken;

    private String customerIp;

    private String description;

}
