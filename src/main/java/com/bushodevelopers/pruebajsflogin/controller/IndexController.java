/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bushodevelopers.pruebajsflogin.controller;

import com.bushodevelopers.pruebajsflogin.ejb.UsuarioFacadeLocal;
import com.bushodevelopers.pruebajsflogin.model.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author javie
 */
@Named
@ViewScoped
public class IndexController implements Serializable{
    
    @EJB
    private UsuarioFacadeLocal EJBUsuario;
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    @PostConstruct
    public void init(){
    usuario = new Usuario();
    }
    
    public String iniciarSesion(){
        Usuario us;
        String redireccion= null;
        
        try {
            us=EJBUsuario.iniciarSesion(usuario);
            if (us!= null) {
                redireccion = "/PaginaPrincipal/principal";//faces-redirect=true //navegacion explicita
            }else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Aviso", "Credenciales incorrectas"));
            }
            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Aviso", "Usuario"));
        }
        return redireccion;
        
        
    }
    
}
