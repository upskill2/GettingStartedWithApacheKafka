package kafka.demo;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaProducerApp {

    public static void main (String[] args) {

        Properties props = new Properties ();

        props.put ("bootstrap.servers", "localhost:9092, localhost:9093");
        props.put ("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put ("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> myProducer = new KafkaProducer (props);

        try {
            for (int i = 0; i < 150; i++) {
                myProducer.send (new ProducerRecord<String, String> ("my_topic", Integer.toString (i), "MyMessage: " + Integer.toString (i)));
                myProducer.send (new ProducerRecord<String, String> ("my_another_topic", Integer.toString (i), "MyMessage: " + Integer.toString (i)));
            }
        } catch (Exception e){
            e.printStackTrace ();
        } finally {
            myProducer.close ();
        }

     //   ProducerRecord myMessage = new ProducerRecord ("my_topic", "Course-001", "My Message 1");
    //    myProducer.send (myMessage);

    }


}
