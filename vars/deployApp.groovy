def call(String composeFile, String branch) {

    if (!(branch in ['dev', 'stg', 'prod'])) {
        error("Deployment not allowed for branch: ${branch}")
    }

    env.COMPOSE_FILE = composeFile

    sh '''
        set -eu

        echo "Validating Docker Compose..."

        docker compose \
            --project-name "$COMPOSE_PROJECT" \
            -f "$COMPOSE_FILE" \
            --env-file .env \
            config -q

        echo "Pulling Docker Hub images..."

        docker compose \
            --project-name "$COMPOSE_PROJECT" \
            -f "$COMPOSE_FILE" \
            --env-file .env \
            pull

        echo "Deploying application..."

        docker compose \
            --project-name "$COMPOSE_PROJECT" \
            -f "$COMPOSE_FILE" \
            --env-file .env \
            up -d --remove-orphans

        echo "Verifying deployment..."

        docker compose \
            --project-name "$COMPOSE_PROJECT" \
            -f "$COMPOSE_FILE" \
            --env-file .env \
            ps
    '''

    echo "Deployment completed for ${branch}"
}
