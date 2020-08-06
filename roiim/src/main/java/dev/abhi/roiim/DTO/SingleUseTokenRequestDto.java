package dev.abhi.roiim.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
public class SingleUseTokenRequestDto {

    private String merchantRefNum;

    private String paymentTypes[] = {"CARD"};

    public SingleUseTokenRequestDto() {
        this.merchantRefNum = "Ref"+gen();
    }

    private int gen() {
        Random r = new Random( System.currentTimeMillis() );
        return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
    }
}
