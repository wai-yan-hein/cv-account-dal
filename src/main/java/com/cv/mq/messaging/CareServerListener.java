/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.mq.messaging;


import com.cv.accountswing.common.ReloadData;
import com.cv.accountswing.gson.DateDeSerializer;
import com.cv.accountswing.gson.DateSerializer;
import com.cv.mq.service.ReceiverService;
import com.cv.accountswing.util.Util1;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.UUID;
import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author winswe
 */
@Component
public class CareServerListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(CareServerListener.class);
    private ReloadData rlData;
    private final Gson gson = new GsonBuilder().
            registerTypeAdapter(Date.class, new DateSerializer()).
            registerTypeAdapter(Date.class, new DateDeSerializer()).create();

    @Autowired
    private ReceiverService rService;
    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public ReloadData getRlData() {
        return rlData;
    }

    public void setRlData(ReloadData rlData) {
        this.rlData = rlData;
    }

    @JmsListener(destination = "${destination.queue}")
    public void receiveMapMessage(final MapMessage message) {

        try {
            String msgOwner = message.getString("msgOwner");
            if (msgOwner != null) {
                if (msgOwner.equals(uuid)) {
                    String operationType = message.getString("operationId");
                    String entityType = message.getString("entityType");
                    Object data = message.getString("data");
                    LOGGER.info("receiveMapMessage : operationType : " + operationType
                            + ";  entityType : " + entityType + "; data : " + data);
                    rService.doReceiverOperation(rlData, operationType, entityType, data);
                }
            }
        } catch (JMSException ex) {
            LOGGER.error("receiveMapMessage : " + ex.getMessage());
        }
    }

    @JmsListener(destination = "${destination.queue}")
    public void receiveMapMessage1(final MapMessage message) {

        try {
            String msgOwner = message.getString("msgOwner");
            if (msgOwner != null) {
                if (msgOwner.equals(uuid)) {
                    String operationType = message.getString("operationId");
                    String entityType = message.getString("entityType");
                    Object data = message.getString("data");
                    LOGGER.info("receiveMapMessage1 : operationType : " + operationType
                            + ";  entityType : " + entityType + "; data : " + data);
                    rService.doReceiverOperation(rlData, operationType, entityType, data);
                }
            }
        } catch (JMSException ex) {
            LOGGER.error("receiveMapMessage : " + ex.getMessage());
        }
    }

    @JmsListener(destination = "${destination.queue}file")
    public void receiveFileMessage(final BytesMessage message) {

        try {
            String msgOwner = message.getStringProperty("msgOwner");
            if (msgOwner != null) {
                if (msgOwner.equals(uuid)) {
                    String operationType = message.getStringProperty("operationId");
                    String entityType = message.getStringProperty("entityType");
                    String fileName = message.getStringProperty("fileName");
                    if (entityType.equals("ACK")) {
                        rService.doReceiverOperation(rlData, operationType, entityType, fileName);
                        LOGGER.info("sysc finished");
                    } else {
                        LOGGER.info("File Start.");
                        Long body = message.getBodyLength();
                        int bodyLength =body.intValue();
                        byte[] tmpBytes = new byte[bodyLength];
                        message.readBytes(tmpBytes, bodyLength);
                        UUID uuid = UUID.randomUUID();
                        String zipfileName = uuid.toString().concat(".zip");

                        //   String a=spService.findById("system.zipfile.path").getPropValue();
                        String filepath = Util1.getAppWorkFolder() + File.separator + "zipFile";

                        FileOutputStream fos = new FileOutputStream(filepath + File.separator + zipfileName);
                        fos.write(tmpBytes);
                        fos.close();
                        fos.flush();
                        Util1.unzip(filepath + File.separator, filepath, zipfileName);
                        String path = filepath + File.separator + zipfileName.replaceAll(".zip", ".json");

                        JsonParser parser = new JsonParser();
                        JsonElement data = null;
                        try {
                            FileInputStream inputStream = new FileInputStream(path);
                            InputStreamReader sReader = new InputStreamReader(inputStream, "UTF-8");
                            BufferedReader bReader = new BufferedReader(sReader);
                            //Read JSON file
                            // data = parser.parse(reader);
                            data = parser.parse(bReader);
                        } catch (JsonIOException | JsonSyntaxException | FileNotFoundException | UnsupportedEncodingException e) {
                            LOGGER.error("receiveFileMessage : " + e.getMessage());
                        }
                        rService.doReceiverFileOperation(rlData, operationType, entityType, data);
                        LOGGER.info("End");

                        LOGGER.info("receiveFileMessageadm : operationType : " + operationType
                                + ";  entityType : " + entityType + "; data : " + data);
                    }

                }
            }
        } catch (Exception ex) {
            LOGGER.error("receiveMapMessage : " + ex.getMessage());

        }
    }
}
