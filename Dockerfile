FROM tomcat:8.0.43-jre8

ADD target/teamsync.war /usr/local/tomcat/webapps/teamsync.war

CMD ["catalina.sh", "run"]
