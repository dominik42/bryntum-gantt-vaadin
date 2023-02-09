import { Gantt, ProjectModel, ViewPreset, DateHelper, CalendarModel } from './gantt.module.js';

window.Vaadin.Flow.brymtumGanttConnector = {
	    initLazy: function (c, rows, deps) {

	        // Check whether the connector was already initialized
	        if (c.$connector) {
	            return;
	        }

	        c.$connector =  {};

            const myCal = new CalendarModel({
                //id        : 42,
                name      : "My calendar",
                intervals : [
                    {
                        recurrentStartDate : "on Sat at 0:00",
                        recurrentEndDate   : "on Mon at 0:00",
                        isWorking          : false
                    }
                ]
            })


	    	const project = new ProjectModel({
	    		eventsData : JSON.parse(rows),
	    		dependenciesData : JSON.parse(deps),
                calendar: myCal
	    	});

            const myViewPreset = new ViewPreset({
                id   : 'myPreset',              // Unique id value provided to recognize your view preset. Not required, but having it you can simply set new view preset by id: scheduler.viewPreset = 'myPreset'

                name : 'My view preset',        // A human-readable name provided to be used in GUI, e.i. preset picker, etc.

                tickWidth  : 24,                // Time column width in horizontal mode
                tickHeight : 50,                // Time column height in vertical mode
                displayDateFormat : 'dd.MM.yyyy',    // Controls how dates will be displayed in tooltips etc

                shiftIncrement : 1,             // Controls how much time to skip when calling shiftNext and shiftPrevious.
                shiftUnit      : 'day',         // Valid values are 'millisecond', 'second', 'minute', 'hour', 'day', 'week', 'month', 'quarter', 'year'.
                defaultSpan    : 70,            // By default, if no end date is supplied to a view it will show 12 hours

                timeResolution : {              // Dates will be snapped to this resolution
                    unit      : 'day',       // Valid values are 'millisecond', 'second', 'minute', 'hour', 'day', 'week', 'month', 'quarter', 'year'.
                    increment : 1
                },

                headers : [
                    {
                        unit        : 'month',
                        dateFormat  : 'MMM',
                    },
                    {
                        unit       : 'week',
                        renderer   : (startDate, endDate) => `KW ${DateHelper.format(startDate, 'WW')}`
                    },
                    {
                        unit       : 'day',
                        dateFormat : 'dd'
                    },
                    {
                        unit       : 'day',
                        dateFormat : 'DD'
                    },
                ],
            });

	    	c.chart = new Gantt({
	    	    adopt : c,
	    	    project : project,
	    	    columns : [
	    	        { type : 'name', width : 250 },
	    	    ],
                viewPreset: myViewPreset,


	    	});
	    	c.chart.zoomToFit();
            c.chart.on({
                // https://bryntum.com/products/gantt/docs/guide/Gantt/basics/events
                // https://bryntum.com/products/gantt/docs/api/Gantt/view/Gantt#events
                taskResizeEnd(event) {
                    console.log('GANTT taskResizeEnd: ', event);
                    // console.log('this: ', this);
                    const customEvent = new CustomEvent('taskResizeEnd', {
                        detail: event.taskRecord,
                        composed: true,
                        cancelable: true,
                        bubbles: true
                    });
                    // this._adopt.dispatchEvent(customEvent);
                    document.getElementsByTagName('bryntum-gantt').item(0).dispatchEvent(customEvent);
                },
                taskDrop(event) {
                    console.log('GANTT taskDrop: ', event);
                    const customEvent = new CustomEvent('taskDrop', {
                        detail: event.taskRecords[0],
                        composed: true,
                        cancelable: true,
                        bubbles: true
                    });
                    // this._adopt.dispatchEvent(customEvent);
                    document.getElementsByTagName('bryntum-gantt').item(0).dispatchEvent(customEvent);
                }
            });


	    } // end initLazy
}