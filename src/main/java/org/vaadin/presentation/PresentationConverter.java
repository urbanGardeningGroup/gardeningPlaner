package org.vaadin.presentation;

import com.vaadin.data.util.converter.Converter;
import com.vaadin.data.util.converter.DefaultConverterFactory;

/**
 * Created by synto on 16.11.2015.
 */
public class PresentationConverter extends DefaultConverterFactory {

        private static final long serialVersionUID = 1L;

        @Override
        public <PRESENTATION, MODEL> Converter<PRESENTATION, MODEL>
        createConverter(Class<PRESENTATION> presentationType,
                        Class<MODEL> modelType) {

            /*
            *
            * Todo: Add custom converters for complex classes
            *
            * */
            // Default to the supertype
            return super.createConverter(presentationType,
                    modelType);
        }

}


