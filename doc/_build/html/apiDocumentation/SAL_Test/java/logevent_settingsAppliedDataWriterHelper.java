/**
 * This file is auto-generated by idlpp
 * 
 * Source: sal_Test.idl
 * Generated on: 2021-04-15T19:02:28+0000
 * OpenSplice 6.11.0
 */
package Test;

import org.opensplice.dds.dcps.Utilities;

public final class logevent_settingsAppliedDataWriterHelper
{

    public static Test.logevent_settingsAppliedDataWriter narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof Test.logevent_settingsAppliedDataWriter) {
            return (Test.logevent_settingsAppliedDataWriter)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

    public static Test.logevent_settingsAppliedDataWriter unchecked_narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof Test.logevent_settingsAppliedDataWriter) {
            return (Test.logevent_settingsAppliedDataWriter)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

}
