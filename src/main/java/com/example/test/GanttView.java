package com.example.test;

import java.time.LocalDate;
import java.util.Arrays;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "")
public class GanttView extends VerticalLayout {

	/**
	 * Construct a new Vaadin view.
	 * <p>
	 * Build the initial UI state for the user accessing the application.
	 *
	 * @param service The message service. Automatically injected Spring managed
	 *                bean.
	 */
	public GanttView() {
		GanttChart ganttChart = new GanttChart();

		LocalDate today = LocalDate.now();
		GanttRow saasProduct = ganttChart.addRow("Launch SaaS Product");
		saasProduct.setStartDate(today);
		saasProduct.setPercentDone(50);
		GanttRow webServer = new GanttRow("Setup web server");
		webServer.setDuration(10);
		webServer.setPercentDone(50);
		saasProduct.getChildren().add(webServer);
		GanttRow apache = new GanttRow("Install Apache");
		apache.setPercentDone(50);
		apache.setStartDate(today);
		apache.setDuration(3);
		webServer.getChildren().add(apache);
		GanttRow firewall = new GanttRow("Configure Firewall");
		firewall.setPercentDone(50);
		firewall.setStartDate(today);
		firewall.setDuration(3);
		webServer.getChildren().add(firewall);
		GanttRow loadBalancer = new GanttRow("Setup load balancer");
		loadBalancer.setPercentDone(50);
		loadBalancer.setStartDate(today);
		loadBalancer.setDuration(3);
		webServer.getChildren().add(loadBalancer);
		GanttRow configurePorts = new GanttRow("Configure ports");
		configurePorts.setPercentDone(50);
		configurePorts.setStartDate(today.plusDays(2));
		configurePorts.setDuration(2);
		webServer.getChildren().add(configurePorts);
		GanttRow runTests = new GanttRow("Run tests");
		runTests.setPercentDone(0);
		runTests.setStartDate(today.plusDays(5));
		runTests.setDuration(2);
		webServer.getChildren().add(runTests);

		Dependency dependency = new Dependency(runTests);
		dependency.setLag(2);
		apache.getDependencies().add(dependency);
		Arrays.asList(firewall, loadBalancer, configurePorts).stream()
			.forEach(row -> row.addDependency(runTests));

		addAndExpand(ganttChart);

        ganttChart.getElement().addEventListener("click", event -> {
            System.out.println("click: " + event);
        });
        ganttChart.getElement().addEventListener("taskresizeend", event -> {
            System.out.println(event);
        });


	}

}
