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
1. Ensure standard maven plugins are used, the advantage is to avoid setting plugin version and conflict.
1. maven-jar-plugin compiles the jar file, add the main-class configure, and add to the target folder
1. Plugins specification can be set in the parent pom. To pull it into the child module, add the plugin the child pom.xml
    1. maven-assembly-plugin creates 1 jar, including all dependencies, to module/target/jar-with-dependency folder.
    1. maven-dependency-plugin adds all dependencies jar to the target folder
1. Cannot find a good plugin for Deb packaging.  Most the plugin is too smart and hard to do simple thing.
1. Maven assembly is cumbersome, so maven-resources-plugin is used for copying files
    1. Add all target/*.jar to Package/opt/tako/module/lib
    1. Add all src/main/resources/* and module/conf to Package/opt/tako/module/conf
    1. Add all module/script/* to Package/opt/tako/module/script
    1. To package for different environment, copy just the corresponding files to the conf folder.
    
    