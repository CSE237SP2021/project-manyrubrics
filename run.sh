#!/bin/bash

javac ./src/manyRubrics/*.java
java -cp ./src manyRubrics.Driver "$@"
rm ./src/manyRubrics/*.class
