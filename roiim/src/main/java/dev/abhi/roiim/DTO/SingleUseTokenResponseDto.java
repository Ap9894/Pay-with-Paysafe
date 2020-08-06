package dev.abhi.roiim.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SingleUseTokenResponseDto {

    private String id;

    private String customerId;

    private Long timeToLiveSeconds;

    private String status;

    private String singleUseCustomerToken;

    private List<String> paymentTypes;

    private String locale;

    private String firstName;

    private String email;

    public SingleUseTokenResponseDto() {}
}
