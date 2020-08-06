package dev.abhi.roiim.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;


@Getter
@Setter
public class CreateProfileDto {
//    private Long Id;

    @NotEmpty
    private  String email;

    @NotEmpty
    private String firstName;

    private String phone;

}
