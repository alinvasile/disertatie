

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create Discount</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Discount List</g:link></span>
        </div>
        <div class="body">
            <h1>Create Discount</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${discountInstance}">
            <div class="errors">
                <g:renderErrors bean="${discountInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="discountType">Discount Type:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:discountInstance,field:'discountType','errors')}">
                                    <g:select id="discountType" name="discountType" from="${discountInstance.constraints.discountType.inList}" value="${discountInstance.discountType}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="discountPercentage">Discount Percentage:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:discountInstance,field:'discountPercentage','errors')}">
                                    <input type="text" id="discountPercentage" name="discountPercentage" value="${fieldValue(bean:discountInstance,field:'discountPercentage')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="discountCummulated">Discount Cummulated:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:discountInstance,field:'discountCummulated','errors')}">
                                    <g:select id="discountCummulated" name="discountCummulated" from="${discountInstance.constraints.discountCummulated.inList}" value="${discountInstance.discountCummulated}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="endHour">End Hour:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:discountInstance,field:'endHour','errors')}">
                                    <g:datePicker name="endHour" value="${discountInstance?.endHour}" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="startHour">Start Hour:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:discountInstance,field:'startHour','errors')}">
                                    <g:datePicker name="startHour" value="${discountInstance?.startHour}" ></g:datePicker>
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
