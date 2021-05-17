package com.example.myapp.event;

import com.example.myapp.entity.SecurityLogData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

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
    public void handleEvent(AuditEvent<SecurityLogData> auditEvent) throws JsonProcessingException {
        //saving security to log to file
        saveTextToFile(new ObjectMapper()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(auditEvent.getData()));
    }

    /**
     *
     * @param text from auditEvent that has to be saved in file
     */
    private void saveTextToFile(String text) {

        String filePathAndName =filePath;
        try (PrintStream out = new PrintStream(new FileOutputStream(filePathAndName))) {
            out.print(text);
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
