package com.cmcc.kafka.producer;

import java.util.Random;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;


public class KafkaProducerPartitioner implements Partitioner {
	
	public KafkaProducerPartitioner(VerifiableProperties props) {}

	public int partition(Object key, int numPartitions) {
		int partition = 0;
		partition = new Random().nextInt(255) % numPartitions;
		return partition;
	}

}
