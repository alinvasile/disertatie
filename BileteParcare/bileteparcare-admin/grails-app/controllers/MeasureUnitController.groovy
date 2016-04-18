

class MeasureUnitController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ measureUnitInstanceList: MeasureUnit.list( params ), measureUnitInstanceTotal: MeasureUnit.count() ]
    }

    def show = {
        def measureUnitInstance = MeasureUnit.get( params.id )

        if(!measureUnitInstance) {
            flash.message = "MeasureUnit not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ measureUnitInstance : measureUnitInstance ] }
    }

    def delete = {
        def measureUnitInstance = MeasureUnit.get( params.id )
        if(measureUnitInstance) {
            try {
                measureUnitInstance.delete()
                flash.message = "MeasureUnit ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "MeasureUnit ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "MeasureUnit not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def measureUnitInstance = MeasureUnit.get( params.id )

        if(!measureUnitInstance) {
            flash.message = "MeasureUnit not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ measureUnitInstance : measureUnitInstance ]
        }
    }

    def update = {
        def measureUnitInstance = MeasureUnit.get( params.id )
        if(measureUnitInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(measureUnitInstance.version > version) {
                    
                    measureUnitInstance.errors.rejectValue("version", "measureUnit.optimistic.locking.failure", "Another user has updated this MeasureUnit while you were editing.")
                    render(view:'edit',model:[measureUnitInstance:measureUnitInstance])
                    return
                }
            }
            measureUnitInstance.properties = params
            if(!measureUnitInstance.hasErrors() && measureUnitInstance.save()) {
                flash.message = "MeasureUnit ${params.id} updated"
                redirect(action:show,id:measureUnitInstance.id)
            }
            else {
                render(view:'edit',model:[measureUnitInstance:measureUnitInstance])
            }
        }
        else {
            flash.message = "MeasureUnit not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def measureUnitInstance = new MeasureUnit()
        measureUnitInstance.properties = params
        return ['measureUnitInstance':measureUnitInstance]
    }

    def save = {
        def measureUnitInstance = new MeasureUnit(params)
        if(!measureUnitInstance.hasErrors() && measureUnitInstance.save()) {
            flash.message = "MeasureUnit ${measureUnitInstance.id} created"
            redirect(action:show,id:measureUnitInstance.id)
        }
        else {
            render(view:'create',model:[measureUnitInstance:measureUnitInstance])
        }
    }
}
