mkdir .\build_shell\classes
mkdir .\build_shell\docs
mkdir .\build_shell\jar\
javac -d .\build_shell\classes .\src\main\java\ru\nsu\ntatarinov\Heapsort.java
javadoc -d .\build_shell\doc\ -charset utf-8 -sourcepath .\src\main\java\ -subpackages ru.nsu.ntatarinov
jar cf .\build_shell\jar\HeapSort.jar .\src\main\java\ru\nsu\ntatarinov\Heapsort.java