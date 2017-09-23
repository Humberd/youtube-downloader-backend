node {

    stage("Checkout") {
        git "https://github.com/Humberd/youtube-downloader-backend.git"
    }

    stage("Package") {
        withMaven(maven: "Maven") {
            sh "mvn clean package"
        }
    }

    dockerComposeFile="docker-compose.quazarus.yml"
    stage("Deploy") {
        sh "docker-compose -f ${dockerComposeFile} --rmi all --remove-orphans"
        sh "docker-compose -f ${dockerComposeFile} up -d"
    }
}