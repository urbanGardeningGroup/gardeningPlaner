package org.vaadin.presentation.helper;

/**
 * Created by synto on 18.11.2015.
 */

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CustomComponent;
import org.vaadin.cdiviewmenu.ViewMenuItem;

import javax.annotation.PostConstruct;


public abstract class AbstractView<P extends AbstractPresenter> extends
        CustomComponent implements ApplicationView<P>, View {

    private static final long serialVersionUID = 1L;


    private P presenter;

    public AbstractView() {
        setSizeFull();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        presenter.onViewEnter();
    }

    protected void setPresenter(P presenter) {
        this.presenter = presenter;
        presenter.setView(this);
    }

    @PostConstruct
    protected void postConstruct() {
        setPresenter(generatePresenter());
    }

    protected abstract P generatePresenter();

    @Override
    public P getPresenter() {
        return presenter;
    }

    @Override
    public String getName() {
        ViewMenuItem annotation = getClass().getAnnotation(ViewMenuItem.class);
        if (annotation != null) {
            return annotation.title();
        } else {
            return getClass().getSimpleName();
        }
    }

    @Override
    public String getId() {
        // Use view id/address as the id by default
        CDIView annotation = getClass().getAnnotation(CDIView.class);
        if (annotation != null) {
            return annotation.value();
        }
        return super.getId();
    }

}