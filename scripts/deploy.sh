cd $(dirname $0)

cd ../spring-tutorials/tut-spring-security-and-angular-js/single
mvn clean install
ret=$?
if [ $ret -ne 0 ]; then
exit $ret
fi
rm -rf target

#cd ../spring-guides/gs-uploading-files
#mvn clean install
#ret=$?
#if [ $ret -ne 0 ]; then
#exit $ret
#fi
#rm -rf target
#rm -rf upload

#cd ../dependency-injection
#mvn clean compile
#ret=$?
#if [ $ret -ne 0 ]; then
#exit $ret
#fi
#rm -rf target
#
#cd ../blog
#mvn clean compile
#ret=$?
#if [ $ret -ne 0 ]; then
#exit $ret
#fi
#rm -rf target
#
#cd ../boot/demo
#mvn clean compile
#ret=$?
#if [ $ret -ne 0 ]; then
#exit $ret
#fi
#rm -rf target
