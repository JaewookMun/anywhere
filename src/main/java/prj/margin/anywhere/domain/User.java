package prj.margin.anywhere.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter @Setter
public class User {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @Column(unique = true)
    private String loginId;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String email;

    private boolean deletedFlag;

    // builder 패턴을 적용해서 초기화하려고하는 필드만 주입하는 생성자 지정
    @Builder
    public User(String name, Role role, String email) {
        this.name = name;
        this.role = role;
        this.email = email;
    }

    public User update(String name) {
        this.name = name;

        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", loginId='" + loginId + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", email='" + email + '\'' +
                ", deletedFlag=" + deletedFlag +
                '}';
    }
}
