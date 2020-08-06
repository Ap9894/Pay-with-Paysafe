package dev.abhi.roiim.Service;


import dev.abhi.roiim.DTO.*;

import dev.abhi.roiim.Repository.CustomerRespository;
import dev.abhi.roiim.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Random;

@Service
public class CustomerServiceImplementation implements CustomerService {

    // also need to check if the user details are already saved??
    // make a api call to the paysafe server
    // save the customerId received from the request in the DB
    // create a Single Use Token
    // return the token

    @Autowired
    CustomerRespository customerRespository;

    @Override
    public String createCustomerProfile(CreateProfileDto createProfileDto) {


        RestTemplate restTemplate = new RestTemplate();

        String encodedApiKey = "Basic " + "cHJpdmF0ZS03NzUxOkItcWEyLTAtNWYwMzFjZGQtMC0zMDJkMDIxNDQ5NmJlODQ3MzJhMDFmNjkwMjY4ZDNiOGViNzJlNWI4Y2NmOTRlMjIwMjE1MDA4NTkxMzExN2YyZTFhODUzMTUwNWVlOGNjZmM4ZTk4ZGYzY2YxNzQ4";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", encodedApiKey);
        headers.add("Simulator", "External");
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));


        Customer customer = customerRespository.findCustomerByEmail(createProfileDto.getEmail());

        if(customer == null) {



//        String apiKey = "private-7751:B-qa2-0-5f031cdd-0-302d0214496be84732a01f690268d3b8eb72e5b8ccf94e2202150085913117f2e1a8531505ee8c cfc8e98df3cf1748";
//        String encodedApiKey = "Basic "+ Base64.getEncoder().encodeToString(apiKey.getBytes());


            final String createCustomerUri = "https://api.test.paysafe.com/paymenthub/v1/customers";


            createCustomerProfileRequestResponseDto newCustomerDto = new createCustomerProfileRequestResponseDto();

            newCustomerDto.setFirstName(createProfileDto.getFirstName());
            newCustomerDto.setEmail(createProfileDto.getEmail());
            newCustomerDto.setPhone(createProfileDto.getPhone());
            newCustomerDto.setMerchantCustomerId(createProfileDto.getEmail() + gen());

            HttpEntity<createCustomerProfileRequestResponseDto> createCustomerRequestEntity = new HttpEntity<createCustomerProfileRequestResponseDto>(newCustomerDto, headers);
            ResponseEntity<createCustomerProfileRequestResponseDto> createCustomerResponseEntity = restTemplate.exchange(createCustomerUri, HttpMethod.POST, createCustomerRequestEntity, createCustomerProfileRequestResponseDto.class);

            createCustomerProfileRequestResponseDto customerResponse = createCustomerResponseEntity.getBody();

            Customer newCustomer = new Customer(
                    customerResponse.getEmail(),
                    customerResponse.getFirstName(),
                    customerResponse.getPhone(),
                    customerResponse.getId()
            );

            customerRespository.save(newCustomer);

        }

        customer = customerRespository.findCustomerByEmail(createProfileDto.getEmail());
        final String singleUseTokenUri = "https://api.test.paysafe.com/paymenthub/v1/customers/"+customer.getCustomerId()+"/singleusecustomertokens";

        SingleUseTokenRequestDto singleUseTokenRequestDto = new SingleUseTokenRequestDto();

        HttpEntity<SingleUseTokenRequestDto> singleUseTokenRequestEntity = new HttpEntity<>(singleUseTokenRequestDto,headers);
        ResponseEntity<SingleUseTokenResponseDto> singleUseTokenResponse = restTemplate.exchange(singleUseTokenUri, HttpMethod.POST, singleUseTokenRequestEntity, SingleUseTokenResponseDto.class);


        if(singleUseTokenResponse.getStatusCode().is2xxSuccessful())
            return singleUseTokenResponse.getBody().getSingleUseCustomerToken();

        return null;
    }

    @Override
    public ProcessPaymentResponseDto processPayment(ProcessPaymentRequestDto processPaymentRequestDto) {

        final String processPaymentUri="https://api.test.paysafe.com/paymenthub/v1/payments";

        String encodedApiKey = "Basic "+"cHJpdmF0ZS03NzUxOkItcWEyLTAtNWYwMzFjZGQtMC0zMDJkMDIxNDQ5NmJlODQ3MzJhMDFmNjkwMjY4ZDNiOGViNzJlNWI4Y2NmOTRlMjIwMjE1MDA4NTkxMzExN2YyZTFhODUzMTUwNWVlOGNjZmM4ZTk4ZGYzY2YxNzQ4";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",encodedApiKey);
        headers.add("Simulator","External");
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<ProcessPaymentRequestDto> processPaymentRequestEntity = new HttpEntity<>(processPaymentRequestDto,headers);
        ResponseEntity<ProcessPaymentResponseDto> processPaymentResponseEntity = restTemplate.exchange(processPaymentUri, HttpMethod.POST, processPaymentRequestEntity, ProcessPaymentResponseDto.class);

        ProcessPaymentResponseDto processPaymentResponse = processPaymentResponseEntity.getBody();
        if(processPaymentResponseEntity.getStatusCode().is2xxSuccessful())
            return processPaymentResponse;
        return null;

    }


    private int gen() {
        Random r = new Random( System.currentTimeMillis() );
        return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
    }
}
