NewSite Application ReadeMe
===============================================================================

Backend API call to Google Blogger:

https://www.googleapis.com/blogger/v3/blogs/9765509/posts?key=AIzaSyAnXtz5frmKEfzeoGGe5q7cUbkVfswFD8A

// no instrumentation, just plain call to main
java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8813,suspend=n -jar target\gs-rest-service-0.1.0.jar com.neosemantix.newsite.Main

// did not work
java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8813,suspend=n -javaagent:C:\Users\umesh\.m2\repository\org\springframework\spring-instrument\4.1.6.RELEASE\spring-instrument-4.1.6.RELEASE.jar -jar target\gs-rest-service-0.1.0.jar com.neosemantix.newsite.Main

// did not work
java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8813,suspend=n -javaagent:C:\Users\umesh\.m2\repository\org\springframework\spring-agent\2.5.6.SEC03\spring-agent-2.5.6.SEC03.jar -jar target\gs-rest-service-0.1.0.jar com.neosemantix.newsite.Main

// note the parameters in command line
java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8813,suspend=n -javaagent:${settings.localRepository}\org\springframework\spring-agent\${spring.version}\spring-instrument-${spring.version}.jar -jar target\gs-rest-service-0.1.0.jar com.neosemantix.newsite.Main

// deploys
java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8813,suspend=n -javaagent:C:\Users\upatil\.m2\repository\org\aspectj\aspectjweaver\1.8.5\aspectjweaver-1.8.5.jar -jar target\gs-rest-service-0.1.0.jar com.neosemantix.newsite.Main


Error message:
https://lists.desy.de/sympa/arc/user-forum/2012-05/msg00003.html


C:\Users\umesh\.m2\repository\org\springframework\spring-instrument

-javaagent:spring-instrument-3.0.5.RELEASE.jar

<argLine>
        -javaagent:${settings.localRepository}/org/springframework/spring-agent/${spring.version}/spring-agent-${spring.version}.jar
      </argLine>

ResponseEntityProxy{[Content-Type: application/json; charset=UTF-8,Content-Length: 64803,Chunked: false]}

	wrappedEntity
	
		content (InputStream) 	read from there
		
		
INSERT INTO collection(
            id, api_key, collector_class, last_ran, name, site_id)
    VALUES (?, ?, ?, ?, ?, ?);		
    
===============================================================================    
Database: PostgreSql    9.4.4
postgres
postgres
===============================================================================