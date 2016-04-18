

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>MeasureUnit List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New MeasureUnit</g:link></span>
        </div>
        <div class="body">
            <h1>MeasureUnit List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="measureUnitDesc" title="Measure Unit Desc" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${measureUnitInstanceList}" status="i" var="measureUnitInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${measureUnitInstance.id}">${fieldValue(bean:measureUnitInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:measureUnitInstance, field:'measureUnitDesc')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${measureUnitInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
