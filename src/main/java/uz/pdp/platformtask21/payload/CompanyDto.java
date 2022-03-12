package uz.pdp.platformtask21.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CompanyDto {

    @NotNull
    private String corpName;

    @NotNull
    private String directorName;

    @NotNull
    private String street;

    private Integer homeNumber;

}
