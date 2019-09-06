package com.stackroute.KafkaRun;
import com.stackroute.KafkaRun.Domain.SampleObject;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.*;
import org.apache.kafka.common.serialization.Serdes;

//import org.apache.kafka.common.serialization.Serdes;

import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Serialized;

import org.apache.kafka.streams.KeyValue;
import org.springframework.boot.SpringApplication;

import java.util.*;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;


public class KafkaProducerExample {

    public static void main(String[] args) throws Exception {


        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "wordcount-application");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "0.0.0.0:9092");
//        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
//        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.serdeFrom(KafkaRunApplication.class));
//        Flux
        Map<String, Object> serdeProps = new HashMap<>();


        final Serializer<SampleObject> KafkaRunApplicationSerialzer = new JsonPOJOSerializer<>();
        serdeProps.put("JsonPOJOClass", SampleObject.class);
        KafkaRunApplicationSerialzer.configure(serdeProps, false);


        final Deserializer<SampleObject> KafkaRunDeserializer = new JsonPOJODeserializer<>();
        serdeProps.put("JsonPOJOClass", SampleObject.class);
        KafkaRunDeserializer.configure(serdeProps, false);

//        final Serdes<> KafkaRunApplicationSerDes = ;
        final Serde<SampleObject> KafkaRunApplicationSerdes = Serdes.serdeFrom(KafkaRunApplicationSerialzer, KafkaRunDeserializer);

        StreamsBuilder builder = new StreamsBuilder();

        KStream<String, SampleObject> textLines = builder.stream("TextLinesTopic", Consumed.with(Serdes.String(), KafkaRunApplicationSerdes));
        textLines.map((i, j) -> {
            System.out.println(i);
            System.out.println(j);
            return KeyValue.pair(i, j);
        });
//        KTable<String,KafkaRunApplication> wordCounts = textLines
//                .flatMapValues(textLine -> Arrays.asList(textLine.toLowerCase().split("\\W+")))
//                .groupBy((key, word) -> {
//                    System.out.println(key + word);
//                    return  word;
//                })
//                .count(Materialized.<String, Long, KeyValueStore<Bytes, byte[]>>as("counts-store"));
//        wordCounts.toStream().to("WordsWithCountsTopic", Produced.with(Serdes.String(), Serdes.Long()));

        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.start();
    }
}



        //SpringApplication.run(KafkaRunApplication.class, args);
//        if (args.length == 0) {
//            runProducer(5);
//        } else {
//            runProducer(Integer.parseInt(args[0]));
//        }
//    }
//
//    private final static String TOPIC = "my-example-topic";
//    private final static String BOOTSTRAP_SERVERS =
//            "localhost:9092,localhost:9093,localhost:9094";
//
//    private static Producer<Long, String> createProducer() {
//        Properties props = new Properties();
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
//                BOOTSTRAP_SERVERS);
//        props.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaExampleProducer");
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
//                LongSerializer.class.getName());
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
//                StringSerializer.class.getName());
//        return new KafkaProducer<>(props);
//    }
//
//    static void runProducer(final int sendMessageCount) throws Exception {
//        final Producer<Long, String> producer = createProducer();
//        long time = System.currentTimeMillis();
//        final CountDownLatch countDownLatch = new CountDownLatch(sendMessageCount);
//
//        try {
//            for (long index = time; index < time + sendMessageCount; index++) {
//                final ProducerRecord<Long, String> record =
//                        new ProducerRecord<>(TOPIC, index,
//                                "Hello Mom " + index);
//                producer.send(record, (metadata, exeception) -> {
//                    long elapsedTime = System.currentTimeMillis() - time;
//                    if (metadata != null) {
//                        System.out.printf("sent record(key=%s value=%s) " +
//                                        "meta(partition=%d, offset=%d) time=%d\n",
//                                record.key(), record.value(), metadata.partition(),
//                                metadata.offset(), elapsedTime);
//                    } else {
//                        //exception.printStackTrace();
//                    }
//                    countDownLatch.countDown();
//
//                });
//                //RecordMetadata metadata = producer.send(record).get();
//            }
//            countDownLatch.await(25, TimeUnit.SECONDS);
//
//        } finally {
//            producer.flush();
//            producer.close();
//        }
//    }
//
//
//}
//
