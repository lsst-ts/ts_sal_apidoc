/**
 * This file is auto-generated by idlpp
 * 
 * Source: sal_Test.idl
 * Generated on: 2021-04-15T19:02:28+0000
 * OpenSplice 6.11.0
 */
package Test;

public class logevent_settingsAppliedTypeSupport extends org.opensplice.dds.dcps.TypeSupportImpl implements DDS.TypeSupportOperations
{
    private static final long serialVersionUID = 1L;

    private long copyCache;

    public logevent_settingsAppliedTypeSupport()
    {
        super("Test::logevent_settingsApplied",
              "",
              "TestID",
              null,
              Test.logevent_settingsAppliedMetaHolder.metaDescriptor);
    }

    @Override
    protected DDS.DataWriter create_datawriter ()
    {
        return new logevent_settingsAppliedDataWriterImpl(this);
    }

    @Override
    protected DDS.DataReader create_datareader ()
    {
        return new logevent_settingsAppliedDataReaderImpl(this);
    }

    @Override
    protected DDS.DataReaderView create_dataview ()
    {
        return new logevent_settingsAppliedDataReaderViewImpl(this);
    }
}
