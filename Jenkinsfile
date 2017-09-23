node {

    stage("Pre Cleanup") {
        sh "pwd"
        sh "ls -al"
        deleteDir()
    }

    stage("Checkout") {
        git "https://github.com/Humberd/youtube-downloader-backend.git"
    }

    stage("Package") {
        withMaven(maven: "Maven") {
            sh "mvn clean package"
        }
    }

    stage("Deploy") {
        dockerComposeFile = "docker-compose.quazarus.yml"

        sh "docker-compose -f ${dockerComposeFile} down --rmi all --remove-orphans"
        sh "docker-compose -f ${dockerComposeFile} up -d"
    }

    stage("Post Cleanup") {
        sh "ls -al"
        deleteDir()
        sh "ls -al"
    }
}