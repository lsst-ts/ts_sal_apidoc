/**
 * This file is auto-generated by idlpp
 * 
 * Source: sal_Test.idl
 * Generated on: 2021-04-15T19:02:24+0000
 * OpenSplice 6.11.0
 */
package Test;

import org.opensplice.dds.dcps.Utilities;

public final class arraysTypeSupportHelper
{

    public static Test.arraysTypeSupport narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof Test.arraysTypeSupport) {
            return (Test.arraysTypeSupport)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

    public static Test.arraysTypeSupport unchecked_narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof Test.arraysTypeSupport) {
            return (Test.arraysTypeSupport)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

}
