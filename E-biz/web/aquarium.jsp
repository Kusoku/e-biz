<%-- 
    Document   : aquarium
    Created on : 2015-06-16, 10:21:27
    Author     : Kwydin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="Models.Tank"%>
<%@page import="Models.Heater"%>
<%@page import="Models.Filter"%>
<%@page import="Models.Aquarium"%>

<%@ include file="js/header.jspf" %>  

	<div id="site_content">		
	  
      <div class="slideshow">
	    <ul class="slideshow">
          <li class="show"><img width="900" height="250" src="images/home_1.jpg" alt="&quot;You can plan your aquarium here&quot;" /></li>
          <li><img width="900" height="250" src="images/home_2.jpg" alt="&quot;Go ahead and try it :)&quot;" /></li>
        </ul> 
	  </div>
	   
	  <div id="content">
        <div class="content_item">
            <%
            System.out.println("1");
            Object o = null;
            Vector<Models.Aquarium> aquariums = null;
            
            o = request.getAttribute("aquariums");
            aquariums = (Vector<Models.Aquarium>) o;
            
            if (o == null) {
                request.getRequestDispatcher("/list").forward(request, response); 
                return;
            }  
            System.out.println("2");
            %>
            
            <h1>List of <%= user.getUsername() %>'s Aquariums</h1>
            <table id="builder" class="tablesorter" style="float:left;">
		<thead>
			<tr>
                                <th>#</th>
                                <th>Created</th>
                                <th>Tank Cap</th>
				<th>Tank Type</th>
                                <th>Filter Cap</th>
                                <th>Filter Type</th>
                                <th>Heater Cap</th>
                                <th>Heater Type</th>
                        </tr>
                <tbody>
                    <%System.out.println("3"); if(aquariums!=null){
                            int i=0;System.out.println("4");
                            for (Models.Aquarium aquarium : aquariums) {%>
				<tr>
                                    <td>
                                        <%=(i+1)%><% i++; %>
                                    </td>
                                    <td>
					<%= aquarium.getDate() %>
                                    </td>
                                    <td>
					<%= aquarium.getTank().getCapacity() %>
                                    </td>
                                    <td>
                                        <%= aquarium.getTank().getType() %>
                                    </td>
                                    <td>
					<%= aquarium.getFilter().getCapacity() %>
                                    </td>
                                    <td>
                                        <%= aquarium.getFilter().getType() %>
                                    </td>
                                    <td>
					<%= aquarium.getHeater().getCapacity() %>
                                    </td>
                                    <td>
                                        <%= aquarium.getHeater().getType() %>
                                    </td>
                                    <td>
					<form action="./delete?id=<%=aquarium.getId()%>" method="post" id="build-form">
                                            <div class="button_small">
                                            <button type="submit">Delete</button>
                                            </div>
					</form>
                                    </td>
				</tr>
                    <%}System.out.println("5");%>
                    
                    
                    
                </tbody>        
            </table>
                  </div><!--close content_item-->
      </div><!--close content-->   
	</div><!--close site_content-->  	

<%@ include file="js/footer_build.jspf" %>  
                    
                    
                    
            
