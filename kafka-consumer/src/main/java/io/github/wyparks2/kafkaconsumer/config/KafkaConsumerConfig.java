package io.github.wyparks2.kafkaconsumer.config;

import io.github.wyparks2.common.Message;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@RequiredArgsConstructor
@Configuration
public class KafkaConsumerConfig {

  private final KafkaProperties kafkaProperties;

  @Bean
  public Map<String, Object> consumerConfigs() {
    Map<String, Object> props =
        new HashMap<>(kafkaProperties.buildConsumerProperties());

    return props;
  }

  @Bean
  public ConsumerFactory<String, Message> consumerFactory() {
    return new DefaultKafkaConsumerFactory<>(
        consumerConfigs(),
        new StringDeserializer(),
        new JsonDeserializer<>(Message.class,false)
    );
  }

  @Bean
  public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Message>>
  kafkaListenerContainerFactory1() {
    ConcurrentKafkaListenerContainerFactory<String, Message> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    return factory;
  }
}
