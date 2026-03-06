package com.globaldocs.factory;

import com.globaldocs.processor.DocumentProcessor;

/**
 * Creator abstract class for the Factory Method pattern.
 */
public abstract class DocumentProcessorFactory {
    // This is the Factory Method
    public abstract DocumentProcessor createProcessor();
}
