# portfolio

# Sandeep Konda's Portfolio Website

A Spring Boot Java web application version of my personal portfolio website.

## How to Run

Visit: http://localhost:8080

## Features
- Responsive design
- Contact form with email notifications
- Modern Spring Boot architecture
- Professional portfolio layout

## Setup
1. Configure email settings in application.properties
2. Run the application
3. Access at localhost:8080


portfolio/                           ← Root of your repository
├── .gitignore
├── Dockerfile
├── Jenkinsfile
├── README.md
├── pom.xml
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── sandeepkonda/
│       │           └── portfolio/
│       │               ├── PortfolioApplication.java
│       │               ├── controller/
│       │               │   └── HomeController.java
│       │               ├── model/
│       │               │   └── Contact.java
│       │               └── service/
│       │                   └── EmailService.java
│       └── resources/
│           ├── application.properties
│           ├── templates/
│           │   └── index.html
│           └── static/
│               ├── css/
│               │   └── style.css
│               └── js/
│                   └── main.js
├── k8s/                             ← Kubernetes manifests
│   ├── namespace.yaml
│   ├── email-secret.yaml
│   ├── deployment.yaml
│   └── ingress.yaml
└── argocd/                          ← Argo CD Applicatio

    └── portfolio-app.yaml
