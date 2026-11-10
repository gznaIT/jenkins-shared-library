def call(String branch) {

    def ports = [
        dev: [
            frontend: '3001',
            backend: '5001'
        ],
        stg: [
            frontend: '3002',
            backend: '5002'
        ],
        prod: [
            frontend: '3003',
            backend: '5003'
        ]
    ]

    if (!ports.containsKey(branch)) {
        error("Unsupported deployment branch: ${branch}")
    }

    def selected = ports[branch]

    writeFile(
        file: '.env',
        text: """BACKEND_IMAGE=${env.BACKEND_TAG_DH}
FRONTEND_IMAGE=${env.FRONTEND_TAG_DH}
BACKEND_HOST_PORT=${selected.backend}
FRONTEND_HOST_PORT=${selected.frontend}
"""
    )

    env.COMPOSE_PROJECT = "three-tier-${branch}"

    echo "Environment prepared for ${branch}"
    echo "Backend image: ${env.BACKEND_TAG_DH}"
    echo "Frontend image: ${env.FRONTEND_TAG_DH}"
    echo "Frontend port: ${selected.frontend}"
    echo "Backend port: ${selected.backend}"
}
