package com.globaldocs.processor;

import com.globaldocs.model.Document;
import com.globaldocs.exception.DocumentProcessingException;

public class ChileDocumentProcessor implements DocumentProcessor {
    @Override
    public String process(Document document) throws DocumentProcessingException {
        // Custom validations will be placed here
        return "ÉXITO: Procesado " + document.getType() + " bajo regulaciones del SII en Chile.";
    }
}
