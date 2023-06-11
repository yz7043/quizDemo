package com.andy.project1.domain.admin;

import com.andy.project1.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class AdminUser {
    private Integer user_id;
    private String firstname;
    private String lastname;
    private String email;
    private Boolean is_active;
    private Boolean is_admin;

    public AdminUser(User user){
        this.user_id = new Integer(user.getUser_id());
        this.firstname = new String(user.getFirstname());
        this.lastname = new String(user.getLastname());
        this.email =new String(user.getEmail());
        this.is_active = new Boolean(user.getIs_active());
        this.is_admin = new Boolean(user.getIs_admin());
    }
}
