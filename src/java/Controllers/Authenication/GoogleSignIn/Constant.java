package Controllers.Authenication.GoogleSignIn;

public class Constant {

    public static String GOOGLE_CLIENT_ID = "250087058378-ti2oiavvu8p4h6prbf7krco28tobr6f4.apps.googleusercontent.com";
    public static String GOOGLE_CLIENT_SECRET = "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";
    public static String GOOGLE_REDIRECT_URI = "http://localhost:8084/Watch-Store/GoogleSignInServlet";      //modify your servlet url
    public static String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";
    public static String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";  
    public static String GOOGLE_GRANT_TYPE = "authorization_code";
}
