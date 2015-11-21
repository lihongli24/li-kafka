package test.com.cmcc.kafka;

import com.cmcc.kafka.consumer.KafkaConsumerListener;

import kafka.consumer.ConsumerIterator;

/** 
 *
 *
 * @Project: cxb-kafka
 * @File: TestListener.java 
 * @Date: 2015年1月14日 
 * @Author: 周强
 * @Copyright: 版权所有 (C) 2015 中国移动 杭州研发中心. 
 *
 * @注意：本内容仅限于中国移动内部传阅，禁止外泄以及用于其他的商业目的 
 */

public class TestListener extends KafkaConsumerListener {

	public void run() {
		ConsumerIterator<byte[], byte[]> it = messageStream.iterator();
		while (it.hasNext())
			System.err.println("Thread " + currentThreadNumber + ": " + new String(it.next().message()));
		System.out.println("Shutting down Thread: " + currentThreadNumber);
	}

}