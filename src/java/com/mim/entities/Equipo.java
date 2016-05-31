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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "equipo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Equipo.findAll", query = "SELECT e FROM Equipo e"),
    @NamedQuery(name = "Equipo.findByIdequipo", query = "SELECT e FROM Equipo e WHERE e.idequipo = :idequipo"),
    @NamedQuery(name = "Equipo.findByNumeroEquipo", query = "SELECT e FROM Equipo e WHERE e.numeroEquipo = :numeroEquipo"),
    @NamedQuery(name = "Equipo.findByListaNombreEquiposIdlistaNombre", query = "SELECT e FROM Equipo e WHERE e.listaNombreEquiposIdlistaNombre = :listaNombreEquiposIdlistaNombre"),
    @NamedQuery(name = "Equipo.findByCodigoBarras", query = "SELECT e FROM Equipo e WHERE e.codigoBarras = :codigoBarras")})
public class Equipo implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipoIdequipo")
    private List<InformacionFabricante> informacionFabricanteList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idequipo")
    private Integer idequipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "numero_equipo")
    private String numeroEquipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lista_nombre_equipos_idlista_nombre")
    private int listaNombreEquiposIdlistaNombre;
    @Size(max = 45)
    @Column(name = "codigo_barras")
    private String codigoBarras;
    @JoinColumn(name = "lugar_idlugar", referencedColumnName = "idlugar")
    @ManyToOne(optional = false)
    private Lugar lugarIdlugar;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipoIdequipo")
    private List<Orden> ordenList;

    public Equipo() {
    }

    public Equipo(Integer idequipo) {
        this.idequipo = idequipo;
    }

    public Equipo(Integer idequipo, String numeroEquipo, int listaNombreEquiposIdlistaNombre) {
        this.idequipo = idequipo;
        this.numeroEquipo = numeroEquipo;
        this.listaNombreEquiposIdlistaNombre = listaNombreEquiposIdlistaNombre;
    }

    public Integer getIdequipo() {
        return idequipo;
    }

    public void setIdequipo(Integer idequipo) {
        this.idequipo = idequipo;
    }

    public String getNumeroEquipo() {
        return numeroEquipo;
    }

    public void setNumeroEquipo(String numeroEquipo) {
        this.numeroEquipo = numeroEquipo;
    }

    public int getListaNombreEquiposIdlistaNombre() {
        return listaNombreEquiposIdlistaNombre;
    }

    public void setListaNombreEquiposIdlistaNombre(int listaNombreEquiposIdlistaNombre) {
        this.listaNombreEquiposIdlistaNombre = listaNombreEquiposIdlistaNombre;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public Lugar getLugarIdlugar() {
        return lugarIdlugar;
    }

    public void setLugarIdlugar(Lugar lugarIdlugar) {
        this.lugarIdlugar = lugarIdlugar;
    }

    @XmlTransient
    public List<Orden> getOrdenList() {
        return ordenList;
    }

    public void setOrdenList(List<Orden> ordenList) {
        this.ordenList = ordenList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idequipo != null ? idequipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Equipo)) {
            return false;
        }
        Equipo other = (Equipo) object;
        if ((this.idequipo == null && other.idequipo != null) || (this.idequipo != null && !this.idequipo.equals(other.idequipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mim.entities.Equipo[ idequipo=" + idequipo + " ]";
    }

    @XmlTransient
    public List<InformacionFabricante> getInformacionFabricanteList() {
        return informacionFabricanteList;
    }

    public void setInformacionFabricanteList(List<InformacionFabricante> informacionFabricanteList) {
        this.informacionFabricanteList = informacionFabricanteList;
    }
    
}
