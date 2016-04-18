

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Parking List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New Parking</g:link></span>
        </div>
        <div class="body">
            <h1>Parking List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="name" title="Name" />
                        
                   	        <g:sortableColumn property="address" title="Address" />
                        
                   	        <g:sortableColumn property="city" title="City" />
                        
                   	        <g:sortableColumn property="region" title="Region" />
                        
                   	        <g:sortableColumn property="country" title="Country" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${parkingInstanceList}" status="i" var="parkingInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${parkingInstance.id}">${fieldValue(bean:parkingInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:parkingInstance, field:'name')}</td>
                        
                            <td>${fieldValue(bean:parkingInstance, field:'address')}</td>
                        
                            <td>${fieldValue(bean:parkingInstance, field:'city')}</td>
                        
                            <td>${fieldValue(bean:parkingInstance, field:'region')}</td>
                        
                            <td>${fieldValue(bean:parkingInstance, field:'country')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${parkingInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
