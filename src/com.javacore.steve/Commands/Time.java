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
        StringBuffer stringBuffer = new StringBuffer();
        Date now = new Date();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        simpleDateFormat.format(now, stringBuffer, new FieldPosition(0));
        System.out.println(now);
    }
}
