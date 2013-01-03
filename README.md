# twitter-sample-client #

Shell application to aid in the review process for http://github.com/nrodrigues/twitter-sample

## Building ##

		git clone http://github.com/nrodrigues/twitter-sample-client
		
		mvn clean package
		
## Running ##
		
		java -jar target\twitter-sample-client-1.0-SNAPSHOT.jar
		
## Available commands##
	
		help

		login --username
		
		list users
		create user --username
		user details
		followers
		following
		start following --username
		stop following --username
		
		tweet -tweet 
		timeline
