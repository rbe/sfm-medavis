/*
 * sfm-medavis
 * sfm-medavis-camel
 * Copyright (C) 2011-2012 art of coding UG, http://www.art-of-coding.eu/
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 27.08.12 12:41
 */
package eu.artofcoding.sfm.medavis.admin.api;

/**
 *
 */
public interface RouteRestService {

    /**
     *
     */
    public String startRoute(String which, String what) throws Exception;

    public String stopRoute(String which, String what) throws Exception;

    public String upload() throws Exception;

}
