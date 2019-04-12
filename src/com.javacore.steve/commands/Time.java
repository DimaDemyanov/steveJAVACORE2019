package commands;

import commandsUtils.Command;
import main.Steve;

import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Time extends Command {

    private final static String SIMPLE_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private final static String AMPM_DATE_FORMAT = "yyyy-MM-dd hh:mm:ss a";


    public Time() {
        name = "time";
    }

    @Override
    public void perform(Steve steve, String [] options) {

        StringBuffer simpleStringBuffer = new StringBuffer();
        StringBuffer AMPMStringBuffer = new StringBuffer();

        Date now = new Date();

        SimpleDateFormat AMPMDateFormat = new SimpleDateFormat(SIMPLE_DATE_FORMAT);
        AMPMDateFormat.format(now, AMPMStringBuffer, new FieldPosition(0));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AMPM_DATE_FORMAT);
        simpleDateFormat.format(now, simpleStringBuffer, new FieldPosition(0));

        System.out.println(AMPMStringBuffer);
        System.out.println(simpleStringBuffer);
    }
}
