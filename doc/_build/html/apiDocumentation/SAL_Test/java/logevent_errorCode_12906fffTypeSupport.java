/**
 * This file is auto-generated by idlpp
 * 
 * Source: sal_Test.idl
 * Generated on: 2021-04-15T19:02:32+0000
 * OpenSplice 6.11.0
 */
package Test;

public class logevent_errorCode_12906fffTypeSupport extends org.opensplice.dds.dcps.TypeSupportImpl implements DDS.TypeSupportOperations
{
    private static final long serialVersionUID = 1L;

    private long copyCache;

    public logevent_errorCode_12906fffTypeSupport()
    {
        super("Test::logevent_errorCode_12906fff",
              "",
              "TestID",
              null,
              Test.logevent_errorCode_12906fffMetaHolder.metaDescriptor);
    }

    @Override
    protected DDS.DataWriter create_datawriter ()
    {
        return new logevent_errorCode_12906fffDataWriterImpl(this);
    }

    @Override
    protected DDS.DataReader create_datareader ()
    {
        return new logevent_errorCode_12906fffDataReaderImpl(this);
    }

    @Override
    protected DDS.DataReaderView create_dataview ()
    {
        return new logevent_errorCode_12906fffDataReaderViewImpl(this);
    }
}
