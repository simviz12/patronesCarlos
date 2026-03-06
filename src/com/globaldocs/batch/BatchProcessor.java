package com.globaldocs.batch;

import com.globaldocs.model.Document;
import com.globaldocs.model.Country;
import com.globaldocs.factory.*;
import com.globaldocs.processor.DocumentProcessor;
import com.globaldocs.exception.DocumentProcessingException;

import java.util.List;

public class BatchProcessor {

    private DocumentProcessorFactory getFactoryForCountry(Country country) {
        switch (country) {
            case COLOMBIA:
                return new ColombiaProcessorFactory();
            case MEXICO:
                return new MexicoProcessorFactory();
            case ARGENTINA:
                return new ArgentinaProcessorFactory();
            case CHILE:
                return new ChileProcessorFactory();
            default:
                throw new IllegalArgumentException("País no soportado.");
        }
    }

    public String processBatch(List<Document> documents) {
        StringBuilder result = new StringBuilder();
        result.append("=== Iniciando procesamiento de ").append(documents.size()).append(" documentos ===\n\n");

        int successCount = 0;
        int errorCount = 0;

        for (Document document : documents) {
            result.append("Procesando [").append(document.getName()).append("] (").append(document.getOriginCountry())
                    .append(")...\n");
            try {
                DocumentProcessorFactory factory = getFactoryForCountry(document.getOriginCountry());
                DocumentProcessor processor = factory.createProcessor();

                String successMessage = processor.process(document);
                result.append(successMessage).append("\n");
                successCount++;
            } catch (DocumentProcessingException e) {
                result.append("ERROR DE VALIDACIÓN: ").append(e.getMessage()).append("\n");
                errorCount++;
            } catch (Exception e) {
                result.append("ERROR FATAL: ").append(e.getMessage()).append("\n");
                errorCount++;
            }
            result.append("------------------------------------------------------\n");
        }

        result.append("\n=== Resumen del Lote ===\n");
        result.append("Exitosos: ").append(successCount).append(" | Fallidos: ").append(errorCount).append("\n");

        return result.toString();
    }
}
