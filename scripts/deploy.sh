cd $(dirname $0)

cd ../spring-guides/gs-rest-service
mvn clean install
ret=$?
if [ $ret -ne 0 ]; then
exit $ret
fi
rm -rf target

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
