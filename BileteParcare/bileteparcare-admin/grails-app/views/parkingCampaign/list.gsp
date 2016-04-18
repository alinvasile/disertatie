

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>ParkingCampaign List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New ParkingCampaign</g:link></span>
        </div>
        <div class="body">
            <h1>ParkingCampaign List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <th>Campaign</th>
                   	    
                   	        <th>Parking</th>
                   	    
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${parkingCampaignInstanceList}" status="i" var="parkingCampaignInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${parkingCampaignInstance.id}">${fieldValue(bean:parkingCampaignInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:parkingCampaignInstance, field:'campaign')}</td>
                        
                            <td>${fieldValue(bean:parkingCampaignInstance, field:'parking')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${parkingCampaignInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
