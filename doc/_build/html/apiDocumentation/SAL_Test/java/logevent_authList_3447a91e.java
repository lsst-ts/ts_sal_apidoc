/**
 * This file is auto-generated by idlpp
 * 
 * Source: sal_Test.idl
 * Generated on: 2021-04-15T19:02:34+0000
 * OpenSplice 6.11.0
 */
package Test;

public final class logevent_authList_3447a91e {

    public int TestID;
    public java.lang.String private_revCode = "";
    public double private_sndStamp;
    public double private_rcvStamp;
    public int private_seqNum;
    public java.lang.String private_identity = "";
    public int private_origin;
    public int private_host;
    public java.lang.String authorizedUsers = "";
    public java.lang.String nonAuthorizedCSCs = "";
    public int priority;

    public logevent_authList_3447a91e() {
    }

    public logevent_authList_3447a91e(
        int _TestID,
        java.lang.String _private_revCode,
        double _private_sndStamp,
        double _private_rcvStamp,
        int _private_seqNum,
        java.lang.String _private_identity,
        int _private_origin,
        int _private_host,
        java.lang.String _authorizedUsers,
        java.lang.String _nonAuthorizedCSCs,
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
        authorizedUsers = _authorizedUsers;
        nonAuthorizedCSCs = _nonAuthorizedCSCs;
        priority = _priority;
    }

}
