package com.example.myapp.event;

import com.example.myapp.entity.SecurityLogData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class AuditEventPublisher {

    // to publish an event, an application event publisher is needed
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;


    // from auth controller this publish event is called

    /**
     *
     * @param message taken from where the event triggered
     */

    public void publishEvent(String message){

        Map<String , String> dataMap = new LinkedHashMap<>();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        dataMap.put("host", request.getHeader("host"));
        dataMap.put("user-agent", request.getHeader("User-Agent"));
        // message from controller
        dataMap.put("message",message);
        dataMap.put("Date", java.time.LocalDate.now().toString());
        dataMap.put("Time", java.time.LocalTime.now().toString());

        //publishing an event
        // we need to pass our event
        applicationEventPublisher.
                publishEvent(new AuditEvent<>
                        (new SecurityLogData(dataMap)));

    }


}
