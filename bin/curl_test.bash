#!/usr/bin/env bash

echo 'Get all employees in database (Axel Rose, Eric Clapton)'
curl -v localhost:9101/employee

echo
echo 'Get employee by ID 101 in database (Axel Rose)'
curl -v localhost:9101/employee/101

echo
echo 'Add a new employee (Bob Ross)'
curl -X POST localhost:9101/employee -H 'Content-type:application/json' -d '{"employeeId": "200", "firstName": "Bob", "lastName": "Ross", "jobRole": "painter"}'

echo
echo "Check that Bob Ross is added"
curl -v localhost:9101/employee/200
# Change employee details (Bob Ross)
echo
echo "Update Bob Ross to be a dancer"
curl -X PUT localhost:9101/employee -H 'Content-type:application/json' -d '{"employeeId": "200", "firstName": "Bob", "lastName": "Ross", "jobRole": "dancer"}'

echo
echo 'Check that Bob Ross is now a dancer'
curl -v localhost:9101/employee/200
