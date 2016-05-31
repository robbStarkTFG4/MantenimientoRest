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
@Table(name = "fotos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fotos.findAll", query = "SELECT f FROM Fotos f"),
    @NamedQuery(name = "Fotos.findByIdfotos", query = "SELECT f FROM Fotos f WHERE f.idfotos = :idfotos"),
    @NamedQuery(name = "Fotos.findByTitulo", query = "SELECT f FROM Fotos f WHERE f.titulo = :titulo"),
    @NamedQuery(name = "Fotos.findByDescripcion", query = "SELECT f FROM Fotos f WHERE f.descripcion = :descripcion"),
    @NamedQuery(name = "Fotos.findByArchivo", query = "SELECT f FROM Fotos f WHERE f.archivo = :archivo")})
public class Fotos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfotos")
    private Integer idfotos;
    @Size(max = 45)
    @Column(name = "titulo")
    private String titulo;
    @Size(max = 700)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "archivo")
    private String archivo;
    @JoinColumn(name = "orden_idorden", referencedColumnName = "idorden")
    @ManyToOne(optional = false)
    private Orden ordenIdorden;

    public Fotos() {
    }

    public Fotos(Integer idfotos) {
        this.idfotos = idfotos;
    }

    public Fotos(Integer idfotos, String archivo) {
        this.idfotos = idfotos;
        this.archivo = archivo;
    }

    public Integer getIdfotos() {
        return idfotos;
    }

    public void setIdfotos(Integer idfotos) {
        this.idfotos = idfotos;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
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
        hash += (idfotos != null ? idfotos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fotos)) {
            return false;
        }
        Fotos other = (Fotos) object;
        if ((this.idfotos == null && other.idfotos != null) || (this.idfotos != null && !this.idfotos.equals(other.idfotos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mim.entities.Fotos[ idfotos=" + idfotos + " ]";
    }
    
}
