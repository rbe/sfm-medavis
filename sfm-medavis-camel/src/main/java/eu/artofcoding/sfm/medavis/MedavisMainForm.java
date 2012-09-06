/*
 * sfm-medavis
 * sfm-medavis-camel
 * Copyright (C) 2011-2012 art of coding UG, http://www.art-of-coding.eu/
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 03.09.12 19:55
 */

package eu.artofcoding.sfm.medavis;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MedavisMainForm {

    private JPanel medavisMainPanel;
    private JButton shutdownButton;

    public MedavisMainForm() {
        shutdownButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MedavisMain.shutdown();
            }
        });
    }

    public JFrame show() {
        JFrame frame = new JFrame("MedavisMainForm");
        frame.setContentPane(new MedavisMainForm().medavisMainPanel);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        return frame;
    }

    private void createUIComponents() {
        shutdownButton=new JButton(); 
    }
}
