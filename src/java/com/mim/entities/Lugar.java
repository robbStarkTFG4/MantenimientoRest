/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author marcoisaac
 */
@Entity
@Table(name = "lugar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lugar.findAll", query = "SELECT l FROM Lugar l"),
    @NamedQuery(name = "Lugar.findByIdlugar", query = "SELECT l FROM Lugar l WHERE l.idlugar = :idlugar"),
    @NamedQuery(name = "Lugar.findByNombre", query = "SELECT l FROM Lugar l WHERE l.nombre = :nombre"),
    @NamedQuery(name = "Lugar.findByDireccion1", query = "SELECT l FROM Lugar l WHERE l.direccion1 = :direccion1"),
    @NamedQuery(name = "Lugar.findByDireccion2", query = "SELECT l FROM Lugar l WHERE l.direccion2 = :direccion2"),
    @NamedQuery(name = "Lugar.findByCiudad", query = "SELECT l FROM Lugar l WHERE l.ciudad = :ciudad"),
    @NamedQuery(name = "Lugar.findByEstado", query = "SELECT l FROM Lugar l WHERE l.estado = :estado")})
public class Lugar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idlugar")
    private Integer idlugar;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 100)
    @Column(name = "direccion1")
    private String direccion1;
    @Size(max = 100)
    @Column(name = "direccion2")
    private String direccion2;
    @Size(max = 70)
    @Column(name = "ciudad")
    private String ciudad;
    @Size(max = 45)
    @Column(name = "estado")
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lugarIdlugar")
    private List<Equipo> equipoList;

    public Lugar() {
    }

    public Lugar(Integer idlugar) {
        this.idlugar = idlugar;
    }

    public Lugar(Integer idlugar, String nombre) {
        this.idlugar = idlugar;
        this.nombre = nombre;
    }

    public Integer getIdlugar() {
        return idlugar;
    }

    public void setIdlugar(Integer idlugar) {
        this.idlugar = idlugar;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion1() {
        return direccion1;
    }

    public void setDireccion1(String direccion1) {
        this.direccion1 = direccion1;
    }

    public String getDireccion2() {
        return direccion2;
    }

    public void setDireccion2(String direccion2) {
        this.direccion2 = direccion2;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<Equipo> getEquipoList() {
        return equipoList;
    }

    public void setEquipoList(List<Equipo> equipoList) {
        this.equipoList = equipoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlugar != null ? idlugar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lugar)) {
            return false;
        }
        Lugar other = (Lugar) object;
        if ((this.idlugar == null && other.idlugar != null) || (this.idlugar != null && !this.idlugar.equals(other.idlugar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mim.entities.Lugar[ idlugar=" + idlugar + " ]";
    }
    
}
