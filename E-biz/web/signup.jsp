

<% String title = "Zarejestruj si?"; %>
<%@ include file="js/header.jspf" %>
<jsp:useBean id="bean" class="Models.User" scope="session" />

	<div id="site_content">		
	  
      <div class="slideshow">
	    <ul class="slideshow">
          <li class="show"><img width="900" height="250" src="images/home_1.jpg" alt="&quot;Go ahead, register&quot;" /></li>
          <li><img width="900" height="250" src="images/home_2.jpg" alt="&quot;I dare you :)&quot;" /></li>
        </ul> 
	  </div>
	   
	  <div id="content">
              
        <div class="content_item">
            <div class="results"><h1><%= Helper.show_results(request)%></h1></div>
            <h1>Register: </h1>
            <section>
            <form action="./signup" method="post">
                    <div class="field">
                        <table>
                            <tr>
                                <td><span style="float:left;">Username:</span></td>
                                <td><input id="user_name" name="name" size="30" type="text" /></td>
                            </tr>
                            <tr>
                                <td><span style="float:left;">Email:</span></td>
                                <td><input id="user_email" name="email" size="30" type="text" /></td>
                            </tr>
                            <tr>
                                <td><span style="float:left;">Password:</span></td>
                                <td><input id="password" name="password" size="30" type="password" /></td>
                            </tr>
                            <tr>
                                <td><span style="float:left;">Repeat Password:</span></td>
                                <td><input id="user_password_confirmation" name="password_confirmation" size="30" type="password" /></td>
                            </tr>
                        </table>
                    </div>
                    <div class="button_small">
                            <button id="user_submit" type="submit" name="commit">Register</button>
                    </div>
            </form>
	</section>
            <br> <br> <br> <br> <br> <br> <a href="/E-biz/" class="back"><< To go back</a>
            
            </div><!--close content_item-->
      </div><!--close content-->   
	</div><!--close site_content-->  	

<%@ include file="js/footer.jspf" %>