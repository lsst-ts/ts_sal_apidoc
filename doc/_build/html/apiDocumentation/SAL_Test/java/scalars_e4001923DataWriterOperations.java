/**
 * This file is auto-generated by idlpp
 * 
 * Source: sal_Test.idl
 * Generated on: 2021-04-15T19:02:33+0000
 * OpenSplice 6.11.0
 */
package Test;

public interface scalars_e4001923DataWriterOperations extends
    DDS.DataWriterOperations
{

    long register_instance(
            Test.scalars_e4001923 instance_data);

    long register_instance_w_timestamp(
            Test.scalars_e4001923 instance_data, 
            DDS.Time_t source_timestamp);

    int unregister_instance(
            Test.scalars_e4001923 instance_data, 
            long handle);

    int unregister_instance_w_timestamp(
            Test.scalars_e4001923 instance_data, 
            long handle, 
            DDS.Time_t source_timestamp);

    int write(
            Test.scalars_e4001923 instance_data, 
            long handle);

    int write_w_timestamp(
            Test.scalars_e4001923 instance_data, 
            long handle, 
            DDS.Time_t source_timestamp);

    int dispose(
            Test.scalars_e4001923 instance_data, 
            long instance_handle);

    int dispose_w_timestamp(
            Test.scalars_e4001923 instance_data, 
            long instance_handle, 
            DDS.Time_t source_timestamp);
    
    int writedispose(
            Test.scalars_e4001923 instance_data, 
            long instance_handle);

    int writedispose_w_timestamp(
            Test.scalars_e4001923 instance_data, 
            long instance_handle, 
            DDS.Time_t source_timestamp);

    int get_key_value(
            Test.scalars_e4001923Holder key_holder, 
            long handle);
    
    long lookup_instance(
            Test.scalars_e4001923 instance_data);

}