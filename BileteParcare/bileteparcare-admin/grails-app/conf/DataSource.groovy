dataSource {
	pooled = true
	driverClassName = "oracle.jdbc.OracleDriver"
	username = "licenta"
	password = "licenta"
}
hibernate {
    cache.use_second_level_cache=true
    cache.use_query_cache=true
    cache.provider_class='com.opensymphony.oscache.hibernate.OSCacheProvider'
}
// environment specific settings
environments {
	development {
		dataSource {
			dbCreate = "update" // one of 'create', 'create-drop','update'
			url = "jdbc:oracle:thin:@localhost:1521:xe"
		}
	}
	test {
		dataSource {
			dbCreate = "update"
			url = "jdbc:oracle:thin:@localhost:1521:xe"
		}
	}
	production {
		dataSource {
			dbCreate = "update"
			url = "jdbc:oracle:thin:@localhost:1521:xe"
		}
	}
}