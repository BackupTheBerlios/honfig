package org.honfig.util;

import org.honfig.MetaConfig;
import org.honfig.impl.DefaultMetaConfig;
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
 *    This class does not parse any particular configurations, it manages informations about available
 *    configurations. Its a meta config class.
 * </p>
 *
 * @author <a href="hocon@homo-developerus.org">HoCon</a>
 * @version $Id: HonfigSAXHandler.java,v 1.1 2006/03/21 20:32:54 conradh Exp $
 *          Date: 2004-12-28
 *          Time: 22:54:43
 */
public class HonfigSAXHandler extends DefaultHandler {
    private static final Logger log = Logger.getLogger(HonfigSAXHandler.class.getName());

    //maybe this definitions should be placed in separete interface.
    public static final String XE_HONFIG = "honfig";
    public static final String XE_CONFIGURATION = "configuration";
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
        elist.put(XE_CONFIGURATION, new ConfigurationElementHandler());
        elist.put(XE_DESCRIPTION, new ElementHandler());
        elist.put(XE_ALGORITHM, new ElementHandler());
        elist.put(XE_PATH, new ElementHandler());
        elist.put(XE_PROVIDER, new ElementHandler());
    }

    /* This map contains list of parsed configurations
    */
    private Map<String, MetaConfig> metaConfigs = null;

    //internal filed, contains carrently parsed configuration
    private DefaultMetaConfig currentMetaConfig = null;

    //internal filed, contains carrently parsed element.
    private String currentElement = null;

    public HonfigSAXHandler() {
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        log.fine("qName = " + qName);
        final ElementHandler eh = elist.get(qName);
        if (eh != null) {
            this.currentElement = qName;
            eh.parse(this, attributes);
        }
    }

    public void characters(char ch[], int start, int length) throws SAXException {
        if (this.currentMetaConfig != null) {
            final String value = new String(ch, start, length);
            log.fine("this.currentElement = " + this.currentElement);
            log.fine("value = " + value);
            this.currentMetaConfig.set(this.currentElement, value);
            this.currentElement = null;
        }
    }


    public Map<String, MetaConfig>getMetaConfigs() {
        return this.metaConfigs;
    }

    private static class ElementHandler {
        public void parse(final HonfigSAXHandler saxHandler, final Attributes attributes) {
        }
    }

    private static final class HonfigElementHandler extends ElementHandler {
        public void parse(final HonfigSAXHandler saxHandler, final Attributes attributes) {
            saxHandler.metaConfigs = new HashMap<String, MetaConfig>(1);
        }
    }

    private static final class ConfigurationElementHandler extends ElementHandler {
        public void parse(final HonfigSAXHandler saxHandler, final Attributes attributes) {
            final String name = attributes.getValue(XA_NAME);
            saxHandler.currentMetaConfig = new DefaultMetaConfig();
            saxHandler.currentMetaConfig.setName(name);
            saxHandler.metaConfigs.put(name, saxHandler.currentMetaConfig);
//            final String type = attributes.getValue(XA_TYPE);
//            saxHandler.currentMetaConfig.setProvider(type);
        }
    }


}
