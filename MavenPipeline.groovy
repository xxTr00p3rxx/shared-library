class MavenPipeline extends BasePipeline {
    @Override
    def build() {
        sh "mvn clean install"
        sh "mvn hpi:hpi"
    }

    @Override
    def test() {
        sh "mvn test"
    }

    @Override
    def publishResult() {
        junit 'target/surefire-reports/*.xml'
    }

    @Override
    def publishArtifacts() {
        sshPublisher(
            continueOnError: false,
            failOnError: true,
            publishers: [
                sshPublisherDesc(
                    configName: 'SFTP_SERVER',
                    transfers: [
                        sshTransfer(
                            cleanRemote: false,
                            excludes: '',
                            execCommand: '',
                            execTimeout: 120000,
                            flatten: false,
                            makeEmptyDirs: false,
                            noDefaultExcludes: false,
                            patternSeparator: '[, ]+',
                            remoteDirectory: 'REPLACE_WITH_REMOTE_DIRECTORY',
                            remoteDirectorySDF: false,
                            removePrefix: '',
                            sourceFiles: 'target/*.jar'
                        )
                    ]
                )
            ]
        )
    }
}
