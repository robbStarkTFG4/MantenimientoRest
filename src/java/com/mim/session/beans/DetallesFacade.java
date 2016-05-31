/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.session.beans;

import com.mim.entities.Detalles;
import com.mim.entities.ListaNombreEquipos;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author marcoisaac
 */
@Stateless
public class DetallesFacade extends AbstractFacade<Detalles> {
    
    @PersistenceContext(unitName = "MantenimientoRestPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public DetallesFacade() {
        super(Detalles.class);
    }
    
    public void create(Detalles currentDetail, String nombre) {
        TypedQuery<ListaNombreEquipos> query = em.createQuery("SELECT c FROM ListaNombreEquipos c WHERE c.nombre = :id ", ListaNombreEquipos.class);
        query.setParameter("id", nombre);
        currentDetail.setListaNombreEquiposIdlistaNombre(query.getSingleResult());
        this.create(currentDetail);
    }
    
}
