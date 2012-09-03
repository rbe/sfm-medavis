/*
 * SFM Medavis Tool
 * Copyright (C) 2011-2012 art of coding UG (haftungsbeschränkt).
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 */
package eu.artofcoding.sfm.medavis.csv.importer.api;

import org.apache.camel.Processor;

/**
 * Empty marker interface for OSGi service resolution.
 */
public interface Medavis10025SQLImport<T> extends Processor {

    /**
     *
     */
    public void insert(T data);

}
