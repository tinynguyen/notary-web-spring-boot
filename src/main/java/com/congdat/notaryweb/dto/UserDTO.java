package com.congdat.notaryweb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends AbstractDTO {

    private String fullName;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String avatar;

}


