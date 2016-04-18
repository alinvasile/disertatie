



/**
 * Authority domain class.
 */
class Roles {

	static hasMany = [people: Users]
 
 
	/** description */
	String description
	/** ROLE String */
	String authority
	
	static mapping = {
		table 'auth_roles'
		people joinTable:[name:'ROLE_APP_USER', key:'id', column:'people_id']
		
	}

	static constraints = {
		authority(blank: false, unique: true)
		description()
	}
}
