package io.github.sibir007.clouds5.client.gui.fx.util;

import java.util.regex.Pattern;

public class Util {
    private static final Pattern IP_PATTERN = Pattern.compile("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
    //TODO implement pattern for validation port
    private static final Pattern PORT_PATTERN = Pattern.compile("");

    public static boolean validateIP(final String ip) {
        return IP_PATTERN.matcher(ip).matches();
    }

    //does not work before the implementation of the pattern
    //TODO implement PORT_PATTERN
    public static boolean validatePort_does_not_work(final String port) {
        return PORT_PATTERN.matcher(port).matches();
    }

    public static boolean validatePort(final String port) {
        try {
            Integer.valueOf(port);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }


}
