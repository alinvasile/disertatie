

class CampaignController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ campaignInstanceList: Campaign.list( params ), campaignInstanceTotal: Campaign.count() ]
    }

    def show = {
        def campaignInstance = Campaign.get( params.id )

        if(!campaignInstance) {
            flash.message = "Campaign not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ campaignInstance : campaignInstance ] }
    }

    def delete = {
        def campaignInstance = Campaign.get( params.id )
        if(campaignInstance) {
            try {
                campaignInstance.delete()
                flash.message = "Campaign ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "Campaign ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "Campaign not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def campaignInstance = Campaign.get( params.id )

        if(!campaignInstance) {
            flash.message = "Campaign not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ campaignInstance : campaignInstance ]
        }
    }

    def update = {
        def campaignInstance = Campaign.get( params.id )
        if(campaignInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(campaignInstance.version > version) {
                    
                    campaignInstance.errors.rejectValue("version", "campaign.optimistic.locking.failure", "Another user has updated this Campaign while you were editing.")
                    render(view:'edit',model:[campaignInstance:campaignInstance])
                    return
                }
            }
            campaignInstance.properties = params
            if(!campaignInstance.hasErrors() && campaignInstance.save()) {
                flash.message = "Campaign ${params.id} updated"
                redirect(action:show,id:campaignInstance.id)
            }
            else {
                render(view:'edit',model:[campaignInstance:campaignInstance])
            }
        }
        else {
            flash.message = "Campaign not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def campaignInstance = new Campaign()
        campaignInstance.properties = params
        return ['campaignInstance':campaignInstance]
    }

    def save = {
        def campaignInstance = new Campaign(params)
        if(!campaignInstance.hasErrors() && campaignInstance.save()) {
            flash.message = "Campaign ${campaignInstance.id} created"
            redirect(action:show,id:campaignInstance.id)
        }
        else {
            render(view:'create',model:[campaignInstance:campaignInstance])
        }
    }
}
