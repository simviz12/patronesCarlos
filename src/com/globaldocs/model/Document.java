package com.globaldocs.model;

import java.io.File;

public class Document {
    private String name;
    private DocumentType type;
    private DocumentFormat format;
    private Country originCountry;
    private File file; // Reference to the actual uploaded file

    public Document(String name, DocumentType type, DocumentFormat format, Country originCountry) {
        this.name = name;
        this.type = type;
        this.format = format;
        this.originCountry = originCountry;
    }

    public Document(String name, DocumentType type, DocumentFormat format, Country originCountry, File file) {
        this.name = name;
        this.type = type;
        this.format = format;
        this.originCountry = originCountry;
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public DocumentType getType() {
        return type;
    }

    public DocumentFormat getFormat() {
        return format;
    }

    public Country getOriginCountry() {
        return originCountry;
    }

    public File getFile() {
        return file;
    }

    @Override
    public String toString() {
        return "Document{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", format=" + format +
                ", country=" + originCountry +
                (file != null ? ", file=" + file.getName() : "") +
                '}';
    }
}
