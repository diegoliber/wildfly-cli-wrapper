## Wildfly CLI Wrapper

Uses Wildfly CLI API to manage Wildfly configuration changes. This wrapper has a single input point, its main method, which has two parameters:

* (1) **Path**: Path to a JBoss resource using CLI syntax, for example: */subsystem=datasources/jdbc-driver=h2* ou */subsystem=datasources/datasource=ExampleDS.* *

* (2) **Change Request**: JSON Message with the desired state of the designated resource (1). (e.g. *"{ "driver-name": "h2", "driver-module-name": "com.h2" }"*

Internally, this Java CLI Application verifies if a resource designated by the Path (1) already exists, if so, confronts actual state with the desired state (2), overwriting it if needed. If the resource doesn't exists, it creates the resource designate by Path (1) with state designated by Change Request (2).

*For domain operation mode you always need to prefix the target profile (.e.g */profile=full-ha/subsystem=datasources...*)
