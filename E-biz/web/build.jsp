<%-- 
    Document   : build
    Created on : 2015-04-03, 12:40:41
    Author     : Konrad
--%>

<%@page import="java.util.Vector"%>
<%@page import="Models.Tank"%>
<%@page import="Models.Heater"%>
<%@page import="Models.Filter"%>
<%@page import="Common.DAO"%>
<%@page import="Common.Helper"%>
<% Models.Aquarium aquarium = (Models.Aquarium) request.getSession().getAttribute("aquarium");%> 
<% String table = request.getSession().getAttribute("table").toString();%>

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
            Object o = null;
            Vector<Models.Filter> filters = null;
            Vector<Models.Heater> heaters = null;
            Vector<Models.Tank> tanks = null;
              
            o = request.getAttribute("heaters");
            heaters = (Vector<Models.Heater>) o;
             
            o = request.getAttribute("filters");
            filters = (Vector<Models.Filter>) o;
 
            o = request.getAttribute("tanks");
            tanks = (Vector<Models.Tank>) o;

            if (o == null) {
                request.getRequestDispatcher("/post_build").forward(request, response); 
                return;
            }          
            System.out.println(request.getSession().getAttribute("table").toString());
            %>

            
		<% if(table=="tank"){ %>
			<h1>Tank</h1>
		<%}%>
		<% if(table=="filter"){ %>
			<h1>Filter</h1>
		<%}%>
		<% if(table=="heater"){ %>
			<h1>Heater</h1>
		<%}%>
		  <table id="builder" class="tablesorter" style="float:left;">
				<thead>
					<tr>
					<% if(table=="tank"){ %>
						<th>#</th>
						<th>type</th>
						<th>capacity</th>
					<%}%>
					<% if(table=="filter"){ %>
						<th>#</th>
						<th>type</th>
						<th>performance</th>
					<%}%>
					<% if(table=="heater"){ %>
						<th>#</th>
						<th>type</th>
						<th>performance</th>
					<%}%>
					</tr>
				</thead>
				<tbody>
					<% if(tanks!=null || filters!=null || heaters!=null){
					
					if(table=="tank"){ 
                                            int i=0;
                                            for (Models.Tank tank : tanks) {%>
						<tr>
							<td>
								<%=(i+1)%><% i++; %>
							</td>
							<td>
								<%= tank.getType() %>
							</td>
							<td>
								<%= tank.getCapacity() %>
							</td>
							<td>
								<form action="./choose?id=<%=tank.getId()%>" method="post" id="build-form">
									<div class="button_small">
									<button type="submit">Choose</button>
									</div>
								</form>
							</td>
						</tr>
					<%}}
					
					if(table=="filter"){ 
                                            int i=0;
                                            for (Models.Filter filter : filters) {%>
						<tr>
                                                    <td>
							<%=(i+1)%><% i++; %>
                                                    </td>
                                                    <td>
							<%= filter.getType() %>
                                                    </td>
                                                    <td>
                                                    	<%= filter.getCapacity() %>
                                                    </td>
                                                    <td>
                                                        <form action="./choose?id=<%=filter.getId()%>" method="post" id="build-form">
                                                            <div class="button_small">
                                                                <button type="submit">Choose</button>
                                                            </div>
							</form>
                                                    </td>
                                                </tr>
					<%}}
					
					if(table=="heater"){ 
                                            int i=0;
                                            for (Models.Heater heater : heaters) {%>
						<tr>
                                                    <td>
							<%=(i+1)%><% i++; %>
                                                    </td>
						
                                                    <td>
                                                    	<%= heater.getType() %>
                                                    </td>
                                                    <td>
							<%= heater.getCapacity() %>
                                                    </td>
                                                    <td>
							<form action="./choose?id=<%=heater.getId()%>" method="post" id="build-form">
                                                            <div class="button_small">
                                                       		<button type="submit">Choose</button>
                                                            </div>
							</form>
                                                    </td>
					</tr>
					<%}}
					
					}else{ %>
					<tr>
						<td colspan="5">No Data</td>
					</tr>
					<% } %>
				</tbody>
			</table>
			

			<table id="builded" class="tablesorter" style="float:right;">
				<thead>
					<tr>
						<th>Aquarium Cap</th>
						<th>Tank Type</th>
						<th>Filter Type</th>
						<th>Heater Type</th>
					</tr>
				</thead>
				<tbody>
				<%	if(aquarium!=null){ %>
					<tr>
						<td id="chosen"><%=aquarium.getTank().getCapacity()%></td>
						<td id="chosen"><%=aquarium.getTank().getType()%></td>
						<td id="chosen"><%if(aquarium.getFilter()!=null){%><%=aquarium.getFilter().getType()%><%}%></td>
						<td id="chosen"><%if(aquarium.getHeater()!=null){%><%=aquarium.getHeater().getType()%><%}%></td>
					</tr>
				
				<% }else{ %>	
					<tr>
						<td id="chosen" colspan="5">No Data</td>
					</tr>
				<% } %>
				</tbody>
			</table>
			<br><br><br><br><br><br><br><br><br>
			<div class="button_small" style="float:right"><a href="./reset">Reset</a></div>
		</div><!--close content_item-->
      </div><!--close content-->   
	</div><!--close site_content-->  	

<%@ include file="js/footer_build.jspf" %>
