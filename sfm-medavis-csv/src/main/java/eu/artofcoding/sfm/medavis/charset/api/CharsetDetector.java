/*
 * SFM Medavis Tool
 * Copyright (C) 2011-2012 art of coding UG (haftungsbeschränkt).
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 */
package eu.artofcoding.sfm.medavis.charset.api;

/**
 *
 */
public interface CharsetDetector {

    /**
     * Detect charset of a file.
     * @param file java.io.File reference.
     * @return String Name of charset.
     */
    public String guessCharset(java.io.File file) throws java.io.IOException;

}
