package io.github.wyparks2.kafcaproducer.controller;

import io.github.wyparks2.common.Message;
import io.github.wyparks2.kafcaproducer.config.KafkaProducer;
import java.util.Objects;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/producer")
@RestController
public class ProducerController {

  private final KafkaProducer kafkaProducer;

  /**
   * 대량의 메시지를 발행한다.
   */
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @GetMapping({
      "/stress",
      "/stress/{count}"
  })
  public void stress(@PathVariable(required = false) Integer count) {
    var stressCount = Objects.isNull(count) ? 1_000 : count;

    IntStream.rangeClosed(1, stressCount).forEach(index -> {
      Message message = new Message(String.valueOf(index));
      kafkaProducer.send(message);
    });
  }
}
