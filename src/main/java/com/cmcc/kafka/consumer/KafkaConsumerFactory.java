package com.cmcc.kafka.consumer;

import java.util.Properties;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.javaapi.consumer.ConsumerConnector;

/**
 *
 *
 * @Project: performance
 * @File: KafkaConsumerFactory.java
 * @Date: 2015年1月14日
 * @Author: 周强
 * @Copyright: 版权所有 (C) 2015 中国移动 杭州研发中心.
 *
 * @注意：本内容仅限于中国移动内部传阅，禁止外泄以及用于其他的商业目的
 */

public class KafkaConsumerFactory {

	private final ConsumerConnector consumer;

	public KafkaConsumerFactory(String address, String groupId, Integer sessionTimeout, Integer syncTime, Integer commitInterval ,
			String zookTimeOut , String autoOffsetReset ) {
		consumer = initConsumer(address, groupId, sessionTimeout, syncTime, commitInterval , zookTimeOut , autoOffsetReset );
	}

	public ConsumerConnector getConsumer() {
		return consumer;
	}

	private ConsumerConnector initConsumer(String address, String groupId, Integer sessionTimeout, Integer syncTime, Integer commitInterval ,
			String zookTimeOut , String autoOffsetReset ) {
		Properties props = new Properties();
		props.put("zookeeper.connect", address);
		props.put("group.id", groupId);
		if (sessionTimeout != null)
			props.put("zookeeper.session.timeout.ms", "" + sessionTimeout);
		if (syncTime != null)
			props.put("zookeeper.sync.time.ms", "" + syncTime);
		if (commitInterval != null)
			props.put("auto.commit.interval.ms", "" + commitInterval);
		// ZooKeeper的连接超时时间
		if (zookTimeOut != null)
		props.put("zookeeper.connection.timeout.ms", zookTimeOut);
		// 如果zookeeper没有offset值或offset值超出范围。那么就给个初始的offset。有smallest、largest、anything可选，分别表示给当前最小的offset、当前最大的offset、抛异常。默认largest
		if (autoOffsetReset != null)
		props.put("auto.offset.reset", autoOffsetReset);
		// 单条消息最大字节
		props.put("fetch.message.max.bytes", "104857600");
		
		return Consumer.createJavaConsumerConnector(new ConsumerConfig(props));
	}

//	@Override
//	protected void finalize() throws Throwable {
//		if (consumer != null)
//			consumer.shutdown();
//		super.finalize();
//	}

}
