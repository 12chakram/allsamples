<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%-- 
<%@ include file="/ipru/checkhttps_session.jsp"%>
<%@ include file="/ipru/security_constraint.jsp"%>
<%@ include file="/ipru/commonsession.jsp"%>  
<%@ include file="html/index.jsp" %>--%>
<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Expires" content="Tue, 01 Jan 1965 12:12:12 GMT">
<meta http-equiv="Pragma" content="no-cache">

<title>ICICI PRUDENTIAL</title>

<link href="html/css/styles1.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" media="screen" href="superfish.css" />
<link rel="stylesheet" href="html/css/stylesheet.css" type="text/css" />
<link rel="stylesheet" href="html/css/style.css" type="text/css" />
<script src="hoverinit.js"></script>
<script src="superfish.js"></script>
<script type="text/javascript" src="html/js/jquery.min.js"></script>
<script type="text/javascript" src="html/js/fade-plugin.js"></script>
<script type="text/javascript" src="html/js/script.js"></script>

<script type="text/javascript">
      var fader;
      (function($) {
        function init() {
          fader = $("#transitionEffect").fadeTransition({pauseTime: 4000,
                                                 transitionTime: 1000,
                                                 ignore: "#introslide",
                                                 delayStart: 100,
                                                 manualNavigation: false,
                                                 pauseOnMouseOver: true,
                                                 createNavButtons: true});
        }
        
        $(document).ready(init);
      })(jQuery);
</script>
<script type="text/javascript" src="html/css/iepngfix/iepngfix_tilebg.js"></script>

<style type="text/css">
* { behavior: url("html/css/iepngfix/iepngfix.htc") }
</style>

<script type="text/javascript" src="html/js/jquery.js"></script>

<script type="text/javascript">
$(document).ready(function () {
	
	$('#toggle-view li').click(function () {

		var text = $(this).children('.show_div');

		if (text.is(':hidden')) {
			text.slideDown('200');
			$(this).children('span').html('-');		
		} else {
			text.slideUp('200');
			$(this).children('span').html('+');		
		}
		
	});

});
</script>

<script LANGUAGE="JavaScript1.2">
function doLogout(){
	if(confirm(" This would close the window for security reasons. Do you want to continue?") ){
		window.location = "/ipru/logout.jsp";
	}
	else{
	//do nothing
	}
}

function okcancel()
{
	if(confirm('Dear Customer,Surrender of your policy attracts surrender charges. For more details refer to the policy document. Click Ok to download form, or Cancel to stay on the same page'))
	{
	 javascript:window.open('/public/Forms/Payout_Request.pdf');
	}	
}

</script>

</head>
<body>
<form name="lintel" method="post" action="">

<div id="main_wapper">
  <div class="top_logo"><a href="#"><img src="html/images/icici-logo.gif" alt="ICICI PRUDENTIAL LIFE INSURANCE" width="270" height="51" /></a></div>
  <div class="top_btn_box"><a href="/customer/mypolicy.jsp"  class="top_btn"><img src="html/images/home.gif" /></a>			

		<a href="#" class="top_btn"><img src="html/images/changes_roles.gif" /></a>

	<a href="javascript: doLogout()"  class="top_btn" style="margin-right:0px;"><img src="html/images/logout.gif" /></a> </div>
  <div class="clr"></div>
  <div class="menu_box" >
    <ul class="sf-menu" id="menu" >
      <li><a href="#" class="menulink">My Profile <img src="html/images/down.gif" /></a>
        <ul>
          <li><a href="/ipru/changeUserId.jsp">Change User ID</a></li>
          <li> <a href="/ipru/chgpass.jsp" >Change Password</a> </li>
          <li> <a href="/OnlineUpdate.do?hidSourceName=DetailView" >View / Update Profile</a></li>
        </ul>
      </li>
      <li><img src="html/images/linkdivider.jpg" /></li>
      <li> <a href="#" class="menulink">e-Transactions<img src="html/images/down.gif" /></a>
        <ul>
          <li><a href="/opservlet.do?hidSourceName=LHSMenuView&lhsMenuOption=payPremium&type=pay">Pay Premium</a></li>
          <li><a href="/customer/topup/transactPolicy.jsp?reqType=4">Top Up</a></li>
          <li><a href="/customer/TransactPolicy.jsp?reqType=1">Switch Online</a></li>
          <li><a href="/customer/TransactPolicy.jsp?reqType=2">Premium Redirection</a></li>
          <li><a href="/customer/TransactPolicy.jsp?reqType=3">Change in Portfolio Strategy</a></li>
          <li> <a href="#" >e- Transaction  History</a>
            <div class="sumaroow"><img src="html/images/arrow.gif" /></div>
            <ul>
              <li class="topline"><a href="/opservlet.do?hidSourceName=LHSMenuView&lhsMenuOption=paymentHistory">Payment</a></li>
              <li><a href="/opservlet.do?hidSourceName=OnlineXRTHistoryView&lhsMenuOption=onlinepdr">PDR</a></li>
              <li><a href="/ipru.do?reqType=4&cId=TopupPageView&actionType=policyhistory">Top Up</a></li>
              <li><a href="#">Switch</a></li>
              <li><a href="#">Premium Redirection</a></li>
            </ul>
          </li>
        </ul>
      </li>
      <li><img src="html/images/linkdivider.jpg" /></li>
      <li> <a href="#" class="menulink">e-Statements <img src="html/images/down.gif" /></a>
        <ul>
          <li><a href="/customer/lapsedLetterPage.jsp">Lapsed Letter</a></li>
          <li> <a href="/customer/notices.jsp" >Notice</a> </li>
          <li ><a href="/customer/portfolioInfo.jsp">Portfolio Statement</a></li>
          <li><a href="/IndOnlineCRPRControlerServlet.do?actionCmd=view">Premium Paid Certificate</a></li>
          <li><a href="/customer/receipts.jsp">Receipt</a></li>
          <li><a href="/customer/unitStmtInfo.jsp?type=cust">Unit Statment</a></li>
          <li><a href="/WelcomeKitServlet.do">Welcome Kit(including e-Policy Certificate)</a></li>
        </ul>
      </li>
      <li><img src="html/images/linkdivider.jpg" /></li>
      <li><a href="#" class="menulink">Information Center <img src="html/images/down.gif" /></a>
        <ul>
          <li><a href="#"  >Policy Details</a>
            <div class="sumaroow"><img src="html/images/arrow.gif" /></div>
            <ul>
              <li ><a href="/customer/paysum.jsp">Payment Summary</a></li>
              <li><a href="/customer/customerpreforeclosedpolicydetail.jsp">Preforeclosure Alert</a></li>
              <li><a href="/customer/premiumCalendar.jsp">Premium Calendar</a></li>
            </ul>
          </li>
          <li><a href="#" >Download Forms</a>
            <div class="sumaroow"><img src="html/images/arrow.gif" /></div>
            <ul>
              <li ><a href="/ipru/appform.jsp">Application Form</a></li>
              <li><a href="/ecsDD/ecsDDSecureHome.jsp">ECS/Direct Debit Online Mandate</a></li>
              <li><a href="/ipru/formattrigger/securePolicySelection.jsp">Service Request Form</a></li>
              <li><a href="javascript:okcancel()">Surrender/Partial Withdrawal Form</a></li>
            </ul>
          </li>
          <li><a href="/public/Others/Premium-Payment.htm" target="_blank">Payment Methods</a></li>
          <li><a href="/public/plans.htm" target="_blank">Products</a></li>
          <li><a href="/iprucampaign/iprucampaign.jsp?type=website" target="_blank">Send Me An Advisor</a></li>
          <li> <a href="#" >Tools and Calculators</a>
            <div class="sumaroow"><img src="html/images/arrow.gif" /></div>
            <ul>
              <li ><a href="/ipru/howmuchinsdoineed.jsp">Insurance Calculator</a></li>
              <li><a href="/ipru/personal.jsp">Retirement Calculator</a></li>
            </ul>
          </li>
        </ul>
      </li>
      <li><a href="/ipru/claims.jsp" class="menulink">Claims </a> </li>
      <li><img src="html/images/linkdivider.jpg" /></li>
      <li><a href="#" class="menulink">Contact Us <img src="html/images/down.gif" /></a>
        <ul>
          <li><a href="/public/Others/Call_Us.htm" target="_blank">Contact Touch Points</a></li>
          <li> <a href="/customer/webform.jsp" >Email Us</a> </li>
        </ul>
      </li>
    </ul>
    </li>
    </ul>
    <div class="clr"></div>
  </div>
  
<!-- x
<script type="text/javascript">
	var menu=new menu.dd("menu");
	menu.init("menu","menuhover");
</script> -->
<script> 
 
    $(document).ready(function(){ 
        $("ul.sf-menu").superfish({ 
        	 animation:   {opacity:'fade-in',height:'slide-down'},   // slide-down effect without fade-in 
            delay:     2000,               // 1.2 second delay on mouseout 
            speed:    'slow',
        }); 
    }); 
 
</script>
</form>
</body>
</html>
