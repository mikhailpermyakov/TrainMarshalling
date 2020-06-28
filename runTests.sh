current=$(pwd)
for file in $current/testData/*
do
java -cp $current/src Main $file > results.out
done
