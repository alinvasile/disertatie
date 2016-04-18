

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>PriceBand List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New PriceBand</g:link></span>
        </div>
        <div class="body">
            <h1>PriceBand List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="currency" title="Currency" />
                        
                   	        <g:sortableColumn property="measureAmount" title="Measure Amount" />
                        
                   	        <th>Measure Unit</th>
                   	    
                   	        <g:sortableColumn property="price" title="Price" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${priceBandInstanceList}" status="i" var="priceBandInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${priceBandInstance.id}">${fieldValue(bean:priceBandInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:priceBandInstance, field:'currency')}</td>
                        
                            <td>${fieldValue(bean:priceBandInstance, field:'measureAmount')}</td>
                        
                            <td>${fieldValue(bean:priceBandInstance, field:'measureUnit')}</td>
                        
                            <td>${fieldValue(bean:priceBandInstance, field:'price')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${priceBandInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
