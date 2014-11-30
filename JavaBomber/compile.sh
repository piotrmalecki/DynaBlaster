rm ./Systeme/*.class
rm ./Systeme/bombergraphics/*.class
rm ./Systeme/bombernetwork/*.class
rm ./Systeme/bomberscene/*.class

javac ./Source/JavaBomber.java -classpath "./Source" -d "./Systeme"
