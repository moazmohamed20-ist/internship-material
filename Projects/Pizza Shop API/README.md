# Project 0: Pizza Shop API

## 1. Shop Status - `GET /check-status`

An end point to check if the shop is **open** or **closed**. Use the `LocalDateTime` class to get the current time and determine if the shop is open. The shop is open from `08:00 AM` to `01:00 PM`.

**✅ Response Status:** `200 OK`  
**🔄 Response:**

```txt
open
```

or

```txt
closed
```

## 2. Make Order Pizza - `POST /order`

When ever this end point is called, a new order should be created and stored. Each order must have the following fields: `createdAt` and `number` (random generated 4-digit).

**✅ Response Status:** `201 Created`  
**🔄 Response:**

```json
{
  "orderNumber": "8451"
}
```

## 3. Track Order - `GET /order/{orderNumber}`

This end point searches for the order by its number, then calculates and returns the time left (in minutes) for the order to be ready. The order will be ready 20 minutes after the order's creation time.

**For example:**
If the order `createdAt` is **10:00:00**, then the order will be _ready at_ **10:20:00**. So, if the _current time_ is **10:08:00**, then the time left for the order to be ready is **12 minutes**.

**✅ Response Status:** `200 OK` (if order exists), `❌ 404 Not Found` (if not found)  
**🔄 Response:**

```json
{
  "timeLeft": 15
}
```
