package com.ksulima.security.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by Krzysztof Sulima on 08.08.2017.
 */

@Getter
@Setter
public class UserCreateForm {

    @NotEmpty
    private String email = "";

    @NotEmpty
    private String password = "";

    @NotEmpty
    private String passwordRepeated = "";

    @NotNull
    private Role role = Role.USER;



}
