# ProjectTemplate
This repository creates multi-modules maven project with *.deb deployment package.
The machine needs dpkg-deb installed.

## Multiple Modules
To add new Maven module:  
1. Copy the ModuleOne directory, gives it a new name.  
1. Inside the new directory, edit pom.xml file. Change the artifactId value to match the new folder name.  
1. Add a new module in the parent pom.xml 

The new module sources the plugin setting from parent pom.  The plugin ran within the child module context.
The deb packaging folder is controlled by the assembly.xml.

## debianPackage folder
The folder contains the control files for installation. 
Different environment may require different configuration.  Sub folder for each environment can be created within conf.
Change assembly.xml to select environment folder for packaging. 


## Create deb Package
Reference:  
https://www.tldp.org/HOWTO/html_single/Debian-Binary-Package-Building-HOWTO/  
Create deb: cd Package && dpkg-deb --build {directory with control} {deb name}  
Installation:  sudo atp install ./{deb name}.deb
remove: sudo apt remove {the Package value in the DEBIAN/control file}




###Lesson Learnt for this project
1. Ensure standard maven plugins are used, the advantage is to avoid setting plugin version and conflict.
1. maven-jar-plugin compiles the jar file, add the main-class configure, and add to the target folder
1. Plugins specification can be set in the parent pom. To pull it into the child module, add the plugin to child pom.xml
    1. maven-assembly-plugin creates 1 jar, including all dependencies, to module/target/jar-with-dependency folder.
    1. maven-dependency-plugin adds all dependencies jar to the target folder
1. Cannot find a good plugin for Deb packaging.  Most the plugin is too smart and hard to do simple thing.
1. Maven assembly is cumbersome, so maven-resources-plugin is used for copying files
    1. Add all target/*.jar to Package/opt/project-module/lib
    1. Add all src/main/resources/* and module/conf to Package/opt/project-module/conf
    1. Add all module/script/* to Package/opt/module/script
    1. To package for different environment, likely each env. needs separate set of configuration. to do so:
        1. create sub-folder for each env. inside the conf folder
        1. move the configuration to the sub-folder
        1. In "copy-conf-content" block, edit the the resource value to point to the sub-folder.
    
    