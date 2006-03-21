/*
 * Copyright (c) 2005 www.honfig.org. All Rights Reserved.
 */
package org.honfig.util;

import org.honfig.MetaConfig;
import org.honfig.impl.DefaultMetaConfig;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * <p>Title: </p>
 * <p>Description: This class is responsible for loading users metaconfigurations.
 *    Custom metaconfigs are described in honfig.xml file. If this file is broken appropriate message is
 *    printed into the logs and only default metaconfig is available.
 *    The default metaconfig is loaded also. This metaconfig is always available. </p>
 *
 * @author <a href="hocon@homo-developerus.org">HoCon</a>
 * @version $Id: MetaConfigurationProvider.java,v 1.1 2006/03/21 20:32:54 conradh Exp $
 *          Date: 2005-01-14
 *          Time: 18:09:49
 */
public class MetaConfigurationProvider {
    private static final Logger log = Logger.getLogger(MetaConfigurationProvider.class.getName());

    public static final String HONFIG_XML = "honfig.xml"; //default metaconfiguration file

    public MetaConfigurationProvider() {

    }

    public final Map<String, MetaConfig> loadMetaConfigs(){
        Map<String, MetaConfig> metaConfigs = loadDefaultMetaConfig(); //initially load default metaConfig
        try {
            metaConfigs = parseMetaConfig(); //if everything will go OK custom metaConfig will be loaded
            log.fine("metaConfigs = " + metaConfigs);
        } catch (Exception e) {
            log.config(HONFIG_XML + "not parseable, useing default metaconfiguration." );
            log.fine( e.getMessage() );
            log.finest( e.toString() );
        }
        return metaConfigs;
    }

    private final Map<String, MetaConfig> parseMetaConfig() throws ParserConfigurationException, SAXException, IOException {
        final SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
        final InputStream is = this.getClass().getClassLoader().getResourceAsStream(HONFIG_XML);
        final HonfigSAXHandler honfigSAXHandler = new HonfigSAXHandler();
        parser.parse(is, honfigSAXHandler);
        return honfigSAXHandler.getMetaConfigs();
    }

    private final Map<String, MetaConfig> loadDefaultMetaConfig(){
        final Map<String, MetaConfig> map = new HashMap<String, MetaConfig>(1);
        final MetaConfig metaConfig = new DefaultMetaConfig();
        map.put( metaConfig.getName() , metaConfig );
        return map;
    }

}
