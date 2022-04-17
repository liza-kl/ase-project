# Project for the Advanced Software Engineering Course
Software Project for the Advanced Software Engineering lecture at the Cooperative State University Karlsruhe. The aim is to get to know basic principles of Domain Driven Design, Programming Principle, Clean Architecture and so on.

# Documentation

[Link to the pdf](https://github.com/liza-kl/ase-project/blob/main/doc/ASE_Documentation.pdf)
# Installation

1. Clone Repository and go to the project root directory
```
git clone git@github.com:liza-kl/ase-project.git 
```
or
```
git clone https://github.com/liza-kl/ase-project.git
```

2. Build the Docker Backend Image 
```
docker build -t ase-project/backend:latest -t ase-project/backend:v.1.0 . 
```
3.Build the Docker Frontend Image 
```
cd ase-project-fe 
docker build -t ase-project/frontend:latest -t ase-project/frontend:v.1.0 .
```
4. Spin up the Back- and Frontend
```
docker-compose up -d 
```
5. URLs

```
Frontend URL: http://localhost:3000
Backend URL: http://localhost:9000
```




