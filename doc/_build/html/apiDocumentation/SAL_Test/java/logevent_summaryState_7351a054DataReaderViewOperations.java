/**
 * This file is auto-generated by idlpp
 * 
 * Source: sal_Test.idl
 * Generated on: 2021-04-15T19:02:33+0000
 * OpenSplice 6.11.0
 */
package Test;

public interface logevent_summaryState_7351a054DataReaderViewOperations extends
    DDS.DataReaderViewOperations
{

    int read(
            Test.logevent_summaryState_7351a054SeqHolder received_data, 
            DDS.SampleInfoSeqHolder info_seq, 
            int max_samples, 
            int sample_states, 
            int view_states, 
            int instance_states);

    int take(
            Test.logevent_summaryState_7351a054SeqHolder received_data, 
            DDS.SampleInfoSeqHolder info_seq, 
            int max_samples, 
            int sample_states, 
            int view_states, 
            int instance_states);

    int read_w_condition(
            Test.logevent_summaryState_7351a054SeqHolder received_data, 
            DDS.SampleInfoSeqHolder info_seq, 
            int max_samples, 
            DDS.ReadCondition a_condition);

    int take_w_condition(
            Test.logevent_summaryState_7351a054SeqHolder received_data, 
            DDS.SampleInfoSeqHolder info_seq, 
            int max_samples, 
            DDS.ReadCondition a_condition);

    int read_next_sample(
            Test.logevent_summaryState_7351a054Holder received_data, 
            DDS.SampleInfoHolder sample_info);

    int take_next_sample(
            Test.logevent_summaryState_7351a054Holder received_data, 
            DDS.SampleInfoHolder sample_info);

    int read_instance(
            Test.logevent_summaryState_7351a054SeqHolder received_data, 
            DDS.SampleInfoSeqHolder info_seq, 
            int max_samples,
            long a_handle, 
            int sample_states, 
            int view_states, 
            int instance_states);

    int take_instance(
            Test.logevent_summaryState_7351a054SeqHolder received_data, 
            DDS.SampleInfoSeqHolder info_seq, 
            int max_samples, 
            long a_handle, 
            int sample_states, 
            int view_states, 
            int instance_states);

    int read_next_instance(
            Test.logevent_summaryState_7351a054SeqHolder received_data, 
            DDS.SampleInfoSeqHolder info_seq, 
            int max_samples, 
            long a_handle, 
            int sample_states, 
            int view_states, 
            int instance_states);

    int take_next_instance(
            Test.logevent_summaryState_7351a054SeqHolder received_data, 
            DDS.SampleInfoSeqHolder info_seq, 
            int max_samples, 
            long a_handle, 
            int sample_states, 
            int view_states, 
            int instance_states);

    int read_next_instance_w_condition(
            Test.logevent_summaryState_7351a054SeqHolder received_data, 
            DDS.SampleInfoSeqHolder info_seq, 
            int max_samples, 
            long a_handle, 
            DDS.ReadCondition a_condition);

    int take_next_instance_w_condition(
            Test.logevent_summaryState_7351a054SeqHolder received_data, 
            DDS.SampleInfoSeqHolder info_seq, 
            int max_samples, 
            long a_handle, 
            DDS.ReadCondition a_condition);

    int return_loan(
            Test.logevent_summaryState_7351a054SeqHolder received_data, 
            DDS.SampleInfoSeqHolder info_seq);

    int get_key_value(
            Test.logevent_summaryState_7351a054Holder key_holder, 
            long handle);
    
    long lookup_instance(
            Test.logevent_summaryState_7351a054 instance);

}
