/**
 * This file is auto-generated by idlpp
 * 
 * Source: sal_Test.idl
 * Generated on: 2021-04-15T19:02:29+0000
 * OpenSplice 6.11.0
 */
package Test;

import org.opensplice.dds.dcps.Utilities;

public final class command_disable_79c48b19DataReaderHelper
{

    public static Test.command_disable_79c48b19DataReader narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof Test.command_disable_79c48b19DataReader) {
            return (Test.command_disable_79c48b19DataReader)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

    public static Test.command_disable_79c48b19DataReader unchecked_narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof Test.command_disable_79c48b19DataReader) {
            return (Test.command_disable_79c48b19DataReader)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

}
