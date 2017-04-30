package com.rettori;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class QueueProcessor implements Processor {

	public void process(Exchange inExchange) {
		String queueName = MyConnectionFactory.readFile("/data/destination-address");
		inExchange.getContext().getProperties().put("queueName", queueName);
		String body = inExchange.getIn().getBody(String.class);
		inExchange.getIn().setBody(body);
	}
}