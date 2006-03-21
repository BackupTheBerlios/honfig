/*
 * Copyright (c) 2004 Your Corporation. All Rights Reserved.
 */
package org.honfig;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 *
 * @author <a href="hocon@homo-developerus.org">HoCon</a>
 * @version $Id: IdentityAlgorithm.java,v 1.1 2006/03/21 20:32:54 conradh Exp $
 *          Date: 2004-12-29
 *          Time: 01:19:12
 */
public interface IdentityAlgorithm {
    Object getToken() throws Exception;
}
