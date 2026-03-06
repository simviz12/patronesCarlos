package com.globaldocs.factory;

import com.globaldocs.processor.ArgentinaDocumentProcessor;
import com.globaldocs.processor.DocumentProcessor;

public class ArgentinaProcessorFactory extends DocumentProcessorFactory {
    @Override
    public DocumentProcessor createProcessor() {
        return new ArgentinaDocumentProcessor();
    }
}
