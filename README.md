This very short note is about how to:

* start HSQLDB (TCP server, in memory mode) and its simple GUI client  
* add Connection Pool to said HSQLDB server and define appropriate jdbc JNDI resource in GlassFish 3.1
* add Hibernate support to GlassFish 3.1


## Clone this project and run database server and (optional) simple GUI client

1. clone or update this project (obvious)
* cd to the project folder (do this with any other terminal)
* `chmod 755 ./scripts/*.sh` 
* run HSQLDB server (TCP, in memory mode):  
`./scripts/runHSQLDBServer.sh`
* run HSQLDB GUI Client (optional but strongly recommended)  
`./scripts/runHSQLDBClient.sh`


## Define Connection Pool and JNDI JDBC resource

1. stop GlassFish if it's running  
`asadmin stop-domain`
* copy jdbc driver hsqldb-2.2.4.jar to glassfish domain lib folder e.g.:  
`cp ./scripts/hsqldb-2.2.4.jar /opt/devel/as/glassfish3/glassfish/domains/domain1/lib/ext/.`
* define resource connection pool and jndi resource. Edit default domain configuration file e.g.:  
`vi /opt/devel/as/glassfish3/glassfish/domains/domain1/config/domain.xml`  
Find `<resource>` element. Copy&Paste following xml fragment next to any other `<jdbc-connection-pool>` element:    
`    <jdbc-connection-pool driver-classname="" datasource-classname="org.hsqldb.jdbc.JDBCDataSource"`  
`    res-type="javax.sql.DataSource" description="" name="HSQLPool" ping="true">`    
`      <property name="DatabaseName" value="jdbc:hsqldb:hsql://localhost/workdb"></property>`    
`      <property name="User" value="SA"></property>`   
`      <property name="Password" value=""></property>`    
`      <property name="connectionAttributes" value=";ifexists=true"></property>`    
`    </jdbc-connection-pool>`    
`    <jdbc-resource pool-name="HSQLPool" description="DataSource for demo apps with HSQLDB" `  
`    jndi-name="jdbc/demoapps"></jdbc-resource>`        
* start GlassFish  
`asadmin start-domain`
* check out connection from inside GlassFish
 * open web console `http://localhost:4848` 
 * click: Resources/JDBC/JDBC Connection Pools
 * click: HSQLPool
 * try Ping, it must Succeed !
 
## Add Hibernate support to GlassFish

* open web console `http://localhost:4848` 
* click: Update Tool
* find and select Hibertate in Available Add-Ons
* click Install
* restart GlassFish
`asadmin restart-domain`

## Don't hesitate deploy & run project
1. `./scripts/buildRedeploy.sh`
* `http://localhost:4848/jeedemo` 
