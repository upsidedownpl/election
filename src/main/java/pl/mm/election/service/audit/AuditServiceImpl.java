package pl.mm.election.service.audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuditServiceImpl implements AuditService {

	@Autowired
	private AuditMessageSender auditMessageSender;
	
	@Override
	@Transactional
	public void send(AuditMessage auditMessage) {
		auditMessageSender.sendMessage(auditMessage);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void sendInNewTx(AuditMessage auditMessage) {
		auditMessageSender.sendMessage(auditMessage);
	}
}
