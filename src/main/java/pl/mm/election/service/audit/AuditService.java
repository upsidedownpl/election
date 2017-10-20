package pl.mm.election.service.audit;

public interface AuditService {

	void send(AuditMessage auditMessage);

	void sendInNewTx(AuditMessage auditMessage);

}
