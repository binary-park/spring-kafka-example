package io.github.wyparks2.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 카프카 토픽.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KafkaTopics {

  /**
   * 예제를 위한 토픽.
   */
  public static final String EXAMPLE_PARTITION_1 = "example-topic-partition-4";
  public static final String EXAMPLE_PARTITION_2 = "example-topic-partition-2";
}
