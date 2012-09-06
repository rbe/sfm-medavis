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
package eu.artofcoding.sfm.medavis.admin.impl;

import org.apache.cxf.common.i18n.Exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;

/**
 * REST service implementation of the Camel batch service.
 */
@Path("/route")
public class RouteRestServiceImpl /*implements RouteRestService*/ {

    /**
     * Logger.
     */
    private Logger logger = LoggerFactory.getLogger(RouteRestServiceImpl.class);

    @GET
    @Path("/start/{which}/{what}")
    // cxf:rsServer[@address] + @Path(class AdminRestServiceImpl) + @Path(String startRoute())
    @Consumes("text/xml")
    @Produces("text/plain")
    public String startRoute(@PathParam("which") String which, @PathParam("what") String what) throws Exception {
        // http://camel.apache.org/cxfrs.html, "How to consume the REST request in Camel", "Note about the resource class"
        return null;
    }

    @GET
    @Path("/stop/{which}/{what}")
    // cxf:rsServer[@address] + @Path(class AdminRestServiceImpl) + @Path(String startRoute())
    @Consumes("text/xml")
    @Produces("text/plain")
    public String stopRoute(@PathParam("which") String which, @PathParam("what") String what) throws Exception {
        return null;
    }

    /*
    @PUT
    @Path("/upload")
    @Consumes("application/octet-stream")
    @Produces("text/plain")
    public String upload(@Multipart("file") java.io.File file) throws Exception {
        return null;
    }
    */
}
