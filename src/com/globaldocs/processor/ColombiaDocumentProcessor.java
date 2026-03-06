package com.globaldocs.processor;

import com.globaldocs.model.Document;
import com.globaldocs.model.DocumentFormat;
import com.globaldocs.exception.DocumentProcessingException;

public class ColombiaDocumentProcessor implements DocumentProcessor {
    @Override
    public String process(Document document) throws DocumentProcessingException {
        // Simulating some Colombian specific regulatory logic
        if (document.getFormat() == DocumentFormat.CSV) {
            throw new DocumentProcessingException(
                    "Las regulaciones de la DIAN en Colombia no aceptan formato CSV para registros oficiales.");
        }
        return "ÉXITO: Procesado " + document.getType() + " bajo regulaciones colombianas.";
    }
}
