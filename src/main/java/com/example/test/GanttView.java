package com.example.test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;


@Route(value = "")
public class GanttView extends VerticalLayout {

	private DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_DATE;

	private Label label1;
	private Label label2;

	/**
	 * Construct a new Vaadin view.
	 * <p>
	 * Build the initial UI state for the user accessing the application.
	 *
	 * @param service The message service. Automatically injected Spring managed
	 *                bean.
	 */
	public GanttView() {
		add(new H2("Vaadin Bryntum Gantt Integration"));
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

		// event listener
		ganttChart.addTaskResizeEndListener(event -> {
			String task = event.getTaskId();
			var date = event.getEndDate();
			label1.setText(String.format("Task %s with new end date: %s", event.getTaskId(), dateFormatter.format(event.getEndDate())));
		});

		ganttChart.addTaskDropListener(event -> {
			String task = event.getTaskId();
			var date = event.getStartDate();
			var duration = event.getDuration();
			label2.setText(String.format("Task %s moved to new start date %s and duration %s", event.getTaskId(), dateFormatter.format(event.getStartDate()), event.getDuration()));
		});


		var header1 = new H4("TaskResizeEndListener: ");
		label1 = new Label();
		HorizontalLayout row1 = new HorizontalLayout(header1, label1);
		row1.setWidthFull();
		row1.setAlignItems(Alignment.BASELINE);

		var header2 = new H4("TaskDropListener: ");
		label2 = new Label();
		HorizontalLayout row2 = new HorizontalLayout(header2, label2);
		row2.setWidthFull();
		row2.setAlignItems(Alignment.BASELINE);

		// layout
		setSizeFull();
		add(row1, row2);

		ganttChart.setHeight("50%");
		add(ganttChart);

	}

}
