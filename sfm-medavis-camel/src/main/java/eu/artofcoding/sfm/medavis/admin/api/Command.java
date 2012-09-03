/*
 * sfm-medavis
 * sfm-medavis-camel
 * Copyright (C) 2011-2012 art of coding UG, http://www.art-of-coding.eu/
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 30.11.11 17:44
 */

package eu.artofcoding.sfm.medavis.admin.api;

public class Command {
    
    String name;
    String value;
    
    public void setName(String name) { this.name = name; }
    public String getName() { return name; }
    
    public void setValue(String value) { this.value = value; }
    public String getValue() { return value; }
    
}
