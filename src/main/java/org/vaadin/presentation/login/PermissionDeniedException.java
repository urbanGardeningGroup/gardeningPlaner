package org.vaadin.presentation.login;

/**
 * Created by synto on 17.11.2015.
 */
public class PermissionDeniedException extends Exception {


    public PermissionDeniedException() {
        super();
    }

    public PermissionDeniedException(String message){
        super(message);
    }
}
