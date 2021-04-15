/**
 * This file is auto-generated by idlpp
 * 
 * Source: sal_Test.idl
 * Generated on: 2021-04-15T19:02:33+0000
 * OpenSplice 6.11.0
 */
package Test;

public interface logevent_settingsApplied_02b3cc9eDataWriterOperations extends
    DDS.DataWriterOperations
{

    long register_instance(
            Test.logevent_settingsApplied_02b3cc9e instance_data);

    long register_instance_w_timestamp(
            Test.logevent_settingsApplied_02b3cc9e instance_data, 
            DDS.Time_t source_timestamp);

    int unregister_instance(
            Test.logevent_settingsApplied_02b3cc9e instance_data, 
            long handle);

    int unregister_instance_w_timestamp(
            Test.logevent_settingsApplied_02b3cc9e instance_data, 
            long handle, 
            DDS.Time_t source_timestamp);

    int write(
            Test.logevent_settingsApplied_02b3cc9e instance_data, 
            long handle);

    int write_w_timestamp(
            Test.logevent_settingsApplied_02b3cc9e instance_data, 
            long handle, 
            DDS.Time_t source_timestamp);

    int dispose(
            Test.logevent_settingsApplied_02b3cc9e instance_data, 
            long instance_handle);

    int dispose_w_timestamp(
            Test.logevent_settingsApplied_02b3cc9e instance_data, 
            long instance_handle, 
            DDS.Time_t source_timestamp);
    
    int writedispose(
            Test.logevent_settingsApplied_02b3cc9e instance_data, 
            long instance_handle);

    int writedispose_w_timestamp(
            Test.logevent_settingsApplied_02b3cc9e instance_data, 
            long instance_handle, 
            DDS.Time_t source_timestamp);

    int get_key_value(
            Test.logevent_settingsApplied_02b3cc9eHolder key_holder, 
            long handle);
    
    long lookup_instance(
            Test.logevent_settingsApplied_02b3cc9e instance_data);

}
