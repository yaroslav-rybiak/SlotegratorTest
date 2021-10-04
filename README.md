### To execute UI tests, run in console
mvn clean test -Dcucumber.options="--tags '@UI'"

### To execute API tests, run in console
mvn clean test -Dcucumber.options="--tags '@API'"

### To execute all tests, run in consloe
mvn clean test