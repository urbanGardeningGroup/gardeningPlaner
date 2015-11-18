package org.vaadin.presentation.helper;

/**
 * Created by synto on 16.11.2015.
 */
public interface ApplicationView<P extends AbstractPresenter> {
    P getPresenter();
    String getName();
    String getId();
}
