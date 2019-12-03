# ProjectTemplate
This repository creates structure for multi-modules project with *.deb deployment project.

#### Multiple Modules
Standard Maven module is used with 2 additional directories, conf and script. To create module:  
1. Copy the Implementation directory, gives it a new name.  
2. Inside the new directory, the edit pom.xml file. Change the artifactId value to match the folder name.  
3. Add a new module in the parent pom.xml   

######conf directory
Content in the conf and src/main/resource will be added  to the /opt/tako/artifactId/conf directory during deployment.  
This is the best place for log4j properties and application configuration.  

######script directory
Content in the script will be added to the /opt/tako/artifactId/script directory during deployment.  
This is the best place for cron script or execution script.  

#### Create deb Package
Reference:  
https://www.tldp.org/HOWTO/html_single/Debian-Binary-Package-Building-HOWTO/  
cd Package/.. && dpkg-deb --build package




###Lesson Learnt for this project
