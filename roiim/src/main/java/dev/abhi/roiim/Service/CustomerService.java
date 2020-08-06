package dev.abhi.roiim.Service;

import dev.abhi.roiim.DTO.CreateProfileDto;
import dev.abhi.roiim.DTO.ProcessPaymentRequestDto;
import dev.abhi.roiim.DTO.ProcessPaymentResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {

    String createCustomerProfile(CreateProfileDto createProfileDto);

    ProcessPaymentResponseDto processPayment(ProcessPaymentRequestDto processPaymentRequestDto);
}
