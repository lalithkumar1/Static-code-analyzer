@ECHO OFF
javac -classpath "C:\poi-bin-4.1.0-20190412\poi-4.1.0\*;C:\poi-bin-4.1.0-20190412\poi-4.1.0\ooxml-lib\*;C:\poi-bin-4.1.0-20190412\poi-4.1.0\lib\*" *.java
java -classpath "C:\poi-bin-4.1.0-20190412\poi-4.1.0\*;C:\poi-bin-4.1.0-20190412\poi-4.1.0\ooxml-lib\*;C:\poi-bin-4.1.0-20190412\poi-4.1.0\lib\*;C:\Static-code-analyzer" StaticToolApplication
PAUSE
