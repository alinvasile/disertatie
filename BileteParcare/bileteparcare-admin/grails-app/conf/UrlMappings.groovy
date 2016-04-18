class UrlMappings {
    static mappings = {
      "/$controller/$action?/$id?"{
	      constraints {
			 // apply constraints here
		  }
	  }
	  
	  "/report/active-campaigns" {      
      controller = 'activeCampaigns'      
      }
      
      "/report/last-day" {      
      controller = 'lastDay'      
    }
    
    "/report/last-month" {      
      controller = 'lastMonth'      
    }
	  
	  
      "/"(view:"/index")
	  "500"(view:'/error')
	}
}
