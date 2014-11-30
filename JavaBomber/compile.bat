del .\Systeme\*.class
del .\Systeme\bombergraphics\*.class
del .\Systeme\bombernetwork\*.class
del .\Systeme\bomberscene\*.class

javac .\Source\JavaBomber.java -classpath ".\Source" -d ".\Systeme"
