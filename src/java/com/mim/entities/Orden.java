/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author marcoisaac
 */
@Entity
@Table(name = "orden")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orden.findAll", query = "SELECT o FROM Orden o"),
    @NamedQuery(name = "Orden.findByIdorden", query = "SELECT o FROM Orden o WHERE o.idorden = :idorden"),
    @NamedQuery(name = "Orden.findByDescripcion", query = "SELECT o FROM Orden o WHERE o.descripcion = :descripcion"),
    @NamedQuery(name = "Orden.findByEstatus", query = "SELECT o FROM Orden o WHERE o.estatus = :estatus"),
    @NamedQuery(name = "Orden.findByStartDate", query = "SELECT o FROM Orden o WHERE o.startDate = :startDate"),
    @NamedQuery(name = "Orden.findByEndDate", query = "SELECT o FROM Orden o WHERE o.endDate = :endDate"),
    @NamedQuery(name = "Orden.findByNumeroOrden", query = "SELECT o FROM Orden o WHERE o.numeroOrden = :numeroOrden"),
    @NamedQuery(name = "Orden.findByEncargado", query = "SELECT o FROM Orden o WHERE o.encargado = :encargado")})
public class Orden implements Serializable {

    @Size(max = 70)
    @Column(name = "prioridad")
    private String prioridad;
    @Size(max = 50)
    @Column(name = "actividad")
    private String actividad;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idorden")
    private Integer idorden;
    @Size(max = 1000)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "estatus")
    private Integer estatus;
    @Column(name = "startDate")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "endDate")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "numero_orden")
    private String numeroOrden;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "encargado")
    private String encargado;
    @JoinColumn(name = "equipo_idequipo", referencedColumnName = "idequipo")
    @ManyToOne(optional = false)
    private Equipo equipoIdequipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ordenIdorden")
    private List<HistorialDetalles> historialDetallesList;

    public Orden() {
    }

    public Orden(Integer idorden) {
        this.idorden = idorden;
    }

    public Orden(Integer idorden, String numeroOrden, String encargado) {
        this.idorden = idorden;
        this.numeroOrden = numeroOrden;
        this.encargado = encargado;
    }

    public Integer getIdorden() {
        return idorden;
    }

    public void setIdorden(Integer idorden) {
        this.idorden = idorden;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getEstatus() {
        return estatus;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public String getEncargado() {
        return encargado;
    }

    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }

    public Equipo getEquipoIdequipo() {
        return equipoIdequipo;
    }

    public void setEquipoIdequipo(Equipo equipoIdequipo) {
        this.equipoIdequipo = equipoIdequipo;
    }

    @XmlTransient
    public List<HistorialDetalles> getHistorialDetallesList() {
        return historialDetallesList;
    }

    public void setHistorialDetallesList(List<HistorialDetalles> historialDetallesList) {
        this.historialDetallesList = historialDetallesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idorden != null ? idorden.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orden)) {
            return false;
        }
        Orden other = (Orden) object;
        if ((this.idorden == null && other.idorden != null) || (this.idorden != null && !this.idorden.equals(other.idorden))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mim.entities.Orden[ idorden=" + idorden + " ]";
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }
    
}
