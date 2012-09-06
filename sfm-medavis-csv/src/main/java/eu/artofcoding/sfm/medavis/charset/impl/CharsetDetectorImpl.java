/*
 * sfm-medavis
 * sfm-medavis-csv
 * Copyright (C) 2011-2012 art of coding UG, http://www.art-of-coding.eu/
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 27.08.12 11:55
 */
package eu.artofcoding.sfm.medavis.charset.impl;

import eu.artofcoding.sfm.medavis.charset.api.CharsetDetector;
import org.mozilla.universalchardet.UniversalDetector;

/**
 * Detect charset of a file.
 */
public class CharsetDetectorImpl implements CharsetDetector {

    /**
     * @see CharsetDetector#guessCharset(java.io.File)
     */
    @Override
    public String guessCharset(java.io.File file) throws java.io.IOException {
        byte[] buf = new byte[4096];
        java.io.FileInputStream fis = new java.io.FileInputStream(file);
        // (1)
        UniversalDetector detector = new UniversalDetector(null);
        // (2)
        int nread;
        while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {
            detector.handleData(buf, 0, nread);
        }
        // (3)
        detector.dataEnd();
        // (4)
        String encoding = detector.getDetectedCharset();
        if (encoding != null) {
            System.out.printf("Detected encoding = %s%n", encoding);
        } else {
            System.out.println("No encoding detected.");
        }
        // (5)
        detector.reset();
        //
        return encoding;
    }

}
