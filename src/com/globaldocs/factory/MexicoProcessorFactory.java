package com.globaldocs.factory;

import com.globaldocs.processor.MexicoDocumentProcessor;
import com.globaldocs.processor.DocumentProcessor;

public class MexicoProcessorFactory extends DocumentProcessorFactory {
    @Override
    public DocumentProcessor createProcessor() {
        return new MexicoDocumentProcessor();
    }
}
