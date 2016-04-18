

class TarriffController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ tarriffInstanceList: Tarriff.list( params ), tarriffInstanceTotal: Tarriff.count() ]
    }

    def show = {
        def tarriffInstance = Tarriff.get( params.id )

        if(!tarriffInstance) {
            flash.message = "Tarriff not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ tarriffInstance : tarriffInstance ] }
    }

    def delete = {
        def tarriffInstance = Tarriff.get( params.id )
        if(tarriffInstance) {
            try {
                tarriffInstance.delete()
                flash.message = "Tarriff ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "Tarriff ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "Tarriff not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def tarriffInstance = Tarriff.get( params.id )

        if(!tarriffInstance) {
            flash.message = "Tarriff not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ tarriffInstance : tarriffInstance ]
        }
    }

    def update = {
        def tarriffInstance = Tarriff.get( params.id )
        if(tarriffInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(tarriffInstance.version > version) {
                    
                    tarriffInstance.errors.rejectValue("version", "tarriff.optimistic.locking.failure", "Another user has updated this Tarriff while you were editing.")
                    render(view:'edit',model:[tarriffInstance:tarriffInstance])
                    return
                }
            }
            tarriffInstance.properties = params
            if(!tarriffInstance.hasErrors() && tarriffInstance.save()) {
                flash.message = "Tarriff ${params.id} updated"
                redirect(action:show,id:tarriffInstance.id)
            }
            else {
                render(view:'edit',model:[tarriffInstance:tarriffInstance])
            }
        }
        else {
            flash.message = "Tarriff not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def tarriffInstance = new Tarriff()
        tarriffInstance.properties = params
        return ['tarriffInstance':tarriffInstance]
    }

    def save = {
        def tarriffInstance = new Tarriff(params)
        if(!tarriffInstance.hasErrors() && tarriffInstance.save()) {
            flash.message = "Tarriff ${tarriffInstance.id} created"
            redirect(action:show,id:tarriffInstance.id)
        }
        else {
            render(view:'create',model:[tarriffInstance:tarriffInstance])
        }
    }
}
