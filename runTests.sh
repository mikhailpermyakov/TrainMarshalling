#!/bin/bash
current=$(pwd)
for file in \
	01positiveInt \
	02positiveInt \
	03zero \
	04zero \
	05negativeInt \
	06negativeInt \
	07positiveFrac \
	08positiveFrac \
	09negativeFrac \
	10negativeFrac \
	11textChar \
	12textChar \
	13specialChar \
	14specialChar \
	15emptyString \
	16emptyString \
	17eofMark \
	18extraZero \
	19eobMark \
	20amountMissing \
	21extraWhitespace \
	22emptyBlock \
	23inconsistentAmount \
	24inconsistentAmount \
	25largerNum \
	26duplicates \
	27one \
	28algoTestOne \
	29algoTestPremax \
	30algoTestMax \
	31algoTestExceeded \
	32algoTestMidrange ;
do
echo \>\>\>
echo The input data being currently tested: $file
java -cp $current/src Main $current/testData/$file
echo \<\<\<
done
