package pl.mm.election.service.audit;

import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@EnableJms
public class AuditMessageListener {

	static final Logger log = LoggerFactory.getLogger(AuditMessageListener.class);
	 
    private static final String QUEUE = "auditQueue";
	
	@JmsListener(destination = QUEUE)
    public void receiveMessage(final Message<AuditMessage> message) throws JMSException {
		log.info("AuditMessage {}", message.getPayload().getMsg());
    }
	
}
