/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author marcoisaac
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.mim.service.CrossOriginResourceSharingFilter.class);
        resources.add(com.mim.service.DetallesFacadeREST.class);
        resources.add(com.mim.service.EquipoFacadeREST.class);
        resources.add(com.mim.service.FotosFacadeREST.class);
        resources.add(com.mim.service.GenericResource.class);
        resources.add(com.mim.service.HistorialDetallesFacadeREST.class);
        resources.add(com.mim.service.InformacionFabricanteFacadeREST.class);
        resources.add(com.mim.service.ListaNombreEquiposFacadeREST.class);
        resources.add(com.mim.service.LugarFacadeREST.class);
        resources.add(com.mim.service.OrdenFacadeREST.class);
    }
    
}
