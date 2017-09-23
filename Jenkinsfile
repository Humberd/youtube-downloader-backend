node {
    properties([buildDiscarder(logRotator(
            artifactDaysToKeepStr: '',
            artifactNumToKeepStr: '2',
            daysToKeepStr: '',
            numToKeepStr: '')),
                disableConcurrentBuilds(),
                pipelineTriggers([githubPush()])])

    stage("Pre Cleanup") {
        deleteDir()
    }

    stage("Checkout") {
        git "https://github.com/Humberd/youtube-downloader-backend.git"
    }

    stage("Build") {
        withMaven(maven: "Maven") {
            sh "mvn clean install -DskipTests=true"
        }
    }

    stage("Test") {
        withMaven(maven: "Maven") {
            sh "mvn test"
        }
    }

    stage("Deploy") {
        dockerComposeFile = "docker-compose.quazarus.yml"
        setCommitEnv()
        setBuildNumberEnv()

        sh "docker-compose -f ${dockerComposeFile} down --rmi all --remove-orphans"
        sh "docker-compose -f ${dockerComposeFile} up -d"
    }

    stage("Post Cleanup") {
        deleteDir()
    }
}

def setCommitEnv() {
    COMMIT = sh(
            script: "git show -s",
            returnStdout: true
    ).trim()

    environment {
        COMMIT = COMMIT
    }
}

def setBuildNumberEnv() {
    BUILD_NUMBER = ${env.BUILD_NUMBER}
    environmane {
        BUILD_NUMBER: BUILD_NUMBER
    }
}