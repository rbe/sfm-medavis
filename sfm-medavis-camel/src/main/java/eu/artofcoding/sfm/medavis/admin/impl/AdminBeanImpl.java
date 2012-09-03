/*
 * sfm-medavis
 * sfm-medavis-camel
 * Copyright (C) 2011-2012 art of coding UG, http://www.art-of-coding.eu/
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 27.08.12 11:16
 */
package eu.artofcoding.sfm.medavis.admin.impl;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.apache.camel.Message;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.apache.cxf.common.i18n.Exception;
import org.apache.cxf.message.MessageContentsList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * NOT USED.
 * Camel controller bean involved in the starting route.
 */
public class AdminBeanImpl {

    /**
     * Logger.
     */
    private Logger logger = LoggerFactory.getLogger(AdminBeanImpl.class);

    private String routeId;

    public String getRouteId() {
        return this.routeId;
    }

    public void setRouteId(String routeId) {
        logger.info("Got route id: " + routeId);
        this.routeId = routeId;
    }

    @Handler
    public String handler(CamelContext camelContext, Message message) throws Exception {
        // Get the operation name from in message
        String operationName = message.getHeader(CxfConstants.OPERATION_NAME, String.class);
        logger.info("operationName=" + operationName);
        String httpMethod = message.getHeader(Exchange.HTTP_METHOD, String.class);
        logger.info("httpMethod=" + httpMethod);
        String path = message.getHeader(Exchange.HTTP_PATH, String.class);
        logger.info("path=" + path);
        //
        Object body = (Object) message.getBody(byte[].class);
        if (null != body) {
            logger.info("Message body: " + message.getClass().getName() + ": " + body + "/" + body.getClass().getName());
            //for (int i = 0; i < which.length; i++) logger.info("w=" + which[i]);
            if (body instanceof MessageContentsList) {
                MessageContentsList mcl = (MessageContentsList) body;
                int bodySize = mcl.size();
                logger.info("body.size()=" + bodySize);
                Object elem0 = null;
                for (int i = 0; i < bodySize; i++) {
                    elem0 = mcl.get(i);
                    logger.info("... " + i + ": " + elem0 + "/" + elem0.getClass().getName());
                }
            }
        }
        //
        //logger.info("Starting route id: ? for Camel context: " + camelContext);
        //camelContext.startRoute(routeId);
        return "Yeeha.";
    }

}
