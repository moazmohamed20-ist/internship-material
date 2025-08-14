# Telecom API

## 1. VIP Customer Check - `GET /customer/{phoneNumber}/vip-status`

An endpoint to check if a customer is VIP based on their phone number. The customer is VIP when the sum of his phone number digits is `prime` 😎.

**✅ Response Status:** `200 OK`  
**🔄 Response:**

```json
{
  "isVip": true
}
```

or

```json
{
  "isVip": false
}
```

## 2. Balance Recharge - `POST /balance/recharge`

An endpoint to process balance recharge using credit card information.

**Validations:**

- `expiryDate` must be in the future.
- `amount` must not exceed **1000**

**📝 Request Body:**

```json
{
  "phoneNumber": "01234567890",
  "cardNumber": "4242424242424242",
  "expiryDate": "12/34",
  "securityCode": "567",
  "amount": 50.0
}
```

**✅ Response Status:** `200 OK` (if successful), `❌ 400 Bad Request` (if validation fails)  
**🔄 Response:**

```json
{
  "success": true,
  "message": "Balance has been recharged successfully"
}
```

or

```json
{
  "success": false,
  "message": "Invalid data received"
}
```

## 3. Balance Transfer - `POST /balance/transfer`

An endpoint to transfer balance from one phone number to another.

**Validations:**

- `toNumber` must start with the same 3 digits as `fromNumber`
- `toNumber` must be in the same length as `fromNumber`
- `amount` must not exceed **1000**

**📝 Request Body:**

```json
{
  "fromNumber": "01234567890",
  "toNumber": "01234567899",
  "amount": 25.0
}
```

**✅ Response Status:** `200 OK` (if successful), `❌ 400 Bad Request` (if validation fails)  
**🔄 Response:**

```json
{
  "success": true,
  "message": "Balance has been transferred successfully"
}
```

or

```json
{
  "success": false,
  "message": "Invalid data received"
}
```

## 4. Send SMS - `POST /sms`

An endpoint to send SMS messages based on predefined templates.

**SMS Template Codes:**

| Code              |
| ----------------- |
| INTERNET_PACKAGES |
| CALL_TONES        |
| PROMOTIONS        |

**📝 Request Body:**

```json
{
  "phoneNumber": "01234567890",
  "tempelateCode": "INTERNET_PACKAGES"
}
```

**✅ Response Status:** `200 OK`  
**🔄 Response:**

```json
{
  "success": true,
  "message": "The SMS has been sent successfully."
}
```
