/**
 * This file is auto-generated by idlpp
 * 
 * Source: sal_Test.idl
 * Generated on: 2021-04-15T19:02:34+0000
 * OpenSplice 6.11.0
 */
package Test;

public final class logevent_heartbeat {

    public int TestID;
    public java.lang.String private_revCode = "";
    public double private_sndStamp;
    public double private_rcvStamp;
    public int private_seqNum;
    public java.lang.String private_identity = "";
    public int private_origin;
    public int private_host;
    public boolean heartbeat;
    public int priority;

    public logevent_heartbeat() {
    }

    public logevent_heartbeat(
        int _TestID,
        java.lang.String _private_revCode,
        double _private_sndStamp,
        double _private_rcvStamp,
        int _private_seqNum,
        java.lang.String _private_identity,
        int _private_origin,
        int _private_host,
        boolean _heartbeat,
        int _priority)
    {
        TestID = _TestID;
        private_revCode = _private_revCode;
        private_sndStamp = _private_sndStamp;
        private_rcvStamp = _private_rcvStamp;
        private_seqNum = _private_seqNum;
        private_identity = _private_identity;
        private_origin = _private_origin;
        private_host = _private_host;
        heartbeat = _heartbeat;
        priority = _priority;
    }

}
