package com.cmcc.kafka.producer;

import java.util.Random;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

/** 
 *
 *
 * @Project: cxb-kafka
 * @File: KafkaProducerPartitioner.java 
 * @Date: 2015年1月14日 
 * @Author: 周强
 * @Copyright: 版权所有 (C) 2015 中国移动 杭州研发中心. 
 *
 * @注意：本内容仅限于中国移动内部传阅，禁止外泄以及用于其他的商业目的 
 */

public class KafkaProducerPartitioner implements Partitioner {
	
	public KafkaProducerPartitioner(VerifiableProperties props) {}

	public int partition(Object key, int numPartitions) {
		int partition = 0;
		partition = new Random().nextInt(255) % numPartitions;
		return partition;
	}

}
