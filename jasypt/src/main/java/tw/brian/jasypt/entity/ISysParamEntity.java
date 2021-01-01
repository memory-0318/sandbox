package tw.brian.jasypt.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;

/**
 * @author Brian Su
 * @description:
 * @date: 2020/3/4
 */
@Data
@Builder(setterPrefix = "set")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ISYSPARAM")
public class ISysParamEntity implements Serializable, Cloneable {

    private static final long serialVersionUID = -1277240795163892372L;
    private Integer sysParamOid;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SYSPARAM_OID")
    public Integer getSysParamOid() {
        return sysParamOid;
    }

    public void setSysParamOid(Integer sysParamOid) {
        this.sysParamOid = sysParamOid;
    }

    private String paramNmEn;

    @Basic
    @Column(name = "PARAM_NM_EN")
    public String getParamNmEn() {
        return paramNmEn;
    }

    public void setParamNmEn(String paramNmEn) {
        this.paramNmEn = paramNmEn;
    }

    private String paramNmCh;

    @Basic
    @Column(name = "PARAM_NM_CH")
    public String getParamNmCh() {
        return paramNmCh;
    }

    public void setParamNmCh(String paramNmCh) {
        this.paramNmCh = paramNmCh;
    }

    private String paramValue;

    @Basic
    @Column(name = "PARAM_VALUE")
    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    private String paramClass;

    @Column(name = "PARAM_CLASS")
    public String getParamClass() {
        return paramClass;
    }

    public void setParamClass(String paramClass) {
        this.paramClass = paramClass;
    }

    /**
     * 更新時間
     */
    private OffsetDateTime updateDateTime;

    @Column(name = "UPDATE_DATE_TIME")
    public OffsetDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(OffsetDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    @Override
    public ISysParamEntity clone() {
        try {
            return (ISysParamEntity) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private String hint;

    @Column(name = "HINT")
    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }
}
