package hyc.upload.dataupload.rabbitmq.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



/**
 *@ClassName Producer
 *@Description TODO
 *@Author DL
 *@Date 2019/8/2 11:27    
 *@Version 1.0
 */
@Component
public class Producer {
    @Autowired
    private AmqpTemplate amqpTemplate;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    ObjectMapper objectMapper;
    public void send(String content){

        System.out.println("send:"+content);
        amqpTemplate.convertAndSend("hello",content);

    }
    public void sendRabbit(String content) throws JsonProcessingException {
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setExchange("logUserExchange");
        rabbitTemplate.setRoutingKey("logUser");
       UserLog userLog=new UserLog();
       userLog.setUserName("admin");
       userLog.setContent(content);
        Message message= MessageBuilder.withBody(objectMapper.writeValueAsBytes(userLog)).setDeliveryMode(MessageDeliveryMode.PERSISTENT).build();
        message.getMessageProperties().setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME, MessageProperties.CONTENT_TYPE_JSON);
        //deliveryTag是消息传送的次数，
        // 我这里是为了让消息队列的第一个消息到达的时候抛出异常，
        // 处理异常让消息重新回到队列，然后再次抛出异常，处理异常拒绝让消息重回队列
        message.getMessageProperties().getDeliveryTag();
       rabbitTemplate.send(message);
    }
    public void sendOrder(String content) throws JsonProcessingException {
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setExchange("orderExchange");
        rabbitTemplate.setRoutingKey("order");
        UserLog userLog=new UserLog();
        userLog.setUserName("admin");
        userLog.setContent(content);
        Message message= MessageBuilder.withBody(objectMapper.writeValueAsBytes(userLog)).setDeliveryMode(MessageDeliveryMode.PERSISTENT).build();
        message.getMessageProperties().setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME, MessageProperties.CONTENT_TYPE_JSON);
        rabbitTemplate.send(message);
    }
}
