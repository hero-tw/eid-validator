JMeter Performance Tests
========================

This contains performance tests to run against a newly deployed instance.


Runtime
-------

These tests (should) reference the host name to test against by pulling in a
property value in the form ```${__P(hostname)}```.

Editing
-------

To edit these values, please use ```./gradlew jmGui``` to launch the JMeter
GUI configured as it will be at build time.

