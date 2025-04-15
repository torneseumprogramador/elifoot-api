package dev.java10x.elifoot.controller.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResponse {

    private Long id;
    private String name;
    private String email;
    private List<String> scopes;
}
