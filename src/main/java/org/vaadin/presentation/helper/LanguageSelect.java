package org.vaadin.presentation.helper;

import com.vaadin.ui.ComboBox;

import java.util.Arrays;
import java.util.List;

import static org.vaadin.presentation.views.LoginViewImpl.Language;

/**
 * Created by synto on 17.11.2015.
 */
public class LanguageSelect extends ComboBox {

    private static final long serialVersionUID = 1L;

    private static final List<Language> languages = Arrays.asList(Language.values());

    public LanguageSelect(String caption) {
        super(caption, languages);
        this.setWidth(10.5f, Unit.EM);
        this.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
        this.setNullSelectionAllowed(false);
        for(final Language languagesUi : languages){
            LanguageSelect.this.setItemIcon(languagesUi, languagesUi.getIcon());
        }
    }
}