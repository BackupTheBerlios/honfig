package org.honfig.impl;

import org.honfig.IdentityAlgorithm;
import org.honfig.MetaConfig;
import org.honfig.ex.CannotSetIdentityAlgorithmException;
import org.honfig.ex.CannotSetProviderException;
import org.honfig.input.ConfigurationProvider;
import org.honfig.input.PropertiesFileConfigurationProvider;
import org.honfig.util.Constants;
import org.honfig.util.HonfigSAXHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * <p>Title: </p>
 * <p>Description: This class represents default implementation of MetaConfig interface.
 *      SampleMetaConfig is used </p>
 *
 * @author <a href="conradh@users.berlios.de">Conradh</a>
 * @version $Id: SampleMetaConfig.java,v 1.1 2006/03/22 12:06:25 conradh Exp $
 *          Date: 2004-12-28
 *          <p/>
 *          Time: 23:38:11
 */
public class SampleMetaConfig implements MetaConfig {
    private static final Logger log = Logger.getLogger(SampleMetaConfig.class.getName());


    private String name;
    private ConfigurationProvider provider;
    private IdentityAlgorithm identityAlgorithm;
    private Map<String, Object> values;


    public SampleMetaConfig() {
        this.values = new HashMap<String, Object>();
        this.name = Constants.DEFAULT_CONFIG_NAME;
        this.provider = new PropertiesFileConfigurationProvider();
        this.identityAlgorithm = new IPIdentityAlgorithm();
        setDescription( "The most basic configuration" );
    }

    public Object get(final String key) {
        return values.get(key);
    }

    public void set(final String key, final Object value) {
        this.values.put(key, value);
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public ConfigurationProvider getProvider() {
        String cp = (String) get(HonfigSAXHandler.XE_PROVIDER);
         try {
            final Class clazz = getClass().getClassLoader().loadClass(cp);
            this.provider = (ConfigurationProvider) clazz.newInstance();
        } catch (Exception e) {
            log.severe( "" + e);
            throw new CannotSetProviderException( cp , e );
        }
        return this.provider;
    }

    public void setProvider(final String provider) {
        set(HonfigSAXHandler.XE_PROVIDER, provider );
    }

    public String getDescription() {
        return (String) get( HonfigSAXHandler.XE_DESCRIPTION );
    }

    public void setDescription(final String description) {
        set(HonfigSAXHandler.XE_DESCRIPTION , description );
    }

    public IdentityAlgorithm getAlgorithm() {
        String algorithm = (String) get( HonfigSAXHandler.XE_ALGORITHM );
        try {
            final Class clazz = getClass().getClassLoader().loadClass(algorithm);
            this.identityAlgorithm = (IdentityAlgorithm) clazz.newInstance();
        } catch (Exception e) {
            log.severe( "" + e);
            throw new CannotSetIdentityAlgorithmException( algorithm , e );
        }
        return this.identityAlgorithm;
    }

    public void setAlgorithm(final String algorithm) {
        set( HonfigSAXHandler.XE_ALGORITHM , algorithm );
    }

    public String toString() {
        return getClass().getName() + "{" +
                "name: " + getName() + ", " +
                "provider: " + getProvider() + ", " +
                "identityAlgorithm: " + getAlgorithm().getClass().getName() + ", " +
                "description: " + getDescription() + "}";
    }
}
