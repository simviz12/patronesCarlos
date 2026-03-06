package com.globaldocs.factory;

import com.globaldocs.processor.ChileDocumentProcessor;
import com.globaldocs.processor.DocumentProcessor;

public class ChileProcessorFactory extends DocumentProcessorFactory {
    @Override
    public DocumentProcessor createProcessor() {
        return new ChileDocumentProcessor();
    }
}
