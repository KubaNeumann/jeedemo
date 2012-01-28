#!/bin/sh

java -cp ./scripts/hsqldb-2.2.4.jar org.hsqldb.util.DatabaseManagerSwing --url jdbc:hsqldb:hsql://localhost/workdb
