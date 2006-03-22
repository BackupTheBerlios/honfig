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
 *    This class does not onStartElement any particular configurations, it manages informations about available
 *    configurations. Its a meta config class.
 * </p>
 *
 * @author <a href="conradh@users.berlios.de">Conradh</a>
 * @version $Id: HonfigSAXHandler.java,v 1.3 2006/03/22 14:47:31 conradh Exp $
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
        if (this.currentElement != null ){
            this.currentElement.onStartElement(this, attributes);
        }
    }

    public void characters(final char[] ch, final int start, final int length) throws SAXException {
        if ( this.currentElement != null ){
            this.currentElement.onText( this, new String(ch, start, length) );
        }
    }

    public void endElement( final String uri , final String localName , final String qName ) throws SAXException {
        log.finest("qName = " + qName);
        this.currentElement = elist.get( qName);
        if (this.currentElement != null ){
            this.currentElement.onEndElement(this);
        }
    }

    public Map<String, MetaConfig>getMetaConfigs() {
        return this.metaConfigs;
    }


    private static class ElementHandler {
        public void onStartElement(final HonfigSAXHandler honfigHandler, final Attributes attributes) {}
        public void onText(final HonfigSAXHandler honfigHandler, final String str) {}
        public void onEndElement(final HonfigSAXHandler honfigHandler) {}
    }

    private static final class HonfigElementHandler extends ElementHandler {
        public void onStartElement(final HonfigSAXHandler honfigHandler, final Attributes attributes) {
            honfigHandler.metaConfigs = new HashMap<String, MetaConfig>(1);
        }

        public void onEndElement( final HonfigSAXHandler honfigHandler ) {
            if ( honfigHandler.defaultConfiguration != null ){
                MetaConfig mc = honfigHandler.metaConfigs.get( honfigHandler.defaultConfiguration );
                if ( mc != null ){
                    honfigHandler.metaConfigs.put( Constants.DEFAULT_CONFIG , mc);
                    log.info( "Default configuration set to: " + honfigHandler.defaultConfiguration );
                }
            }
        }
    }
    private static final class DefaultConfigurationElementHandler extends ElementHandler {
        public void onText(final HonfigSAXHandler honfigHandler, final String str) {
            honfigHandler.defaultConfiguration = str.trim();
            log.finer( "Default configuration will be :" + honfigHandler.defaultConfiguration );
        }
    }

    private static final class ConfigurationElementHandler extends ElementHandler {
        public void onStartElement(final HonfigSAXHandler honfigHandler, final Attributes attributes) {
            final String name = attributes.getValue(XA_NAME);
            honfigHandler.currentMetaConfig = new SampleMetaConfig();
            honfigHandler.currentMetaConfig.setName(name);
            honfigHandler.metaConfigs.put(name, honfigHandler.currentMetaConfig);
        }
    }

    private static final class ConfigurationChildrenElementHandler extends ElementHandler {
        private String name;
        public ConfigurationChildrenElementHandler( final String name ) { this.name = name; }

        public void onText(final HonfigSAXHandler honfigHandler, final String str) {
            honfigHandler.currentMetaConfig.set( this.name , str);
        }
    }


}
