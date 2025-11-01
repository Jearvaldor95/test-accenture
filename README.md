# test-accenture
Technical Test - Backend Developer


## 📦 Estructura de Servicios

### 🧍 Test accenture
- Franquisias: Crear, listar, actualizar y obtener producto con maximo stock de sucursal por franquicia
- Crear una franquicia

POST /api/franchises
Cuerpo de la solicitud:
```bash
{
  "name": "Franchise Nueva"
}
```
Respuesta
```bash
{
  "id": 1,
  "name": "Franchise Nueva",
}
```
Obtener todas las franquicias
GET /api/franchises
Respuesta
```bash
Respuesta
{
  "id": 1,
  "name": "Franchise Nueva",
  "branches": []
}

```
Actualizar nombre de franquicia
PUT /api/franchises/1?name=Franchise Actualizada
Respuesta
```bash
Respuesta
{
  "id": 1,
  "name": "Franchise Actualizada"
}
```

- Sucursales: Crear, listar y actualizar en nombre de la sucursal
  Branches

Crear sucursal 
POST /api/branches
Respuesta:
```bash
{
  "name": "Sucursal 1",
  "franchiseId": 1
}
```
Actualizar nombre de sucursal

PUT /branches/{id}?name=NuevoNombre
Response Example:
```bash
{
  "id": 1,
  "name": "NuevoNombre"
}
```

Obtener todas las sucursales con sus productos

GET /api/branches
Respuesta
```bash
[
  {
    "id": 1,
    "name": "Sucursal 1",
    "products": [
      {
        "id": 1,
        "name": "Producto A",
        "stock": 50
      },
      {
        "id": 2,
        "name": "Producto B",
        "stock": 30
      }
    ]
  },
  {
    "id": 2,
    "name": "Sucursal 2",
    "products": [
      {
        "id": 3,
        "name": "Producto C",
        "stock": 20
      }
    ]
  }
]
```

- productos: Crear, listar, actualizar en nombre del producto, actualizar stock y eliminar producto

Crear producto
POST /api/products
Respuesta:
```bash
{
  "name": "Producto A",
  "stock": 50,
  "branchId": 1
}
```
Actualizar nombre del producto
PUT /products/{id}?name=NuevoNombre
Respuesta:
```bash
{
  "id": 1,
  "name": "NuevoNombre",
  "stock": 50
}
```
Actualizar stock del producto
PATCH /products/{id}/stock?stock=75
Respuesta:
```bash
{
  "id": 1,
  "name": "Producto A",
  "stock": 75
}
```
Eliminar producto
DELETE /products/{id}
---

## 🛠️ Tecnologías usadas

- Java 17
- Spring Boot
- WebFlux
- R2DBC
- Mysql
- Docker / Docker Compose
- CI / CD con GitHub Actions
- MapStruct
- Lombok

---

## 🚀 Cómo ejecutar con Docker

### 1. Requisitos

- Docker
- Docker Compose

### 2. Construcción de los JARs

Primero, desde la raíz de cada servicio ejecutá:

```bash
mvn clean package -DskipTests
```
Esto genera los .jar necesarios en la carpeta target.

### 3. Crear un archivo .env en la raiz del proyecto

``` bash
R2DBC_URL=r2dbc:mysql://mysqldb:3306/tu-nombre-base-datos
USERNAME_DEV=tu-usuario
PASSWORD_DEV=tu-password

MYSQL_DATABASE=tu-nombre-base-datos
MYSQL_ROOT_PASSWORD=tu-password

PROFILE_ACTIVE=dev
```
Asegurate de primero crear tu base de datos

### 4. Levantar los servicios

```bash
docker-compose up --build
```

-- Puedes probar tambien esta levantada en Azure
URL: https://test-accenture-app.azurewebsites.net/api/franchises
