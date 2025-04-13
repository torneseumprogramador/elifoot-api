package dev.java10x.elifoot.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StadiumResponse {

    private Long id;
    private String name;
    private String city;
    private Integer capacity;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String urlImg;
}
