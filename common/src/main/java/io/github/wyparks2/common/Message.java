package io.github.wyparks2.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

/**
 * 토픽 메시지.
 *
 * 불변 클래스로 만들고 싶다면 생성자에 @JsonProperty를 이용하자.
 */
@ToString
@Getter
public class Message {
  private final String payload;

  public Message(@JsonProperty("payload") String payload) {
    this.payload = payload;
  }
}
