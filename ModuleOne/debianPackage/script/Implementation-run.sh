HOME="/opt/${company-prefix}${project.artifactId}"
java -Dlogback.configurationFile=$HOME/conf/logback.xml -jar $HOME/lib/${project.build.finalName}.jar