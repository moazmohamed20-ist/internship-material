# Project 0: Pizza Shop IVR

## Diagram

```mermaid
---
config:
  layout: dagre
  look: handDrawn
---
flowchart TD
    A(["Welcome to Tony's Pizza"]) --> A1["Languages Menu"]
    A1 --> L1[/"1 - Arabic"/] & L2[/"2 - English"/]

    L1 --> M1["Main Menu"]
    L2 --> M1
    M1 --> O1[/"1 - Speak to Agent"/] & O2[/"2 - Order Pizza"/] & O3[/"3 - Track Order"/]

    %% Speak to Staff Branch
    O1 --> P4(["Transferring you to an agent"])

    %% Order Pizza Branch
    O2 --> D{"Is shop open?"}
    D -- Yes --> P1(["Your order will be ready in 20 minutes"])
    D -- No --> P2(["Sorry, we are closed now"])

    %% Track Order Branch with Input
    O3 --> I1[["Please enter your 4-digit order number"]]
    I1 --> P3(["Your order is on the way"])

    %% End
    P1 --> Z(["Thank you for calling Tony's Pizza!"])
    P2 --> Z
    P3 --> Z
```

## Prompt Matrix

| Filename                | English Prompt                                                                        | Arabic Prompt                                                                     |
| ----------------------- | ------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------- |
| Welcome.wav             | Welcome to Tony’s Pizza.                                                              | أهلاً بك في بيتزا توني.                                                           |
| LangMenu.wav            | For English press 2                                                                   | للغة العربية اضغط 1                                                               |
| MainMenu.wav            | To Speak to an agent, press 1. To Order pizza, press 2. To Track your order, press 3. | للتحدث مع أحد موظفي خدمة العملاء، اضغط 1. لطلب بيتزا، اضغط 2. لتتبع طلبك، اضغط 3. |
| NoInput.wav             | Sorry, we did not receive any input.                                                  | عذراً، لم نستلم أي إدخال.                                                         |
| NoMatch.wav             | Sorry, that is not a valid option.                                                    | عذراً، هذا خيار غير صحيح.                                                         |
| MaxTries.wav            | Sorry, you have exceeded the maximum number of attempts.                              | عذراً، لقد تجاوزت الحد الأقصى لعدد المحاولات.                                     |
| TransferringToAgent.wav | Transferring you to an agent, please hold.                                            | جارٍ تحويلك إلى أحد موظفين خدمة العملاء، يرجى الانتظار.                           |
| OrderOnTheWay.wav       | Your order is on the way.                                                             | طلبك في الطريق.                                                                   |
| ShopClosed.wav          | Sorry, we are closed now.                                                             | نأسف، نحن مغلقون حالياً.                                                          |
| EnterOrderNumber.wav    | Please enter your 4-digit order number.                                               | يرجى إدخال رقم طلبك المكون من 4 أرقام.                                            |
| OrderConfirmed.wav      | Your order will be ready in 20 minutes.                                               | طلبك سيكون جاهزاً خلال 20 دقيقة.                                                  |
| ThankYou.wav            | Thank you for calling Tony’s Pizza!                                                   | شكراً لاتصالك ببيتزا توني!                                                        |
