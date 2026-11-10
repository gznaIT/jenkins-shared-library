def call() {
    sh '''
        set -eu

        echo "Pushing backend image..."
        docker push "$BACKEND_TAG_DH"

        echo "Pushing frontend image..."
        docker push "$FRONTEND_TAG_DH"

        echo "Both images pushed successfully!"
    '''
}
