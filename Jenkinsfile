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

        environment {
            COMMIT = getCommit()
            BUILD_NO = getBuildNumber()
        }
        println env.COMMIT

        sh "docker-compose -f ${dockerComposeFile} down --rmi all --remove-orphans"
        sh "docker-compose -f ${dockerComposeFile} up -d"
    }

    stage("Post Cleanup") {
        deleteDir()
    }
}

def getCommit() {
    return sh(
            script: "git show -s",
            returnStdout: true
    ).trim()
}

def getBuildNumber() {
    return env.BUILD_NUMBER
}