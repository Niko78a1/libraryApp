package dtu.library.acceptance_tests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import dtu.library.app.DateServer;
import dtu.library.app.LibraryApp;

public class MockDateHolder {

    // Oprettelse af den falske kalender
    DateServer fakeDateServer = mock(DateServer.class);

    // Konstruktør
    public MockDateHolder (LibraryApp libraryApp) {
        GregorianCalendar calendar = new GregorianCalendar(); // 1. Laver kalender med den "sande" tid
        setDate(calendar); // 2. Vi sender den sande tid ned til metoden setDate(). Fortæller den "falske" kalender at den skal starte med at sige "lige nu"
        libraryApp.setDateServer(fakeDateServer); // 3. Vi beder så LibraryApp om at bruge den falske kalender
    }

    // Metode til at programmere den falske kalender
    public void setDate(Calendar calendar) {
        // 1. Tager den kalender vi får sendt og sletter klokkeslettet
        Calendar c = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        // 2. Vi fortæller vores kalender at næste gang getDate() kaldes på denne, skal den bare returnere kalenderen c.
        when(this.fakeDateServer.getDate()).thenReturn(c);
    }

    // Metode til at sætte tiden frem med x antal dage
    public void advanceDateByDays(int days) {
        // 1. Vi spørger den falske kalender: "Hvilken dag har vi i dag?"
        Calendar currentDate = fakeDateServer.getDate();

        // 2. Vi opretter en helt ny kalender og kopiere den falske kalenders tid over i den, så vi ikke ødelægger den gamle kalender
        Calendar nextDate = new GregorianCalendar();
        nextDate.setTime(currentDate.getTime());

        // 3. Vi tager den nye kalender og lægger et bestemt antal dage til
        nextDate.add(Calendar.DAY_OF_YEAR, days);

        // 4. vi kalder vores setDate, med den nye fremtidsdato, så at når vi nu spørger denne kalender er vi altså x dage fremme i tiden i forhold til den originale
        setDate(nextDate);
    }
    
}
