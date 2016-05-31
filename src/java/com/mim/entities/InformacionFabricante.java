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
@Table(name = "informacion_fabricante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InformacionFabricante.findAll", query = "SELECT i FROM InformacionFabricante i"),
    @NamedQuery(name = "InformacionFabricante.findByIdinformacionFabricante", query = "SELECT i FROM InformacionFabricante i WHERE i.idinformacionFabricante = :idinformacionFabricante"),
    @NamedQuery(name = "InformacionFabricante.findByParametro", query = "SELECT i FROM InformacionFabricante i WHERE i.parametro = :parametro"),
    @NamedQuery(name = "InformacionFabricante.findByValor", query = "SELECT i FROM InformacionFabricante i WHERE i.valor = :valor")})
public class InformacionFabricante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idinformacion_fabricante")
    private Integer idinformacionFabricante;
    @Size(max = 80)
    @Column(name = "parametro")
    private String parametro;
    @Size(max = 600)
    @Column(name = "valor")
    private String valor;
    @JoinColumn(name = "equipo_idequipo", referencedColumnName = "idequipo")
    @ManyToOne(optional = false)
    private Equipo equipoIdequipo;

    public InformacionFabricante() {
    }

    public InformacionFabricante(String parametro) {
        this.parametro = parametro;
    }

    public InformacionFabricante(Integer idinformacionFabricante) {
        this.idinformacionFabricante = idinformacionFabricante;
    }

    public Integer getIdinformacionFabricante() {
        return idinformacionFabricante;
    }

    public void setIdinformacionFabricante(Integer idinformacionFabricante) {
        this.idinformacionFabricante = idinformacionFabricante;
    }

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Equipo getEquipoIdequipo() {
        return equipoIdequipo;
    }

    public void setEquipoIdequipo(Equipo equipoIdequipo) {
        this.equipoIdequipo = equipoIdequipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idinformacionFabricante != null ? idinformacionFabricante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InformacionFabricante)) {
            return false;
        }
        InformacionFabricante other = (InformacionFabricante) object;
        if ((this.idinformacionFabricante == null && other.idinformacionFabricante != null) || (this.idinformacionFabricante != null && !this.idinformacionFabricante.equals(other.idinformacionFabricante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mim.entities.InformacionFabricante[ idinformacionFabricante=" + idinformacionFabricante + " ]";
    }
    
}
