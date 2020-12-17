package tw.brian.jasypt.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "set", builderClassName = "Builder", toBuilder = true)
@Entity
@Table(name = "IUSER")
public class IUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_OID")
    private Integer userOid;

    @Column(name = "ORG_CD")
    private String orgCd;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "USER_PASSWD")
    private String userPasswd;

    @Column(name = "ENCODE_USER_NAME")
    private String userName;

    @Column(name = "ENCODE_USER_TEL")
    private String userTel;
}
