/**
 * This file is auto-generated by idlpp
 * 
 * Source: sal_Test.idl
 * Generated on: 2021-04-15T19:02:33+0000
 * OpenSplice 6.11.0
 */
package Test;

public class scalars_e4001923TypeSupport extends org.opensplice.dds.dcps.TypeSupportImpl implements DDS.TypeSupportOperations
{
    private static final long serialVersionUID = 1L;

    private long copyCache;

    public scalars_e4001923TypeSupport()
    {
        super("Test::scalars_e4001923",
              "",
              "TestID",
              null,
              Test.scalars_e4001923MetaHolder.metaDescriptor);
    }

    @Override
    protected DDS.DataWriter create_datawriter ()
    {
        return new scalars_e4001923DataWriterImpl(this);
    }

    @Override
    protected DDS.DataReader create_datareader ()
    {
        return new scalars_e4001923DataReaderImpl(this);
    }

    @Override
    protected DDS.DataReaderView create_dataview ()
    {
        return new scalars_e4001923DataReaderViewImpl(this);
    }
}
