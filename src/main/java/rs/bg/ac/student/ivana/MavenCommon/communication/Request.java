package rs.bg.ac.student.ivana.MavenCommon.communication;

import java.io.Serializable;


public class Request  implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Operation operation;
    private Object argument;

    public Request() {
    }

    public Request(Operation operation, Object argument) {
        this.operation = operation;
        this.argument = argument;
    }

    public Object getArgument() {
        return argument;
    }

    public void setArgument(Object argument) {
        this.argument = argument;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }
    
    
}
