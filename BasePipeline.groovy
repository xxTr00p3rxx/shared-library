class BasePipeline {
    def build() {
        throw new MissingMethodException('build', this.class, [])
    }

    def test() {
        throw new MissingMethodException('test', this.class, [])
    }

    def publishResult() {
        throw new MissingMethodException('publishResult', this.class, [])
    }

    def publishArtifacts() {
        throw new MissingMethodException('publishArtifacts', this.class, [])
    }

    def run() {
        build()
        test()
        publishResult()
        publishArtifacts()
    }
}
