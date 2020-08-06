package dev.abhi.roiim.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GatewayResponse {

    private String authCode;

    private String avsResponse;

    private String cvvVerification;

}
