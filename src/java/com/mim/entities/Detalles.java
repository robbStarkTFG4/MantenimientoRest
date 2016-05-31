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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author marcoisaac
 */
@Entity
@Table(name = "detalles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detalles.findAll", query = "SELECT d FROM Detalles d"),
    @NamedQuery(name = "Detalles.findByIddetalles", query = "SELECT d FROM Detalles d WHERE d.iddetalles = :iddetalles"),
    @NamedQuery(name = "Detalles.findByParametro", query = "SELECT d FROM Detalles d WHERE d.parametro = :parametro"),
    @NamedQuery(name = "Detalles.findByShowParam", query = "SELECT d FROM Detalles d WHERE d.showParam = :showParam"),
    @NamedQuery(name = "Detalles.findByOpcionalParam", query = "SELECT d FROM Detalles d WHERE d.opcionalParam = :opcionalParam")})
public class Detalles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddetalles")
    private Integer iddetalles;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "parametro")
    private String parametro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "showParam")
    private boolean showParam;
    @Basic(optional = false)
    @NotNull
    @Column(name = "opcionalParam")
    private boolean opcionalParam;
    @JoinColumn(name = "lista_nombre_equipos_idlista_nombre", referencedColumnName = "idlista_nombre")
    @ManyToOne(optional = false)
    private ListaNombreEquipos listaNombreEquiposIdlistaNombre;

    public Detalles() {
    }

    public Detalles(Integer iddetalles) {
        this.iddetalles = iddetalles;
    }

    public Detalles(Integer iddetalles, String parametro, boolean showParam, boolean opcionalParam) {
        this.iddetalles = iddetalles;
        this.parametro = parametro;
        this.showParam = showParam;
        this.opcionalParam = opcionalParam;
    }

    public Integer getIddetalles() {
        return iddetalles;
    }

    public void setIddetalles(Integer iddetalles) {
        this.iddetalles = iddetalles;
    }

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    public boolean getShowParam() {
        return showParam;
    }

    public void setShowParam(boolean showParam) {
        this.showParam = showParam;
    }

    public boolean getOpcionalParam() {
        return opcionalParam;
    }

    public void setOpcionalParam(boolean opcionalParam) {
        this.opcionalParam = opcionalParam;
    }

    public ListaNombreEquipos getListaNombreEquiposIdlistaNombre() {
        return listaNombreEquiposIdlistaNombre;
    }

    public void setListaNombreEquiposIdlistaNombre(ListaNombreEquipos listaNombreEquiposIdlistaNombre) {
        this.listaNombreEquiposIdlistaNombre = listaNombreEquiposIdlistaNombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddetalles != null ? iddetalles.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalles)) {
            return false;
        }
        Detalles other = (Detalles) object;
        if ((this.iddetalles == null && other.iddetalles != null) || (this.iddetalles != null && !this.iddetalles.equals(other.iddetalles))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mim.entities.Detalles[ iddetalles=" + iddetalles + " ]";
    }
    
}
