Structure and Explanation of deployment.yml
apiVersion: apps/v1

Specifies the API version for the Deployment resource. apps/v1 is the stable version for deployments.
kind: Deployment

Indicates that the resource being defined is a Deployment, which manages the desired state of application Pods.
metadata

name: job
The name of the Deployment is job.
labels: { app: job }
Adds a label to the Deployment for identification or grouping purposes.
spec

Describes the desired state of the Deployment.

replicas: 1

Specifies that the Deployment should maintain one replica of the Pod. If the Pod fails, Kubernetes will automatically recreate it to ensure one instance is always running.
selector

matchLabels: { app: job }
Specifies the label selector for identifying the Pods managed by this Deployment. It matches Pods with the label app: job.
template

Defines the blueprint for the Pods created by the Deployment.

metadata

name: job
The name of the Pods created from this template is job.
labels: { app: job }
Assigns the label app: job to these Pods, enabling the Service to route traffic to them.
spec

Specifies the configuration for the Pod.

containers

A list of container definitions for the Pod.

name: job

Names the container job.
image: thangtran3112/jobms:latest

Specifies the Docker image for the container. The image thangtran3112/jobms:latest will be pulled from the registry.
imagePullPolicy: Always

Ensures the latest version of the image is pulled every time the Pod is created or restarted.
ports

Describes the ports exposed by the container.
containerPort: 8082
Specifies that the container listens on port 8082.
env

Specifies environment variables for the container.
name: SPRING_PROFILES_ACTIVE
Sets an environment variable SPRING_PROFILES_ACTIVE in the container.
value: k8s
The environment variable's value is k8s, indicating the application is running in a Kubernetes profile.
restartPolicy: Always

Ensures the Pods are always restarted if they fail.
