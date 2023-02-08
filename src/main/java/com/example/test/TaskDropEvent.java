package com.example.test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.DomEvent;
import com.vaadin.flow.component.EventData;

import elemental.json.JsonObject;
import lombok.Getter;

@DomEvent("taskDrop")
public class TaskDropEvent extends ComponentEvent<GanttChart> {

    @Getter private String taskId;
    @Getter private LocalDate startDate;
    @Getter private Integer duration;

    public TaskDropEvent(GanttChart source, boolean fromClient, @EventData("event.detail") JsonObject details) {
        super(source, fromClient);
        this.taskId = details.getString("id");
        this.startDate = LocalDate.parse(details.getString("startDate"), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        this.duration = Double.valueOf(details.getNumber("duration")).intValue();
    }

}

