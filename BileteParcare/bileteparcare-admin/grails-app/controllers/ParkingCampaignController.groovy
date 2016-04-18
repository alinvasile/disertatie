

class ParkingCampaignController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ parkingCampaignInstanceList: ParkingCampaign.list( params ), parkingCampaignInstanceTotal: ParkingCampaign.count() ]
    }

    def show = {
        def parkingCampaignInstance = ParkingCampaign.get( params.id )

        if(!parkingCampaignInstance) {
            flash.message = "ParkingCampaign not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ parkingCampaignInstance : parkingCampaignInstance ] }
    }

    def delete = {
        def parkingCampaignInstance = ParkingCampaign.get( params.id )
        if(parkingCampaignInstance) {
            try {
                parkingCampaignInstance.delete()
                flash.message = "ParkingCampaign ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "ParkingCampaign ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "ParkingCampaign not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def parkingCampaignInstance = ParkingCampaign.get( params.id )

        if(!parkingCampaignInstance) {
            flash.message = "ParkingCampaign not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ parkingCampaignInstance : parkingCampaignInstance ]
        }
    }

    def update = {
        def parkingCampaignInstance = ParkingCampaign.get( params.id )
        if(parkingCampaignInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(parkingCampaignInstance.version > version) {
                    
                    parkingCampaignInstance.errors.rejectValue("version", "parkingCampaign.optimistic.locking.failure", "Another user has updated this ParkingCampaign while you were editing.")
                    render(view:'edit',model:[parkingCampaignInstance:parkingCampaignInstance])
                    return
                }
            }
            parkingCampaignInstance.properties = params
            if(!parkingCampaignInstance.hasErrors() && parkingCampaignInstance.save()) {
                flash.message = "ParkingCampaign ${params.id} updated"
                redirect(action:show,id:parkingCampaignInstance.id)
            }
            else {
                render(view:'edit',model:[parkingCampaignInstance:parkingCampaignInstance])
            }
        }
        else {
            flash.message = "ParkingCampaign not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def parkingCampaignInstance = new ParkingCampaign()
        parkingCampaignInstance.properties = params
        return ['parkingCampaignInstance':parkingCampaignInstance]
    }

    def save = {
        def parkingCampaignInstance = new ParkingCampaign(params)
        if(!parkingCampaignInstance.hasErrors() && parkingCampaignInstance.save()) {
            flash.message = "ParkingCampaign ${parkingCampaignInstance.id} created"
            redirect(action:show,id:parkingCampaignInstance.id)
        }
        else {
            render(view:'create',model:[parkingCampaignInstance:parkingCampaignInstance])
        }
    }
}
