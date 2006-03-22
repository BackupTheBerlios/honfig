package org.honfig.spi.provider;

import org.honfig.Configuration;
import org.honfig.impl.PropertiesConfigurationImpl;
import org.honfig.spi.MetaConfig;

import java.util.logging.Logger;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

/**
 * <p>Title: </p>
 * <p>Description:  </p>
 *
 * @author <a href="conradh@users.berlios.de">Conradh</a>
 * @version $Id: PropertiesXMLFileConfigurationProvider.java,v 1.1 2006/03/22 22:27:24 conradh Exp $
 *          Date: 2006-03-22
 *          Time: 22:38:37
 */
public class PropertiesXMLFileConfigurationProvider extends AbstractConfigurationProvider {
    private static final Logger log = Logger.getLogger(PropertiesXMLFileConfigurationProvider.class.getName());

    private static final String FILEEXT=".xml";



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
                props.loadFromXML(is);
            }else{
                log.warning( "Cannot find " + streamName + ".");
            }
        } catch (IOException e) {
            log.severe( "Cannot load " + streamName );
            log.severe( e.getMessage() );
        }
    }


}

