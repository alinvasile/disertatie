

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>CampaignDetails List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New CampaignDetails</g:link></span>
        </div>
        <div class="body">
            <h1>CampaignDetails List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <th>Campaign</th>
                   	    
                   	        <th>Discount</th>
                   	    
                   	        <th>Tarrif</th>
                   	    
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${campaignDetailsInstanceList}" status="i" var="campaignDetailsInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${campaignDetailsInstance.id}">${fieldValue(bean:campaignDetailsInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:campaignDetailsInstance, field:'campaign')}</td>
                        
                            <td>${fieldValue(bean:campaignDetailsInstance, field:'discount')}</td>
                        
                            <td>${fieldValue(bean:campaignDetailsInstance, field:'tarrif')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${campaignDetailsInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
