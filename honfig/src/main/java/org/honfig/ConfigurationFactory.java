package org.honfig;

import org.honfig.util.Constants;

import java.util.Map;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 *
 * @author <a href="conradh@users.berlios.de">Conradh</a>
 * @version $Id: ConfigurationFactory.java,v 1.2 2006/03/22 16:16:14 conradh Exp $
 *          Date: 2006-03-22
 *          Time: 10:04:05
 */
public class ConfigurationFactory {
    private static final Logger log = Logger.getLogger( ConfigurationFactory.class.getName());

    private static final ConfigurationFactory instance = new ConfigurationFactory();

    private final ConfigurationManager configurationManager;
    private final Map<String, Configuration> configs;

    private ConfigurationFactory() {
        this.configurationManager = new ConfigurationManager();
        this.configs = new HashMap<String, Configuration>(1);
    }

    public static Configuration getConfiguration( final String cfgName ) throws Exception {
        ConfigurationManager cm = new ConfigurationManager();
        Configuration cfg = cm.getConfiguration( cfgName );
        log.fine( "Configuration " + cfgName + " loaded.");
        return cfg;
    }

    public static Configuration getConfiguration( ) throws Exception {
        ConfigurationManager cm = new ConfigurationManager();
        Configuration cfg = cm.getConfiguration();
        log.fine( "Default configuration loaded.");
        return cfg;
    }



    public static Configuration getCachedConfiguration( final String cfgName ) throws Exception {
        Configuration conf = instance.configs.get( cfgName );
        if ( conf == null ){
            conf = instance.configurationManager.getConfiguration( cfgName ) ;
            instance.configs.put( cfgName , conf );
            log.fine( "Configuration " + cfgName + " loaded.");
        }else{
            log.fine( "Configuration " + cfgName + " found in cache.");
        }
        return conf;
    }

    public static Configuration getCachedConfiguration( ) throws Exception {
        Configuration conf = instance.configs.get( Constants.DEFAULT_CONFIG );
        if ( conf == null ){
            conf = instance.configurationManager.getConfiguration( ) ;
            instance.configs.put( Constants.DEFAULT_CONFIG , conf );
            log.fine( "Default configuration loaded.");
        }else{
            log.fine( "Default configuration found in cache.");
        }
        return conf;
    }
}
