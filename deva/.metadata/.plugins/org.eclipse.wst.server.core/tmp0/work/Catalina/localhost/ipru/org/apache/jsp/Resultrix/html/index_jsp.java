package org.apache.jsp.Resultrix.html;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.List<java.lang.String> _jspx_dependants;

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
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("<title>ICICI PRUDENTIAL</title>\r\n");
      out.write("<link href=\"css/stylesheet.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("<script type=\"text/javascript\" src=\"js/jquery.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"js/fade-plugin.js\"></script>\r\n");
      out.write("<link rel=\"stylesheet\" href=\"css/style.css\" type=\"text/css\" />\r\n");
      out.write("<script type=\"text/javascript\" src=\"js/script.js\"></script>\r\n");
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
      out.write("    </script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"css/iepngfix/iepngfix_tilebg.js\"></script>\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write("* { behavior: url(\"css/iepngfix/iepngfix.htc\") }\r\n");
      out.write("</style>\r\n");
      out.write("<script type=\"text/javascript\" src=\"js/jquery.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\r\n");
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
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<div id=\"main_wapper\">\r\n");
      out.write("  <div class=\"top_logo\"><a href=\"#\"><img src=\"images/icici-logo.gif\" alt=\"ICICI PRUDENTIAL LIFE INSURANCE\" width=\"270\" height=\"51\" /></a></div>\r\n");
      out.write("  <div class=\"top_btn_box\"><a href=\"#\"  class=\"top_btn\"><img src=\"images/home.gif\" /></a><a href=\"#\" class=\"top_btn\"><img src=\"images/changes_roles.gif\" /></a> <a href=\"#\"  class=\"top_btn\" style=\"margin-right:0px;\"><img src=\"images/logout.gif\" /></a> </div>\r\n");
      out.write("  <div class=\"clr\"></div>\r\n");
      out.write("  <div class=\"menu_box\" >\r\n");
      out.write("    <ul class=\"menu\" id=\"menu\" >\r\n");
      out.write("      <li><a href=\"#\" class=\"menulink\">My Profile <img src=\"images/down.gif\" /></a>\r\n");
      out.write("        <ul>\r\n");
      out.write("          <li><a href=\"#\">Change User ID</a></li>\r\n");
      out.write("          <li> <a href=\"#\" >Change Password</a> </li>\r\n");
      out.write("          <li> <a href=\"#\" >View / Update Profile</a></li>\r\n");
      out.write("        </ul>\r\n");
      out.write("      </li>\r\n");
      out.write("      <li><img src=\"images/linkdivider.jpg\" /></li>\r\n");
      out.write("      <li> <a href=\"#\" class=\"menulink\">e-Transactions<img src=\"images/down.gif\" /></a>\r\n");
      out.write("        <ul>\r\n");
      out.write("          <li><a href=\"#\">Pay Premium</a></li>\r\n");
      out.write("          <li><a href=\"#\">Top Up</a></li>\r\n");
      out.write("          <li><a href=\"#\">Switch Online</a></li>\r\n");
      out.write("          <li><a href=\"#\">Premium Redirection</a></li>\r\n");
      out.write("          <li><a href=\"#\">Change in Portfolio Strategy</a></li>\r\n");
      out.write("          <li> <a href=\"#\" >e- Transaction  History</a>\r\n");
      out.write("            <div class=\"sumaroow\"><img src=\"images/arrow.gif\" /></div>\r\n");
      out.write("            <ul>\r\n");
      out.write("              <li class=\"topline\"><a href=\"#\">Payment</a></li>\r\n");
      out.write("              <li><a href=\"#\">PDR</a></li>\r\n");
      out.write("              <li><a href=\"#\">Top Up</a></li>\r\n");
      out.write("              <li><a href=\"#\">Switch</a></li>\r\n");
      out.write("              <li><a href=\"#\">Premium Redirection</a></li>\r\n");
      out.write("            </ul>\r\n");
      out.write("          </li>\r\n");
      out.write("        </ul>\r\n");
      out.write("      </li>\r\n");
      out.write("      <li><img src=\"images/linkdivider.jpg\" /></li>\r\n");
      out.write("      <li> <a href=\"#\" class=\"menulink\">e-Statements <img src=\"images/down.gif\" /></a>\r\n");
      out.write("        <ul>\r\n");
      out.write("          <li><a href=\"#\">Lapsed Letter</a></li>\r\n");
      out.write("          <li> <a href=\"#\" >Notice</a> </li>\r\n");
      out.write("          <li ><a href=\"#\">Portfolio Statement</a></li>\r\n");
      out.write("          <li><a href=\"#\">Premium Paid Certificate</a></li>\r\n");
      out.write("          <li><a href=\"#\">Receipt</a></li>\r\n");
      out.write("          <li><a href=\"#\">Unit Statment</a></li>\r\n");
      out.write("          <li><a href=\"#\">Welcome Kit(including e-Policy Certificate)</a></li>\r\n");
      out.write("        </ul>\r\n");
      out.write("      </li>\r\n");
      out.write("      <li><img src=\"images/linkdivider.jpg\" /></li>\r\n");
      out.write("      <li><a href=\"#\" class=\"menulink\">Information Center <img src=\"images/down.gif\" /></a>\r\n");
      out.write("        <ul>\r\n");
      out.write("          <li><a href=\"#\"  >Policy Details</a>\r\n");
      out.write("            <div class=\"sumaroow\"><img src=\"images/arrow.gif\" /></div>\r\n");
      out.write("            <ul>\r\n");
      out.write("              <li ><a href=\"#\">Payment Summary</a></li>\r\n");
      out.write("              <li><a href=\"#\">Preforeclosure Alert</a></li>\r\n");
      out.write("              <li><a href=\"#\">Premium Calendar</a></li>\r\n");
      out.write("            </ul>\r\n");
      out.write("          </li>\r\n");
      out.write("          <li><a href=\"#\" >Download Forms</a>\r\n");
      out.write("            <div class=\"sumaroow\"><img src=\"images/arrow.gif\" /></div>\r\n");
      out.write("            <ul>\r\n");
      out.write("              <li ><a href=\"#\">Application Form</a></li>\r\n");
      out.write("              <li><a href=\"#\">ECS/Direct Debit Online Mandate</a></li>\r\n");
      out.write("              <li><a href=\"#\">Service Request Form</a></li>\r\n");
      out.write("              <li><a href=\"#\">Surrender /Partial Withdrawal Form</a></li>\r\n");
      out.write("            </ul>\r\n");
      out.write("          </li>\r\n");
      out.write("          <li><a href=\"#\" >Payment Methods</a></li>\r\n");
      out.write("          <li><a href=\"#\" >Products</a></li>\r\n");
      out.write("          <li><a href=\"#\" >Send Me An Advisor</a></li>\r\n");
      out.write("          <li> <a href=\"#\" >Tools and Calculators</a>\r\n");
      out.write("            <div class=\"sumaroow\"><img src=\"images/arrow.gif\" /></div>\r\n");
      out.write("            <ul>\r\n");
      out.write("              <li ><a href=\"#\">Insurance Calculator</a></li>\r\n");
      out.write("              <li><a href=\"#\">Retirement Calculator</a></li>\r\n");
      out.write("            </ul>\r\n");
      out.write("          </li>\r\n");
      out.write("        </ul>\r\n");
      out.write("      </li>\r\n");
      out.write("      <li><a href=\"#\" class=\"menulink\">Claims </a> </li>\r\n");
      out.write("      <li><img src=\"images/linkdivider.jpg\" /></li>\r\n");
      out.write("      <li><a href=\"#\" class=\"menulink\">Contact Us <img src=\"images/down.gif\" /></a>\r\n");
      out.write("        <ul>\r\n");
      out.write("          <li><a href=\"#\">Contact Touch Points</a></li>\r\n");
      out.write("          <li> <a href=\"#\" >Email Us</a> </li>\r\n");
      out.write("        </ul>\r\n");
      out.write("      </li>\r\n");
      out.write("    </ul>\r\n");
      out.write("    </li>\r\n");
      out.write("    </ul>\r\n");
      out.write("    <div class=\"clr\"></div>\r\n");
      out.write("  </div>\r\n");
      out.write("  <div class=\"alert_box_bg\">\r\n");
      out.write("    <table width=\"96%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\r\n");
      out.write("      <tr>\r\n");
      out.write("        <td width=\"651\" height=\"20\" align=\"left\" valign=\"top\" class=\"h1\"><strong>Welcome, <span class=\"h2\">Preeti B. Sancheti.</span> You last visited the site on December, 29, 2011 04:11:36 PM IST.</strong></td>\r\n");
      out.write("        <td width=\"161\" align=\"center\" valign=\"middle\" class=\" primum_btn_bg\" ><table width=\"85%\" border=\"0\" align=\"right\" cellpadding=\"0\" cellspacing=\"0\">\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td height=\"20\" align=\"left\" valign=\"middle\">Premium alerts ( 102 )</td>\r\n");
      out.write("    </tr>\r\n");
      out.write("</table></td>\r\n");
      out.write("        <td width=\"11\" align=\"center\" valign=\"middle\" class=\"h1\">&nbsp;</td>\r\n");
      out.write("        <td width=\"108\" align=\"left\" valign=\"top\" class=\"inbox_btn_bg\"><table width=\"78%\" border=\"0\" align=\"right\" cellpadding=\"0\" cellspacing=\"0\">\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td height=\"20\" align=\"left\" valign=\"middle\">Inbox ( 102 )</td>\r\n");
      out.write("    </tr>\r\n");
      out.write("</table>\r\n");
      out.write("</td>\r\n");
      out.write("      </tr>\r\n");
      out.write("      <tr>\r\n");
      out.write("        <td height=\"35\" colspan=\"4\" align=\"left\" valign=\"middle\"><table width=\"100%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\r\n");
      out.write("            <tr>\r\n");
      out.write("              <td width=\"4%\" align=\"center\" valign=\"top\"><img src=\"images/alert_update.jpg\" width=\"24\" height=\"26\" /></td>\r\n");
      out.write("              <td width=\"14%\" align=\"center\" valign=\"middle\"><strong class=\"h5\">Alerts and updates</strong></td>\r\n");
      out.write("              <td width=\"82%\" align=\"left\" valign=\"middle\"><div class=\"alert_note_box \">Lorem Ipsum is <span class=\"h4\">simply dummy text</span> of the printing and typesetting industry the printing</div></td>\r\n");
      out.write("            </tr>\r\n");
      out.write("          </table></td>\r\n");
      out.write("      </tr>\r\n");
      out.write("    </table>\r\n");
      out.write("  </div>\r\n");
      out.write("  <div class=\"banner_shedow_box\"><img src=\"images/box_drop_shedow.jpg\" width=\"970\" height=\"11\" /></div>\r\n");
      out.write("  <div class=\"banner_box\">\r\n");
      out.write("    <div class=\"banner_left\">\r\n");
      out.write("      <div class=\"banner_containe\">\r\n");
      out.write("        <div class=\"banner_headding h1\"><strong>Experts speak on your policy holdings</strong></div>\r\n");
      out.write("        <div class=\"banner_image\"><img src=\"images/image1.png\" width=\"130\" height=\"132\" /></div>\r\n");
      out.write("        <div class=\"text\">\r\n");
      out.write("          <div class=\"h3\">Your family still needs more protection!<br />\r\n");
      out.write("            <span class=\"h6\">To protect their future, you need  </span><span class=\"h7\">22% more cover <span class=\"h6\">Give <br />\r\n");
      out.write("            them comprehensive protection by taking</span></span> <span class=\"h6\">a policy with <br />\r\n");
      out.write("            premium of </span><span class=\"h7\"><img src=\"images/ru1.jpg\" width=\"10\" height=\"13\" />2500 pa </span><br />\r\n");
      out.write("            <br />\r\n");
      out.write("            <a href=\"#\"><img src=\"images/know_more.jpg\" /></a> <a href=\"#\"><img src=\"images/talk_expert.jpg\" width=\"145\" height=\"32\" /></a><br />\r\n");
      out.write("          </div>\r\n");
      out.write("          <div class=\"total_sum_box\"><img src=\"images/box1.jpg\" /></div>\r\n");
      out.write("          <div class=\"total_sum_taxt\"><strong>Total sum assured with us <img src=\"images/ru2.jpg\" width=\"9\" height=\"11\" />18,00,000</strong></div>\r\n");
      out.write("          <div class=\"total_sum_box\"><img src=\"images/box2.jpg\" /></div>\r\n");
      out.write("          <div class=\"total_sum_taxt\"><strong>Uncovered for</strong> <strong><img src=\"images/ru2.jpg\" width=\"9\" height=\"11\" />6,00,000</strong></div>\r\n");
      out.write("          <div class=\"clr\"></div>\r\n");
      out.write("        </div>\r\n");
      out.write("      </div>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"banner_right\">\r\n");
      out.write("      <div id=\"transitionEffect\">\r\n");
      out.write("        <div class=\"slide r1\"><img src=\"images/icare_banner.jpg\" alt=\"\" width=\"220\" height=\"190\" /></div>\r\n");
      out.write("        <div class=\"slide r2\"><img src=\"images/icare_banner2.jpg\" width=\"220\" height=\"190\" /></div>\r\n");
      out.write("        <div class=\"slide r3\"><img src=\"images/icare_banner.jpg\" alt=\"\" width=\"220\" height=\"190\" /></div>\r\n");
      out.write("      </div>\r\n");
      out.write("    </div>\r\n");
      out.write("  </div>\r\n");
      out.write("  <div class=\"policy_summary_box\">\r\n");
      out.write("    <div class=\"policy_summary_top\"><img src=\"images/policy_summury_top.jpg\" width=\"930\" height=\"8\" /></div>\r\n");
      out.write("    <div class=\"policy_summary_middle\">\r\n");
      out.write("      <div class=\"policy_summary__headding\">\r\n");
      out.write("        <table width=\"90%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("          <tr>\r\n");
      out.write("            <td height=\"55\" align=\"center\" valign=\"middle\"><strong>Policy Summary</strong></td>\r\n");
      out.write("          </tr>\r\n");
      out.write("        </table>\r\n");
      out.write("      </div>\r\n");
      out.write("      <div class=\"Quick_Links_box\">\r\n");
      out.write("        <table width=\"550\" border=\"0\" align=\"left\" cellpadding=\"0\" cellspacing=\"0\">\r\n");
      out.write("          <tr>\r\n");
      out.write("            <td width=\"121\" align=\"center\" valign=\"middle\" class=\"h1\"><strong>Quick Links</strong></td>\r\n");
      out.write("            <td width=\"124\" align=\"left\" valign=\"middle\"><a href=\"#\"><img src=\"images/pzay_primium.jpg\" width=\"115\" height=\"23\" /></a></td>\r\n");
      out.write("            <td width=\"85\" align=\"left\" valign=\"middle\"><a href=\"#\"><img src=\"images/top_up.jpg\" width=\"75\" height=\"23\" /></a></td>\r\n");
      out.write("            <td width=\"84\" align=\"left\" valign=\"middle\"><a href=\"#\"><img src=\"images/switch.jpg\" width=\"75\" height=\"23\" /></a></td>\r\n");
      out.write("            <td width=\"186\" align=\"left\" valign=\"middle\"><a href=\"3\"><img src=\"images/primium_paid_certificarte.jpg\" width=\"180\" height=\"23\" /></a></td>\r\n");
      out.write("          </tr>\r\n");
      out.write("        </table>\r\n");
      out.write("      </div>\r\n");
      out.write("      <div class=\"clr\"></div>\r\n");
      out.write("      <div class=\"policy_name_box\">\r\n");
      out.write("        <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("          <tr>\r\n");
      out.write("            <td align=\"left\" valign=\"top\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" >\r\n");
      out.write("                <tr>\r\n");
      out.write("                  <td width=\"15%\" height=\"35\" align=\"left\" valign=\"top\" >&nbsp;</td>\r\n");
      out.write("                  <td width=\"85%\" align=\"left\" valign=\"top\" style=\"border-top:1px solid #ecece4; border-right:1px solid #ecece4; \"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("                      <tr>\r\n");
      out.write("                        <td width=\"21%\" height=\"35\" align=\"center\" valign=\"middle\" bgcolor=\"#ddddcf\" class=\"h2\" style=\"border-top:1px solid #ecece4; border-right:1px solid #fff;\"><strong>Policy Name</strong></td>\r\n");
      out.write("                        <td width=\"9%\" height=\"35\" align=\"center\" valign=\"middle\" bgcolor=\"#ddddcf\" class=\"h2\" style=\"border-top:1px solid #ecece4; border-right:1px solid #fff;\"><strong>Policy<br />\r\n");
      out.write("                          Number</strong></td>\r\n");
      out.write("                        <td width=\"13%\" height=\"35\" align=\"center\" valign=\"middle\" bgcolor=\"#ddddcf\" style=\"border-top:1px solid #ecece4; border-right:1px solid #fff;\"><strong class=\"h2\">Status*</strong></td>\r\n");
      out.write("                        <td width=\"10%\" height=\"35\" align=\"center\" valign=\"middle\" bgcolor=\"#ddddcf\" style=\"border-top:1px solid #ecece4; border-right:1px solid #fff;\"><strong class=\"h2\">Premium<br />\r\n");
      out.write("                          Due Date</strong></td>\r\n");
      out.write("                        <td width=\"14%\" height=\"35\" align=\"center\" valign=\"middle\" bgcolor=\"#ddddcf\" style=\"border-top:1px solid #ecece4; border-right:1px solid #fff;\"><strong class=\"h2\"></strong>\r\n");
      out.write("                          <table width=\"70%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\r\n");
      out.write("                            <tr>\r\n");
      out.write("                              <td colspan=\"2\"><span class=\"h2\"><strong>Total Premium</strong></span><br /></td>\r\n");
      out.write("                            </tr>\r\n");
      out.write("                            <tr>\r\n");
      out.write("                              <td width=\"63%\"><strong class=\"h2\">Installment**</strong></td>\r\n");
      out.write("                              <td width=\"37%\"><img src=\"images/ru.jpg\" width=\"17\" height=\"13\" /></td>\r\n");
      out.write("                            </tr>\r\n");
      out.write("                          </table></td>\r\n");
      out.write("                        <td width=\"15%\" height=\"35\" align=\"center\" valign=\"middle\" bgcolor=\"#ddddcf\" style=\"border-top:1px solid #ecece4; border-right:1px solid #fff;\"><strong class=\"h2\">Fund Details</strong></td>\r\n");
      out.write("                        <td width=\"18%\" height=\"35\" align=\"center\" valign=\"middle\" bgcolor=\"#ddddcf\" style=\"border-top:1px solid #ecece4; border-right:1px solid #ecece4;\"><table width=\"75%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("                            <tr>\r\n");
      out.write("                              <td width=\"82%\"><strong class=\"h2\">Sum Assured</strong></td>\r\n");
      out.write("                              <td width=\"18%\"><img src=\"images/ru.jpg\" width=\"17\" height=\"13\" /></td>\r\n");
      out.write("                            </tr>\r\n");
      out.write("                          </table></td>\r\n");
      out.write("                      </tr>\r\n");
      out.write("                      <tr> </tr>\r\n");
      out.write("                    </table></td>\r\n");
      out.write("                </tr>\r\n");
      out.write("                <tr>\r\n");
      out.write("                  <td colspan=\"2\" align=\"left\" valign=\"top\" class=\"h2\" ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("                      <tr>\r\n");
      out.write("                        <td width=\"15%\" height=\"45\" align=\"left\" valign=\"top\" bgcolor=\"#ddddcf\" style=\" border-left:1px solid #ecece4; border-bottom:1px solid #ecece4;  padding-left:10px; padding-top:6px;  border-right:1px solid #ecece4; \" ><strong class=\"h8\">Life &amp;\r\n");
      out.write("                          Pension<br />\r\n");
      out.write("                          Policies</strong></td>\r\n");
      out.write("                        <td width=\"85%\" align=\"left\" valign=\"top\" style=\" border-right:1px solid #ecece4; border-bottom:1px solid #ecece4;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("                            <tr>\r\n");
      out.write("                              <td width=\"21%\" height=\"45\" align=\"center\" valign=\"middle\" class=\"h10\" style=\" border-right:1px solid #ecece4;\">ICICI Pru Guaranteed savings insurance Plan</td>\r\n");
      out.write("                              <td width=\"9%\" height=\"45\" align=\"center\" valign=\"middle\" style=\" border-right:1px solid #ecece4;\">00219063<br /></td>\r\n");
      out.write("                              <td width=\"13%\" height=\"45\" align=\"center\" valign=\"middle\" style=\" border-right:1px solid #ecece4;\"><a href=\"#\" class=\"h10\">Inforce Premium<br />\r\n");
      out.write("                                Holiday</a><br /></td>\r\n");
      out.write("                              <td width=\"10%\" height=\"45\" align=\"center\" valign=\"middle\" style=\" border-right:1px solid #ecece4;\">Not<br />\r\n");
      out.write("                                applicable<br /></td>\r\n");
      out.write("                              <td width=\"14%\" height=\"45\" align=\"center\" valign=\"middle\" style=\" border-right:1px solid #ecece4;\">0<br /></td>\r\n");
      out.write("                              <td width=\"15%\" height=\"45\" align=\"center\" valign=\"middle\" style=\" border-right:1px solid #ecece4;\"><span class=\"h10\">Fund Value</span><br /></td>\r\n");
      out.write("                              <td width=\"18%\" height=\"45\" align=\"center\" valign=\"middle\" style=\" \">5,00,000<br /></td>\r\n");
      out.write("                            </tr>\r\n");
      out.write("                          </table></td>\r\n");
      out.write("                      </tr>\r\n");
      out.write("                    </table></td>\r\n");
      out.write("                </tr>\r\n");
      out.write("              </table></td>\r\n");
      out.write("          </tr>\r\n");
      out.write("          <tr>\r\n");
      out.write("            <td height=\"2\" align=\"left\" valign=\"top\"></td>\r\n");
      out.write("          </tr>\r\n");
      out.write("          <tr>\r\n");
      out.write("            <td align=\"left\" valign=\"top\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"border:1px solid #ecece4;\" >\r\n");
      out.write("                <tr>\r\n");
      out.write("                  <td width=\"15%\" height=\"45\" align=\"center\" valign=\"middle\" bgcolor=\"#ddddcf\"  ><strong class=\"h8\">Health\r\n");
      out.write("                    policies</strong></td>\r\n");
      out.write("                  <td width=\"85%\" align=\"left\" valign=\"top\" ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("                      <tr>\r\n");
      out.write("                        <td width=\"21%\" height=\"45\" align=\"center\" valign=\"middle\" style=\" border-right:1px solid #ecece4;\"><strong>-</strong></td>\r\n");
      out.write("                        <td width=\"9%\" height=\"45\" align=\"center\" valign=\"middle\" style=\" border-right:1px solid #ecece4;\" ><strong>-</strong></td>\r\n");
      out.write("                        <td width=\"13%\" height=\"45\" align=\"center\" valign=\"middle\" style=\" border-right:1px solid #ecece4;\"><strong>-</strong></td>\r\n");
      out.write("                        <td width=\"10%\" height=\"45\" align=\"center\" valign=\"middle\" style=\" border-right:1px solid #ecece4;\" ><strong>-</strong></td>\r\n");
      out.write("                        <td width=\"14%\" height=\"45\" align=\"center\" valign=\"middle\" style=\" border-right:1px solid #ecece4;\" >-</td>\r\n");
      out.write("                        <td width=\"15%\" height=\"45\" align=\"center\" valign=\"middle\" style=\" border-right:1px solid #ecece4;\" ><strong>-</strong></td>\r\n");
      out.write("                        <td width=\"18%\" height=\"45\" align=\"center\" valign=\"middle\"  ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("                            <tr>\r\n");
      out.write("                              <td align=\"center\" valign=\"top\" style=\"font-size:11px;\">You have not planned yet</td>\r\n");
      out.write("                            </tr>\r\n");
      out.write("                            <tr>\r\n");
      out.write("                              <td align=\"center\" valign=\"middle\"><a href=\"#\"><img src=\"images/get_stander.jpg\" width=\"99\" height=\"25\" /></a></td>\r\n");
      out.write("                            </tr>\r\n");
      out.write("                          </table></td>\r\n");
      out.write("                      </tr>\r\n");
      out.write("                    </table></td>\r\n");
      out.write("                </tr>\r\n");
      out.write("              </table></td>\r\n");
      out.write("          </tr>\r\n");
      out.write("        </table>\r\n");
      out.write("      </div>\r\n");
      out.write("      <div class=\"clr\"></div>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"clr\"></div>\r\n");
      out.write("    <div class=\"policy_summary_bottom\">\r\n");
      out.write("      <table width=\"850\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td width=\"438\" height=\"37\" align=\"left\" valign=\"middle\" class=\"h1\"><strong>Canât view all your policies? <span class=\"h4\"><a href=\"#\" class=\"h4\">Click here</a></span> to submit a request</strong></td>\r\n");
      out.write("          <td width=\"208\" height=\"37\" align=\"center\" valign=\"middle\"><strong class=\"h11\">Total life cover with us</strong></td>\r\n");
      out.write("          <td width=\"168\" height=\"37\" align=\"center\" valign=\"middle\" style=\"background-image:url(images/total_bg.jpg); background-repeat:no-repeat; \"><strong class=\"h9\"><img src=\"images/ru3.gif\" width=\"11\" height=\"16\" /> 18,00,000</strong></td>\r\n");
      out.write("          <td width=\"119\" height=\"37\" align=\"right\" valign=\"middle\"><em class=\"h10\">Is this enough?</em></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("      </table>\r\n");
      out.write("    </div>\r\n");
      out.write("  </div>\r\n");
      out.write("  <ul id=\"toggle-view\">\r\n");
      out.write("    <a href=\"#a\" style=\"text-decoration:none;\">\r\n");
      out.write("    <li><b>*Policy Details&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;</b><span>+</span>\r\n");
      out.write("      <div class=\"show_div\">\r\n");
      out.write("        <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("          <tr>\r\n");
      out.write("            <td width=\"1%\" height=\"20\" align=\"left\" valign=\"middle\"><img src=\"images/arrow1.png\" width=\"4\" height=\"7\" /></td>\r\n");
      out.write("            <td width=\"99%\" height=\"20\" align=\"left\" valign=\"middle\">** Total Premium Installment (Rs) is       Premium amount + Education cess + Service tax.</td>\r\n");
      out.write("          </tr>\r\n");
      out.write("          <tr>\r\n");
      out.write("            <td height=\"20\" align=\"left\" valign=\"middle\"><img src=\"images/arrow1.png\" width=\"4\" height=\"7\" /></td>\r\n");
      out.write("            <td height=\"20\" align=\"left\" valign=\"middle\">* InForce - Policy is active and life       cover is applicable.</td>\r\n");
      out.write("          </tr>\r\n");
      out.write("          <tr>\r\n");
      out.write("            <td height=\"20\" align=\"left\" valign=\"middle\"><img src=\"images/arrow1.png\" width=\"4\" height=\"7\" /></td>\r\n");
      out.write("            <td height=\"20\" align=\"left\" valign=\"middle\">Vested policy - Premium payment term       is over and vesting age has been postponed.</td>\r\n");
      out.write("          </tr>\r\n");
      out.write("          <tr>\r\n");
      out.write("            <td height=\"20\" align=\"left\" valign=\"middle\"><img src=\"images/arrow1.png\" width=\"4\" height=\"7\" /></td>\r\n");
      out.write("            <td height=\"20\" align=\"left\" valign=\"middle\">Lapsed - Life cover is not applicable       due to non payment of premium installments.</td>\r\n");
      out.write("          </tr>\r\n");
      out.write("          <tr>\r\n");
      out.write("            <td height=\"20\" align=\"left\" valign=\"middle\"><img src=\"images/arrow1.png\" width=\"4\" height=\"7\" /></td>\r\n");
      out.write("            <td height=\"20\" align=\"left\" valign=\"middle\">Paid  Up - Premium payments have not been made for the policy after policy has  acquired a surrender value.</td>\r\n");
      out.write("          </tr>\r\n");
      out.write("        </table>\r\n");
      out.write("      </div>\r\n");
      out.write("    </li>\r\n");
      out.write("    </a>\r\n");
      out.write("    <div class=\"clr\"></div>\r\n");
      out.write("  </ul>\r\n");
      out.write("  <div class=\"clr\"></div>\r\n");
      out.write("  <br />\r\n");
      out.write("  <span class=\"h11\" style=\"margin-left:20px;\"><strong>Bonus Details</strong></span><br />\r\n");
      out.write("  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Policy no <span class=\"h4\">01234567</span> has earned Bonus of<samp class=\"h4\"> <img src=\"images/ru5.jpg\" width=\"6\" height=\"9\" /> 50 </samp>on <samp class=\"h4\">20-06-2011. </samp>Please check unit statement for details. <br />\r\n");
      out.write("  <br />\r\n");
      out.write("  <span class=\"h11\" style=\"margin-left:20px;\"><strong>Maturity Details</strong></span><br />\r\n");
      out.write("  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Policy no <span class=\"h4\">01234567</span> has earned Bonus of<samp class=\"h4\"> <img src=\"images/ru5.jpg\" width=\"6\" height=\"9\" /> 50 </samp>on <samp class=\"h4\">20-06-2011. </samp>Please check unit statement for details. <br />\r\n");
      out.write("  <br />\r\n");
      out.write("  <br />\r\n");
      out.write("  <div class=\"clr\"></div>\r\n");
      out.write("</div>\r\n");
      out.write("<div class=\"clr\"></div>\r\n");
      out.write("<div class=\"footer\">\r\n");
      out.write("  <div class=\"footer_text\">Copyright &copy 2000-2001 ICICI Prudential Life Insurance Company LTD<br />\r\n");
      out.write("    Best Viewed In 1024x768 resolution</div>\r\n");
      out.write("</div>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\tvar menu=new menu.dd(\"menu\");\r\n");
      out.write("\tmenu.init(\"menu\",\"menuhover\");\r\n");
      out.write("</script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
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
