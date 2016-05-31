/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.controllers;

import com.mim.entities.ListaNombreEquipos;
import com.mim.session.beans.ListaNombreEquiposFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author marcoisaac
 */
@Named("names")
@ViewScoped
public class NombreEquiposCtrl implements Serializable {

    private ListaNombreEquipos nombre = new ListaNombreEquipos();
    private List<ListaNombreEquipos> nombresList;

    @EJB
    ListaNombreEquiposFacade nombreSession;

    @PostConstruct
    private void init() {
        nombresList = nombreSession.findAll();
    }

    public void guardarListener(ActionEvent e) {
        nombreSession.create(nombre);
        nombre = new ListaNombreEquipos();
        nombresList.clear();
        nombresList = nombreSession.findAll();
        RequestContext.getCurrentInstance().update("namesForm");
    }

    public ListaNombreEquipos getNombre() {
        return nombre;
    }

    public List<ListaNombreEquipos> getNombresList() {
        return nombresList;
    }

}
