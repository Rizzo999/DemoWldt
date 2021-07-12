package wldt;

import it.unimore.dipi.iot.wldt.engine.WldtEngine;
import it.unimore.dipi.iot.wldt.worker.mqtt.Mqtt2MqttWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Demo of a WLDT enabled Digital Twin that mirrors an MQTT IoT Device
 * received telemetry data from the physical asset and command from
 * external applications according to the following schema:
 *
 * Telemetry:
 *
 * DEVICE ---- [msg] ----> BROKER-A ----> (DT) ---- [msg] ---- BROKER-B ----> CONSUMER(s)
 *                       (Port:1883)                          (Port:1884)
 *
 * Commands:
 *
 * DEVICE <---- [msg] ---- BROKER-A <---- (DT) <---- [msg] ---- BROKER-B <---- APP(s)
 *                        (Port:1883)                          (Port:1884)
 *
 * @author : Marco Picone, Ph.D. (marco.picone@unimore.it)
 * @created: 21/05/2021
 * @project: WLDT - MQTT Example
 */
public class WldtMqttProcessWithConf {

    private static final String TAG = "[WLDT-Process]";

    private static final Logger logger = LoggerFactory.getLogger(WldtMqttProcessWithConf.class);

//    private static final String DEMO_TEMPERATURE_TOPIC_ID = "temperature_topic";
//    private static final String DEMO_TEMPERATURE_RESOURCE_ID = "temperature";
//
//    private static final String DEMO_COMMAND_TOPIC_ID = "command_topic";
//    private static final String DEMO_COMMAND_RESOURCE_ID = "default_command_channel";
//
//    private static final String SOURCE_BROKER_ADDRESS = "192.168.1.172";
//    private static final int SOURCE_BROKER_PORT = 1883;
//
//    private static final String DESTINATION_BROKER_ADDRESS = "192.168.1.172";
//    private static final int DESTINATION_BROKER_PORT = 1884;
//
//    private static final String DEVICE_ID = "com:iot:dummy:dummyMqttDevice001";

    public static void main(String[] args)  {

        try{

            logger.info("{} Initializing WLDT-Engine ... ", TAG);

            //Example loading everything from the configuration file
            WldtEngine wldtEngine = new WldtEngine();

            Mqtt2MqttWorker mqtt2MqttWorker = new Mqtt2MqttWorker(wldtEngine.getWldtId());

            wldtEngine.addNewWorker(mqtt2MqttWorker);
            wldtEngine.startWorkers();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

