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
package eu.artofcoding.sfm.medavis.csv.importer.api;

import org.apache.camel.Processor;

/**
 * Empty marker interface for OSGi service resolution.
 */
public interface Medavis307SQLImport<T> extends Processor {

    /**
     *
     */
    public void insert(T data);

}
