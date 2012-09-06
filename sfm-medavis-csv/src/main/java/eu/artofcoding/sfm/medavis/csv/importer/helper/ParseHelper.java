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
package eu.artofcoding.sfm.medavis.csv.importer.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author rbe
 */
public class ParseHelper {

    /**
     * Logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ParseHelper.class);

    public static final Calendar CAL_1_1_1900;
    
    static {
        // Default birthday if none found/parseable
        CAL_1_1_1900 = Calendar.getInstance();
        CAL_1_1_1900.set(Calendar.YEAR, 1900);
        CAL_1_1_1900.set(Calendar.MONTH, Calendar.JANUARY);
        CAL_1_1_1900.set(Calendar.DAY_OF_MONTH, 1);
    }
    
    /**
     * Do not create an instance of this class.
     */
    private ParseHelper() {
    }

    /**
     * @param input String containing a parseable number.
     * @return Integer
     */
    public static Integer parseInteger(String input) {
        Integer i = null;
        try {
            i = Integer.valueOf(input);
        } catch (NumberFormatException e) {
            logger.debug("parseInteger: Could not parse integer '" + input + "': " + e.getMessage());
            i = 0;
        }
        return i;
    }

    /**
     * @param input String containing a parseable number.
     * @return Double
     */
    public static Double parseDouble(String input) {
        Double d = null;
        try {
            // Replace comma with dot
            input = input.replaceAll(",", ".");
            d = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            logger.debug("Cannot parse double '" + input + "': " + e.getMessage());
            d = 0.0d;
        }
        return d;
    }

    /**
     * @param input String containing a parseable date.
     * @return java.util.Date
     */
    public static java.util.Date parseDate(String input) {
        // Date formatter for german date.
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        java.util.Date date = null;
        try {
            date = sdf.parse(input);
        } catch (ParseException e) {
            logger.debug("Could not parse date '" + input + "': " + e.getMessage());
            date = null;
        } catch (NumberFormatException e) {
            logger.debug("Could not parse date '" + input + "': " + e.getMessage());
            date = null;
        }
        return date;
    }

    /**
     * @param input String containing a parseable date/time.
     * @return java.util.Date
     */
    public static java.util.Date parseDateTimeHHmm(String input) {
        // Date formatter for german date.
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        java.util.Date date = null;
        try {
            date = sdf.parse(input);
        } catch (ParseException e) {
            logger.debug("Could not parse date '" + input + "': " + e.getMessage());
            date = null;
        } catch (NumberFormatException e) {
            logger.debug("Could not parse date '" + input + "': " + e.getMessage());
            date = null;
        }
        return date;
    }

}
