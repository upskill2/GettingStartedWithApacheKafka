package kafka.demo.consumer.groups;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaProducerGroupApp {

    public static void main (String[] args) {

        Properties props = new Properties ();

        props.put ("bootstrap.servers", "localhost:9092, localhost:9093");
        props.put ("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put ("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> myProducer = new KafkaProducer (props);

        try {
            for (int i = 0; i < 100; i++) {
                myProducer.send (new ProducerRecord ("my_another_topic", "abcdwertyuzcvbcnmdsfa"));
            }
        } catch (Exception e) {
            e.printStackTrace ();
        } finally {
            myProducer.close ();
        }

    }


}
