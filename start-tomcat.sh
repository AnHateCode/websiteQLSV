wget https://downloads.apache.org/tomcat/tomcat-9/v9.0.65/bin/apache-tomcat-9.0.65.tar.gz
tar xvfz apache-tomcat-9.0.65.tar.gz
mv apache-tomcat-9.0.65 tomcat

# Copy WAR file vào thư mục webapps của Tomcat
cp QLSV2.2.war tomcat/webapps/

# Start Tomcat server
./tomcat/bin/catalina.sh run
chmod +x start-tomcat.sh
