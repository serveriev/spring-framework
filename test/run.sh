cd $(dirname $0)
cd ../dependency-injection

mvn clean compile
ret=$?
if [ $ret -ne 0 ]; then
exit $ret
fi
rm -rf target

exit