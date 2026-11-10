def call(
    String dockerHubUser,
    String backendPath,
    String frontendPath,
    String imageTag
) {
    env.BACKEND_TAG_DH =
        "${dockerHubUser}/three-tier-app-backend:${imageTag}"

    env.FRONTEND_TAG_DH =
        "${dockerHubUser}/three-tier-app-frontend:${imageTag}"

    sh """
        set -eu

        docker build \
            -t '${env.BACKEND_TAG_DH}' \
            '${backendPath}'

        docker build \
            -t '${env.FRONTEND_TAG_DH}' \
            '${frontendPath}'
    """
}
