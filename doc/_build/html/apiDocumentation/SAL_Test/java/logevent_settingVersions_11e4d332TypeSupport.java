/**
 * This file is auto-generated by idlpp
 * 
 * Source: sal_Test.idl
 * Generated on: 2021-04-15T19:02:32+0000
 * OpenSplice 6.11.0
 */
package Test;

public class logevent_settingVersions_11e4d332TypeSupport extends org.opensplice.dds.dcps.TypeSupportImpl implements DDS.TypeSupportOperations
{
    private static final long serialVersionUID = 1L;

    private long copyCache;

    public logevent_settingVersions_11e4d332TypeSupport()
    {
        super("Test::logevent_settingVersions_11e4d332",
              "",
              "TestID",
              null,
              Test.logevent_settingVersions_11e4d332MetaHolder.metaDescriptor);
    }

    @Override
    protected DDS.DataWriter create_datawriter ()
    {
        return new logevent_settingVersions_11e4d332DataWriterImpl(this);
    }

    @Override
    protected DDS.DataReader create_datareader ()
    {
        return new logevent_settingVersions_11e4d332DataReaderImpl(this);
    }

    @Override
    protected DDS.DataReaderView create_dataview ()
    {
        return new logevent_settingVersions_11e4d332DataReaderViewImpl(this);
    }
}
