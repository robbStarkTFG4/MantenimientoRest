/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.session.beans;

import com.mim.entities.ListaNombreEquipos;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author marcoisaac
 */
@Stateless
public class ListaNombreEquiposFacade extends AbstractFacade<ListaNombreEquipos> {

    @PersistenceContext(unitName = "MantenimientoRestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ListaNombreEquiposFacade() {
        super(ListaNombreEquipos.class);
    }
    
}
