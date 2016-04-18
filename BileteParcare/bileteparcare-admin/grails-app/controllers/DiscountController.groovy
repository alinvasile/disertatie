

class DiscountController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ discountInstanceList: Discount.list( params ), discountInstanceTotal: Discount.count() ]
    }

    def show = {
        def discountInstance = Discount.get( params.id )

        if(!discountInstance) {
            flash.message = "Discount not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ discountInstance : discountInstance ] }
    }

    def delete = {
        def discountInstance = Discount.get( params.id )
        if(discountInstance) {
            try {
                discountInstance.delete()
                flash.message = "Discount ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "Discount ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "Discount not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def discountInstance = Discount.get( params.id )

        if(!discountInstance) {
            flash.message = "Discount not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ discountInstance : discountInstance ]
        }
    }

    def update = {
        def discountInstance = Discount.get( params.id )
        if(discountInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(discountInstance.version > version) {
                    
                    discountInstance.errors.rejectValue("version", "discount.optimistic.locking.failure", "Another user has updated this Discount while you were editing.")
                    render(view:'edit',model:[discountInstance:discountInstance])
                    return
                }
            }
            discountInstance.properties = params
            if(!discountInstance.hasErrors() && discountInstance.save()) {
                flash.message = "Discount ${params.id} updated"
                redirect(action:show,id:discountInstance.id)
            }
            else {
                render(view:'edit',model:[discountInstance:discountInstance])
            }
        }
        else {
            flash.message = "Discount not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def discountInstance = new Discount()
        discountInstance.properties = params
        return ['discountInstance':discountInstance]
    }

    def save = {
        def discountInstance = new Discount(params)
        if(!discountInstance.hasErrors() && discountInstance.save()) {
            flash.message = "Discount ${discountInstance.id} created"
            redirect(action:show,id:discountInstance.id)
        }
        else {
            render(view:'create',model:[discountInstance:discountInstance])
        }
    }
}
