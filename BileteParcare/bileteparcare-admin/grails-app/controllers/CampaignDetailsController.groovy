

class CampaignDetailsController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ campaignDetailsInstanceList: CampaignDetails.list( params ), campaignDetailsInstanceTotal: CampaignDetails.count() ]
    }

    def show = {
        def campaignDetailsInstance = CampaignDetails.get( params.id )

        if(!campaignDetailsInstance) {
            flash.message = "CampaignDetails not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ campaignDetailsInstance : campaignDetailsInstance ] }
    }

    def delete = {
        def campaignDetailsInstance = CampaignDetails.get( params.id )
        if(campaignDetailsInstance) {
            try {
                campaignDetailsInstance.delete()
                flash.message = "CampaignDetails ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "CampaignDetails ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "CampaignDetails not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def campaignDetailsInstance = CampaignDetails.get( params.id )

        if(!campaignDetailsInstance) {
            flash.message = "CampaignDetails not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ campaignDetailsInstance : campaignDetailsInstance ]
        }
    }

    def update = {
        def campaignDetailsInstance = CampaignDetails.get( params.id )
        if(campaignDetailsInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(campaignDetailsInstance.version > version) {
                    
                    campaignDetailsInstance.errors.rejectValue("version", "campaignDetails.optimistic.locking.failure", "Another user has updated this CampaignDetails while you were editing.")
                    render(view:'edit',model:[campaignDetailsInstance:campaignDetailsInstance])
                    return
                }
            }
            campaignDetailsInstance.properties = params
            if(!campaignDetailsInstance.hasErrors() && campaignDetailsInstance.save()) {
                flash.message = "CampaignDetails ${params.id} updated"
                redirect(action:show,id:campaignDetailsInstance.id)
            }
            else {
                render(view:'edit',model:[campaignDetailsInstance:campaignDetailsInstance])
            }
        }
        else {
            flash.message = "CampaignDetails not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def campaignDetailsInstance = new CampaignDetails()
        campaignDetailsInstance.properties = params
        return ['campaignDetailsInstance':campaignDetailsInstance]
    }

    def save = {
        def campaignDetailsInstance = new CampaignDetails(params)
        if(!campaignDetailsInstance.hasErrors() && campaignDetailsInstance.save()) {
            flash.message = "CampaignDetails ${campaignDetailsInstance.id} created"
            redirect(action:show,id:campaignDetailsInstance.id)
        }
        else {
            render(view:'create',model:[campaignDetailsInstance:campaignDetailsInstance])
        }
    }
}
