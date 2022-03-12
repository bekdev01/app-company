package uz.pdp.platformtask21.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class WorkerDto {

    @NotNull
    private String name;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String street;

    @NotNull
    private Integer homeNumber;

    @NotNull
    private Long departmentId;
}
