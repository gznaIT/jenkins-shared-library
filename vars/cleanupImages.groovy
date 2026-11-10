def call() {
    sh '''
        set -eu

        echo "Cleaning unused dangling Docker images..."

        docker image prune -f

        echo "Docker cleanup completed."
    '''
}
