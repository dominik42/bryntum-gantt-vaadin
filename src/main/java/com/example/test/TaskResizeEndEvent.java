package com.example.test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.DomEvent;
import com.vaadin.flow.component.EventData;

import elemental.json.JsonObject;
import lombok.Getter;

@DomEvent("taskResizeEnd")
public class TaskResizeEndEvent extends ComponentEvent<GanttChart> {

    @Getter private String taskId;
    @Getter private LocalDate endDate;
    @Getter private Integer duration;

    public TaskResizeEndEvent(GanttChart source, boolean fromClient, @EventData("event.detail") JsonObject details) {
        super(source, fromClient);
        this.taskId = details.getString("id");
        this.endDate = LocalDate.parse(details.getString("endDate"), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        this.duration = Double.valueOf(details.getNumber("duration")).intValue();
    }

}