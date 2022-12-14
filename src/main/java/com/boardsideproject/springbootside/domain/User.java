package com.boardsideproject.springbootside.domain;

import com.boardsideproject.springbootside.dto.UserUpdateDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ManyToAny;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@Entity
public class User {

    @JsonIgnore
    @Column(name = "user_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 50, nullable = false)
    private String username;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @JsonIgnore
    @Column(name = "activated")
    private boolean activated;

    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")}
    )
    private Set<Authority> authorities = new HashSet<>();

    @Builder
    public User(String username, String email, String password, boolean activated, Set<Authority> authorities){
        this.username = username;
        this.email = email;
        this.password = password;
        this.activated = activated;
        this.authorities = authorities;
    }

    public void addAuthority(Authority authority) {
        this.getAuthorities().add(authority);
    }

    public void removeAuthority(Authority authority) {
        this.getAuthorities().remove(authority);
    }

    public void activate(boolean flag) {
        this.activated = flag;
    }

    public String getAuthoritiesToString() {
        return this.authorities.stream()
                .map(Authority::getAuthorityName)
                .collect(Collectors.joining(","));
    }

    public void updateUser(UserUpdateDto dto, PasswordEncoder passwordEncoder) {
        if(dto.getPassword() != null) this.password = passwordEncoder.encode(dto.getPassword());
        if(dto.getUsername() != null) this.username = dto.getUsername();
        if(dto.getAuthorities().size() > 0) {
            this.authorities = dto.getAuthorities().stream()
                    .filter(UserAuth::containsKey)
                    .map(UserAuth::get)
                    .map(Authority::new)
                    .collect(Collectors.toSet());
        }
    }
}
