package test.com.cmcc.kafka;

import org.junit.Test;

import com.cmcc.kafka.consumer.KafkaConsumerContainer;
import com.cmcc.kafka.consumer.KafkaConsumerFactory;

/**
 *
 *
 * @Project: cxb-kafka
 * @File: ConsumerTest.java
 * @Date: 2015年1月14日
 * @Author: 周强
 * @Copyright: 版权所有 (C) 2015 中国移动 杭州研发中心.
 *
 * @注意：本内容仅限于中国移动内部传阅，禁止外泄以及用于其他的商业目的
 */

public class ConsumerTest {

	@Test
	public void testConsumer() throws Exception {
		KafkaConsumerContainer container1 = new KafkaConsumerContainer(new KafkaConsumerFactory("192.168.11.206:2181", "topic_test", 400, 200, 1000 , "" , "" ), "topic_box_cmcc", "test.com.cmcc.kafka.TestListener", 5);
		//KafkaConsumerContainer container2 = new KafkaConsumerContainer(new KafkaConsumerFactory("192.168.11.206:2181", "test_group", 400, 200, 1000), "topic_box_customer", "test.com.cmcc.kafka.TestListener", 5);
		//Thread.sleep(100);
	}
}
