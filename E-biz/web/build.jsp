<%-- 
    Document   : build
    Created on : 2015-04-03, 12:40:41
    Author     : Konrad
--%>

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
		<% if(aquarium.table=="aquarium"){ %>
			<h1>Aquarium</h1>
		<%}%>
		<% if(aquarium.table=="filter"){ %>
			<h1>Filter</h1>
		<%}%>
		<% if(aquarium.table=="heater"){ %>
			<h1>Heater</h1>
		<%}%>
		
		  <table id="builder" class="tablesorter" style="float:left;">
				<thead>
					<tr>
					<% if(aquarium.table=="aquarium"){ %>
						<th>#</th>
						<th>type</th>
						<th>capacity</th>
					<%}%>
					<% if(aquarium.table=="filter"){ %>
						<th>#</th>
						<th>type</th>
						<th>performance</th>
					<%}%>
					<% if(aquarium.table=="heater"){ %>
						<th>#</th>
						<th>type</th>
						<th>performance</th>
					<%}%>
					</tr>
				</thead>
				<tbody>
					<% if(data.length){
					
					if(aquarium.table=="aquarium"){ 
                        
						for(var i = 0;i < data.length;i++) { %>
						<tr>
							<td>
								<%=(i+1)%>
							</td>
							<td>
								<%= data[i].type %>
							</td>
							<td>
								<%= data[i].capacity %>
							</td>
							<td>
								<form action="/build?table=<%=aquarium.table%>&aqCap=<%=data[i].capacity%>&aqType=<%=data[i].type%>&filType=<%=aquarium.filType%>&heatType=<%=aquarium.heatType%>" method="post" id="build-form">
									<div class="button_small">
									<button type="submit">Choose</button>
									</div>
								</form>
							</td>
						</tr>
					<%}}
					
					if(aquarium.table=="filter"){ 
                        
					for(var i = 0;i < data.length;i++) { %>
					<tr>
						<td>
							<%=(i+1)%>
						</td>
						<td>
							<%= data[i].type %>
						</td>
						<td>
							<%= data[i].capacity %>
						</td>
						<td>
							<form action="/build?table=<%=aquarium.table%>&aqCap=<%=aquarium.aqCap%>&aqType=<%=aquarium.aqType%>&filType=<%=data[i].type%>&heatType=<%=aquarium.heatType%>" method="post" id="build-form">
								<div class="button_small">
								<button type="submit">Choose</button>
								</div>
							</form>
						</td>
					</tr>
					<%}}
					
					if(aquarium.table=="heater"){ 
                        
					for(var i = 0;i < data.length;i++) { %>
					<tr>
						<td>
							<%=(i+1)%>
						</td>
						<td>
							<%= data[i].type %>
						</td>
						<td>
							<%= data[i].capacity %>
						</td>
						<td>
							<form action="/build?table=<%=aquarium.table%>&aqCap=<%=aquarium.aqCap%>&aqType=<%=aquarium.aqType%>&filType=<%=aquarium.filType%>&heatType=<%=data[i].type%>" method="post" id="build-form">
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
						<th>Aquarium Type</th>
						<th>Filter Type</th>
						<th>Heater Type</th>
					</tr>
				</thead>
				<tbody>
				<%	if(aquarium.aqCap!==""){ %>
					<tr>
						<td id="chosen"><%=aquarium.aqCap%></td>
						<td id="chosen"><%=aquarium.aqType%></td>
						<td id="chosen"><%=aquarium.filType%></td>
						<td id="chosen"><%=aquarium.heatType%></td>
					</tr>
				
				<% }else{ %>	
					<tr>
						<td id="chosen" colspan="5">No Data</td>
					</tr>
				<% } %>
				</tbody>
			</table>
			<br><br><br><br><br><br><br><br><br>
			<div class="button_small" style="float:right"><a href="/build">Reset</a></div>
		</div><!--close content_item-->
      </div><!--close content-->   
	</div><!--close site_content-->  	

<%@ include file="js/footer_build.jspf" %>
