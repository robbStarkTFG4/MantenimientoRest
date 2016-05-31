/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.session.beans;

import com.mim.entities.InformacionFabricante;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author marcoisaac
 */
@Stateless
public class InformacionFabricanteFacade extends AbstractFacade<InformacionFabricante> {

    @PersistenceContext(unitName = "MantenimientoRestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InformacionFabricanteFacade() {
        super(InformacionFabricante.class);
    }
    
}
