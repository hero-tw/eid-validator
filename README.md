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


```./gradlew bootRun```

Build Requirements
------------------

Refer to [DockerFile](https://github.com/atl-tw/JenkinsJNLPWorkerWithDockerAWS/blob/master/Dockerfile).

Configured Jenkins Environment:
```
credentials: aws-keys
credentials: EID_DATABASE
secret text: EID_DATABASE_HOST
```

Terraform State
------------------
Terraform state is stored in tf-eid-validator s3 bucket

In order to initialize the terraform state one needs to run the following command:

```terraform init```

Infrastructure
--------------

### General

In order to create the infrastructure required by this application one needs to run the following terraform command:


```
export TF_VAR_eid_db_password=change_it # stores the database password in an envivonment variable
terraform apply # creates EID infrastructure
```

In production you'll probably want to set the env variable in your CI/CD solution.


### Database

An RDS Mysql 8.0 instance is created as part of the Terraform configurations.
A database and user both named *eid* are created through the terraform scripts.

### Docker Repo (ECR)