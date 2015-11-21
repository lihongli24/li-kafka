package com.cmcc.kafka.consumer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @Project: performance
 * @File: KafkaConsumerContainer.java
 * @Date: 2015年1月14日
 * @Author: 周强
 * @Copyright: 版权所有 (C) 2015 中国移动 杭州研发中心.
 *
 * @注意：本内容仅限于中国移动内部传阅，禁止外泄以及用于其他的商业目的
 */

public class KafkaConsumerContainer {
	private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerContainer.class);
	private ExecutorService executor;

	public KafkaConsumerContainer(KafkaConsumerFactory factory, String topic, String className, int threadNums) {
		initMessageListener(factory.getConsumer(), topic, className, threadNums);
	}

	@SuppressWarnings("unchecked")
	private void initMessageListener(ConsumerConnector consumer, String topic, String className, int threadNums) {
		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		topicCountMap.put(topic, new Integer(threadNums));
		Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
		List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);
		executor = Executors.newFixedThreadPool(threadNums);
		int threadNumber = 0;
		for (final KafkaStream<byte[], byte[]> stream : streams) {
			try {
				Class<KafkaConsumerListener> listenerClass = (Class<KafkaConsumerListener>) Class.forName(className);
				KafkaConsumerListener listener = listenerClass.newInstance();
				listener.setCurrentThreadNumber(threadNumber);
				listener.setMessageStream(stream);
				executor.submit(listener);
				threadNumber++;
			} catch (Exception e) {
				logger.error("init message error" + e );
			}
		}
	}

//	@Override
//	protected void finalize() throws Throwable {
//		if (executor != null)
//			executor.shutdown();
//		super.finalize();
//	}
}
