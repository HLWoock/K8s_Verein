package de.woock.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {
	
	public static final String EXCHANGE_NAME         = "schufa_tx";
	public static final String DEFAULT_PARSING_QUEUE = "schufa_queue";
	public static final String ROUTNG_KEY            = "schufa";

	@Bean
	public TopicExchange schufaExchange() {
		return new TopicExchange(EXCHANGE_NAME);
	}
	
	@Bean
	public Queue defautParsigQueue() {
		return new Queue(DEFAULT_PARSING_QUEUE);
	}
	
	@Bean
	public Binding queueToExcangeBinding() {
		return BindingBuilder.bind(defautParsigQueue()).to(schufaExchange()).with(ROUTNG_KEY);
	}
	
	@Bean
	public Jackson2JsonMessageConverter producerMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

}
