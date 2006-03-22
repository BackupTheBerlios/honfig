/*
 * Copyright (c) 2005 www.honfig.org. All Rights Reserved.
 */
package org.honfig.test;

import junit.framework.TestCase;
import org.honfig.Configuration;
import org.honfig.ConfigurationFactory;

import java.util.logging.Logger;

public class ConfigurationManagerTest extends TestCase {
    private static final Logger log = Logger.getLogger( ConfigurationManagerTest.class.getName());
    public ConfigurationManagerTest() {
    }

    public void testGetConfiguration()  {
        try{
            final Configuration conf = ConfigurationFactory.getConfiguration( "config2");
            log.fine( "conf" + conf);
            final Configuration conf2 = ConfigurationFactory.getConfiguration();
            System.out.println( "conf2 = " + conf2 ); 
            assertEquals( "Override prop error", conf.get("foo"), "barbar" );
            assertEquals( "new prop error", conf.get("alaala"), "alaala" );
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}