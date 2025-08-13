# Products API

## 1. Get All Products - `GET /products`

**✅ Response Status:** `200 OK`  
**🔄 Response:**

```json
[
  {
    "id": 1,
    "name": "Product A",
    "price": 10.5
  },
  {
    "id": 2,
    "name": "Product B",
    "price": 20.0
  }
]
```

## 2. Create Product - `POST /products`

**📝 Request Body:**

```json
{
  "name": "Product C",
  "price": 15.0
}
```

**✅ Response Status:** `201 Created`  
**🔄 Response:**

```json
{
  "id": 3,
  "name": "Product C",
  "price": 15.0
}
```

## 3. Get Product by ID - `GET /products/{id}`

**✅ Response Status:** `200 OK` (if found), `❌ 404 Not Found` (if not found)  
**🔄 Response:**

```json
{
  "id": 1,
  "name": "Product A",
  "price": 10.5
}
```

## 4. Update Product - `PUT /products/{id}`

**📝 Request Body:**

```json
{
  "name": "Product C Updated",
  "price": 17.0
}
```

**✅ Response Status:** `200 OK` (if updated), `❌ 404 Not Found` (if not found)  
**🔄 Response:**

## 5. Delete Product - `DELETE /products/{id}`

**✅ Response Status:** `204 No Content` (if deleted), `❌ 404 Not Found` (if not found)
