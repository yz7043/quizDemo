package com.andy.project1.domain.util;

import com.andy.project1.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class LoggedUser {
    private String email;
    private String firstname;
    private String lastname;
    private Boolean is_admin;
    public LoggedUser(User user){
        if(user == null)
            return;
        this.email = user.getEmail();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.is_admin = user.getIs_admin();
    }
}
