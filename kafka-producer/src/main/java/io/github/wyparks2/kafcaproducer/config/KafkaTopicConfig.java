package io.github.wyparks2.kafcaproducer.config;

import io.github.wyparks2.common.KafkaTopics;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

  @Bean
  public NewTopic topicPartition1() {
    return new NewTopic(KafkaTopics.EXAMPLE_PARTITION_1, 3, (short) 1);
  }

  @Bean
  public NewTopic topicPartition2() {
    return new NewTopic(KafkaTopics.EXAMPLE_PARTITION_2, 2, (short) 1);
  }
}
