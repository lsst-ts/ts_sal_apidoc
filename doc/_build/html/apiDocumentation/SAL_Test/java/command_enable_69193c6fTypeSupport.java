/**
 * This file is auto-generated by idlpp
 * 
 * Source: sal_Test.idl
 * Generated on: 2021-04-15T19:02:29+0000
 * OpenSplice 6.11.0
 */
package Test;

public class command_enable_69193c6fTypeSupport extends org.opensplice.dds.dcps.TypeSupportImpl implements DDS.TypeSupportOperations
{
    private static final long serialVersionUID = 1L;

    private long copyCache;

    public command_enable_69193c6fTypeSupport()
    {
        super("Test::command_enable_69193c6f",
              "",
              "TestID",
              null,
              Test.command_enable_69193c6fMetaHolder.metaDescriptor);
    }

    @Override
    protected DDS.DataWriter create_datawriter ()
    {
        return new command_enable_69193c6fDataWriterImpl(this);
    }

    @Override
    protected DDS.DataReader create_datareader ()
    {
        return new command_enable_69193c6fDataReaderImpl(this);
    }

    @Override
    protected DDS.DataReaderView create_dataview ()
    {
        return new command_enable_69193c6fDataReaderViewImpl(this);
    }
}
