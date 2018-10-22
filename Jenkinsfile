pipeline {
  agent {
    kubernetes {
          label 'autoscale'
          defaultContainer 'jnlp'
          yamlFile '../JenkinsPod.yaml'
        }
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
            sh './gradlew -Dorg.gradle.daemon=false clean'
        }
    }

    stage('Build') {
        steps {
            sh './gradlew -Dorg.gradle.daemon=false bootJar'
            archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true
        }
    }

    stage('Test') {
       steps {
           script {
              try {
                sh 'AWS_SECRET_ACCESS_KEY=$AWS_KEY_PSW AWS_ACCESS_KEY_ID=$AWS_KEY_USR ./gradlew -Dorg.gradle.daemon=false check'
              } finally {
                publishHTML(target: [reportDir:'build/reports/tests/test',
                                                    reportFiles: 'index.html',
                                                    reportName: 'Unit Tests', keepAll: true])
                publishHTML(target: [reportDir:'build/reports/findbugs',
                                    reportFiles: 'main.html,test.html,contractTest.html',
                                    reportName: 'FindBugs', keepAll: true])
                publishHTML(target: [reportDir:'build/reports/jacoco/test/html',
                    reportFiles: 'index.html',
                    reportName: 'Code Coverage', keepAll: true])

            }
         }
     }
    }

    stage('Dockerize') {
        steps {
            sh 'EID_DATABASE_HOST=$EID_DATABASE_HOST EID_DATABASE_USER=$EID_DATABASE_KEY_USR EID_DATABASE_PASSWORD=$EID_DATABASE_KEY_PSW ./gradlew -Dorg.gradle.daemon=false docker'
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
                sh './gradlew -Dorg.gradle.daemon=false dependencyCheckUpdate dependencyCheckAnalyze'
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
                    sh 'AWS_SECRET_ACCESS_KEY=$AWS_KEY_PSW AWS_ACCESS_KEY_ID=$AWS_KEY_USR ./gradlew -Dorg.gradle.daemon=false jmClean jmRun xslt --stacktrace'
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



