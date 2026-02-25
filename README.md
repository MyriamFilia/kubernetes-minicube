# Kubernetes-Minikube Project

Ce projet démontre le cycle complet de développement d'un micro-service Java : de la conteneurisation avec Docker jusqu'à l'orchestration avec Kubernetes (Minikube), incluant le routage avancé via Ingress et les mises à jour sans interruption (Rolling Updates).

## 🛠 Pré-requis

- **Docker Desktop** (Windows/Mac/Linux)
- **Minikube & Kubectl**
- **Java & Gradle** (pour la compilation du service)

---

## 1. Compilation et Dockerisation

### Compiler l'application Java

Si vous modifiez le code pour ajouter de nouvelles fonctionnalités :

```powershell
# Sous Windows
.\gradlew clean build

# Sous Linux/Mac
./gradlew clean build

```

### Dockerisation

```powershell
# Build de la version
docker build -t votreUserDocker/filia-service:numero version .

# Push vers Docker Hub
docker push votreUserDocker/filia-service:numero version

```

## 2. Déploiement Kubernetes

Avant d'appliquer nos configurations, il est important de comprendre les deux composants que nous allons créer sur notre cluster :

Le Deployment (myservice-deployment.yml) : C'est le chef d'orchestre de vos conteneurs. Il indique à Kubernetes comment créer et gérer les instances de votre application. Il définit l'image Docker à utiliser et le nombre de répliques (Pods) qui doivent tourner en simultané.

Le Service (myservice-service.yml) : Les Pods étant éphémères (leur adresse IP change s'ils redémarrent), le Service agit comme un point d'accès réseau fixe et stable. Il se charge de répartir le trafic entrant (Load Balancing) vers les Pods disponibles.

### Appliquer les fichiers YAML

Maintenant que le rôle de chaque composant est clair, lancez ces commandes pour demander à Kubernetes de les créer :

```powershell
# Appliquer le fichier de déploiement
kubectl apply -f myservice-deployment.yml

# Appliquer le fichier de service
kubectl apply -f myservice-service.yml

```

### Vérifier l'état

Vérifiez que vos Pods tournent et que votre Service a bien obtenu une adresse :

```powershell
# Vérifier l'état des Pods
kubectl get pods

# Vérifier l'état du Service
kubectl get svc

```

## 3. Configuration de l'Ingress

L'Ingress permet d'accéder au service via un nom de domaine personnalisé (myservice.info) au lieu d'une adresse IP technique.

### Configuration Windows (Hosts)

Ouvrez C:\windows\system32\drivers\etc\hosts en mode Administrateur.

Ajoutez la ligne suivante :
127.0.0.1 myservice.info

### Activez l'Ingress et le tunnel Minikube :

```powershell
# Activer l'Ingress
minikube addons enable ingress

# Activer le tunnel Minikube
minikube tunnel

```

### Appliquez avec :

```powershell
# Appliquer le fichier de service
kubectl apply -f ingress.yml

```

## 4. Rolling Updates & Rollback

C'est ici que l'on gère le cycle de vie de l'application sans coupure de service.

### Mettre à jour vers une nouvelle version

```powerShell
# Mettre à jour vers la version 2
kubectl set image deployment/myservice filia-service=votreUserDocker/filia-service:2

```

### Surveiller la mise à jour

```powerShell
kubectl rollout status deployment/myservice

```

### Gestion de l'historique et Rollback

Si la nouvelle version présente un bug, revenez en arrière instantanément :

```powerShell
# Voir l'historique
kubectl rollout history deployment/myservice

# Annuler la dernière mise à jour (Rollback) :

kubectl rollout undo deployment/myservice

# Revenir à une révision spécifique (ex: Révision 1) :

kubectl rollout undo deployment/myservice --to-revision=1

```

## 5. Nettoyage complet

Pour supprimer toutes les ressources créées et libérer la mémoire :

```powerShell
# Supprimer le ingress
kubectl delete ingress example-ingress

# Supprimer le service
kubectl delete service myservice

# Supprimer le deployment
kubectl delete deployment myservice

# Arrêter Minikube
minikube stop

```
