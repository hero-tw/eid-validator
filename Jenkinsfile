pipeline {
  agent {
      label 'autoscale'
  }
  environment {
    AWS_KEY = credentials('aws-keys')
    EID_DATABASE_KEY = credentials('EID_DATABASE')
    EID_DATABASE_HOST = credentials('EID_DATABASE_HOST')
  }
  stages {
    stage('Init') {
        steps {
            checkout scm
            sh './gradlew clean'
        }
    }

    stage('Build') {
        steps {
            sh './gradlew bootJar'
            archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true
        }
    }
/*
    stage('Test') {
       steps {
           script {
              try {
                sh 'AWS_SECRET_ACCESS_KEY=$AWS_KEY_PSW AWS_ACCESS_KEY_ID=$AWS_KEY_USR ./gradlew check'
              } finally {
                publishHTML(target: [reportDir:'build/reports/tests/test',
                                                    reportFiles: 'index.html',
                                                    reportName: 'Unit Tests', keepAll: true])
                publishHTML(target: [reportDir:'build/reports/findbugs',
                                    reportFiles: 'main.html,test.html',
                                    reportName: 'FindBugs', keepAll: true])
                publishHTML(target: [reportDir:'build/reports/jacoco/test/html',
                    reportFiles: 'index.html',
                    reportName: 'Code Coverage', keepAll: true])

            }
         }
     }
    }
*/
    stage('Dockerize') {
        steps {
            sh 'AWS_DEFAULT_REGION=us-east-1 AWS_SECRET_ACCESS_KEY=$AWS_KEY_PSW AWS_ACCESS_KEY_ID=$AWS_KEY_USR eval $(aws ecr get-login --no-include-email)'
            sh 'EID_DATABASE_HOST=$EID_DATABASE_HOST EID_DATABASE_USER=$EID_DATABASE_KEY_USR EID_DATABASE_PASSWORD=$EID_DATABASE_KEY_PSW ./gradlew docker dockerPush'
        }
    }

    stage('Deploy') {
        steps {
            sh 'AWS_SECRET_ACCESS_KEY=$AWS_KEY_PSW AWS_ACCESS_KEY_ID=$AWS_KEY_USR ./deploy.sh'
        }
    }

    stage('Security') {
      steps {
        script {
            try {
                sh './gradlew dependencyCheckUpdate dependencyCheckAnalyze'
            } finally {
                publishHTML(target: [reportDir:'build/reports/dependency-check',
                                        reportFiles: 'dependency-check-report.html',
                                        reportName: 'Dependency Report', keepAll: true])
            }
        }
      }
    }

    stage('Performance') {
           steps {
               script {
                  try {
                    sh 'AWS_SECRET_ACCESS_KEY=$AWS_KEY_PSW AWS_ACCESS_KEY_ID=$AWS_KEY_USR ./gradlew jmClean jmRun xslt --stacktrace'
                  } finally {
                    def htmlFiles
                    dir('build/reports/jmeter') {
                       htmlFiles = findFiles glob: '*.html'
                    }
                    publishHTML(target: [reportDir:'build/reports/jmeter',
                        reportFiles: htmlFiles.join(','),
                        reportName: 'Performance Report', keepAll: true])

                }
             }
         }
        }
  }
}



