package by.bsuir.util;

import by.bsuir.command.constant.Attribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ResourceBundle;

public class TextUtil {
    public static final String TRUE = "true";
    public static final String FALSE = "false";
    public static final String BYN = "BYN";
    public static final String SPACE = " ";
    public static final String SLASH = "/";
    private static final String QUESTION_MARK = "?";

    private static final String EMPTY = "";
    private static final String LOCAL = "local_";
    private static final String P = "<p class='";
    private static final String P_CLOSE = "'>";
    private static final String P_END = "</p>";
    private static final String SPAN = "<span class='";
    private static final String TITLE = "' title='";
    private static final String SPAN_CLOSE = "'>";
    private static final String SPAN_END = "</span>";
    private static final String ALERT = "<div class='alert alert-success' id='alert'><button href='#' class='close' " +
            "data-dismiss='alert' aria-label='close'>×</button><p>Вы ";
    private static final String SUBS = "<b>подписались</b> на обновления ";
    private static final String UNSUBS = "<b class='error'>отписались</b> от обновлений ";
    private static final String ALERT_CENTER = "категории \"";
    private static final String ALERT_END = "\"</p></div>";

    private static final String TBODY_START = "<tbody>";
    private static final String TBODY_END = "</tbody>";
    private static final String TR = "<tr><td>";
    private static final String TD = "</td><td>";
    private static final String TR_END = "</td></tr>";
    private static final String DAY_RU = "дней";
    private static final String DAY_EN = "days";
    private static final String KBPS_RU = "Кбит/с";
    private static final String KBPS_EN = "kbps";

    private static final String TABLE_START = "<table>";
    private static final String TABLE_END = "</table>";
    private static final String BUTTON_START = "<button class='delete'>";
    private static final String BUTTON_END = "</button>";

    public static String concat(String... strings){
        StringBuilder sb = new StringBuilder();
        for(String s : strings){
            sb.append(s);
        }
        return sb.toString();
    }


    public static String getQueryString(HttpServletRequest request){
        String URI = request.getRequestURI();
        String query = request.getQueryString();
        return query == null ? URI : concat(URI, QUESTION_MARK, query);
    }

    public static String getRedirectPath(HttpSession session, String defaultPath){
        String path = (String) session.getAttribute(Attribute.LAST_QUERY);
        return path == null ? defaultPath : path;
    }

    public static String getSubscriptionAlert(String categoryName, boolean isSubscription){
        StringBuilder sb = new StringBuilder();
        sb.append(ALERT);
        sb.append(isSubscription ? SUBS : UNSUBS);
        sb.append(ALERT_CENTER);
        sb.append(categoryName).append(ALERT_END);
        return sb.toString();
    }

    public static String getParagraphHtmlElement(String elementClass, String key, String language){
        ResourceBundle bundle = ResourceBundle.getBundle(LOCAL + language);
        return concat(P, elementClass, P_CLOSE, bundle.getString(key), P_END);
    }

    public static String getSpanHtmlElement(String elementClass, String title){
        StringBuilder sb = new StringBuilder(SPAN).append(elementClass);
        if(title != null){
            sb.append(TITLE).append(title);
        }
        sb.append(SPAN_CLOSE).append(SPAN_END);
        return sb.toString();
    }

//    public static String getTariffTableContent(TariffDTO tariff, String language, String name, String cost,
//                                               String period, String load, String upload, String description){
//        ResourceBundle bundle = ResourceBundle.getBundle(LOCAL + language);
//
//        String rusDescription = (tariff.getRusDescription() == null) ? EMPTY : tariff.getRusDescription();
//        String engDescription = (tariff.getEngDescription() == null) ? EMPTY : tariff.getEngDescription();
//
//        StringBuilder sb = new StringBuilder(TBODY_START);
//        sb.append(TR).append(bundle.getString(name)).append(TD).append(tariff.getRusName());
//        sb.append(TD).append(tariff.getEngName()).append(TR_END).append(TR).append(bundle.getString(cost)).append(TD);
//        sb.append(tariff.getMonthlyCost()).append(SPACE).append(BYN).append(TD).append(tariff.getMonthlyCost());
//        sb.append(SPACE).append(BYN).append(TR_END).append(TR).append(bundle.getString(period));
//        sb.append(TD).append(tariff.getPeriod()).append(SPACE).append(DAY_RU).append(TD).append(tariff.getPeriod());
//        sb.append(SPACE).append(DAY_EN).append(TR_END).append(TR).append(bundle.getString(load));
//        sb.append(TD).append(tariff.getLoadSpeed()).append(SPACE).append(KBPS_RU).append(TD).append(tariff.getLoadSpeed());
//        sb.append(SPACE).append(KBPS_EN).append(TR_END).append(TR).append(bundle.getString(upload)).append(TD);
//        sb.append(tariff.getUploadSpeed()).append(SPACE).append(KBPS_RU).append(TD).append(tariff.getUploadSpeed());
//        sb.append(SPACE).append(KBPS_EN).append(TR_END).append(TR).append(bundle.getString(description)).append(TD);
//        sb.append(rusDescription).append(TD).append(engDescription).append(TR_END).append(TBODY_END);
//
//        return sb.toString();
//    }

//    public static String getStatisticTable(List<Payment> payments, String language,
//                                           String accrued, String withdraw, String delete){
//        return concat(TABLE_START, TBODY_START,
//                getPaymentContent(payments, language, accrued, withdraw, delete), TBODY_END, TABLE_END);
//    }

//    public static String getPaymentContent(List<Payment> payments, String language,
//                                           String accrued, String withdraw, String delete){
//        ResourceBundle bundle = ResourceBundle.getBundle(LOCAL + language);
//        StringBuilder sb = new StringBuilder();
//        for(Payment payment : payments){
//            sb.append(TR).append(DateManager.toString(payment.getDate())).append(TD);
//            sb.append(payment.getSum()).append(SPACE).append(BYN).append(TD);
//            sb.append(payment.isAdd() ? bundle.getString(accrued) : bundle.getString(withdraw));
//            sb.append(TD).append(BUTTON_START).append(bundle.getString(delete)).append(BUTTON_END).append(TR_END);
//        }
//        return sb.toString();
//    }

//    public static String getTrafficContent(List<Traffic> traffics, String language, String mb, String delete){
//        ResourceBundle bundle = ResourceBundle.getBundle(LOCAL + language);
//        StringBuilder sb = new StringBuilder();
//        for(Traffic traffic : traffics){
//            sb.append(TR).append(DateManager.toStringWithoutTime(traffic.getDate())).append(TD).append(traffic.getTraffic());
//            sb.append(SPACE).append(bundle.getString(mb)).append(TD).append(BUTTON_START);
//            sb.append(bundle.getString(delete)).append(BUTTON_END).append(TR_END);
//        }
//        return sb.toString();
//    }
}
