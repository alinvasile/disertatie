

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show CampaignDetails</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">CampaignDetails List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New CampaignDetails</g:link></span>
        </div>
        <div class="body">
            <h1>Show CampaignDetails</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:campaignDetailsInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Campaign:</td>
                            
                            <td valign="top" class="value"><g:link controller="campaign" action="show" id="${campaignDetailsInstance?.campaign?.id}">${campaignDetailsInstance?.campaign?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Discount:</td>
                            
                            <td valign="top" class="value"><g:link controller="discount" action="show" id="${campaignDetailsInstance?.discount?.id}">${campaignDetailsInstance?.discount?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Tarrif:</td>
                            
                            <td valign="top" class="value"><g:link controller="tarriff" action="show" id="${campaignDetailsInstance?.tarrif?.id}">${campaignDetailsInstance?.tarrif?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${campaignDetailsInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
