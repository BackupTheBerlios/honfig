package org.honfig;

import org.honfig.ex.NoSuchConfigurationException;
import org.honfig.ex.DefaultConfigurationNotFoundException;
import org.honfig.input.ConfigurationProvider;
import org.honfig.util.Constants;
import org.honfig.util.MetaConfigurationProvider;

import java.util.Map;
import java.util.logging.Logger;

/**
 * <p>Title: </p>
 * <p>Description:  </p>
 *
 * @author <a href="conradh@users.berlios.de">Conradh</a>
 * @version $Id: ConfigurationManager.java,v 1.2 2006/03/22 14:48:10 conradh Exp $
 *          Date: 2004-12-28
 *          Time: 21:56:56
 */
public class ConfigurationManager {
    private static final Logger log = Logger.getLogger(ConfigurationManager.class.getName());

    private Map<String, MetaConfig> metaConfigs;    

    public ConfigurationManager() {
        final MetaConfigurationProvider mcp = new MetaConfigurationProvider();
        /**
         * metaConfigurations are loaded from honfig.xml file or
         * SampleMetaConfig is used if honfig.xml is not available. In case of honfig.xml
         * availability collection of proper metaConfigurations can be loaded.
         */
        this.metaConfigs = mcp.loadMetaConfigs();
    }

    public Configuration getConfiguration(final String name) throws Exception {
        final MetaConfig metaConfig = this.metaConfigs.get(name);
        if ( metaConfig == null ){
            throw new NoSuchConfigurationException( name );
        }

        //get the CP which is able to load a configuration of a given type.
        ConfigurationProvider cp = metaConfig.getProvider();
        // load configuration based on the metaconfig.
        return cp.load( metaConfig );
    }

    public Configuration getConfiguration() throws Exception {
        final MetaConfig metaConfig = this.metaConfigs.get(Constants.DEFAULT_CONFIG);
        if ( metaConfig == null ){
            throw new DefaultConfigurationNotFoundException();
        }

        //get the CP which is able to load a configuration of a given type.
        ConfigurationProvider cp = metaConfig.getProvider();
        // load configuration based on the metaconfig.
        return cp.load( metaConfig );
    }

    
}
