package com.globaldocs.factory;

import com.globaldocs.processor.ColombiaDocumentProcessor;
import com.globaldocs.processor.DocumentProcessor;

public class ColombiaProcessorFactory extends DocumentProcessorFactory {
    @Override
    public DocumentProcessor createProcessor() {
        return new ColombiaDocumentProcessor();
    }
}
