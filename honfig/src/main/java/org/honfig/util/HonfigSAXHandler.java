package org.honfig.util;

import org.honfig.MetaConfig;
import org.honfig.impl.SampleMetaConfig;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * <p>Title: </p>
 * <p>Description: This class is responsible for parsing honfig.xml file. Parsed infromations about available/define
 *    configuration is stored in a map. This map contains MetaConfig objects with adequate configurations.
 *    This class does not onElement any particular configurations, it manages informations about available
 *    configurations. Its a meta config class.
 * </p>
 *
 * @author <a href="conradh@users.berlios.de">Conradh</a>
 * @version $Id: HonfigSAXHandler.java,v 1.2 2006/03/22 13:17:20 conradh Exp $
 *          Date: 2004-12-28
 *          Time: 22:54:43
 */
public class HonfigSAXHandler extends DefaultHandler {
    private static final Logger log = Logger.getLogger(HonfigSAXHandler.class.getName());

    //maybe this definitions should be placed in separete interface.
    public static final String XE_HONFIG = "honfig";
    public static final String XE_CONFIGURATION = "configuration";
    public static final String XE_DEFAULTCONFIGURATION = "defaultConfiguration";
    public static final String XA_NAME = "name";
    public static final String XA_TYPE = "type";
    public static final String XE_DESCRIPTION = "description";
    public static final String XE_ALGORITHM = "algorithm";
    public static final String XE_PROVIDER = "provider";
    public static final String XE_PATH = "path";


    private static final Map<String, ElementHandler> elist = new HashMap<String, ElementHandler>(5);

    /* Initiate elements handler map. This elements are from XML config file. Each element has its SAX handler
     * HonfigElementHandler handles parsing main element in honfig.xml file
     * ConfigurationelementHandlef handles configuration element
     * ElementHandler is a generic handler for elements inside configuration element.
     */
    static {
        elist.put(XE_HONFIG, new HonfigElementHandler());
        elist.put(XE_DEFAULTCONFIGURATION, new DefaultConfigurationElementHandler());
        elist.put(XE_CONFIGURATION, new ConfigurationElementHandler());
        elist.put(XE_DESCRIPTION, new ConfigurationChildrenElementHandler( XE_DESCRIPTION ));
        elist.put(XE_ALGORITHM, new ConfigurationChildrenElementHandler( XE_ALGORITHM ));
        elist.put(XE_PATH, new ConfigurationChildrenElementHandler( XE_PATH ));
        elist.put(XE_PROVIDER, new ConfigurationChildrenElementHandler( XE_PROVIDER ));
    }

    /* This map contains list of parsed configurations
    */
    private Map<String, MetaConfig> metaConfigs = null;

    //internal field, contains currently parsed configuration
    private SampleMetaConfig currentMetaConfig = null;

    //internal field, contains currently parsed element.
    private ElementHandler currentElement = null;

    //The name of configuratin that was marked as default. 
    private String defaultConfiguration = null;

    public HonfigSAXHandler() {
    }

    public void startElement(final String uri, final String localName, final String qName, final Attributes attributes) throws SAXException {
        log.finest("qName = " + qName);
        this.currentElement = elist.get( qName);
        this.currentElement.onElement(this, attributes);
    }

    public void characters(final char[] ch, final int start, final int length) throws SAXException {
        if ( this.currentElement != null ){
            this.currentElement.onText( this, new String(ch, start, length) );
        }
    }

    public Map<String, MetaConfig>getMetaConfigs() {
        return this.metaConfigs;
    }

    public String getDefaultConfiguration() {
        return defaultConfiguration;
    }


    private static class ElementHandler {
        public void onElement(final HonfigSAXHandler saxHandler, final Attributes attributes) {}
        public void onText(final HonfigSAXHandler saxHandler, final String str) {}
    }

    private static final class HonfigElementHandler extends ElementHandler {
        public void onElement(final HonfigSAXHandler saxHandler, final Attributes attributes) {
            saxHandler.metaConfigs = new HashMap<String, MetaConfig>(1);
        }
    }
    private static final class DefaultConfigurationElementHandler extends ElementHandler {
        public void onText(final HonfigSAXHandler saxHandler, final String str) {
            saxHandler.defaultConfiguration = str;
        }
    }

    private static final class ConfigurationElementHandler extends ElementHandler {
        public void onElement(final HonfigSAXHandler saxHandler, final Attributes attributes) {
            final String name = attributes.getValue(XA_NAME);
            saxHandler.currentMetaConfig = new SampleMetaConfig();
            saxHandler.currentMetaConfig.setName(name);
            saxHandler.metaConfigs.put(name, saxHandler.currentMetaConfig);
        }
    }

    private static final class ConfigurationChildrenElementHandler extends ElementHandler {
        private String name;
        public ConfigurationChildrenElementHandler( final String name ) { this.name = name; }

        public void onText(final HonfigSAXHandler saxHandler, final String str) {
            saxHandler.currentMetaConfig.set( this.name , str);
        }
    }


}
