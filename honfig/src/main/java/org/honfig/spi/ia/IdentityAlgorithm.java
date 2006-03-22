/*
 * Copyright (c) 2004 Your Corporation. All Rights Reserved.
 */
package org.honfig.spi.ia;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 *
 * @author <a href="conradh@users.berlios.de">Conradh</a>
 * @version $Id: IdentityAlgorithm.java,v 1.1 2006/03/22 19:30:52 conradh Exp $
 *          Date: 2004-12-29
 *          Time: 01:19:12
 */
public interface IdentityAlgorithm {
    Object getToken() throws Exception;
}
