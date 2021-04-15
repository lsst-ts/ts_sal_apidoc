/**
 * This file is auto-generated by idlpp
 * 
 * Source: sal_Test.idl
 * Generated on: 2021-04-15T19:02:26+0000
 * OpenSplice 6.11.0
 */
package Test;

public interface command_startDataWriterOperations extends
    DDS.DataWriterOperations
{

    long register_instance(
            Test.command_start instance_data);

    long register_instance_w_timestamp(
            Test.command_start instance_data, 
            DDS.Time_t source_timestamp);

    int unregister_instance(
            Test.command_start instance_data, 
            long handle);

    int unregister_instance_w_timestamp(
            Test.command_start instance_data, 
            long handle, 
            DDS.Time_t source_timestamp);

    int write(
            Test.command_start instance_data, 
            long handle);

    int write_w_timestamp(
            Test.command_start instance_data, 
            long handle, 
            DDS.Time_t source_timestamp);

    int dispose(
            Test.command_start instance_data, 
            long instance_handle);

    int dispose_w_timestamp(
            Test.command_start instance_data, 
            long instance_handle, 
            DDS.Time_t source_timestamp);
    
    int writedispose(
            Test.command_start instance_data, 
            long instance_handle);

    int writedispose_w_timestamp(
            Test.command_start instance_data, 
            long instance_handle, 
            DDS.Time_t source_timestamp);

    int get_key_value(
            Test.command_startHolder key_holder, 
            long handle);
    
    long lookup_instance(
            Test.command_start instance_data);

}
