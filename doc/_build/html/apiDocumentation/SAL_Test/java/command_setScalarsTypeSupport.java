/**
 * This file is auto-generated by idlpp
 * 
 * Source: sal_Test.idl
 * Generated on: 2021-04-15T19:02:26+0000
 * OpenSplice 6.11.0
 */
package Test;

public class command_setScalarsTypeSupport extends org.opensplice.dds.dcps.TypeSupportImpl implements DDS.TypeSupportOperations
{
    private static final long serialVersionUID = 1L;

    private long copyCache;

    public command_setScalarsTypeSupport()
    {
        super("Test::command_setScalars",
              "",
              "TestID",
              null,
              Test.command_setScalarsMetaHolder.metaDescriptor);
    }

    @Override
    protected DDS.DataWriter create_datawriter ()
    {
        return new command_setScalarsDataWriterImpl(this);
    }

    @Override
    protected DDS.DataReader create_datareader ()
    {
        return new command_setScalarsDataReaderImpl(this);
    }

    @Override
    protected DDS.DataReaderView create_dataview ()
    {
        return new command_setScalarsDataReaderViewImpl(this);
    }
}
