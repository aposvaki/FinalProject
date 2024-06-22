package gr.aueb.cf.fnlprojecttecheshop.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;


@MappedSuperclass
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractModel {

    @ColumnDefault("true")
    @Column(name = "is_active")
    private Boolean isActive;


    public abstract Collection<? extends GrantedAuthority> getAuthorities();

    public abstract String getPassword();

    public abstract String getUsername();

    public abstract boolean isAccountNonExpired();

    public abstract boolean isAccountNonLocked();

    public abstract boolean isCredentialsNonExpired();

    public abstract boolean isEnabled();

}
