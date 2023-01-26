package kafka.demo.consumer.groups;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class KafkaConsumerSubscribeApp04 {

    public static void main (String[] args) {

        Properties props = new Properties ();

        props.put ("bootstrap.servers", "localhost:9092, localhost:9093");
        props.put ("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put ("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put ("group.id", "test-group");

        KafkaConsumer<String, String> myConsumer = new KafkaConsumer (props);

        List<String> topics = new ArrayList<> ();
        topics.add ("my_another_topic");

        myConsumer.subscribe (topics);

        try {
            while (true){
                ConsumerRecords<String,String> records = myConsumer.poll (1);
                for (ConsumerRecord<String,String> record : records
                     ) {
                    String date = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(record.timestamp ());

                    System.out.println (String.format ("Topic: %s, Partition: %s, Value: %s, TimeStamp: %s",
                            record.topic (),record.partition (),record.value ().toUpperCase (), date));
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
