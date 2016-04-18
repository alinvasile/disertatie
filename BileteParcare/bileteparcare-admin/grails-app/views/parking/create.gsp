

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create Parking</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Parking List</g:link></span>
        </div>
        <div class="body">
            <h1>Create Parking</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${parkingInstance}">
            <div class="errors">
                <g:renderErrors bean="${parkingInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name">Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:parkingInstance,field:'name','errors')}">
                                    <input type="text" maxlength="200" id="name" name="name" value="${fieldValue(bean:parkingInstance,field:'name')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="address">Address:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:parkingInstance,field:'address','errors')}">
                                    <textarea rows="5" cols="40" name="address">${fieldValue(bean:parkingInstance, field:'address')}</textarea>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="city">City:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:parkingInstance,field:'city','errors')}">
                                    <input type="text" maxlength="200" id="city" name="city" value="${fieldValue(bean:parkingInstance,field:'city')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="region">Region:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:parkingInstance,field:'region','errors')}">
                                    <input type="text" maxlength="200" id="region" name="region" value="${fieldValue(bean:parkingInstance,field:'region')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="country">Country:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:parkingInstance,field:'country','errors')}">
                                    <input type="text" maxlength="200" id="country" name="country" value="${fieldValue(bean:parkingInstance,field:'country')}"/>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Create" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
