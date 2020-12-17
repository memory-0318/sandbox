
package tw.brian.jasypt.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder(setterPrefix = "set", toBuilder = true)
@Entity
@Table(name = "USER_INFO_APP")
public class UserInfoAppEntity {

    @Id
    @Column(name = "ACCOUNT")
    private String account;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "TELH")
    private String telh;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "NAME")
    private String name;

    @Column(name = "IDENTIFY_NUMBER")
    private String identifyNumber;

}