# Vaadin Integration example for Bryntum Gantt Chart

- fork of https://github.com/mstahv/bryntum-gantt-vaadin with the following changes

  * Java 17
  * Vaadin 23.2
  * Spring Boot 2.7
  * using Gantt Chart Trial version 5.2.4
  * including der WebComponent version gantt.wc.module.js instead of gantt.module.js



*To run the example, download Bryntum Gantt library and install it to /fronend folder. See more from Running the Application section below.*

This project currently contains a PoC of [Bryntum Gantt Chart](https://www.bryntum.com/products/gantt/) integration for Vaadin. It can render basic charts, but the API has not been tested in any real app. Also, it is currently not packaged as a Vaadin add-on, but just contains implementation in a Spring Boot project.

![Screenshot](https://github.com/mstahv/bryntum-gantt-vaadin/blob/master/screenshot.png?raw=true "Screenshot")


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

