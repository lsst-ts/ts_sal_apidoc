/**
 * This file is auto-generated by idlpp
 * 
 * Source: sal_Test.idl
 * Generated on: 2021-04-15T19:02:33+0000
 * OpenSplice 6.11.0
 */
package Test;

public final class command_wait {

    public int TestID;
    public java.lang.String private_revCode = "";
    public double private_sndStamp;
    public double private_rcvStamp;
    public int private_seqNum;
    public java.lang.String private_identity = "";
    public int private_origin;
    public int private_host;
    public int ack;
    public double duration;

    public command_wait() {
    }

    public command_wait(
        int _TestID,
        java.lang.String _private_revCode,
        double _private_sndStamp,
        double _private_rcvStamp,
        int _private_seqNum,
        java.lang.String _private_identity,
        int _private_origin,
        int _private_host,
        int _ack,
        double _duration)
    {
        TestID = _TestID;
        private_revCode = _private_revCode;
        private_sndStamp = _private_sndStamp;
        private_rcvStamp = _private_rcvStamp;
        private_seqNum = _private_seqNum;
        private_identity = _private_identity;
        private_origin = _private_origin;
        private_host = _private_host;
        ack = _ack;
        duration = _duration;
    }

}
