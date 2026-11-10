def call(String credentialsId) {
    withCredentials([
        usernamePassword(
            credentialsId: credentialsId,
            usernameVariable: 'DOCKERHUB_USER_VAR',
            passwordVariable: 'DOCKERHUB_PASS'
        )
    ]) {
        sh '''
            set +x

            printf '%s' "$DOCKERHUB_PASS" |
            docker login \
                --username "$DOCKERHUB_USER_VAR" \
                --password-stdin
        '''
    }
}
