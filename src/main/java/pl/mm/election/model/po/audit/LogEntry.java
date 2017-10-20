package pl.mm.election.model.po.audit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class LogEntry {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_logEntry")
	@SequenceGenerator(name="seq_logEntry", sequenceName = "seq_logEntry", allocationSize=50)
	private long id;
	
	private String msg;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
