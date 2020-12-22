package tw.brian.jasypt.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "set", toBuilder = true)
@Entity
@Table(name = "USER_INFO")
public class UserInfoEntity {

    @Id
    @Column(name = "ACCOUNT")
    private String account;

    @Column(name = "PASSWD")
    private String passwd;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "TELH")
    private String telh;

    @Column(name = "PHONE")
    private String phone;
}
