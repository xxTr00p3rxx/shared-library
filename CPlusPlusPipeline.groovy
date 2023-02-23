class CPlusPlusPipeline extends BasePipeline {
    @Override
    def build() {
        sh "cmake --build ."
    }

    @Override
    def test() {
        sh "ctest"
    }

    @Override
    def publishResult() {
        junit 'test_results/*.xml'
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
                            sourceFiles: 'target/*'
                        )
                    ]
                )
            ]
        )
    }
}
