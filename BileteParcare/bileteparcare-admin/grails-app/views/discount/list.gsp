

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Discount List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New Discount</g:link></span>
        </div>
        <div class="body">
            <h1>Discount List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="discountType" title="Discount Type" />
                        
                   	        <g:sortableColumn property="discountPercentage" title="Discount Percentage" />
                        
                   	        <g:sortableColumn property="discountCummulated" title="Discount Cummulated" />
                        
                   	        <g:sortableColumn property="endHour" title="End Hour" />
                        
                   	        <g:sortableColumn property="startHour" title="Start Hour" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${discountInstanceList}" status="i" var="discountInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${discountInstance.id}">${fieldValue(bean:discountInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:discountInstance, field:'discountType')}</td>
                        
                            <td>${fieldValue(bean:discountInstance, field:'discountPercentage')}</td>
                        
                            <td>${fieldValue(bean:discountInstance, field:'discountCummulated')}</td>
                        
                            <td>${fieldValue(bean:discountInstance, field:'endHour')}</td>
                        
                            <td>${fieldValue(bean:discountInstance, field:'startHour')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${discountInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
