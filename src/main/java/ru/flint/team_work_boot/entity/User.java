package ru.flint.team_work_boot.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import ru.flint.team_work_boot.config.security.PasswordDeserializer;

import java.io.Serializable;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, exclude = {"password"})
public class User extends AbstractEntity implements Serializable{
    @Column(name = "email", nullable = false)
    @Email(message = "Enter valid e-mail")
    @Size(max = 128)
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank(message = "Password must not be empty")
    @Size(min = 5, max = 256)
    // https://stackoverflow.com/a/12505165/548473
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonDeserialize(using = PasswordDeserializer.class)
    private String password;


    private boolean enabled = true;


    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "authorities", joinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "authority"}, name = "uk_user_role")})
    @Column(name = "authority")
    @ElementCollection(fetch = FetchType.EAGER)
    @NotEmpty(message = "Roles must not be empty")
    private Set<Role> roles;

    public void setEmail(String email) {
        this.email = StringUtils.hasText(email) ? email.toLowerCase() : null;
    }
    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
    }
}
