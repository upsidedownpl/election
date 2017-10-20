package pl.mm.election.service.audit;

import java.io.Serializable;

public class AuditMessage implements Serializable {

	private static final long serialVersionUID = 5272182805665364531L;
	
	private final String msg;

	public AuditMessage(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}
	
}
