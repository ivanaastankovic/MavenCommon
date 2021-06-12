package rs.bg.ac.student.ivana.MavenCommon.communication;

import java.io.Serializable;

public enum Operation implements Serializable{
    LOGIN,
    ADD_CLIENT,
    ADD_CLAIM,
    ADD_RISKTYPE,
    EDIT_CLIENT,
    EDIT_CLAIM,
    GETALL_CLAIMS,
    GETALLBYCLIENT_CLAIMS,
    GETALL_RISKTYPES,
    GETBYID_CLIENT,
    DELETE_CLIENT,
    DELETE_RISKTYPE,
    GETBYID_CLAIM,
    LOGOUT
    
    
}
