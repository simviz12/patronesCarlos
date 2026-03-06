package com.globaldocs.processor;

import com.globaldocs.model.Document;
import com.globaldocs.exception.DocumentProcessingException;

/**
 * Product interface for the Factory Method pattern.
 */
public interface DocumentProcessor {
    String process(Document document) throws DocumentProcessingException;
}
