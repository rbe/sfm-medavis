/*
 * SFM Medavis Tool
 * Copyright (C) 2011-2012 art of coding UG (haftungsbeschränkt).
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 */
package eu.artofcoding.sfm.medavis.csv.importer.impl;

import eu.artofcoding.sfm.medavis.csv.importer.api.OrbisIcpmSQLImport;
import eu.artofcoding.sfm.medavis.csv.importer.bean.OrbisIcpmBean;
import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author rbe
 */
public class OrbisIcpmSQLImportImpl implements OrbisIcpmSQLImport<OrbisIcpmBean> {

    /**
     * Logger.
     */
    private Logger logger = LoggerFactory.getLogger(OrbisIcpmSQLImportImpl.class);

    /**
     * DataSource.
     */
    private DataSource dataSource;

    /**
     * Spring's JDBC template.
     */
    private JdbcTemplate jdbcTemplate;

    private String sql = "INSERT INTO orbisicpm"
            + " (fachabteilung1, schluesselnr, katalog1, prozedur, fallhn, ophn, opbezug, g, durchgefuehrtam, fallnummer, name, vorname, geburtstag, vorstaufnahme, aufnahme, entlassung, titel, anrede, praefix, suffix, pid, fachabteilung, katalog)"
            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    /**
     * Setter for dataSource.
     */
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
        logger.debug(String.format("Got dataSource=%s and jdbcTemplate=%s", dataSource, jdbcTemplate));
    }

    /**
     *
     */
    @Override
    public void insert(OrbisIcpmBean orbisBean) {
        if (null != dataSource && null != orbisBean) {
            if (null != jdbcTemplate) {
                try {
                    jdbcTemplate.update(sql,
                            orbisBean.getFachabteilung1(),
                            orbisBean.getSchluesselnr(),
                            orbisBean.getKatalog1(),
                            orbisBean.getProzedur(),
                            orbisBean.getFallhn(),
                            orbisBean.getOphn(),
                            orbisBean.getOpbezug(),
                            orbisBean.getG(),
                            orbisBean.getDurchgefuehrtam(),
                            orbisBean.getFallnummer(),
                            orbisBean.getName(),
                            orbisBean.getVorname(),
                            orbisBean.getGeburtstag(),
                            orbisBean.getVorstaufnahme(),
                            orbisBean.getAufnahme(),
                            orbisBean.getEntlassung(),
                            orbisBean.getTitel(),
                            orbisBean.getAnrede(),
                            orbisBean.getPraefix(),
                            orbisBean.getSuffix(),
                            orbisBean.getPid(),
                            orbisBean.getFachabteilung(),
                            orbisBean.getKatalog());
                } catch (org.springframework.jdbc.BadSqlGrammarException e) {
                    logger.error(String.format("Could not insert data: %s", e.getMessage()));
                } catch (org.springframework.dao.DataIntegrityViolationException e) {
                    // TODO Ignore or write line into error-file?
                    logger.error(String.format("Cannot insert data: %s: %s", orbisBean, e.getMessage()));
                }
            } else {
                logger.error("Sorry, no jdbcTemplate!");
            }
        } else {
            logger.error(String.format("Sorry, no dataSource(=%s) or orbisBean(=%s)", dataSource, orbisBean));
        }
    }

    /**
     * Camel Processor.
     */
    @Override
    public void process(Exchange exchange) throws Exception {
        // Get payload from incoming message body
        List<OrbisIcpmBean> payload = (List<OrbisIcpmBean>) exchange.getIn().getBody();
        // Process every parsed line from CSV file
        for (OrbisIcpmBean orbisBean : payload) {
            insert(orbisBean);
        }
    }

}
