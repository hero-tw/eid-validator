EID Validator
=============

This project has a repository of "Identities" for a person. This includes
name, date of birth, a list of historical addresses, a list of state issued 
ID numbers. 

After you store an identity, it allows you to query based on a partial identity
and will give you a unique result with a score indicating confidence level of 
a match. The lack of a clear winner, or no match, will result in a 404. See:

[Insertion](./doc/examples/Insertion.md)
[Query](./doc/examples/Query.md)

Local Requirements
------------------

Java 8, MySQL configured with an eid database. See 
```config/application-local.properties```  for configuration. 


```./graldew bootRun```

Build Requirements
------------------

Refer to [DockerFile](https://github.com/atl-tw/JenkinsJNLPWorkerWithDockerAWS/blob/master/Dockerfile).

Configured Jenkins Environment:
```
credentials: aws-keys
credentials: EID_DATABASE
secret text: EID_DATABASE_HOST
```
