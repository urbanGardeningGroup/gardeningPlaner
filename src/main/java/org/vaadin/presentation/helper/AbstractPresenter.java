package org.vaadin.presentation.helper;

/**
 * Created by synto on 16.11.2015.
 */
public abstract class AbstractPresenter<V extends ApplicationView> {
    private V view;
    protected abstract void onViewEnter();
    protected void setView(V view) {
        this.view = view;
    }
    protected V getView() {
        return view;
    }
}
