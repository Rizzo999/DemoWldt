package smartobject;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class MqttDevice {

    private final static Logger logger = LoggerFactory.getLogger(MqttDevice.class);

    private static String BROKER_URL = "tcp://127.0.0.1:1883";

    private static final int MESSAGE_COUNT = 10000;

    private static final String TELEMETRY_TOPIC = "telemetry/mqttDevice001/resource/presence";

    private static final String COMMAND_TOPIC = "cmd/mqttDevice001";

    public static void main(String args[]){

        try{

            String mqttClientId = UUID.randomUUID().toString();
            MqttClientPersistence persistence = new MemoryPersistence();
            IMqttClient client = new MqttClient(BROKER_URL, mqttClientId, persistence);
            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(10);
            client.connect(options);
            logger.info("Connected ! Client Id: {}", mqttClientId);
            handleReceivedCommands(client);
            PresenceSensor presenceSensor = new PresenceSensor();

            for(int i = 0; i < MESSAGE_COUNT; i++) {

                boolean sensorValue = presenceSensor.getPresenceValue();
                String payloadString = Boolean.toString(sensorValue);
                publishData(client, TELEMETRY_TOPIC, payloadString);
                Thread.sleep(1000);
            }

            client.disconnect();
            client.close();

            logger.info("Disconnected !");

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private static void handleReceivedCommands(IMqttClient client){

        try{

            logger.info("Subscribing to the Command topic: {}", COMMAND_TOPIC);

            if(client != null && client.isConnected())
                client.subscribe(COMMAND_TOPIC, new IMqttMessageListener() {
                    @Override
                    public void messageArrived(String topic, MqttMessage msg) throws Exception {
                        byte[] payload = msg.getPayload();
                        logger.info("Message Command Received ({}) Message Received: {}", topic, new String(payload));
                    }
                });

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Send a target String Payload to the specified MQTT topic
     *
     * @param mqttClient
     * @param topic
     * @param msgString
     * @throws MqttException
     */
    public static void publishData(IMqttClient mqttClient, String topic, String msgString) throws MqttException {

        logger.debug("Publishing to Topic: {} Data: {}", topic, msgString);

        if (mqttClient.isConnected() && msgString != null && topic != null) {

            //Create an MQTT Message defining the required QoS Level and if the message is retained or not
            MqttMessage msg = new MqttMessage(msgString.getBytes());
            msg.setQos(0);
            msg.setRetained(false);
            mqttClient.publish(topic,msg);

            logger.info("Data Correctly Published to Topic: {} Data: {}", topic, msgString);
        }
        else{
            logger.error("Error: Topic or Msg = Null or MQTT Client is not Connected !");
        }

    }


}
