/**
 * This file is auto-generated by idlpp
 * 
 * Source: sal_Test.idl
 * Generated on: 2021-04-15T19:02:31+0000
 * OpenSplice 6.11.0
 */
package Test;

public class command_setValue_caab4364TypeSupport extends org.opensplice.dds.dcps.TypeSupportImpl implements DDS.TypeSupportOperations
{
    private static final long serialVersionUID = 1L;

    private long copyCache;

    public command_setValue_caab4364TypeSupport()
    {
        super("Test::command_setValue_caab4364",
              "",
              "TestID",
              null,
              Test.command_setValue_caab4364MetaHolder.metaDescriptor);
    }

    @Override
    protected DDS.DataWriter create_datawriter ()
    {
        return new command_setValue_caab4364DataWriterImpl(this);
    }

    @Override
    protected DDS.DataReader create_datareader ()
    {
        return new command_setValue_caab4364DataReaderImpl(this);
    }

    @Override
    protected DDS.DataReaderView create_dataview ()
    {
        return new command_setValue_caab4364DataReaderViewImpl(this);
    }
}
