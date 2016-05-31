/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author marcoisaac
 */
@Entity
@Table(name = "historial_detalles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistorialDetalles.findAll", query = "SELECT h FROM HistorialDetalles h"),
    @NamedQuery(name = "HistorialDetalles.findByIdhistorialDetalles", query = "SELECT h FROM HistorialDetalles h WHERE h.idhistorialDetalles = :idhistorialDetalles"),
    @NamedQuery(name = "HistorialDetalles.findByValor", query = "SELECT h FROM HistorialDetalles h WHERE h.valor = :valor"),
    @NamedQuery(name = "HistorialDetalles.findByParametro", query = "SELECT h FROM HistorialDetalles h WHERE h.parametro = :parametro")})
public class HistorialDetalles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idhistorial_detalles")
    private Integer idhistorialDetalles;
    @Size(max = 600)
    @Column(name = "valor")
    private String valor;
    @Size(max = 250)
    @Column(name = "parametro")
    private String parametro;
    @JoinColumn(name = "orden_idorden", referencedColumnName = "idorden")
    @ManyToOne(optional = false)
    private Orden ordenIdorden;

    public HistorialDetalles() {
    }

    public HistorialDetalles(String parametro) {
        this.parametro = parametro;
    }

    public HistorialDetalles(Integer idhistorialDetalles) {
        this.idhistorialDetalles = idhistorialDetalles;
    }

    public Integer getIdhistorialDetalles() {
        return idhistorialDetalles;
    }

    public void setIdhistorialDetalles(Integer idhistorialDetalles) {
        this.idhistorialDetalles = idhistorialDetalles;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    public Orden getOrdenIdorden() {
        return ordenIdorden;
    }

    public void setOrdenIdorden(Orden ordenIdorden) {
        this.ordenIdorden = ordenIdorden;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idhistorialDetalles != null ? idhistorialDetalles.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistorialDetalles)) {
            return false;
        }
        HistorialDetalles other = (HistorialDetalles) object;
        if ((this.idhistorialDetalles == null && other.idhistorialDetalles != null) || (this.idhistorialDetalles != null && !this.idhistorialDetalles.equals(other.idhistorialDetalles))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mim.entities.HistorialDetalles[ idhistorialDetalles=" + idhistorialDetalles + " ]";
    }
    
}
