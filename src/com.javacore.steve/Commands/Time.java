package Commands;

import Main.Steve;

import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class Time extends Command {
    public Time(Steve steve) {
        super(steve);
    }

    @Override
    public void perform() {
        StringBuffer simpleStringBuffer = new StringBuffer();
        StringBuffer AMPMStringBuffer = new StringBuffer();
        Date now = new Date();

        SimpleDateFormat AMPMDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
        AMPMDateFormat.format(now, AMPMStringBuffer, new FieldPosition(0));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
        AMPMDateFormat.format(now, simpleStringBuffer, new FieldPosition(0));
        System.out.println(AMPMStringBuffer);
        System.out.println(simpleStringBuffer);
    }
}
