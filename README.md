<h1 align="center">Paint</h1>

<p align="center"> Paint app built with Angular and Spring Boot </p>

<div align="center">

<a href="https://github.com/TarekSaeed0/Paint/graphs/contributors">
  <picture>
    <source media="(prefers-color-scheme: dark)"  srcset="https://img.shields.io/github/contributors/TarekSaeed0/Paint?style=for-the-badge&labelColor=%23151b23&color=%234493f8">
    <source media="(prefers-color-scheme: light)" srcset="https://img.shields.io/github/contributors/TarekSaeed0/Paint?style=for-the-badge&labelColor=%23f6f8fa&color=%230969da">
    <img alt="GitHub Contributors" src="https://img.shields.io/github/contributors/TarekSaeed0/Paint?style=for-the-badge&labelColor=%23151b23&color=%234493f8">
  </picture>
</a>
<a href="https://github.com/TarekSaeed0/Paint/pulse">
  <picture>
    <source media="(prefers-color-scheme: dark)"  srcset="https://img.shields.io/github/last-commit/TarekSaeed0/Paint?style=for-the-badge&labelColor=%23151b23&color=%234493f8">
    <source media="(prefers-color-scheme: light)" srcset="https://img.shields.io/github/last-commit/TarekSaeed0/Paint?style=for-the-badge&labelColor=%23f6f8fa&color=%230969da">
    <img alt="GitHub Last Commit" src="https://img.shields.io/github/last-commit/TarekSaeed0/Paint?style=for-the-badge&labelColor=%23151b23&color=%234493f8">
  </picture>
</a>
<a href="https://github.com/TarekSaeed0/Paint/stargazers">
  <picture>
    <source media="(prefers-color-scheme: dark)"  srcset="https://img.shields.io/github/stars/TarekSaeed0/Paint?style=for-the-badge&labelColor=%23151b23&color=%234493f8">
    <source media="(prefers-color-scheme: light)" srcset="https://img.shields.io/github/stars/TarekSaeed0/Paint?style=for-the-badge&labelColor=%23f6f8fa&color=%230969da">
    <img alt="GitHub Repo stars" src="https://img.shields.io/github/stars/TarekSaeed0/Paint?style=for-the-badge&labelColor=%23151b23&color=%234493f8">
  </picture>
    </a>
</div>

## Features

- Save and load drawings as JSON or XML.
- Many available shapes: Line, Circle, Ellipse, Triangle, etc.
- Add, remove, move, scale, rotate and duplicate shapes.

## Building

### Prerequisites

- [Java Development Kit (JDK) 21+](https://www.oracle.com/middleeast/java/technologies/downloads/)
- [Apache Maven](https://maven.apache.org/install.html)
- [Node.js](https://nodejs.org/en/download)
- [Angular CLI](https://angular.dev/tools/cli/setup-local)

### Backend Setup

- Navigate to the backend directory:

```sh
cd backend
```

- Build the backend using Maven:

```sh
mvn clean install
```

- Run the Spring Boot application:

```sh
mvn spring-boot:run
```

### Frontend Setup

- Navigate to the frontend directory:

```sh
cd frontend
```

- Install the required dependencies:

```sh
npm install
```

- Run the Angular development server:

```sh
ng serve
```

- Open your web browser and navigate to `http://localhost:4200`
