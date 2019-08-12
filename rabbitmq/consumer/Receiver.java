package hyc.upload.dataupload.rabbitmq.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.rabbitmq.client.Channel;

import hyc.upload.dataupload.rabbitmq.producer.UserLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 *@ClassName Receiver
 *@Description TODO
 *@Author DL
 *@Date 2019/8/2 14:49    
 *@Version 1.0
 */
@Component
public class Receiver implements ChannelAwareMessageListener {
    private static final Logger log= LoggerFactory.getLogger(Receiver.class);
    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = {"hello"})
    @RabbitHandler
    public void process(String hello) throws InterruptedException {
        log.info("Receiver:{}",hello);
    }
    @RabbitListener(queues = {"logUserQueue"},containerFactory = "singleListenerContainer")
    public void consumeUserLog(@Payload byte[] message) throws IOException {
    log.info("receiver1-logUser:{}",new String(message));
    }
    @RabbitListener(queues = {"logUserQueue"},containerFactory = "singleListenerContainer")
    public void consumeUserLog2(@Payload byte[] message,@Header(AmqpHeaders.CONSUMER_QUEUE) String queue) throws IOException {
        //@Payload明确表示为负荷
        UserLog userLog=objectMapper.readValue(message,UserLog.class);
        log.info("queue:{},receiver2-logUser:{}",queue,userLog);
    }

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {

        log.info("tag:{}",message.getMessageProperties().getDeliveryTag());
        log.info(Thread.currentThread().getName()+"-收到消息"+new String(message.getBody()));
        //重回消息队列
        channel.basicReject(message.getMessageProperties().getDeliveryTag(),false);
        //确认消费
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }

 /*   @RabbitListener(queues = {"orderQueue"},containerFactory = "simpleMessageListenerContainer")
    public void consumeOrder(Message message, Channel channel) throws IOException {
        log.info("收到消息");
        channel.basicAck(message.getMessa
        geProperties().getDeliveryTag(),true);
    }
*/

}
