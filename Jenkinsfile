pipeline {
  agent any
  environment {
    AWS_REGION    = '<AWS_REGION>'
    AWS_ACCOUNT_ID= '<AWS_ACCOUNT_ID>'
    ECR_REPO      = 'portfolio'
    ECR_URI       = "${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com/${ECR_REPO}"
    IMAGE_TAG     = "${env.BUILD_NUMBER}"
  }
  stages {
    stage('Checkout') {
      steps { checkout scm }
    }
    stage('Build') {
      steps { sh 'mvn clean package -DskipTests' }
    }
    stage('Docker Build & Push') {
      steps {
        sh """
          aws ecr get-login-password --region $AWS_REGION \
            | docker login --username AWS --password-stdin $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com
          docker build -t $ECR_REPO:$IMAGE_TAG .
          docker tag $ECR_REPO:$IMAGE_TAG $ECR_URI:$IMAGE_TAG
          docker tag $ECR_REPO:$IMAGE_TAG $ECR_URI:latest
          docker push $ECR_URI:$IMAGE_TAG
          docker push $ECR_URI:latest
        """
      }
    }
    stage('Deploy with ArgoCD') {
      steps {
        withCredentials([string(credentialsId: 'ARGOCD_TOKEN', variable: 'ARGOCD_TOKEN')]) {
          sh "argocd app sync portfolio --grpc-web --server argocd.example.com --auth-token $ARGOCD_TOKEN"
        }
      }
    }
  }
  post {
    success { echo 'Deployment succeeded!' }
    failure { echo 'Build or deployment failed.' }
  }
}
