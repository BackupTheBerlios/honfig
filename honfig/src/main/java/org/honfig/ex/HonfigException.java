/*
 * Copyright (c) 2005 www.honfig.org. All Rights Reserved.
 */
package org.honfig.ex;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 *
 * @author <a href="conradh@users.berlios.de">Conradh</a>
 * @version $Id: HonfigException.java,v 1.2 2006/03/22 19:30:52 conradh Exp $
 *          Date: 2005-11-08
 *          Time: 21:53:43
 */
public class HonfigException extends Exception {
    private final static long serialVersionUID = 1;

    private final int errNo;


    public HonfigException(String message, Throwable cause, int errNo) {
        super(message, cause);
        this.errNo = errNo;
    }


    public String getMessage() {
        return "HONFIG-" + this.errNo + ": " + super.getMessage();
    }
}
