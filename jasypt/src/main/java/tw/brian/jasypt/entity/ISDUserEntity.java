package tw.brian.jasypt.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Brian Su
 * @description:
 * @date: 2020/3/4
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "set", toBuilder = true)
@Entity
@Table(name = "ISDUSER")
public class ISDUserEntity implements Serializable {
    private static final long serialVersionUID = -6398459918741847149L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "S_ID")
    private Integer sId;

    @Column(name = "ENCODE_USER_NAME")
    private String userName;

    @Column(name = "ENCODE_USER_TEL")
    private String userTel;
}
