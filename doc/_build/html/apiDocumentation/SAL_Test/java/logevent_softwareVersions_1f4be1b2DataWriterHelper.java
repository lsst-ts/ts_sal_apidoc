/**
 * This file is auto-generated by idlpp
 * 
 * Source: sal_Test.idl
 * Generated on: 2021-04-15T19:02:33+0000
 * OpenSplice 6.11.0
 */
package Test;

import org.opensplice.dds.dcps.Utilities;

public final class logevent_softwareVersions_1f4be1b2DataWriterHelper
{

    public static Test.logevent_softwareVersions_1f4be1b2DataWriter narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof Test.logevent_softwareVersions_1f4be1b2DataWriter) {
            return (Test.logevent_softwareVersions_1f4be1b2DataWriter)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

    public static Test.logevent_softwareVersions_1f4be1b2DataWriter unchecked_narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof Test.logevent_softwareVersions_1f4be1b2DataWriter) {
            return (Test.logevent_softwareVersions_1f4be1b2DataWriter)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

}
