/**
 * This file is auto-generated by idlpp
 * 
 * Source: sal_Test.idl
 * Generated on: 2021-04-15T19:02:25+0000
 * OpenSplice 6.11.0
 */
package Test;

public interface command_disableDataReaderViewOperations extends
    DDS.DataReaderViewOperations
{

    int read(
            Test.command_disableSeqHolder received_data, 
            DDS.SampleInfoSeqHolder info_seq, 
            int max_samples, 
            int sample_states, 
            int view_states, 
            int instance_states);

    int take(
            Test.command_disableSeqHolder received_data, 
            DDS.SampleInfoSeqHolder info_seq, 
            int max_samples, 
            int sample_states, 
            int view_states, 
            int instance_states);

    int read_w_condition(
            Test.command_disableSeqHolder received_data, 
            DDS.SampleInfoSeqHolder info_seq, 
            int max_samples, 
            DDS.ReadCondition a_condition);

    int take_w_condition(
            Test.command_disableSeqHolder received_data, 
            DDS.SampleInfoSeqHolder info_seq, 
            int max_samples, 
            DDS.ReadCondition a_condition);

    int read_next_sample(
            Test.command_disableHolder received_data, 
            DDS.SampleInfoHolder sample_info);

    int take_next_sample(
            Test.command_disableHolder received_data, 
            DDS.SampleInfoHolder sample_info);

    int read_instance(
            Test.command_disableSeqHolder received_data, 
            DDS.SampleInfoSeqHolder info_seq, 
            int max_samples,
            long a_handle, 
            int sample_states, 
            int view_states, 
            int instance_states);

    int take_instance(
            Test.command_disableSeqHolder received_data, 
            DDS.SampleInfoSeqHolder info_seq, 
            int max_samples, 
            long a_handle, 
            int sample_states, 
            int view_states, 
            int instance_states);

    int read_next_instance(
            Test.command_disableSeqHolder received_data, 
            DDS.SampleInfoSeqHolder info_seq, 
            int max_samples, 
            long a_handle, 
            int sample_states, 
            int view_states, 
            int instance_states);

    int take_next_instance(
            Test.command_disableSeqHolder received_data, 
            DDS.SampleInfoSeqHolder info_seq, 
            int max_samples, 
            long a_handle, 
            int sample_states, 
            int view_states, 
            int instance_states);

    int read_next_instance_w_condition(
            Test.command_disableSeqHolder received_data, 
            DDS.SampleInfoSeqHolder info_seq, 
            int max_samples, 
            long a_handle, 
            DDS.ReadCondition a_condition);

    int take_next_instance_w_condition(
            Test.command_disableSeqHolder received_data, 
            DDS.SampleInfoSeqHolder info_seq, 
            int max_samples, 
            long a_handle, 
            DDS.ReadCondition a_condition);

    int return_loan(
            Test.command_disableSeqHolder received_data, 
            DDS.SampleInfoSeqHolder info_seq);

    int get_key_value(
            Test.command_disableHolder key_holder, 
            long handle);
    
    long lookup_instance(
            Test.command_disable instance);

}
