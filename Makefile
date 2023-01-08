build:
	javac -d ./ -cp ./ Node.java
	javac -d ./ -cp ./ Avl_Tree.java
	javac -d ./ -cp ./ avltree.java
clean:
	rm ./*.class
	rm output_file.txt