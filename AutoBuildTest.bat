@ECHO OFF
javac -classpath C:\apache_poi\* *.java
java -classpath "C:\apache_poi\*;C:\Static-code-analyzer" MainContainer
PAUSE
