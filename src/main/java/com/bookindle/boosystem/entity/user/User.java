package com.bookindle.boosystem.entity.user;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(name = "user")
public class User implements UserDetails {
    //主键及自动增长
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//由数据库控制,auto是程序统一控制
    private long id;

    @NotEmpty(message = "昵称不能为空")
    @Size(min = 1, max = 20)
    @Column(nullable = false, unique = true)
    private String name;

    //@Column(nullable = false, unique = true)
    private String email;

   // @Column(nullable = false, unique = true)
    private String mobile;

    private String password;

    private String cnname;

    private boolean Enabled;

    @Column(nullable = true)
    private String validataCode;

    @Column(nullable = true)
    private Integer followSize = 0;//关注数

    private Integer fanSize = 0; //粉丝数

    @Transient
    private Integer isFriend = 0;//关系，0表示没有关系，2表示互相关注

    @Column(nullable = true)
    private Long createTime;

    @Column(nullable = true)
    private Long lastModifyTime;

    @Column(nullable = true)
    private String outDate;

    @Column(nullable = true)
    private String avatar;
    //private Collection<? extends GrantedAuthority> authorities;
    //多对多映射，用户角色
    @ManyToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private List<UserRole> roles;




    //获取当前用户实例的password
    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User() {
    }

    //获取当前用户实例的name
    @Override
    public String getUsername() {
        return this.name;
    }

    //帐号是否不过期，false则验证不通过
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //帐号是否不锁定，false则验证不通过
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //凭证是否不过期，false则验证不通过
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //该帐号是否启用，false则验证不通过
    @Override
    public boolean isEnabled() {
         return true;
       // return Enabled;
    }

    //根据自定义逻辑来返回用户权限，如果用户权限返回空或者和拦截路径对应权限不同，验证不通过
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<UserRole> roles = this.getRoles();
        System.out.println("role000888"+roles);
        for (UserRole role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRolename()));
        }
        return authorities;
    }

    public String getProfilePicture() {
        return avatar;
    }

    public void setProfilePicture(String profilePicture) {
        this.avatar = profilePicture;
    }
}

