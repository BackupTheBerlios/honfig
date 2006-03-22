/*
 * Copyright (c) 2005 www.honfig.org. All Rights Reserved.
 */
package org.honfig.spi.provider;

import org.honfig.Configuration;
import org.honfig.MetaConfig;
import org.honfig.spi.provider.AbstractConfigurationProvider;
import org.honfig.impl.PropertiesConfigurationImpl;

import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 *
 * @author <a href="hocon@homo-developerus.org">HoCon</a>
 * @version $Id: PropertiesFileConfigurationProvider.java,v 1.1 2006/03/22 19:07:48 conradh Exp $
 *          Date: 2005-01-14
 *          Time: 19:07:48
 */
public class PropertiesFileConfigurationProvider extends AbstractConfigurationProvider {
    private static final Logger log = Logger.getLogger(PropertiesFileConfigurationProvider.class.getName());

    private static final String FILEEXT=".properties";



    public Configuration load( final MetaConfig metaConfig ) {
        Properties props = new Properties();
        log.finer("metaConfig = " + metaConfig);
        String token = "";
        try {
            token = (String) metaConfig.getAlgorithm().getToken();
        } catch ( Exception e ) {
            log.severe( "Cannot get token for : " + metaConfig.getAlgorithm().getClass().getName() + "\n" + e.getMessage() );
        }

        String name = metaConfig.getName();
        String commonStreamName = name + FILEEXT;
        String customStreamName = token + "-" + name + FILEEXT;

        loadStream( commonStreamName , props );
        loadStream( customStreamName , props );
        log.info( "Loaded " +props.size() + " entries.");

        Configuration configuration = new PropertiesConfigurationImpl( props );
        return configuration;
    }



    private void loadStream( final String streamName , final Properties props ) {
        try {
            log.config("streamName = " + streamName);
            InputStream is = getClass().getClassLoader().getResourceAsStream(streamName);
            if ( is != null ){
                props.load(is);
            }else{
                log.warning( "Cannot find " + streamName + ".");
            }
        } catch (IOException e) {
            log.severe( "Cannot load " + streamName );
            log.severe( e.getMessage() );
        }
    }


}
