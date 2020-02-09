package de.htwg.iotstack.MqttGateway.Persistence;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;
import org.influxdb.annotation.TimeColumn;

import java.time.Instant;

@Measurement(name = "ttn-message")
public class TTNMessage {

    public TTNMessage(String app_id, String dev_id, String type, String message) {
        this.app_id = app_id;
        this.dev_id = dev_id;
        this.type = type;
        this.message = message;
    }

    @TimeColumn
    @Column(name = "time")
    private Instant time;

    @Column(name = "app-id", tag = true)
    private String app_id;

    @Column(name = "dev-id", tag = true)
    private String dev_id; // Tags must be declared as String

    @Column(name = "type", tag = true)
    private String type; // Tags must be declared as String

    @Column(name = "message")
    private String message;
}
