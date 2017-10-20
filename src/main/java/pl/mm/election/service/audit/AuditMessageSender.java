package pl.mm.election.service.audit;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class AuditMessageSender {

	@Autowired
    private JmsTemplate jmsTemplate;
	
	@Autowired
	@Qualifier("auditQueue")
	private Destination auditQueue;
 
    public void sendMessage(final AuditMessage auditMessage) {
 
        jmsTemplate.send(auditQueue, new MessageCreator(){
                @Override
                public Message createMessage(Session session) throws JMSException{
                    ObjectMessage objectMessage = session.createObjectMessage(auditMessage);
                    return objectMessage;
                }
            });
    }
	
}
