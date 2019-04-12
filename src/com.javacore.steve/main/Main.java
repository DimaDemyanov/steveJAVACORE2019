package main;

import commandsUtils.Commands;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;

import java.io.IOException;

public class Main {

    public static final Logger LOGGER = LogManager.getLogger("Steve_logger");

    public static void main(String[] args) {
        Steve steve = null;
        try {
            steve = new Steve();
            steve.run();
        } catch (IOException e) {
            System.out.println("Cannot open properites");
        } catch (HibernateException e) {
            System.out.println("Cannot open db connection");
        }
    }
}
