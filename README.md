# Vaadin Integration example for Bryntum Gantt Chart

- fork of https://github.com/mstahv/bryntum-gantt-vaadin with the following changes

  * Java 17
  * Vaadin 23.2
  * Spring Boot 2.7
  * tested with Gantt Chart Trial v5.2.4 full version 5.2.7
  * customized gantt chart (different calendar, view preset) 
  * event propagating from Gantt chart component to Vaadin Flow Application
  

*To run the example, download Bryntum Gantt library and install it to /fronend folder. See more from Running the Application section below.*

This project currently contains a PoC of [Bryntum Gantt Chart](https://www.bryntum.com/products/gantt/) integration for Vaadin. It can render basic charts, but it is currently not packaged as a Vaadin add-on, but just contains implementation in a Spring Boot project.

## Event propagation

In order to propagate events from the frontend Gantt chart component back to your Vaadin Flow Application, the following steps are necessary

* declare the event listener for the gantt chart in ganttConnector.js (see examples for taskResizeEnd and taskDrop events)
* implement the corresponding event classes in your Vaadin Flow Backend, `extends ComponentEvent<GanttChart>` (see com.example.test.TaskResizeEndEvent.java)
* enable the GanttChart.java for adding event listeners
* add event listeners anywhere in your Flow Application (e.g. within your View class)
* the examples contains such listeners for two events:
    * taskDrop - move a whole task onto a new start date
    * taskResizeEnd - change the end date of a given task
    * the labels above the Gantt chart shows the event results 

![Screenshot](https://github.com/dominik42/bryntum-gantt-vaadin/blob/master/screenshot.png?raw=true "Screenshot")

## Localization

* you can localize your gantt chart by adding `frontend/gantt.custom.locale.<LOCALE>.js` e.g. `frontend/gantt.custom.locale.De.js` as documented [here](https://bryntum.com/products/gantt/docs/guide/Gantt/customization/localization)


Note, the LICENSE applies only to the integration part, not to the Bryntum Gantt Chart.

## Running the Application

Download Bryntum Gantt Chart and install gantt.module.js and gantt.default.css and fonts directory (fontawesome) to the frontend folder, next to the ganttConnector.js file.

Import the project to the IDE of your choosing as a Maven project.

Run the application using `mvn spring-boot:run` or by running the `Application` class directly from your IDE.

Open http://localhost:8080/ in your browser.

If you want to run the application locally in the production mode, run `mvn spring-boot:run -Pproduction`.

To run Integration Tests, execute `mvn verify -Pintegration-tests`.

## More Information

- [Vaadin Flow](https://vaadin.com/flow) documentation
- [Using Vaadin and Spring](https://vaadin.com/docs/v14/flow/spring/tutorial-spring-basic.html) article

