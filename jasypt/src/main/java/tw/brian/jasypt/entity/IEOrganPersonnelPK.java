package tw.brian.jasypt.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Brian Su
 * @description:
 * @date: 2020/3/4
 */
public class IEOrganPersonnelPK implements Serializable {

    private static final long serialVersionUID = -6556140606213966037L;
    private String organ;
    private Integer pid;
    private Integer dataYear;

    @Id
    @Column(name = "ORGAN")
    public String getOrgan() {
        return organ;
    }

    public void setOrgan(String organ) {
        this.organ = organ;
    }

    @Id
    @Column(name = "PID")
    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    @Column(name = "DATA_YEAR")
    @Id
    public Integer getDataYear() {
        return dataYear;
    }

    public void setDataYear(Integer dataYear) {
        this.dataYear = dataYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        IEOrganPersonnelPK that = (IEOrganPersonnelPK) o;

        if (organ != null ? !organ.equals(that.organ) : that.organ != null) {
            return false;
        }
        if (pid != null ? !pid.equals(that.pid) : that.pid != null) {
            return false;
        }
        if (dataYear != null ? !dataYear.equals(that.dataYear) : that.dataYear != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = organ != null ? organ.hashCode() : 0;
        result = 31 * result + (pid != null ? pid.hashCode() : 0);
        result = 31 * result + (dataYear != null ? dataYear.hashCode() : 0);
        return result;
    }
}
