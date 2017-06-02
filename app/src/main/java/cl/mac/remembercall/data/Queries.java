package cl.mac.remembercall.data;

import java.util.List;

import cl.mac.remembercall.models.Reminder;

/**
 * Created by Michael on 21-05-2017.
 */

public class Queries {

    public List<Reminder> reminders(){
        return Reminder.find(Reminder.class, "done = 0");
    }

}
