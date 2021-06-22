package com.example.myapp.event;

import com.example.myapp.entity.SecurityLogData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class AuditEventHandler {
    @Value("${filepath}")
    private String filePath;

    /**
     *
     * @param auditEvent instance that encapsulates the SecurityLogData
     * @throws JsonProcessingException
     */

    @EventListener
    @Async
    public void handleEvent(AuditEvent<SecurityLogData> auditEvent) throws IOException {
        //saving security to log to file
        saveTextToFile(new ObjectMapper()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(auditEvent.getData()));
    }

    /**
     *
     * @param text from auditEvent that has to be saved in file
     */
    private void saveTextToFile(String text) throws IOException {

        File file = new File(filePath);
        FileWriter fileWriter = new FileWriter(file, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(text);
        bufferedWriter.close();
        fileWriter.close();

    }
}
