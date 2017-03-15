package press.wein.home.common;

import press.wein.home.model.bo.UserSession;

/**
 * 应用获取用户session
 * @author oukailiang
 * @create 2017-02-22 下午5:34
 */

public class ApplicationUserContext {

    private static final ThreadLocal<UserSession> session = new ThreadLocal<UserSession>();


    private ApplicationUserContext() {
        //do nothing
    }

    public static UserSession getUser() {

        return session.get();
    }

    public static void setUser(UserSession user) {
        session.set(user);
    }

    public static void clear() {
        session.set(null);
    }
}
