package ink.baojie.cloud.appauth8102.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author renbaojie
 */
@Slf4j
public class IpUtils {

    public static String getRemoteIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    // e.printStackTrace();
                }
                ip = inet.getHostAddress();
            }
        }
        if (ip == null) {
            return "unknown";
        }

        //对于通过多个代理的情况，第一个IP为客户端真实IP，多个IP按照','分割
        if (ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip;
    }

    public static String getLocalIp() {
        Enumeration<?> netInterfaces;
        List<NetworkInterface> netlist = new ArrayList<>();
        try {
            //获取当前环境下的所有网卡
            netInterfaces = NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
                if (ni.isLoopback()) {
                    //过滤 lo网卡
                    continue;
                }
                //倒置网卡顺序
                netlist.add(0, ni);
            }

            // 用上述方法获取所有网卡时，得到的顺序与服务器中用ifconfig命令看到的网卡顺序相反，
            // 因此，想要从第一块网卡开始遍历时，需要将Enumeration<?>中的元素倒序

            // 遍历每个网卡
            for (NetworkInterface list : netlist) {

                // 获取网卡下所有ip
                Enumeration<?> cardipaddress = list.getInetAddresses();

                // 将网卡下所有ip地址取出
                while (cardipaddress.hasMoreElements()) {
                    InetAddress ip = (InetAddress) cardipaddress.nextElement();
                    if (!ip.isLoopbackAddress()) {
                        if (ip.getHostAddress().equalsIgnoreCase("127.0.0.1")) {
                            continue;
                        }
                        // 过滤ipv6地址  add by liming 2013-9-3
                        if (ip instanceof Inet6Address) {
                            //return ip.getHostAddress();
                            continue;
                        }
                        // 返回ipv4地址
                        if (ip instanceof Inet4Address) {
                            return ip.getHostAddress();
                        }
                    }
                    // 默认返回
                    return InetAddress.getLocalHost().getHostAddress();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
