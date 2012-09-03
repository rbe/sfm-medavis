/*
 * SFM Medavis Tool
 * Copyright (C) 2011-2012 art of coding UG (haftungsbeschränkt).
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 */
package eu.artofcoding.sfm.medavis.csv.importer.impl;

import eu.artofcoding.sfm.medavis.csv.importer.api.Medavis10025CSVReader;
import eu.artofcoding.sfm.medavis.csv.importer.bean.Medavis10025Bean;
import eu.artofcoding.sfm.medavis.csv.importer.helper.ParseHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Read contents of a Medavis (weird) CSV file and put it into database:
 * #10025: Leistungen Material.
 * @author rbe
 */
public class Medavis10025CSVReaderImpl extends AbstractMedavisCSVReader<Medavis10025Bean> implements Medavis10025CSVReader {

    /**
     * Logger.
     */
    private Logger logger = LoggerFactory.getLogger(Medavis10025CSVReaderImpl.class);

    /**
     * Constructor.
     */
    public Medavis10025CSVReaderImpl() {
        //logger.debug(".init<>");
        clazz = Medavis10025Bean.class;
    }

    /**
     * @param bean A data bean.
     * @param list One line of the CSV file, containing Strings
     */
    @Override
    protected void parseData(Object bean, String[] list) {
        // Cast argument
        Medavis10025Bean medavisBean = (Medavis10025Bean) bean;
        // Number of columns
        int numberOfColumns = list.length;
        // Column counter
        int colCount = 0;
        // Process every column
        for (String entry : list) {
            // Fill Medavis10025Bean with data
            //   col =  0                            1           2                          3             4              5                     6              7                                          8        9          10
            //         ["Kostenträger",              "Episode",  "Patient",                 "Unt.-Datum", "Unt. Kürzel", "Unters.kurzbez.",    "Mat. Kürzel", "Material",                                "Menge", "Einheit", ""]
            //         ["_am_Kasse_ohne_Abrechnung", "12345678", "Xxxx, Xxxxx *11.01.1911", "22.02.2011", "xxxxxxx",     "CT Abdomen Routine", "BARI150",     "Barilux-CT Suspkonz. 150ml PZN -0101528", "1",     "ml",      ""]
            switch (colCount) {
                case 0:
                    medavisBean.setKostentraeger(entry);
                    break;
                case 1:
                    medavisBean.setEpisode(entry);
                    break;
                case 2:
                    // Split entry by '*' to get name and birthday
                    String[] patient = entry.split("\\*");
                    if (patient.length >= 1) {
                        medavisBean.setPatient(null != patient[0] ? patient[0].trim() : "Unbekannt?");
                    } else {
                        logger.error(String.format("parseData: Could not parse patient in line number %d '%s': Length of array NOT >= 1", totalLineNumber, entry));
                    }
                    if (patient.length >= 2) {
                        java.util.Date geburtstag = ParseHelper.parseDate(patient[1]);
                        medavisBean.setGeburtstag(geburtstag);
                    } else {
                        logger.error(String.format("parseData: Could not parse geburtstag in line number %d '%s': Length of array NOT >= 1", totalLineNumber, entry));
                    }
                    break;
                case 3:
                    java.util.Date d = ParseHelper.parseDate(entry);
                    medavisBean.setUntersuchung(d);
                    if (null == d) {
                        logger.error(String.format("parseData: Could not parse untersuchung in line number %d '%s'", totalLineNumber, entry));
                    }
                    break;
                case 4:
                    medavisBean.setUntKuerzel(entry);
                    break;
                case 5:
                    medavisBean.setUntKurzbezeichnung(entry);
                    break;
                case 6:
                    medavisBean.setMaterialKuerzel(entry);
                    break;
                case 7:
                    medavisBean.setMaterial(entry);
                    break;
                case 8:
                    medavisBean.setMenge(ParseHelper.parseDouble(entry));
                    break;
                case 9:
                    medavisBean.setEinheit(entry);
                    break;
            }
            // Increase column counter
            colCount++;
        }
    }

}
