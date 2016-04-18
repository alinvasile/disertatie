



/**
 * User domain class.
 */
class Users {
	static transients = ['pass']
	static hasMany = [authorities: Roles]
	static belongsTo = Roles

	/** Username */
	String username
	/** User Real Name*/
	String userRealName
	/** MD5 Password */
	String passwd
	/** enabled */
	boolean enabled

	String email
	boolean emailShow

	/** description */
	String description = ''

	/** plain password to create a MD5 password */
	String pass = '[secret]'

	static constraints = {
		username(blank: false, unique: true)
		userRealName(blank: false)
		passwd(blank: false)
		enabled()
	}
	
	static mapping = {
	authorities joinTable:[name:'ROLE_APP_USER',  column:'authorities_id']
	
	}
}
