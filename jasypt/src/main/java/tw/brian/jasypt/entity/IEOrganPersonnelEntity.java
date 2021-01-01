package tw.brian.jasypt.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "IEORGANPERSONNEL_TEST")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(setterPrefix = "set", toBuilder = true)
public class IEOrganPersonnelEntity implements Serializable {

    private static final long serialVersionUID = -2221772906007309555L;

    @Id
    @Column(name = "PID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pid;

    @Column(name = "ORGAN")
    private String organ;

    @Column(name = "DATA_YEAR")
    private Integer dataYear;

    @Column(name = "ENCODE_PERSONNEL_NM")
    private byte[] encodePersonnelNm;

    @Column(name = "ENCODE_TEL_NUMBER")
    private byte[] encodeTelNumber;
}
