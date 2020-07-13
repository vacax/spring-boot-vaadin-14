package edu.pucmm.eict.vaadin14.views.calendario;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.vaadin.stefan.fullcalendar.Entry;
import org.vaadin.stefan.fullcalendar.FullCalendar;
import org.vaadin.stefan.fullcalendar.FullCalendarBuilder;

import java.time.LocalDate;

@Route("calendario")
public class Calendario extends VerticalLayout {

    public Calendario(){
        // Create a new calendar instance and attach it to our layout
        FullCalendar calendar = FullCalendarBuilder.create().build();
        add(calendar);
        setFlexGrow(1, calendar);

        // Create a initial sample entry
        Entry entry = new Entry();
        entry.setTitle("Some event");
        entry.setStart(LocalDate.now().withDayOfMonth(3).atTime(10, 0), calendar.getTimezone());
        entry.setEnd(entry.getStart().plusHours(2), calendar.getTimezone());
        entry.setColor("#ff3333");

        calendar.addEntry(entry);
    }
}
