/**
 * This file is auto-generated by idlpp
 * 
 * Source: sal_Test.idl
 * Generated on: 2021-04-15T19:02:32+0000
 * OpenSplice 6.11.0
 */
package Test;

public class logevent_scalars_1d41189eTypeSupport extends org.opensplice.dds.dcps.TypeSupportImpl implements DDS.TypeSupportOperations
{
    private static final long serialVersionUID = 1L;

    private long copyCache;

    public logevent_scalars_1d41189eTypeSupport()
    {
        super("Test::logevent_scalars_1d41189e",
              "",
              "TestID",
              null,
              Test.logevent_scalars_1d41189eMetaHolder.metaDescriptor);
    }

    @Override
    protected DDS.DataWriter create_datawriter ()
    {
        return new logevent_scalars_1d41189eDataWriterImpl(this);
    }

    @Override
    protected DDS.DataReader create_datareader ()
    {
        return new logevent_scalars_1d41189eDataReaderImpl(this);
    }

    @Override
    protected DDS.DataReaderView create_dataview ()
    {
        return new logevent_scalars_1d41189eDataReaderViewImpl(this);
    }
}
