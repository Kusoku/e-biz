<% String title = "Zaloguj si?"; %>
<%@ include file="js/header.jspf" %>

	<div id="site_content">		
	  
      <div class="slideshow">
	    <ul class="slideshow">
          <li class="show"><img width="900" height="250" src="images/home_1.jpg" alt="&quot;Go ahead, log in&quot;" /></li>
          <li><img width="900" height="250" src="images/home_2.jpg" alt="&quot;I dare you :)&quot;" /></li>
        </ul> 
	  </div>
	   
	  <div id="content">
              
        <div class="content_item">
            <div class="results"><h1><%= Helper.show_results(request)%></h1></div>
            <h1>Log in: </h1>
            <section>
            <form action="./signin" method="post">
                    <div class="field">
                        <table>
                            <tr>
                                <td><span style="float:left;">Email:</span></td>
                                <td><input id="email" name="session_email" size="30" type="text" /></td>
                            </tr>
                            <tr>
                                <td><span style="float:left;">Password:</span></td>
                                <td><input id="password" name="session_password" size="30" type="password" /></td>
                            </tr>
                        </table>
                    </div>
                    <div class="button_small">
                            <button type="submit">Log in</button>
                    </div>
            </form>
	</section>
            <br> <br> <br> <br> <br> <br>
	<section>
            <p>New user? <a href="./signup">Register!</a></p>
                
	</section>
        <a href="/E-biz/" class="back"><< To go back</a>
            
            </div><!--close content_item-->
      </div><!--close content-->   
	</div><!--close site_content-->  	

<%@ include file="js/footer.jspf" %>
