package org.vaadin.presentation;
import java.util.Locale;

/**
 * Created by synto on 16.11.2015.
 */
public interface IuiLabels {

    public static final Locale DE_CH = new Locale("de", "CH");
    public static final Locale EN_US = new Locale("en", "US");
    public static final Locale FR_CH = new Locale("fr", "CH");

    public enum TextIdentifier{

        //User



        //General
        ERRORVIEWTITLE("ErrorViewTitle"),
        ERRORVIEWINFO("ErrorViewInfo"),
        GARDEN_INFORMATION("GardenInfo"),
        CHOSEN_PLANTS("ChosenPlants"),
        CATEGORY("Category"),
        TYPE("Type"),
        CLIMATE("Climate"),
        LANGUAGE("Language"),
        UPLOAD("Upload"),
        GENERAL_INFO("GeneralInfo"),
        SUMMARY("Summary"),
        CALCULATED("Calculated"),
        RECALCULATE("Recalculate"),
        REMOVE_SELECTED("RemoveSelected"),
        ADD("Add"),
        WELCOMEBACK("WelcomeBack");

        private String key;

        private TextIdentifier(String key){
            this.key = key;
        }

        public String getKey(){
            return key;
        }

    }
    /**
     * Returns the text attributed to this identifier.
     * @param id <code>TextIdentifier</code> to identify the text.
     */
    public String getText(TextIdentifier id);
    /**
     * Returns the text attributed to this identifier with lowercase letters.
     * @param id <code>TextIdentifier</code> to identify the text.
     */
    public String getTextLc(TextIdentifier id);


    public void setLocale(final Locale locale);
}