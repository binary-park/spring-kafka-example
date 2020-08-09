package io.github.wyparks2.kafcaproducer.config;

import io.github.wyparks2.common.KafkaTopics;
import io.github.wyparks2.common.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class KafkaProducer {

  private final KafkaTemplate<String, Message> kafkaTemplate;

  public void send(Message message) {
    kafkaTemplate.send(KafkaTopics.EXAMPLE_PARTITION_1, message);
  }
}
