package dtu.library.app;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateServer {

    public Calendar getDate() {
        // 1. Opretter nyt kalender objekt
        Calendar calendar = new GregorianCalendar();

        // 2. Fjerner klokkeslæt, så vi kun har datoen tilbage
        Calendar c = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        // 3. returner kalenderen der indeholder ÅR, MÅNED og DATO PÅ MÅNEDEN
        return c;
    }
    
}
