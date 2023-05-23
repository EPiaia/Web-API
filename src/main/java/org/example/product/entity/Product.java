package org.example.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "PRODUCT")
public class Product implements Serializable {

    @Id
    @Column(name = "P_ID")
    private Integer pId;
    @NotNull
    @Column(name = "P_DESC")
    private String pDesc;
    @NotNull
    @Column(name = "P_MARCA")
    private String pMarca;
    @NotNull
    @Column(name = "P_VALOR_UN")
    private BigDecimal pValorUn;

    public Product() {
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getpDesc() {
        return pDesc;
    }

    public void setpDesc(String pDesc) {
        this.pDesc = pDesc;
    }

    public String getpMarca() {
        return pMarca;
    }

    public void setpMarca(String pMarca) {
        this.pMarca = pMarca;
    }

    public BigDecimal getpValorUn() {
        return pValorUn;
    }

    public void setpValorUn(BigDecimal pValorUn) {
        this.pValorUn = pValorUn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return pId.equals(product.pId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pId);
    }
}
