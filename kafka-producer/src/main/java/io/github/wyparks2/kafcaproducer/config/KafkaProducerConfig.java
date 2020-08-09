package io.github.wyparks2.kafcaproducer.config;

import io.github.wyparks2.common.Message;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class KafkaProducerConfig {

  private final KafkaProperties kafkaProperties;

  @Bean
  public Map<String, Object> producerConfigs() {
    Map<String, Object> props =
        new HashMap<>(kafkaProperties.buildProducerProperties());

    // 메시지 배치 사이즈. 사이즈가 MAX_BOCK_MS_CONFIG 시간 전에 차면 전달
    props.put(ProducerConfig.BATCH_SIZE_CONFIG, 1000);
    // 발송 가능한 메시지의 최대값
    props.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, 2 * 1024 * 1024);
    // 메시지 전송 전에 대기하는 시간
    props.put(ProducerConfig.LINGER_MS_CONFIG, 10);
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
    // -1 무손실, 0 유실 가능성 있으나 처리량 증가, 1 리더에 데이터 기록 성공
    props.put(ProducerConfig.ACKS_CONFIG, "-1");

    return props;
  }

  @Bean
  public ProducerFactory<String, Message> producerFactory() {
    return new DefaultKafkaProducerFactory<>(producerConfigs());
  }

  @Bean
  public KafkaTemplate<String, Message> kafkaTemplate() {
    KafkaTemplate<String, Message> kafkaTemplate = new KafkaTemplate<>(producerFactory());

    kafkaTemplate.setProducerListener(new ProducerListener<>() {
      @Override
      public void onSuccess(
          ProducerRecord<String, Message> producerRecord,
          RecordMetadata recordMetadata) {

        log.info("ACK from ProducerListener message: {} offset:  {}",
            producerRecord.value(),
            recordMetadata.offset());
      }
    });

    return kafkaTemplate;
  }
}
