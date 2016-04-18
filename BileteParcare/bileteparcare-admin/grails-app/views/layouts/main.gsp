<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <title><g:layoutTitle default="Grails" /></title>
        <link rel="stylesheet" href="${createLinkTo(dir:'css',file:'main.css')}" />
        <link rel="shortcut icon" href="${createLinkTo(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <link rel="stylesheet" href="${createLinkTo(dir:'js',file:'extjs-2-2-1/resources/css/ext-all.css')}"></link>		
		<script type="text/javascript" src="${createLinkTo(dir:'js',file:'extjs-2-2-1/adapter/ext/ext-base.js')}"></script>
		<script type="text/javascript" src="${createLinkTo(dir:'js',file:'extjs-2-2-1/ext-all-debug.js')}"></script>
		<script type="text/javascript" charset="utf-8">
			Ext.BLANK_IMAGE_URL = "${createLinkTo(dir:'js',file:'extjs-2-2-1/resources/images/default/s.gif')}";
		</script>
		<script type="text/javascript" src="/bileteparcare-admin/js/gradualfader.js">
		
		/***********************************************
		* Gradual Element Fader- (c) Dynamic Drive DHTML code library (www.dynamicdrive.com)
		* Visit http://www.dynamicDrive.com for hundreds of DHTML scripts
		* This notice must stay intact for legal use
		***********************************************/
		
		</script>
		<g:javascript library="application" />
        <g:layoutHead />        			
    </head>
    <body>
	    <div id="west-campaign" align=center>
	      <ul>
	       <li class="controller">
	            <a href="/bileteparcare-admin/campaign/index" >
	            	<img src="/bileteparcare-admin/images/site/campanie.bmp" class="gradualfader"  /> <br/>
	       				Campanii
	       		</a>
	       		<hr/>
	       	</li>        
            <li class="controller">  
			    <a href="/bileteparcare-admin/campaignDetails/index">         
            		<img src="/bileteparcare-admin/images/site/campanie-detalii.gif" class="gradualfader"  /> <br/>
            		Detalii campanii
            	</a>
            	<hr/>
            </li>
            <li class="controller" >
            	<a href="/bileteparcare-admin/discount/index">
                	<img src="/bileteparcare-admin/images/site/reducere.jpg" class="gradualfader"  /> <br/>
            		Reduceri
            	</a>
            	<hr/>
            </li>
          </ul>
	    </div>
	    
	    <div id="west-tariff" align=center>
	      <ul>
	      	 <li class="controller">
	         	<a href="/bileteparcare-admin/tarriff/index">
	         	<img src="/bileteparcare-admin/images/site/tarife.gif" class="gradualfader"  /> <br/>
	         	Tarife
	         	</a>
	         	<hr/>
	         </li>        
             <li class="controller">
             	<a href="/bileteparcare-admin/measureUnit/index">
             	<img src="/bileteparcare-admin/images/site/unitate-masura.gif" class="gradualfader"  /> <br/>
             	Unitati de masura
             	</a>
             	<hr/>
             </li>        
             <li class="controller">
             	<a href="/bileteparcare-admin/priceBand/index">
             		<img src="/bileteparcare-admin/images/site/pret.gif" class="gradualfader"  /> <br/>
             		Pret
             	</a>
             	<hr/>
             </li>
	      </ul>
	    </div>
	    
	    <div id="west-parking" align="center">
	      <ul>
	        <li class="controller">
	        	<a href="/bileteparcare-admin/parking/index">
	        	<img src="/bileteparcare-admin/images/site/parcare.gif" class="gradualfader"  /> <br/>
	        	Parcari
	        	</a>
	        	<hr/>
	        </li>
	        <li class="controller">
	        	<a href="/bileteparcare-admin/parkingCampaign/index">
	        	<img src="/bileteparcare-admin/images/site/promotie-parcare.gif" class="gradualfader"  /> <br/>
	        	Legaturi parcari - campanii
	        	</a>
	        	<hr/>
	        </li>
	      </ul>
	    </div>
	    
	    <div id="west-reports" align="center">
	      <ul>
	        <li class="controller">
	        	<a href="/bileteparcare-admin/report/active-campaigns">
	        		<img src="/bileteparcare-admin/images/site/pdf.gif" class="gradualfader"  /> <br/>
	        		Campanii active
	        	</a>
	        	<hr/>
	        </li>        
	        <li class="controller">
	        	<a href="/bileteparcare-admin/report/last-day">
	        		<img src="/bileteparcare-admin/images/site/pdf.gif" class="gradualfader"  /> <br/>
	        		Incasari in ziua precedenta
	        	</a>
	        	<hr/>
	        </li>
	        <li class="controller">
	        	<a href="/bileteparcare-admin/report/last-month">
	        	<img src="/bileteparcare-admin/images/site/pdf.gif" class="gradualfader"  /> <br/>
	        	Incasari in luna precedenta
	        	</a>
	        	<hr/>
	        	</li>
           </ul>
	    </div>
	    
	    <div id="west-users" align="center">
	      <ul>
	        <li class="controller">
	        	<a href="/bileteparcare-admin/users/list">
	        		<img src="/bileteparcare-admin/images/site/users.jpg" class="gradualfader"  /> <br/>
	        		Utilizatori
	        	</a>
	        	<hr/>
	        </li>        
	        <li class="controller">
	        	<a href="/bileteparcare-admin/roles/list">
	        		<img src="/bileteparcare-admin/images/site/role.jpg" class="gradualfader"  /> <br/>
	        		Roluri
	        	</a>
	        	<hr/>
	        </li>
	        <li class="controller">
	        	<a href="/bileteparcare-admin/requestmap/list">
	        	<img src="/bileteparcare-admin/images/site/grant.jpg" class="gradualfader"  /> <br/>
	        	Permisiuni
	        	</a>
	        	<hr/>
	        	</li>
           </ul>
	    </div>
	    
    	<div id="north" align="right">
    		<p>
    		  <a href="/bileteparcare-admin/logout">Iesire</a>
    		</p>
  		</div>   
  		   
		<div id="center1">
			<g:layoutBody />
		</div>
		
		<div id="props-panel" style="width:200px;height:200px;overflow:hidden;">
  		</div>
  		
		<div id="south">			
			<center> Sistem administrare si facturare bilete pentru parcari publice cu taxa </center>
			<center> Universitatea din Bucuresti. Facultatea de Matematica si Informatica. Master Baze de date I.D. </center>
    		<center> Copyright 2009 Vasile Alin </center>
  		</div>
  		
  		<script type="text/javascript">
			gradualFader.init() //activate gradual fader
		</script>
    </body>	
</html>