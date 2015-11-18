package org.vaadin.presentation;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Created by synto on 16.11.2015.
 */
public class DefaultUiLablesImp implements IuiLabels {

    private ResourceBundle labels;

    public DefaultUiLablesImp(Locale inLocale){
        this.labels = ResourceBundle.getBundle("GardenBundle", inLocale);
    }

    @Override
    public String getText(TextIdentifier id) {
        return labels.getString(id.getKey());
    }

    @Override
    public String getTextLc(TextIdentifier id) {
        String strText = labels.getString(id.getKey());
        return strText.toLowerCase();
    }

    @Override
    public void setLocale(Locale locale) {
        try {
            this.labels = ResourceBundle.getBundle(
                    "GardenBundle", locale);
        } catch (final MissingResourceException e) {
            e.printStackTrace();
        }
    }

}
