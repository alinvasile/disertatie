

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create CampaignDetails</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">CampaignDetails List</g:link></span>
        </div>
        <div class="body">
            <h1>Create CampaignDetails</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${campaignDetailsInstance}">
            <div class="errors">
                <g:renderErrors bean="${campaignDetailsInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="campaign">Campaign:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:campaignDetailsInstance,field:'campaign','errors')}">
                                    <g:select optionKey="id" from="${Campaign.list()}" name="campaign.id" value="${campaignDetailsInstance?.campaign?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="discount">Discount:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:campaignDetailsInstance,field:'discount','errors')}">
                                    <g:select optionKey="id" from="${Discount.list()}" name="discount.id" value="${campaignDetailsInstance?.discount?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="tarrif">Tarrif:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:campaignDetailsInstance,field:'tarrif','errors')}">
                                    <g:select optionKey="id" from="${Tarriff.list()}" name="tarrif.id" value="${campaignDetailsInstance?.tarrif?.id}" ></g:select>
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
