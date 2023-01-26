package kafka.demo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class KafkaConsumerAssignApp {

    public static void main (String[] args) {

        Properties props = new Properties ();

        props.put ("bootstrap.servers", "localhost:9092, localhost:9093");
        props.put ("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put ("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> myConsumer = new KafkaConsumer (props);

        TopicPartition partition0 = new TopicPartition ("my_topic", 0);
        TopicPartition partition1 = new TopicPartition ("my_another_topic", 2);
        List<TopicPartition> partitions = new ArrayList<> ();
        partitions.add (partition0);
        partitions.add (partition1);

        myConsumer.assign (partitions);

        try {
            while (true){
                ConsumerRecords<String,String> records = myConsumer.poll (10);
                for (ConsumerRecord<String,String> record : records
                ) {
                    System.out.println (String.format ("Topic: %s, Partition: %s, Offset: %s, Key: %s, Value: %s",
                            record.topic (),record.partition (),record.offset (),record.key (),record.value ()));
                }


            }
        } catch (Exception e){
            e.printStackTrace ();
        }
        finally {
            myConsumer.close ();
        }


    }

}
