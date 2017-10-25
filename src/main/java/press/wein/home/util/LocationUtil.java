package press.wein.home.util;

/**
 * @author oukailiang
 * @create 2017-09-09 上午10:23
 */

public class LocationUtil {

    private static final double EARTH_RADIUS = 6371000;
    public static double getDistance(double lat0, double lng0, double lat1,
                                     double lng1) {
        lat0 = Math.toRadians(lat0);
        lat1 = Math.toRadians(lat1);
        lng0 = Math.toRadians(lng0);
        lng1 = Math.toRadians(lng1);

        double dislng = Math.abs(lng0 - lng1);
        double dislat = Math.abs(lat0 - lat1);
        double h = hav(dislat) + Math.cos(lat0) * Math.cos(lat1) * hav(dislng);
        double distance = 2 * EARTH_RADIUS * Math.asin(Math.sqrt(h));

        return distance;
    }

    public static double hav(double theta) {
        double s = Math.sin(theta / 2);
        return s * s;
    }
}
