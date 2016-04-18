

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create Campaign</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Campaign List</g:link></span>
        </div>
        <div class="body">
            <h1>Create Campaign</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${campaignInstance}">
            <div class="errors">
                <g:renderErrors bean="${campaignInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="campaignName">Campaign Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:campaignInstance,field:'campaignName','errors')}">
                                    <textarea rows="5" cols="40" name="campaignName">${fieldValue(bean:campaignInstance, field:'campaignName')}</textarea>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="details">Details:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:campaignInstance,field:'details','errors')}">
                                    <textarea rows="5" cols="40" name="details">${fieldValue(bean:campaignInstance, field:'details')}</textarea>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="campaignFrom">Campaign From:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:campaignInstance,field:'campaignFrom','errors')}">
                                    <g:datePicker name="campaignFrom" value="${campaignInstance?.campaignFrom}" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="campaignTo">Campaign To:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:campaignInstance,field:'campaignTo','errors')}">
                                    <g:datePicker name="campaignTo" value="${campaignInstance?.campaignTo}" ></g:datePicker>
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
