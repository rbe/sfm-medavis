/*
 * sfm-medavis
 * sfm-medavis-camel
 * Copyright (C) 2011-2012 art of coding UG, http://www.art-of-coding.eu/
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 06.09.12 10:10
 */

package eu.artofcoding.sfm.medavis.admin.impl;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.StreamCache;
import org.apache.camel.converter.stream.FileInputStreamCache;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Camel controller bean involved in the starting route.
 */
public class FileUploadBeanImpl implements Processor {

    /**
     * Logger.
     */
    private Logger logger = LoggerFactory.getLogger(FileUploadBeanImpl.class);

    /**
     * Working directory, remember to change directory in Camel route. 
     */
    private final String workDirectory = "sfm-medavis-work";

    /**
     * HTTP parameter name must be: http://.../upload?name=<type><subtype>.importer
     * @param exchange
     * @throws Exception
     */
    @Override
    public void process(Exchange exchange) throws Exception {
        Message in = exchange.getIn();
        FileInputStreamCache body = in.getBody(FileInputStreamCache.class);
        /*
        // Dump headers
        Map<String, Object> headers = in.getHeaders();
        Object o = null;
        for (String k : headers.keySet()) {
            o = headers.get(k);
            System.out.println("k=" + k + " v=" + o + "/" + o.getClass().getName());
        }
        */
        // Filename and size
        String name = (String) in.getHeader("name");
        int contentLength = (int) in.getHeader("content-length", -1, Integer.class);
        // TODO Log contentLength
        try {
            // Content
            ByteArrayOutputStream baos = new ByteArrayOutputStream(contentLength);
            baos.write(body);
            File file = new File(String.format("%s/tmp", workDirectory), name + ".importer");
            logger.info("Got file = '" + file.getAbsolutePath() + "'");
            FileUtils.writeByteArrayToFile(file, baos.toByteArray());
            // Move file depending on its name
            boolean knownFileType = false;
            StringBuilder suffix = new StringBuilder();
            if (name.contains("medavis")) {
                suffix.append("medavis");
                knownFileType = true;
            } else if (name.contains("orbis")) {
                suffix.append("orbis");
                knownFileType = true;
            }
            if (knownFileType) {
                // Strip file type, remaining is subtype
                String name2 = name.replaceAll(suffix.toString(), "");
                suffix.append("/").append(name2).append("/");
                if (null != suffix && suffix.length() > 0) {
                    File dir = new File(String.format("%s/%s", workDirectory, suffix.toString()));
                    logger.info(String.format("Moving file '%s' to '%s'", file.getName(), dir.getAbsolutePath()));
                    FileUtils.moveFileToDirectory(file, dir, true);
                }
            } else {
                logger.error(String.format("Unknown file type, don't know where to move file '%s'", file.getName()));
            }
            // HTTP status code 200
            exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, 200);
        } catch (Exception e) {
            // HTTP status code 500
            exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, 500);
        }
    }

}
