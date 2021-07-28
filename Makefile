all:
	javac -encoding utf8 -cp . -sourcepath src -d out/ src/crossyroad/*.java
	java -cp out crossyroad/App
clean:
	rm -rf out