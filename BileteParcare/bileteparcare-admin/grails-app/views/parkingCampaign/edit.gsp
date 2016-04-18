

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit ParkingCampaign</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">ParkingCampaign List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New ParkingCampaign</g:link></span>
        </div>
        <div class="body">
            <h1>Edit ParkingCampaign</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${parkingCampaignInstance}">
            <div class="errors">
                <g:renderErrors bean="${parkingCampaignInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${parkingCampaignInstance?.id}" />
                <input type="hidden" name="version" value="${parkingCampaignInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="campaign">Campaign:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:parkingCampaignInstance,field:'campaign','errors')}">
                                    <g:select optionKey="id" from="${Campaign.list()}" name="campaign.id" value="${parkingCampaignInstance?.campaign?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="parking">Parking:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:parkingCampaignInstance,field:'parking','errors')}">
                                    <g:select optionKey="id" from="${Parking.list()}" name="parking.id" value="${parkingCampaignInstance?.parking?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" value="Update" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
