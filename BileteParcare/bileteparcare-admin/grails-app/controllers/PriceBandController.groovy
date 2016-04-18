

class PriceBandController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ priceBandInstanceList: PriceBand.list( params ), priceBandInstanceTotal: PriceBand.count() ]
    }

    def show = {
        def priceBandInstance = PriceBand.get( params.id )

        if(!priceBandInstance) {
            flash.message = "PriceBand not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ priceBandInstance : priceBandInstance ] }
    }

    def delete = {
        def priceBandInstance = PriceBand.get( params.id )
        if(priceBandInstance) {
            try {
                priceBandInstance.delete()
                flash.message = "PriceBand ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "PriceBand ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "PriceBand not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def priceBandInstance = PriceBand.get( params.id )

        if(!priceBandInstance) {
            flash.message = "PriceBand not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ priceBandInstance : priceBandInstance ]
        }
    }

    def update = {
        def priceBandInstance = PriceBand.get( params.id )
        if(priceBandInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(priceBandInstance.version > version) {
                    
                    priceBandInstance.errors.rejectValue("version", "priceBand.optimistic.locking.failure", "Another user has updated this PriceBand while you were editing.")
                    render(view:'edit',model:[priceBandInstance:priceBandInstance])
                    return
                }
            }
            priceBandInstance.properties = params
            if(!priceBandInstance.hasErrors() && priceBandInstance.save()) {
                flash.message = "PriceBand ${params.id} updated"
                redirect(action:show,id:priceBandInstance.id)
            }
            else {
                render(view:'edit',model:[priceBandInstance:priceBandInstance])
            }
        }
        else {
            flash.message = "PriceBand not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def priceBandInstance = new PriceBand()
        priceBandInstance.properties = params
        return ['priceBandInstance':priceBandInstance]
    }

    def save = {
        def priceBandInstance = new PriceBand(params)
        if(!priceBandInstance.hasErrors() && priceBandInstance.save()) {
            flash.message = "PriceBand ${priceBandInstance.id} created"
            redirect(action:show,id:priceBandInstance.id)
        }
        else {
            render(view:'create',model:[priceBandInstance:priceBandInstance])
        }
    }
}
