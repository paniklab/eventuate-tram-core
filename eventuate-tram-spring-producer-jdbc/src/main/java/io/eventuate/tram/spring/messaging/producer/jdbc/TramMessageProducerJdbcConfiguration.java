package io.eventuate.tram.spring.messaging.producer.jdbc;

import io.eventuate.common.id.IdGenerator;
import io.eventuate.common.jdbc.EventuateCommonJdbcOperations;
import io.eventuate.common.jdbc.EventuateSchema;
import io.eventuate.common.spring.id.IdGeneratorConfiguration;
import io.eventuate.common.spring.jdbc.EventuateCommonJdbcOperationsConfiguration;
import io.eventuate.common.spring.jdbc.sqldialect.SqlDialectConfiguration;
import io.eventuate.tram.messaging.producer.common.MessageProducerImplementation;
import io.eventuate.tram.messaging.producer.jdbc.MessageProducerJdbcImpl;
import io.eventuate.tram.spring.messaging.producer.common.TramMessagingCommonProducerConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({SqlDialectConfiguration.class,
    TramMessagingCommonProducerConfiguration.class,
    EventuateCommonJdbcOperationsConfiguration.class,
    IdGeneratorConfiguration.class})
public class TramMessageProducerJdbcConfiguration {

  @Bean
  @ConditionalOnMissingBean(MessageProducerImplementation.class)
  public MessageProducerImplementation messageProducerImplementation(EventuateCommonJdbcOperations eventuateCommonJdbcOperations,
                                                                     IdGenerator idGenerator,
                                                                     EventuateSchema eventuateSchema) {
    return new MessageProducerJdbcImpl(eventuateCommonJdbcOperations,
            idGenerator,
            eventuateSchema);
  }
}
