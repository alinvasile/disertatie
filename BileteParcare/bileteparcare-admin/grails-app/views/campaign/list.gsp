

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Campaign List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New Campaign</g:link></span>
        </div>
        <div class="body">
            <h1>Campaign List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="campaignName" title="Campaign Name" />
                        
                   	        <g:sortableColumn property="details" title="Details" />
                        
                   	        <g:sortableColumn property="campaignFrom" title="Campaign From" />
                        
                   	        <g:sortableColumn property="campaignTo" title="Campaign To" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${campaignInstanceList}" status="i" var="campaignInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${campaignInstance.id}">${fieldValue(bean:campaignInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:campaignInstance, field:'campaignName')}</td>
                        
                            <td>${fieldValue(bean:campaignInstance, field:'details')}</td>
                        
                            <td>${fieldValue(bean:campaignInstance, field:'campaignFrom')}</td>
                        
                            <td>${fieldValue(bean:campaignInstance, field:'campaignTo')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${campaignInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
