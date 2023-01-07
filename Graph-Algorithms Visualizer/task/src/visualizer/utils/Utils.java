package visualizer.utils;

import java.awt.*;
import java.util.concurrent.atomic.AtomicLong;

public class Utils {
    public static final Font TIMES_ROMAN_28_PLAIN = new Font("TimesRoman", Font.PLAIN, 28);
    public static final Font TIMES_ROMAN_24_PLAIN = new Font("TimesRoman", Font.PLAIN, 24);
    public static final Font TIMES_ROMAN_14_BOLD = new Font("TimesRoman", Font.BOLD, 14);

    private static final AtomicLong atomicLong = new AtomicLong();

    public static long getNextId() {
        return atomicLong.getAndIncrement();
    }
}
