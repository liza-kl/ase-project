# Project for the Advanced Software Engineering Course
Software Project for the Advanced Software Engineering lecture at the Cooperative State University Karlsruhe. The aim is to get to know basic principles of Domain Driven Design, Programming Principle, Clean Architecture and so on.

# Useful Links

- [🧭 Project Proposal GER](https://github.com/liza-kl/ase-project/blob/main/doc/ASE_Themeneinreichung.pdf)
- [📝 Documentation GER](https://github.com/liza-kl/ase-project/blob/main/doc/ASE_Documentation.pdf)
- [✅ Entity Test Classes](https://github.com/liza-kl/ase-project/blob/main/domain/src/test/kotlin/EntitiesTestClass.kt)
- [✅ Value Objects Test Classes (3)](https://github.com/liza-kl/ase-project/blob/main/domain/src/test/kotlin/VOTestClass.kt)
- [✅ Controller Test Classes (4)](https://github.com/liza-kl/ase-project/blob/main/plugins/src/test/kotlin/de/dhbw/ka/controllers/ControllerTests.kt)
- [🎨 Design Pattern UML](https://github.com/liza-kl/ase-project/blob/main/doc/SpecificationPatternUML.png)

# Installation

1. Clone Repository and go to the project root directory
```
git clone git@github.com:liza-kl/ase-project.git 
```
or
```
git clone https://github.com/liza-kl/ase-project.git
```

2. Build the Docker Backend Image (the . at the end is important!)
```
docker build -t ase-project/backend:latest -t ase-project/backend:v.1.0 . 
```
3. Build the Docker Frontend Image (the . at the end is important!)
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

