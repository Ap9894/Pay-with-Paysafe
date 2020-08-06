package dev.abhi.roiim.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class createCustomerProfileRequestResponseDto {

    private  String id;

    private  String merchantCustomerId;

    private String email;

    private String firstName;

    private String phone;

    private String status;

    private String locale;

    private String paymentToken;

}
