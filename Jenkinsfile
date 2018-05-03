pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        git(url: 'git@github.com:muquifo/desafio-spring-boot.git', branch: 'master', changelog: true)
      }
    }
  }
}