package com.cmcc.kafka.producer;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;


public class KafkaProducerFactory {

	private final Producer<String, String> producer;

	public KafkaProducerFactory(String connectAddress, String dataAddress , String requestRequiredAcks , String producertype , String queueBufferingMaxMs ,
			String queueBufferingMaxMessages , String batchNumMessages , String messageSendMaxRetries , String compressionCodec ) {
		producer = initProducer(connectAddress, dataAddress , requestRequiredAcks , producertype , queueBufferingMaxMs , queueBufferingMaxMessages ,
				batchNumMessages , messageSendMaxRetries , compressionCodec );
	}
	
	public void send(String topic, String message) {
		producer.send(new KeyedMessage<String, String>(topic, "keys", message));
	}
	
	public void destoryProducer() {
		producer.close();
	}
	
	private Producer<String, String> initProducer(String connectAddress, String dataAddress , String requestRequiredAcks , String producertype ,
			String queueBufferingMaxMs ,String queueBufferingMaxMessages , String batchNumMessages , String messageSendMaxRetries ,
			String compressionCodec ) {
		Properties props = new Properties();
		props.put("zookeeper.connect", connectAddress);
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		// 指定消息发送对应分区方式，若不指定，则随机发送到一个分区，也可以在发送消息的时候指定分区类型。
		props.put("partitioner.class", "com.cmcc.kafka.producer.KafkaProducerPartitioner");
		props.put("metadata.broker.list", dataAddress);
		//该属性表示你需要在消息被接收到的时候发送ack给发送者。以保证数据不丢失
		//0: producer不会等待broker发送ack
		//1: 当leader接收到消息之后发送ack
		//2: 当所有的follower都同步消息成功后发送ack
		props.put("request.required.acks", requestRequiredAcks);
		props.put("producer.type", producertype); // producer消息发送的模式,默认为同步sync,建议异步async
		props.put("queue.buffering.max.ms", queueBufferingMaxMs); // 异步模式下，那么就会在设置的时间缓存消息，并一次性发送
		props.put("queue.buffering.max.messages", queueBufferingMaxMessages); // 异步的模式下 最长等待的消息数
		props.put("batch.num.messages", batchNumMessages); // 异步模式下，每次发送的最大消息数，前提是触发了queue.buffering.max.messages或是queue.buffering.max.ms的限制
		props.put("message.send.max.retries", messageSendMaxRetries); // 消息发送重试次数，默认3次
		props.put("compression.codec", compressionCodec); // 消息压缩算法,none,gzip,snappy

		ProducerConfig config = new ProducerConfig(props);
		return new Producer<String, String>(config);
	}

//	@Override
//	protected void finalize() throws Throwable {
//		destoryProducer();
//		super.finalize();
//	}
}
