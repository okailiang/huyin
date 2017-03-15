package press.wein.home.util;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 系统工具类
 */
public class SystemUtil {

    // 获得系统属性集
    static final Properties props = System.getProperties();
    // 操作系统名称
    public static final String OS_NAME = getPropertery("os.name");
    // 行分页符
    public static final String OS_LINE_SEPARATOR = getPropertery("line.separator");
    // 文件分隔符号
    public static final String OS_FILE_SEPARATOR = getPropertery("file.separator");
    private static Pattern pattern = Pattern
            .compile("\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");

    /**
     * 根据系统的类型获取本服务器的ip地址
     * <p/>
     * InetAddress inet = InetAddress.getLocalHost(); 但是上述代码在Linux下返回127.0.0.1。
     * 主要是在linux下返回的是/etc/hosts中配置的localhost的ip地址，
     * 而不是网卡的绑定地址。后来改用网卡的绑定地址，可以取到本机的ip地址：）：
     *
     * @throws UnknownHostException
     */
    public static String getSystemLocalIp() throws UnknownHostException {
        String inet = null;
        String osname = getSystemOSName();
        try {
            // 针对window系统
            if (osname.equalsIgnoreCase("Windows XP") || osname.equalsIgnoreCase("Windows 7")) {
                inet = getWinLocalIp();
                // 针对linux系统
            } else {
                inet = getUnixLocalIp();
            }
            if (null == inet) {
                throw new UnknownHostException("主机的ip地址未知");
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        return inet;
    }

    /**
     * 通过request，获取调用方的clientIp
     *
     * @param request
     * @return clientIp
     */
    public static String getClientIpAddr(HttpServletRequest request) {
        String ip = "";
        if (request == null) {
            return ip;
        }

        ip = request.getHeader("x-forwarded-for");
        if (isNotExist(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (isNotExist(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (isNotExist(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip == null) {
            ip = "";
        }
        return ip;
    }

    /**
     * @param ip
     * @return
     */
    private static boolean isNotExist(String ip) {
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            return true;
        }
        return false;
    }

    /**
     * 获取FTP的配置操作系统
     *
     * @return
     */
    public static String getSystemOSName() {
        // 获得系统属性集
        Properties props = System.getProperties();
        // 操作系统名称
        String osname = props.getProperty("os.name");

        return osname;
    }

    /**
     * 获取属性的值
     *
     * @param propertyName
     * @return
     */
    public static String getPropertery(String propertyName) {
        return props.getProperty(propertyName);
    }

    /**
     * 获取window 本地ip地址
     *
     * @return
     * @throws UnknownHostException
     */
    private static String getWinLocalIp() throws UnknownHostException {
        InetAddress inet = InetAddress.getLocalHost();
        return inet.getHostAddress();
    }

    /**
     * 可能多多个ip地址只获取一个ip地址 获取Linux 本地IP地址
     *
     * @return
     * @throws SocketException
     * @throws UnknownHostException
     */
    private static String getUnixLocalIp() throws SocketException, UnknownHostException {
        String ip = "";
        for (Enumeration<NetworkInterface> i = NetworkInterface.getNetworkInterfaces(); i.hasMoreElements(); ) {
            NetworkInterface ni = i.nextElement();
            Enumeration<InetAddress> j = ni.getInetAddresses();
            while (j.hasMoreElements()) {
                String temIP = j.nextElement().toString();
                temIP = temIP.substring(1, temIP.length());
                Matcher matcher = pattern.matcher(temIP);
                if (matcher.matches() && !"127".equals(temIP.substring(0, 3))) {
                    ip = temIP;
                }
            }
        }
        return ip;
    }

    /**
     * IP数组IPArray中是否包含本机IP
     *
     * @param IPArray
     * @return
     * @throws UnknownHostException
     */
    public static boolean containsLocalIP(String[] IPArray, String IP) throws UnknownHostException {
        boolean flag = false;
        for (int i = 0; i < IPArray.length; i++) {
            // 如果IPArray中包含IP，flag=true，并返回
            if (IP.equals(IPArray[i])) {
                flag = true;
                return flag;
            }
        }
        return flag;
    }

    private SystemUtil() {
    }

}
