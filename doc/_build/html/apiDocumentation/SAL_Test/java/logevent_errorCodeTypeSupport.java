/**
 * This file is auto-generated by idlpp
 * 
 * Source: sal_Test.idl
 * Generated on: 2021-04-15T19:02:27+0000
 * OpenSplice 6.11.0
 */
package Test;

public class logevent_errorCodeTypeSupport extends org.opensplice.dds.dcps.TypeSupportImpl implements DDS.TypeSupportOperations
{
    private static final long serialVersionUID = 1L;

    private long copyCache;

    public logevent_errorCodeTypeSupport()
    {
        super("Test::logevent_errorCode",
              "",
              "TestID",
              null,
              Test.logevent_errorCodeMetaHolder.metaDescriptor);
    }

    @Override
    protected DDS.DataWriter create_datawriter ()
    {
        return new logevent_errorCodeDataWriterImpl(this);
    }

    @Override
    protected DDS.DataReader create_datareader ()
    {
        return new logevent_errorCodeDataReaderImpl(this);
    }

    @Override
    protected DDS.DataReaderView create_dataview ()
    {
        return new logevent_errorCodeDataReaderViewImpl(this);
    }
}