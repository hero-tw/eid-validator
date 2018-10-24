EID Validator
=============


Local Requirements
------------------

Java 8, MySQL configured with an eid database. See 
```config/application-local.properties```  for configuration. 


./graldew bootRun

Build Requirements
------------------

Refer to [DockerFile](https://github.com/atl-tw/JenkinsJNLPWorkerWithDockerAWS/blob/master/Dockerfile).

Configured Jenkins Environment:

credentials: aws-keys
credentials: EID_DATABASE
secret text: EID_DATABASE_HOST

