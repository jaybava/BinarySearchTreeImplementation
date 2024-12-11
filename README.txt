Binary Search Tree (BST) Dictionary with Multimedia Support
This project implements an Ordered Dictionary using a Binary Search Tree (BST) in Java. The dictionary supports insertion, deletion, searching, and retrieving the smallest and largest elements. Additionally, it supports multimedia operations such as displaying images, playing sounds, and browsing HTML files.

Project Structure
.
├── BinarySearchTree.java        - Implementation of the Binary Search Tree
├── BSTDictionary.java           - BST-based Ordered Dictionary implementation
├── BSTDictionaryADT.java        - Interface defining the dictionary operations
├── BSTNode.java                 - Represents nodes in the BST
├── DictionaryException.java     - Custom exception for dictionary operations
├── Interface.java               - Interactive console interface for the dictionary
├── Key.java                     - Class representing keys used in the dictionary
├── Record.java                  - Class representing records with key-value pairs
│
├── MultimediaException.java     - Custom exception for multimedia errors
├── PictureViewer.java           - Displays images in a window
├── ShowHTML.java                - Displays HTML pages in a window
├── SoundPlayer.java             - Plays audio files
├── StringReader.java            - Reads user input from the console
│
├── TestDict.java                - Test suite for the BST Dictionary
├── Sample.java                  - Sample program demonstrating multimedia capabilities
│
└── Resources:
    ├── small.txt                - Sample dictionary data file
    └── Various multimedia files (images, sounds, HTML)

Features
Dictionary Operations
The dictionary supports the following operations:

Insertion (put): Add a new record with a key-value pair.
Search (get): Retrieve a record by its key.
Deletion (remove): Delete a record by its key.
Successor/Predecessor: Find the next or previous key in order.
Smallest/Largest: Retrieve the smallest or largest key in the dictionary.

Multimedia Support
The project includes multimedia features for:

Displaying Images (PictureViewer.java)​
Playing Sounds (SoundPlayer.java)​
Browsing HTML Files (ShowHTML.java)​

Interactive Console
The Interface.java class provides a command-line interface with the following commands:

define <word>: Display the definition of the word.
translate <word>: Translate the word.
sound <word>: Play a sound associated with the word.
play <word>: Play a music file associated with the word.
say <word>: Play a voice file associated with the word.
show <word>: Display an image associated with the word.
animate <word>: Display an animated image associated with the word.
browse <word>: Browse an HTML file associated with the word.
list: List all records in the dictionary.
first: Display the first record in the dictionary.
last: Display the last record in the dictionary.

Testing
The project includes a comprehensive test suite in TestDict.java, which tests various dictionary operations like insertion, deletion, and finding successors and predecessors.

How to Run the Project
Compilation
Compile all the Java files:

javac *.java

Running the Interactive Interface
Run the Interface class:

java Interface small.txt

Running the Sample Multimedia Program
Run the Sample class to demonstrate multimedia features:

java Sample

Running Tests
Run the TestDict class:

java TestDict

You can run specific tests by passing a test number as an argument:

java TestDict 1

Sample Data File (small.txt)
Example contents of small.txt:

course
A series of talks or lessons, for example, CS2210.
homework
Very enjoyable work that students need to complete outside the classroom.
nap
Something that students should try to avoid doing in class.
algorithm
/algorithme
flower
flower.jpg
spring
-spring.wav
matrix
matrices.html