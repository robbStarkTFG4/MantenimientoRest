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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author marcoisaac
 */
@Entity
@Table(name = "lista_nombre_equipos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ListaNombreEquipos.findAll", query = "SELECT l FROM ListaNombreEquipos l"),
    @NamedQuery(name = "ListaNombreEquipos.findByIdlistaNombre", query = "SELECT l FROM ListaNombreEquipos l WHERE l.idlistaNombre = :idlistaNombre"),
    @NamedQuery(name = "ListaNombreEquipos.findByNombre", query = "SELECT l FROM ListaNombreEquipos l WHERE l.nombre = :nombre")})
public class ListaNombreEquipos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idlista_nombre")
    private Integer idlistaNombre;
    @Size(max = 100)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "listaNombreEquiposIdlistaNombre")
    private List<Detalles> detallesList;

    public ListaNombreEquipos() {
    }

    public ListaNombreEquipos(String nombre) {
        this.nombre = nombre;
    }

    public ListaNombreEquipos(Integer idlistaNombre) {
        this.idlistaNombre = idlistaNombre;
    }

    public Integer getIdlistaNombre() {
        return idlistaNombre;
    }

    public void setIdlistaNombre(Integer idlistaNombre) {
        this.idlistaNombre = idlistaNombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Detalles> getDetallesList() {
        return detallesList;
    }

    public void setDetallesList(List<Detalles> detallesList) {
        this.detallesList = detallesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlistaNombre != null ? idlistaNombre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ListaNombreEquipos)) {
            return false;
        }
        ListaNombreEquipos other = (ListaNombreEquipos) object;
        if (!this.nombre.equals(other.nombre)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mim.entities.ListaNombreEquipos[ idlistaNombre=" + idlistaNombre + " ]";
    }

}
