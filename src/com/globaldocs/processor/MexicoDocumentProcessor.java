package com.globaldocs.processor;

import com.globaldocs.model.Document;
import com.globaldocs.model.DocumentType;
import com.globaldocs.model.DocumentFormat;
import com.globaldocs.exception.DocumentProcessingException;

public class MexicoDocumentProcessor implements DocumentProcessor {
    @Override
    public String process(Document document) throws DocumentProcessingException {
        // Simulating some Mexican specific regulatory logic
        if (document.getType() == DocumentType.TAX_RETURN && document.getFormat() != DocumentFormat.PDF) {
            throw new DocumentProcessingException(
                    "Las regulaciones del SAT en México exigen que las declaraciones de impuestos sean estrictamente en PDF.");
        }
        return "ÉXITO: Procesado " + document.getType() + " bajo regulaciones mexicanas.";
    }
}
