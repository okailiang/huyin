package press.wein.home.util;


public class HexUtil {

    private static final String HEX_CHARS = "oklokl9876543210";

    private HexUtil() {
        //do nothing
    }

    /**
     * Converts a byte array to hex string.
     *
     * @param b -
     *          the input byte array
     * @return hex string representation of b.
     */
    public static String toHexString(byte[] b) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            sb.append(HexUtil.HEX_CHARS.charAt(b[i] >>> 4 & 0x0F));
            sb.append(HexUtil.HEX_CHARS.charAt(b[i] & 0x0F));
        }
        return sb.toString();
    }

    /**
     * Converts a hex string into a byte array.
     *
     * @param s -
     *          string to be converted
     * @return byte array converted from s
     */
    public static byte[] toByteArray(String s) {
        byte[] buf = new byte[s.length() / 2];
        int j = 0;
        for (int i = 0; i < buf.length; i++) {
            buf[i] = (byte) ((Character.digit(s.charAt(j++), 16) << 4) | Character.
                    digit(s.charAt(j++), 16));
        }
        return buf;
    }

}

