package com.cmcc.kafka.consumer;

import kafka.consumer.KafkaStream;


public abstract class KafkaConsumerListener implements Runnable {

	protected KafkaStream<byte[], byte[]> messageStream;
	protected int currentThreadNumber;
	
	public void setMessageStream(KafkaStream<byte[], byte[]> messageStream) {
		this.messageStream = messageStream;
	}
	public void setCurrentThreadNumber(int currentThreadNumber) {
		this.currentThreadNumber = currentThreadNumber;
	}

}
