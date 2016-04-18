

class ParkingController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ parkingInstanceList: Parking.list( params ), parkingInstanceTotal: Parking.count() ]
    }

    def show = {
        def parkingInstance = Parking.get( params.id )

        if(!parkingInstance) {
            flash.message = "Parking not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ parkingInstance : parkingInstance ] }
    }

    def delete = {
        def parkingInstance = Parking.get( params.id )
        if(parkingInstance) {
            try {
                parkingInstance.delete()
                flash.message = "Parking ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "Parking ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "Parking not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def parkingInstance = Parking.get( params.id )

        if(!parkingInstance) {
            flash.message = "Parking not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ parkingInstance : parkingInstance ]
        }
    }

    def update = {
        def parkingInstance = Parking.get( params.id )
        if(parkingInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(parkingInstance.version > version) {
                    
                    parkingInstance.errors.rejectValue("version", "parking.optimistic.locking.failure", "Another user has updated this Parking while you were editing.")
                    render(view:'edit',model:[parkingInstance:parkingInstance])
                    return
                }
            }
            parkingInstance.properties = params
            if(!parkingInstance.hasErrors() && parkingInstance.save()) {
                flash.message = "Parking ${params.id} updated"
                redirect(action:show,id:parkingInstance.id)
            }
            else {
                render(view:'edit',model:[parkingInstance:parkingInstance])
            }
        }
        else {
            flash.message = "Parking not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def parkingInstance = new Parking()
        parkingInstance.properties = params
        return ['parkingInstance':parkingInstance]
    }

    def save = {
        def parkingInstance = new Parking(params)
        if(!parkingInstance.hasErrors() && parkingInstance.save()) {
            flash.message = "Parking ${parkingInstance.id} created"
            redirect(action:show,id:parkingInstance.id)
        }
        else {
            render(view:'create',model:[parkingInstance:parkingInstance])
        }
    }
}
