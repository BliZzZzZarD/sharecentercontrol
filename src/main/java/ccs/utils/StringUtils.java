package ccs.utils;

public class StringUtils {
    public static String getPrepareText(String text) {
        return text != null ? "%" + text + "%" : "%";
    }
}
