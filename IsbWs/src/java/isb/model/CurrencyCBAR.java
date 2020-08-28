/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isb.model;
import java.util.Date;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.Type;

/**
 *
 * @author User
 */
@Table(name = "ISB_CURRENCYCBAR")
@Entity(name = "Isb_CurrencyCbar")
public class CurrencyCBAR implements Serializable {

    @SequenceGenerator(name = "entitySeq", sequenceName = "IDGENERATOR", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entitySeq")
    @Column(name = "ID")
    @Id
    private long id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "NOMINAL")
    private int nominal;

    @Column(name = "NAME")
    private String name;

    @Column(name = "VALUE")
    private double value;

    @Temporal(javax.persistence.TemporalType.DATE)
    @Type(type = "date")
    @Column(name = "CURRENCYDATE")
    private Date currencyDate;

    @Type(type = "date")
    @Column(name = "REGDATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date regDate;

        public CurrencyCBAR() {
    }

    public CurrencyCBAR(String code, int nominal, String name, double value, Date currencyDate) {
        this.code = code;
        this.nominal = nominal;
        this.name = name;
        this.value = value;
        this.currencyDate = currencyDate;
        this.regDate = new Date();
    }

    public CurrencyCBAR(long id, String code, int nominal, String name, double value, Date currencyDate, Date regDate) {
        this.id = id;
        this.code = code;
        this.nominal = nominal;
        this.name = name;
        this.value = value;
        this.currencyDate = currencyDate;
        this.regDate = regDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getNominal() {
        return nominal;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Date getCurrencyDate() {
        return currencyDate;
    }

    public void setCurrencyDate(Date currencyDate) {
        this.currencyDate = currencyDate;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "CurrencyCBAR{" + "id=" + id + ", code=" + code + ", nominal=" + nominal + ", name=" + name + ", value=" + value + ", currencyDate=" + currencyDate + ", regDate=" + regDate + '}';
    }
    
}
