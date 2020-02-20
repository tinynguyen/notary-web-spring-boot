package com.congdat.notaryweb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;


@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document(collection = "users")
public class User extends AbstractModel {

    public User() {
        this.avatarName = "placeholder.jpg";
    }

    @Size(max = 50, message = "{user.fullName.size}")
    @NotEmpty(message = "{user.fullName.notempty}")
    private String fullName;

    @NotEmpty(message = "{user.username.notempty}")
    @Size(min = 6, max = 50, message = "{user.username.size}")
    private String username;

    @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$!^&*()%]).{6,100})",
            message = "{user.password.pattern}")
    @NotEmpty(message = "{user.password.notempty}")
    private String password;

    @Pattern(regexp = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
            message = "{user.email.pattern}")
    @NotEmpty(message = "{user.email.notempty}")
    private String email;

//    @Pattern(regexp = "^\\+(?:[0-9] ?){6,16}[0-9]$", message = "{user.phone.pattern}")
    private String phone;
    private int enabled;
    private String avatarPath;
    private String avatarName;
    private List<String> roles;
}
