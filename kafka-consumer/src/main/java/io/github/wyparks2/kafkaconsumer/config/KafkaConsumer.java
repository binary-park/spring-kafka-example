package io.github.wyparks2.kafkaconsumer.config;

import io.github.wyparks2.common.KafkaTopics;
import io.github.wyparks2.common.Message;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Kafka Consumer.
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaConsumer {

  @Value("${spring.application.name}")
  private String applicationName;

  @KafkaListener(
      topics = KafkaTopics.EXAMPLE_PARTITION_1,
      containerFactory = "kafkaListenerContainerFactory1"
  )
  public void listen1(Message message) {
    log.info("[{}] {}", "consumer-1", message);
  }

  @KafkaListener(
      topics = KafkaTopics.EXAMPLE_PARTITION_1,
      containerFactory = "kafkaListenerContainerFactory2"
  )
  public void listen2(Message message) {
    log.info("[{}] {}", "consumer-2", message);
  }

  @KafkaListener(
      topics = KafkaTopics.EXAMPLE_PARTITION_1,
      containerFactory = "kafkaListenerContainerFactory3"
  )
  public void listen3(Message message) {
    log.info("[{}] {}", "consumer-3", message);
  }

//  @KafkaListener(topics = KafkaTopics.EXAMPLE_PARTITION_1)
//  public void listen(List<Message> messages) {
//    for (Message message : messages) {
//      log.info("[{}] {}", applicationName, message);
//    }
//
//    log.info("batch size : {}", messages.size());
//  }
}
