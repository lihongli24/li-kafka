package com.cmcc.kafka.consumer;

import kafka.consumer.KafkaStream;

/**
 *
 *
 * @Project: performance
 * @File: KafkaConsumerListener.java
 * @Date: 2015年1月14日
 * @Author: 周强
 * @Copyright: 版权所有 (C) 2015 中国移动 杭州研发中心.
 *
 * @注意：本内容仅限于中国移动内部传阅，禁止外泄以及用于其他的商业目的
 */

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
