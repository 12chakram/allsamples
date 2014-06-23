package org.apache.jsp.Resultrix;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class myjsp_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.List<java.lang.String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<java.lang.String>(1);
    _jspx_dependants.add("/Resultrix/header.jsp");
  }

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.List<java.lang.String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\r\n");
      out.write("<title>Insert title here</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write('\r');
      out.write('\n');

	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);

      out.write("\r\n");
      out.write("\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("<meta http-equiv=\"Expires\" content=\"Tue, 01 Jan 1965 12:12:12 GMT\">\r\n");
      out.write("<meta http-equiv=\"Pragma\" content=\"no-cache\">\r\n");
      out.write("\r\n");
      out.write("<title>ICICI PRUDENTIAL</title>\r\n");
      out.write("\r\n");
      out.write("<link href=\"html/css/styles1.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("<link rel=\"stylesheet\" media=\"screen\" href=\"superfish.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" href=\"html/css/stylesheet.css\" type=\"text/css\" />\r\n");
      out.write("<link rel=\"stylesheet\" href=\"html/css/style.css\" type=\"text/css\" />\r\n");
      out.write("<script src=\"superfish.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"html/js/jquery.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"html/js/fade-plugin.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"html/js/script.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("      var fader;\r\n");
      out.write("      (function($) {\r\n");
      out.write("        function init() {\r\n");
      out.write("          fader = $(\"#transitionEffect\").fadeTransition({pauseTime: 4000,\r\n");
      out.write("                                                 transitionTime: 1000,\r\n");
      out.write("                                                 ignore: \"#introslide\",\r\n");
      out.write("                                                 delayStart: 100,\r\n");
      out.write("                                                 manualNavigation: false,\r\n");
      out.write("                                                 pauseOnMouseOver: true,\r\n");
      out.write("                                                 createNavButtons: true});\r\n");
      out.write("        }\r\n");
      out.write("        \r\n");
      out.write("        $(document).ready(init);\r\n");
      out.write("      })(jQuery);\r\n");
      out.write("</script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"html/css/iepngfix/iepngfix_tilebg.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write("* { behavior: url(\"html/css/iepngfix/iepngfix.htc\") }\r\n");
      out.write("</style>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" src=\"html/js/jquery.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("$(document).ready(function () {\r\n");
      out.write("\t\r\n");
      out.write("\t$('#toggle-view li').click(function () {\r\n");
      out.write("\r\n");
      out.write("\t\tvar text = $(this).children('.show_div');\r\n");
      out.write("\r\n");
      out.write("\t\tif (text.is(':hidden')) {\r\n");
      out.write("\t\t\ttext.slideDown('200');\r\n");
      out.write("\t\t\t$(this).children('span').html('-');\t\t\r\n");
      out.write("\t\t} else {\r\n");
      out.write("\t\t\ttext.slideUp('200');\r\n");
      out.write("\t\t\t$(this).children('span').html('+');\t\t\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t});\r\n");
      out.write("\r\n");
      out.write("});\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("<script LANGUAGE=\"JavaScript1.2\">\r\n");
      out.write("function doLogout(){\r\n");
      out.write("\tif(confirm(\" This would close the window for security reasons. Do you want to continue?\") ){\r\n");
      out.write("\t\twindow.location = \"/ipru/logout.jsp\";\r\n");
      out.write("\t}\r\n");
      out.write("\telse{\r\n");
      out.write("\t//do nothing\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function okcancel()\r\n");
      out.write("{\r\n");
      out.write("\tif(confirm('Dear Customer,Surrender of your policy attracts surrender charges. For more details refer to the policy document. Click Ok to download form, or Cancel to stay on the same page'))\r\n");
      out.write("\t{\r\n");
      out.write("\t javascript:window.open('/public/Forms/Payout_Request.pdf');\r\n");
      out.write("\t}\t\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<form name=\"lintel\" method=\"post\" action=\"\">\r\n");
      out.write("\r\n");
      out.write("<div id=\"main_wapper\">\r\n");
      out.write("  <div class=\"top_logo\"><a href=\"#\"><img src=\"html/images/icici-logo.gif\" alt=\"ICICI PRUDENTIAL LIFE INSURANCE\" width=\"270\" height=\"51\" /></a></div>\r\n");
      out.write("  <div class=\"top_btn_box\"><a href=\"/customer/mypolicy.jsp\"  class=\"top_btn\"><img src=\"html/images/home.gif\" /></a>\t\t\t\r\n");
      out.write("\r\n");
      out.write("\t\t<a href=\"#\" class=\"top_btn\"><img src=\"html/images/changes_roles.gif\" /></a>\r\n");
      out.write("\r\n");
      out.write("\t<a href=\"javascript: doLogout()\"  class=\"top_btn\" style=\"margin-right:0px;\"><img src=\"html/images/logout.gif\" /></a> </div>\r\n");
      out.write("  <div class=\"clr\"></div>\r\n");
      out.write("  <div class=\"menu_box\" >\r\n");
      out.write("    <ul class=\"menu\" id=\"menu\" >\r\n");
      out.write("      <li><a href=\"#\" class=\"menulink\">My Profile <img src=\"html/images/down.gif\" /></a>\r\n");
      out.write("        <ul>\r\n");
      out.write("          <li><a href=\"/ipru/changeUserId.jsp\">Change User ID</a></li>\r\n");
      out.write("          <li> <a href=\"/ipru/chgpass.jsp\" >Change Password</a> </li>\r\n");
      out.write("          <li> <a href=\"/OnlineUpdate.do?hidSourceName=DetailView\" >View / Update Profile</a></li>\r\n");
      out.write("        </ul>\r\n");
      out.write("      </li>\r\n");
      out.write("      <li><img src=\"html/images/linkdivider.jpg\" /></li>\r\n");
      out.write("      <li> <a href=\"#\" class=\"menulink\">e-Transactions<img src=\"html/images/down.gif\" /></a>\r\n");
      out.write("        <ul>\r\n");
      out.write("          <li><a href=\"/opservlet.do?hidSourceName=LHSMenuView&lhsMenuOption=payPremium&type=pay\">Pay Premium</a></li>\r\n");
      out.write("          <li><a href=\"/customer/topup/transactPolicy.jsp?reqType=4\">Top Up</a></li>\r\n");
      out.write("          <li><a href=\"/customer/TransactPolicy.jsp?reqType=1\">Switch Online</a></li>\r\n");
      out.write("          <li><a href=\"/customer/TransactPolicy.jsp?reqType=2\">Premium Redirection</a></li>\r\n");
      out.write("          <li><a href=\"/customer/TransactPolicy.jsp?reqType=3\">Change in Portfolio Strategy</a></li>\r\n");
      out.write("          <li> <a href=\"#\" >e- Transaction  History</a>\r\n");
      out.write("            <div class=\"sumaroow\"><img src=\"html/images/arrow.gif\" /></div>\r\n");
      out.write("            <ul>\r\n");
      out.write("              <li class=\"topline\"><a href=\"/opservlet.do?hidSourceName=LHSMenuView&lhsMenuOption=paymentHistory\">Payment</a></li>\r\n");
      out.write("              <li><a href=\"/opservlet.do?hidSourceName=OnlineXRTHistoryView&lhsMenuOption=onlinepdr\">PDR</a></li>\r\n");
      out.write("              <li><a href=\"/ipru.do?reqType=4&cId=TopupPageView&actionType=policyhistory\">Top Up</a></li>\r\n");
      out.write("              <li><a href=\"#\">Switch</a></li>\r\n");
      out.write("              <li><a href=\"#\">Premium Redirection</a></li>\r\n");
      out.write("            </ul>\r\n");
      out.write("          </li>\r\n");
      out.write("        </ul>\r\n");
      out.write("      </li>\r\n");
      out.write("      <li><img src=\"html/images/linkdivider.jpg\" /></li>\r\n");
      out.write("      <li> <a href=\"#\" class=\"menulink\">e-Statements <img src=\"html/images/down.gif\" /></a>\r\n");
      out.write("        <ul>\r\n");
      out.write("          <li><a href=\"/customer/lapsedLetterPage.jsp\">Lapsed Letter</a></li>\r\n");
      out.write("          <li> <a href=\"/customer/notices.jsp\" >Notice</a> </li>\r\n");
      out.write("          <li ><a href=\"/customer/portfolioInfo.jsp\">Portfolio Statement</a></li>\r\n");
      out.write("          <li><a href=\"/IndOnlineCRPRControlerServlet.do?actionCmd=view\">Premium Paid Certificate</a></li>\r\n");
      out.write("          <li><a href=\"/customer/receipts.jsp\">Receipt</a></li>\r\n");
      out.write("          <li><a href=\"/customer/unitStmtInfo.jsp?type=cust\">Unit Statment</a></li>\r\n");
      out.write("          <li><a href=\"/WelcomeKitServlet.do\">Welcome Kit(including e-Policy Certificate)</a></li>\r\n");
      out.write("        </ul>\r\n");
      out.write("      </li>\r\n");
      out.write("      <li><img src=\"html/images/linkdivider.jpg\" /></li>\r\n");
      out.write("      <li><a href=\"#\" class=\"menulink\">Information Center <img src=\"html/images/down.gif\" /></a>\r\n");
      out.write("        <ul>\r\n");
      out.write("          <li><a href=\"#\"  >Policy Details</a>\r\n");
      out.write("            <div class=\"sumaroow\"><img src=\"html/images/arrow.gif\" /></div>\r\n");
      out.write("            <ul>\r\n");
      out.write("              <li ><a href=\"/customer/paysum.jsp\">Payment Summary</a></li>\r\n");
      out.write("              <li><a href=\"/customer/customerpreforeclosedpolicydetail.jsp\">Preforeclosure Alert</a></li>\r\n");
      out.write("              <li><a href=\"/customer/premiumCalendar.jsp\">Premium Calendar</a></li>\r\n");
      out.write("            </ul>\r\n");
      out.write("          </li>\r\n");
      out.write("          <li><a href=\"#\" >Download Forms</a>\r\n");
      out.write("            <div class=\"sumaroow\"><img src=\"html/images/arrow.gif\" /></div>\r\n");
      out.write("            <ul>\r\n");
      out.write("              <li ><a href=\"/ipru/appform.jsp\">Application Form</a></li>\r\n");
      out.write("              <li><a href=\"/ecsDD/ecsDDSecureHome.jsp\">ECS/Direct Debit Online Mandate</a></li>\r\n");
      out.write("              <li><a href=\"/ipru/formattrigger/securePolicySelection.jsp\">Service Request Form</a></li>\r\n");
      out.write("              <li><a href=\"javascript:okcancel()\">Surrender/Partial Withdrawal Form</a></li>\r\n");
      out.write("            </ul>\r\n");
      out.write("          </li>\r\n");
      out.write("          <li><a href=\"/public/Others/Premium-Payment.htm\" target=\"_blank\">Payment Methods</a></li>\r\n");
      out.write("          <li><a href=\"/public/plans.htm\" target=\"_blank\">Products</a></li>\r\n");
      out.write("          <li><a href=\"/iprucampaign/iprucampaign.jsp?type=website\" target=\"_blank\">Send Me An Advisor</a></li>\r\n");
      out.write("          <li> <a href=\"#\" >Tools and Calculators</a>\r\n");
      out.write("            <div class=\"sumaroow\"><img src=\"html/images/arrow.gif\" /></div>\r\n");
      out.write("            <ul>\r\n");
      out.write("              <li ><a href=\"/ipru/howmuchinsdoineed.jsp\">Insurance Calculator</a></li>\r\n");
      out.write("              <li><a href=\"/ipru/personal.jsp\">Retirement Calculator</a></li>\r\n");
      out.write("            </ul>\r\n");
      out.write("          </li>\r\n");
      out.write("        </ul>\r\n");
      out.write("      </li>\r\n");
      out.write("      <li><a href=\"/ipru/claims.jsp\" class=\"menulink\">Claims </a> </li>\r\n");
      out.write("      <li><img src=\"html/images/linkdivider.jpg\" /></li>\r\n");
      out.write("      <li><a href=\"#\" class=\"menulink\">Contact Us <img src=\"html/images/down.gif\" /></a>\r\n");
      out.write("        <ul>\r\n");
      out.write("          <li><a href=\"/public/Others/Call_Us.htm\" target=\"_blank\">Contact Touch Points</a></li>\r\n");
      out.write("          <li> <a href=\"/customer/webform.jsp\" >Email Us</a> </li>\r\n");
      out.write("        </ul>\r\n");
      out.write("      </li>\r\n");
      out.write("    </ul>\r\n");
      out.write("    </li>\r\n");
      out.write("    </ul>\r\n");
      out.write("    <div class=\"clr\"></div>\r\n");
      out.write("  </div>\r\n");
      out.write("  \r\n");
      out.write("<!-- x\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\tvar menu=new menu.dd(\"menu\");\r\n");
      out.write("\tmenu.init(\"menu\",\"menuhover\");\r\n");
      out.write("</script> -->\r\n");
      out.write("<script> \r\n");
      out.write(" \r\n");
      out.write("    $(document).ready(function(){ \r\n");
      out.write("        $(\"ul.menu\").superfish({ \r\n");
      out.write("            animation: {height:'show'},   // slide-down effect without fade-in \r\n");
      out.write("            delay:     1200               // 1.2 second delay on mouseout \r\n");
      out.write("        }); \r\n");
      out.write("    }); \r\n");
      out.write(" \r\n");
      out.write("</script>\r\n");
      out.write("</form>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
