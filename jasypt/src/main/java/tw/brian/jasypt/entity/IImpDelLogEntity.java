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
@Table(name = "IIMPDELLOG")
public class IImpDelLogEntity implements Serializable {

    private static final long serialVersionUID = -5991659420917969448L;
    private Integer impDelOid;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @javax.persistence.Column(name = "IMP_DEL_OID")
    public Integer getImpDelOid() {
        return impDelOid;
    }

    public void setImpDelOid(Integer impDelOid) {
        this.impDelOid = impDelOid;
    }

    private byte[] encodePassOpName;

    @Basic
    @javax.persistence.Column(name = "ENCODE_PASS_OP_NAME")
    public byte[] getEncodePassOpName() {
        return encodePassOpName;
    }

    public void setEncodePassOpName(byte[] encodePassOpName) {
        this.encodePassOpName = encodePassOpName;
    }
}
