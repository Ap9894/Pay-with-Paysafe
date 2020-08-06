package dev.abhi.roiim.controller;

import dev.abhi.roiim.DTO.*;
import dev.abhi.roiim.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class Controller {

    @Autowired
    CustomerService customerService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/createToken")
    public ResponseDto<String> createCustomerProfile(@RequestBody CreateProfileDto createProfileDto){

        String token = customerService.createCustomerProfile(createProfileDto);

        if(token != null)
            return new ResponseDto(token, HttpStatus.OK);

        return new ResponseDto<>("error occured", HttpStatus.OK);

    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/payment")
    public ResponseDto<String> processPayment(@RequestBody PaymentDto paymentDto){


        ProcessPaymentRequestDto processPaymentRequestDto = new ProcessPaymentRequestDto(paymentDto);
        ProcessPaymentResponseDto processPaymentResponseDto = customerService.processPayment(processPaymentRequestDto);
        if(processPaymentResponseDto != null)
            return new ResponseDto<>("Completed", HttpStatus.OK);
        return new ResponseDto<>("error processing payment", HttpStatus.OK);
    }

}
