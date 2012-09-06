/*
 * sfm-medavis
 * sfm-medavis-camel
 * Copyright (C) 2011-2012 art of coding UG, http://www.art-of-coding.eu/
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 03.09.12 19:52
 */

package eu.artofcoding.sfm.medavis;

import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import javax.swing.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public class MedavisMain {

    private static final AtomicBoolean completed = new AtomicBoolean(false);

    private static final CountDownLatch latch = new CountDownLatch(1);

    private static FileSystemXmlApplicationContext applicationContext0;

    private static JFrame medavisMainFrame;

    private static void addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                shutdown();
            }
        }));
    }

    public static void shutdown() {
        latch.countDown();
        completed.getAndSet(true);
        try {
            applicationContext0.refresh();
            // Get Camel context
            final SpringCamelContext camel = (SpringCamelContext) applicationContext0.getBean("medavisCamelContext");
            camel.stop();
            // Stop Spring
            applicationContext0.close();
            // Dispose main frame
            medavisMainFrame.dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        // Show main form
        medavisMainFrame = new MedavisMainForm().show();
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
