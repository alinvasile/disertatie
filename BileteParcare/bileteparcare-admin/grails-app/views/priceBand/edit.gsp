

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit PriceBand</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">PriceBand List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New PriceBand</g:link></span>
        </div>
        <div class="body">
            <h1>Edit PriceBand</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${priceBandInstance}">
            <div class="errors">
                <g:renderErrors bean="${priceBandInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${priceBandInstance?.id}" />
                <input type="hidden" name="version" value="${priceBandInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="currency">Currency:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:priceBandInstance,field:'currency','errors')}">
                                
                                    <input type="text" maxlength="3" id="currency" name="currency" value="${fieldValue(bean:priceBandInstance,field:'currency')}"/>
                                 <!--
                                 <g:currencySelect  name="currency" value="${fieldValue(bean:priceBandInstance,field:'currency')}" />
                                 -->
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="measureAmount">Measure Amount:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:priceBandInstance,field:'measureAmount','errors')}">
                                    <input type="text" id="measureAmount" name="measureAmount" value="${fieldValue(bean:priceBandInstance,field:'measureAmount')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="measureUnit">Measure Unit:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:priceBandInstance,field:'measureUnit','errors')}">
                                    <g:select optionKey="id" from="${MeasureUnit.list()}" name="measureUnit.id" value="${priceBandInstance?.measureUnit?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="price">Price:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:priceBandInstance,field:'price','errors')}">
                                    <input type="text" id="price" name="price" value="${fieldValue(bean:priceBandInstance,field:'price')}" />
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
