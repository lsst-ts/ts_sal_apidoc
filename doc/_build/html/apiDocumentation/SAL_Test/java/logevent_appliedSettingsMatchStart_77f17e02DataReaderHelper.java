/**
 * This file is auto-generated by idlpp
 * 
 * Source: sal_Test.idl
 * Generated on: 2021-04-15T19:02:31+0000
 * OpenSplice 6.11.0
 */
package Test;

import org.opensplice.dds.dcps.Utilities;

public final class logevent_appliedSettingsMatchStart_77f17e02DataReaderHelper
{

    public static Test.logevent_appliedSettingsMatchStart_77f17e02DataReader narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof Test.logevent_appliedSettingsMatchStart_77f17e02DataReader) {
            return (Test.logevent_appliedSettingsMatchStart_77f17e02DataReader)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

    public static Test.logevent_appliedSettingsMatchStart_77f17e02DataReader unchecked_narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof Test.logevent_appliedSettingsMatchStart_77f17e02DataReader) {
            return (Test.logevent_appliedSettingsMatchStart_77f17e02DataReader)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

}