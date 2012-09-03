/*
 * SFM Medavis Tool
 * Copyright (C) 2011-2012 art of coding UG (haftungsbeschränkt).
 *
 * Nutzungslizenz siehe http://files.art-of-coding.eu/aoc/AOCPL_v10_de.html
 * Use is subject to license terms, see http://files.art-of-coding.eu/aoc/AOCPL_v10_en.html
 *
 */
package eu.artofcoding.sfm.medavis.csv.exporter.impl;

import eu.artofcoding.sfm.medavis.csv.exporter.api.CSVExporter;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

/**
 * Export a SQL view into a CSV file. Strategy is to send SQL query, read
 * metadata of result and produce a CSV file.
 * @author rbe
 */
public class CSVExporterImpl implements CSVExporter {

    /**
     * Logger.
     */
    private Logger logger = LoggerFactory.getLogger(CSVExporterImpl.class);
    /**
     * DataSource.
     */
    private DataSource dataSource;
    /**
     * Spring's JDBC template.
     */
    private JdbcTemplate jdbcTemplate;

    /**
     * Setter for dataSource.
     * @param dataSource
     */
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
        logger.debug("Got dataSource=" + dataSource + " and jdbcTemplate=" + jdbcTemplate);
    }

    /**
     * Constructor.
     */
    public CSVExporterImpl() {
        //logger.debug(".init<>");
    }

    /**
     * @param view
     * @return
     */
    private File viewToCsv(String view) {
        File file = null;
        OutputStream writer = null;
        int line = 0; // Line counter
        try {
            // Create temporary file
            file = File.createTempFile(view + "_", ".importer");
            file.deleteOnExit();
            // Open PrintWriter
            writer = new FileOutputStream(file);
            if (null != dataSource && null != jdbcTemplate && null != view) {
                // Setup StringBuilder
                StringBuilder sb = new StringBuilder();
                // Query database
                String sql = String.format("SELECT * FROM %s", view);
                List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
                // Process result
                for (Map<String, Object> row : result) {
                    // Write header in first line
                    if (line == 0) {
                        for (String key : row.keySet()) {
                            sb.append(key).append(";");
                        }
                        String header = sb.substring(0, sb.length() - 1);
                        if (logger.isDebugEnabled()) {
                            logger.debug(String.format("View %s, header=%s", view, header));
                        }
                        writer.write(header.getBytes("UTF-8"));
                        writer.write("\r\n".getBytes());
                    } else {
                        // Cleanup StringBuilder instance
                        sb.delete(0, sb.length());
                        for (String key : row.keySet()) {
                            sb.append(row.get(key)).append(";");
                        }
                        String content = sb.substring(0, sb.length() - 1);
                        writer.write(content.getBytes("UTF-8"));
                        writer.write("\r\n".getBytes());
                    }
                    if (logger.isDebugEnabled() && line % 1000 == 0) {
                        logger.debug(String.format("Processed 1000 lines, wrote to %s", file));
                    }
                    // Increase line counter
                    line++;
                }
            } else {
                throw new IllegalStateException(String.format("DataSource (%s), JdbcTemplate (%s) and/or view (%s) null", dataSource, jdbcTemplate, view));
            }
        } catch (IOException e) {
            logger.error(null, e);
        } finally {
            if (logger.isInfoEnabled()) {
                logger.info(String.format("Completed, see %s", file));
            }
            try {
                // Close writer
                writer.close();
            } catch (IOException e) {
                logger.error(null, e);
            }
        }
        return file;
    }

    /**
     * Compress a byte[] using gzip.
     * @param b The byte[] to compress. @params base64 Encode bytes as base64?
     * @return byte[]
     */
    private byte[] zip(byte[] b, boolean base64) throws IOException {
        ByteArrayOutputStream targetStream = new ByteArrayOutputStream();
        GZIPOutputStream zipStream = new GZIPOutputStream(targetStream);
        zipStream.write(b);
        zipStream.close();
        if (base64) {
            byte[] zipped = targetStream.toByteArray();
            targetStream.close();
            return null; // Groovy: zipped.encodeBase64();
        } else {
            return targetStream.toByteArray();
        }
    }

    /**
     * Convenience method for: {@link #zip(byte[], boolean)}
     * @param str The string to compress using gzip.
     * @return byte[]
     */
    private byte[] zip(String str) throws IOException {
        return zip(str.getBytes(), false);
    }

    /**
     * Compress a file and set it as output message.
     * @param out
     * @param file
     * @param compress
     * @throws FileNotFoundException
     * @throws IOException
     */
    private void putFileContentToMessageBody(Message out, File file, boolean compress) throws FileNotFoundException, IOException {
        FileInputStream fin = null;
        ByteArrayOutputStream bin = null;
        try {
            // Read file into byte[] and gzip it
            fin = new FileInputStream(file);
            bin = new ByteArrayOutputStream();
            byte[] buf = new byte[2 * 1024 * 1024];
            int buflen = 0;
            while ((buflen = fin.read(buf)) > -1) {
                bin.write(buf, 0, buflen);
            }
            byte[] b = null;
            if (compress) {
                b = zip(bin.toByteArray(), false);
                // Content-Enconding
                out.setHeader(Exchange.CONTENT_ENCODING, "gzip");
                out.setHeader("Vary", "Accept-Encoding");
                // Content-Length
                out.setHeader(Exchange.CONTENT_LENGTH, b.length);
            } else {
                // Content-Length
                out.setHeader(Exchange.CONTENT_LENGTH, file.length());
            }
            // Set content
            out.setBody(b);
            // Content-type
            out.setHeader(Exchange.CONTENT_TYPE, "text/html; charset=utf-8");
            // Cache
            out.setHeader("Cache-Control", "no-cache, no-store, max-age=0, must-revalidate");
            out.setHeader("Pragma", "no-cache");
        } // catch (Exception e) { logger.error(null, e); }
        finally {
            if (null != fin) {
                fin.close();
            }
            if (null != bin) {
                bin.close();
            }
            if (null != file) {
                file.delete();
            }
        }
    }

    /**
     * HTTP parameter name must be: http://.../export?name=<sql-view>
     * @param exchange
     * @throws Exception
     */
    @Override
    public void process(Exchange exchange) throws Exception {
        Message in = exchange.getIn();
        Message out = exchange.getOut();
        //
        File file;
        boolean compressed;
        try {
            // Name of SQL view to export
            String view = in.getHeader("name", String.class);
            file = viewToCsv(view);
            // Disposition: inline, attachment?
            String disposition = in.getHeader("disposition", String.class);
            if (null == disposition) {
                disposition = "attachment";
            }
            // Compress?
            String acceptEncoding = in.getHeader("Accept-Encoding", String.class);
            if (null != acceptEncoding && acceptEncoding.contains("gzip")) {
                if (logger.isInfoEnabled()) {
                    logger.info(String.format("Gzpping CSV file %s for view %s", file, view));
                }
                putFileContentToMessageBody(out, file, true);
                // Content-Disposition
                out.setHeader("Content-Disposition", String.format("%s; filename=%s.gz", disposition, file.getName()));
                compressed = true;
            } else {
                putFileContentToMessageBody(out, file, false);
                // Content-Disposition
                out.setHeader("Content-Disposition", String.format("%s; filename=%s", disposition, file.getName()));
                compressed = false;
            }
            // HTTP status code 200
            out.setHeader(Exchange.HTTP_RESPONSE_CODE, 200);
            // Log
            if (logger.isInfoEnabled()) {
                logger.info(String.format("Streaming %s (Accept-Encoding=%s) CSV file %s %s for view %s", compressed ? "compressed" : "uncompressed", acceptEncoding, file, compressed ? "len=" + file.length() + "/" + ((byte[]) out.getBody()).length + "/" + out.getHeader(Exchange.CONTENT_LENGTH) : "len=" + file.length(), view));
            }
        } catch (Exception e) {
            logger.error(null, e);
            // HTTP status code 500
            out.setHeader(Exchange.HTTP_RESPONSE_CODE, 500);
        }
    }
}
