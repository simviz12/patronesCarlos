package com.globaldocs.model;

public enum DocumentFormat {
    PDF, DOC, DOCX, MD, CSV, TXT, XLSX;

    /**
     * Detects the DocumentFormat from a file extension string.
     * Returns null if the extension is not supported.
     */
    public static DocumentFormat fromExtension(String extension) {
        if (extension == null)
            return null;
        switch (extension.toLowerCase()) {
            case "pdf":
                return PDF;
            case "doc":
                return DOC;
            case "docx":
                return DOCX;
            case "md":
                return MD;
            case "csv":
                return CSV;
            case "txt":
                return TXT;
            case "xlsx":
                return XLSX;
            default:
                return null;
        }
    }

    /**
     * Returns a list of all supported extensions as strings.
     */
    public static String[] getSupportedExtensions() {
        return new String[] { "pdf", "doc", "docx", "md", "csv", "txt", "xlsx" };
    }
}
