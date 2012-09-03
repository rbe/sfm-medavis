/*
 * sfm-medavis
 * sfm-medavis-camel
 * Copyright (C) 2011-2012 art of coding UG, http://www.art-of-coding.eu/
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 27.08.12 12:40
 */

package eu.artofcoding.sfm.medavis;

import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public class MedavisMain {

    private static final AtomicBoolean completed = new AtomicBoolean(false);

    private static final CountDownLatch latch = new CountDownLatch(1);

    private static ApplicationContext applicationContext0;
    
    /**
     * Stop Camel when JVM shuts down.
     */
    private static void addShutdownHook() {
        // Get Camel context
        final SpringCamelContext camel = (SpringCamelContext) applicationContext0.getBean("medavisCamelContext");
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    camel.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }

    public static void main(String[] args) throws Exception {
        // Spring Filesystem Application Context
        final String[] filesystemConfigLocation = new String[]{
                "conf/medavis-datasource.xml",
                "conf/system/medavis-csv-spring-beans.xml",
                "conf/system/medavis-camel-spring-beans.xml",
                "conf/medavis-camel.xml"
        };
        applicationContext0 = new FileSystemXmlApplicationContext(filesystemConfigLocation);
        // Add shutdown hook
        addShutdownHook();
        // Await countdown latch... it's intended to wait forever
        while (!completed.get()) {
            try {
                latch.await();
            } catch (InterruptedException e) {
                // ignore
            }
        }
    }

}
