package com.alin.disertatie.bileteonline.method.reservation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Struct;
import java.util.HashMap;
import java.util.Map;

import oracle.jdbc.OracleTypes;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

import org.apache.commons.dbcp.PoolableConnection;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlReturnType;
import org.springframework.jdbc.core.SqlTypeValue;
import org.springframework.jdbc.core.support.AbstractSqlTypeValue;
import org.springframework.jdbc.object.StoredProcedure;

/**
 * <p>
 * Class that creates a reservation by calling the pl/sql code.
 * </p>
 *
 * @author Alin Vasile
 * @version 1.0
 */
public class MakeReservation extends StoredProcedure {

	public MakeReservation(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate, "PKG_RESERVATION.make_reservation");
		declareParameter(new SqlInOutParameter("reservationObject", OracleTypes.STRUCT, ReservationType.SQL_NAME ,new SqlReturnType(){

			public Object getTypeValue(CallableStatement cs, int paramIndex,
					int sqlType, String typeName) throws SQLException {
				Connection con = cs.getConnection();
				Map<String,Class<?>> typeMap = con.getTypeMap();
				typeMap.put(typeName, ReservationType.class);
				Object o = cs.getObject(paramIndex);
				return o;
			}
			
		}));
		//declareParameter(new SqlOutParameter("return", Types.NUMERIC));		
		compile();
	}
	
	/**
	 * Creates a reservation for a vehicle
	 * 
	 * @param reservation
	 * @return The created reservation
	 */
	public ReservationType execute(final ReservationType reservation) {
		
        SqlTypeValue value = new AbstractSqlTypeValue() {
            protected Object createTypeValue(Connection conn, int sqlType, String typeName) throws SQLException {
            	
            	Connection usableConn = conn;
            	
            	// A ClassCastException appears
            	// when working with dbcp poolable connections
            	// get innermost delegate to receive the actual Connection instance 
            	if(conn instanceof PoolableConnection){
            		PoolableConnection poolableConn = (PoolableConnection)conn;
            		usableConn = poolableConn.getInnermostDelegate();
            	}
            	
                StructDescriptor itemDescriptor = new StructDescriptor(typeName, usableConn);
                
                Struct item = new STRUCT(itemDescriptor, usableConn,
                        new Object[] {
                			reservation.getParking(),
                			//new java.sql.Date(reservation.getReservationDate().getTime()),
                			null,
                			new java.sql.Timestamp(reservation.getReservationFrom().getTime()),
                			//reservation.getReservationId(),
                			null,
                			new java.sql.Timestamp(reservation.getReservationTo().getTime()),
                			reservation.getVehicleNumber(),
                			reservation.getCanceled()
                        });
                return item;
            }
        };
        
        Map<String,Object> inParam = new HashMap<String,Object>(1);
        inParam.put("reservationObject", value);
        Map outValues = execute(inParam);
                
        return (ReservationType)outValues.get("reservationObject");
    }
	
	
	
	
}
