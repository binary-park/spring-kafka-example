package io.github.wyparks2.kafkaconsumer.config;

import io.github.wyparks2.common.KafkaTopics;
import io.github.wyparks2.common.Message;
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

  @KafkaListener(
      topics = KafkaTopics.EXAMPLE_PARTITION_2
  )
  public void listen1(Message message) throws InterruptedException {
    Thread.sleep(100);
    log.info("{}", message);
  }
}
