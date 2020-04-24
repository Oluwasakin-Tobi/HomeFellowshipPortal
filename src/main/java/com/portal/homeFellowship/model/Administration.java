/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.homeFellowship.model;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 *
 * @author tobi
 * @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
 */
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class Administration implements Serializable {

    private static final long serialVersionUID = 2085461287386274373L;
    private boolean authenticate;
    private boolean validate;
    private boolean edited;
    private boolean editedRole;
    private UserAdmin user;

   
    @PostConstruct
    public void init() {
        this.authenticate = false;
        this.validate = false;
        this.edited = false;
        this.editedRole = false;
        this.user = null;
    }
    

    
    public boolean isEditedRole() {
		return editedRole;
	}



	public void setEditedRole(boolean editedRole) {
		this.editedRole = editedRole;
	}



	public boolean isEdited() {
		return edited;
	}



	public void setEdited(boolean edited) {
		this.edited = edited;
	}



	public UserAdmin getUser() {
        return user;
    }

    public void setUser(UserAdmin user) {
        this.user = user;
    }

    public boolean isAuthenticate() {
        return authenticate;
    }

    public void setAuthenticate(boolean authenticate) {
        this.authenticate = authenticate;
    }

    public boolean isValidate() {
        return validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }
}
