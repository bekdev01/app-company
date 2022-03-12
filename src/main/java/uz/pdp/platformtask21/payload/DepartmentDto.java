package uz.pdp.platformtask21.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DepartmentDto {

    @NotNull
    private String name;

    @NotNull
    private Long companyId;
}
