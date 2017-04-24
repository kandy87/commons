
install to localrepo
====================

 mvn org.apache.maven.plugins:maven-install-plugin:2.5.2:install-file      -Dfile=/home/iranget/Freelance/InventoryFSB/dbUtils/target/dbUtils-1.0.0-jar-with-dependencies.jar     -DgroupId=com.infsb.commons.dbutils -DartifactId=dbUtils     -Dversion=1.0.0 -Dpackaging=jar     -DlocalRepositoryPath=/home/iranget/.m2/repository/

bundle the jar
================

mvn clean compile assembly:single
